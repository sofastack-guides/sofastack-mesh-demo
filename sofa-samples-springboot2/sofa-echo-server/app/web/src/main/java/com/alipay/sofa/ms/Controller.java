package com.alipay.sofa.ms;


import com.alipay.sofa.ms.endpoint.filter.RpcExceptionFilter;
import com.alipay.sofa.ms.endpoint.impl.HelloServiceImpl;
import com.alipay.sofa.ms.service.BenchmarkService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private HelloServiceImpl helloService;

    @Autowired
    private BenchmarkService benchmarkService;

    @GetMapping("/status")
    public HelloServiceImpl callCnt() {
        return helloService;
    }

    @GetMapping("/status/set")
    public String setStatus(@RequestParam(required = false, defaultValue = "0") int sleep,
                            @RequestParam(required = false, defaultValue = "false")boolean throwException) {
        helloService.sleep = sleep;
        helloService.throwException = throwException;
        RpcExceptionFilter.exception = throwException;
        return "success";
    }

    // ======================================================

    @GetMapping("/send")
    public String send() {
        try {
            return benchmarkService.send();
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_512_byte")
    public String send_512_byte() {
        try {
            return benchmarkService.send_512_byte(randomString(512));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_512_byte, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_1k")
    public String send_1k() {
        try {
            return benchmarkService.send_1k(randomString(1024));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_1k, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_2k")
    public String send_2k() {
        try {
            return benchmarkService.send_2k(randomString(1024 * 2));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_2k, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_4k")
    public String send_4k() {
        try {
            return benchmarkService.send_4k(randomString(1024 * 4));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_4k, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_8k")
    public String send_8k() {
        try {
            return benchmarkService.send_8k(randomString(1024 * 8));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_8k, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    static char[] chars = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z',
    };

    static String randomString(int len) {
        return RandomStringUtils.random(len, 0, chars.length, true, true, chars);
    }





}
