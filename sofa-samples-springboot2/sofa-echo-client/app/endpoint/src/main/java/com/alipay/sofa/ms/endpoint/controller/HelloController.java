package com.alipay.sofa.ms.endpoint.controller;

import com.alipay.sofa.ms.service.HelloService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    Random random = new Random();

    private List<String> times = Lists.newArrayList();
    private ExecutorService metricExecutor;


    @Resource
    private HelloService helloService;


    @PostConstruct
    public void init() {
        metricExecutor = Executors.newSingleThreadExecutor();
    }

    /**
     *
     * @param name 请求里的参数
     * @param count 每个线程调用次数
     * @param sleep 每个线程发起调用请求的间隔时间
     * @param concurrent 并发数
     * @param metricSplit 统计每秒的请求数，每一行显示几个数值
     * @param metricHintRate 每秒请求数低于平均 qps 百分之多少的时候，高亮显示
     * @param sampleRate 接口采样比例，万分制。为了减少页面输出内容
     */
    @GetMapping("/hi")
    public String sayHi(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "count", required = false, defaultValue = "1") int count,
                        @RequestParam(value = "sleep", required = false, defaultValue = "0") int sleep,
                        @RequestParam(value = "concurrent", required = false, defaultValue = "1") int concurrent,
                        @RequestParam(value = "metricSplit", required = false, defaultValue = "-1") int metricSplit,
                        @RequestParam(value = "metricHintRate", required = false, defaultValue = "1") double metricHintRate,
                        @RequestParam(value = "sampleRate", required = false, defaultValue = "10000") int sampleRate)
            throws InterruptedException {

        if (StringUtils.isEmpty(name)) {
            name = " sofa";
        }

        long startTime = System.currentTimeMillis();
        AtomicLong totalCallTimes = new AtomicLong();

        boolean countMetric = metricSplit > 0;
        CountDownLatch latch = new CountDownLatch(concurrent);
        Map<String, String> results = Maps.newConcurrentMap();
        Map<String, Integer> metrics = Maps.newConcurrentMap();

        for (int i = 0; i < concurrent; i++) {
            runOneThread(name, count, sleep, results, metrics, countMetric, totalCallTimes, sampleRate, latch);
        }

        latch.await();

        long totalDuration = System.currentTimeMillis() - startTime;

        StringBuilder renderResult = new StringBuilder();

        renderResult.append("总请求数 : ").append(concurrent * count).append("</br>");
        renderResult.append("总响应耗时 : ").append(totalDuration).append(" 毫秒</br>");
        double avgDuration = totalDuration * 1.0 / (concurrent * count);
        double singleCallAvgDuration = totalCallTimes.get() * 1.0 / (concurrent * count);
        renderResult.append("总平均耗时 : ").append(avgDuration).append(" 毫秒</br>");
        renderResult.append("单个请求平均耗时 : ").append(singleCallAvgDuration).append(" 毫秒</br>");
        int qps = new Double(1000 / avgDuration).intValue();
        renderResult.append("QPS : ").append(qps).append(" </br>");

        if (countMetric) {
            renderMetric(renderResult, metrics, qps, metricSplit, metricHintRate);
        }

        return renderResult(renderResult, results);
    }



    private void runOneThread(String name, int count, int sleep, Map<String, String> results, Map<String, Integer> metrics,
                              boolean countMetric,
                              AtomicLong totalCallTimes,
                              int sampleRate,
                              CountDownLatch latch) {
        new Thread(() -> {
            StringBuilder result = new StringBuilder();
            for (int i = 1; i <= count; i++) {
                long startTime = System.currentTimeMillis();
                try {
                    if (sleep > 0) {
                        Thread.sleep(sleep);
                    }
                    //3. 服务调用
                    String response = helloService.sayHi(name);

                    //4. 统计结果
                    long duration = System.currentTimeMillis() - startTime;
                    totalCallTimes.addAndGet(duration);

                    //在数据量大的情况下，采集部分请求结果展示到页面上
                    if (shouldSample(sampleRate)) {
                        result.append("第 ").append(i).append("次调用，sleep ").append(sleep).append(" 毫秒，耗时 ").append(duration).append(
                                " 毫秒, 结果：")
                                .append(response).append("<br>");
                    }

                    logger.info(response);

                    if (countMetric) {
                        doMetric(metrics);
                    }
                } catch (Exception e) {
                    logger.error("call failed.", e);
                    long duration = System.currentTimeMillis() - startTime;
                    result.append("第 ").append(i).append("次调用，sleep ").append(sleep).append(" 毫秒，耗时 ").append(duration).append(" 毫秒, 结果：")
                            .append(e.getMessage()).append(
                            "<br>");
                }
            }

            results.put(Thread.currentThread().getName(), result.toString());
            latch.countDown();
        }).start();

    }

    private boolean shouldSample(int sampleRate) {
        return random.nextInt(10000) <= sampleRate;
    }

    private void doMetric(Map<String, Integer> metrics) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String second = format.format(new Date());

        metricExecutor.submit(() -> {
            Integer count = metrics.get(second);
            if (count == null) {
                synchronized (metrics) {
                    count = metrics.get(second);
                    if (count == null) {
                        times.add(second);
                        metrics.put(second, 1);
                    } else {
                        metrics.put(second, count + 1);
                    }
                }
            } else {
                metrics.put(second, count + 1);
            }
        });
    }

    private void renderMetric(StringBuilder sb, Map<String, Integer> metric, int qps, int metricSplit, double metricHintRate) {
        int count = 1;
        for (String time : times) {
            int secondCount = metric.get(time);

            if (secondCount >= qps * metricHintRate) {
                sb.append(time).append(" = ").append(secondCount).append("&nbsp;&nbsp;&nbsp;");
            } else {
                sb.append("<font color='red'>").append(time).append(" = ").append(secondCount).append("&nbsp;&nbsp;&nbsp;").append(
                        "</font>");
            }

            if (count % metricSplit == 0) {
                sb.append("<br>");
            }
            count++;
        }

        times = Lists.newArrayList();
    }

    private String renderResult(StringBuilder sb, Map<String, String> results) {
        for (Map.Entry<String, String> entry : results.entrySet()) {
            sb.append("<h5>").append(entry.getKey()).append("</h5>");
            sb.append("<div> ").append(entry.getValue()).append("</div>");
        }
        return sb.toString();
    }

}
