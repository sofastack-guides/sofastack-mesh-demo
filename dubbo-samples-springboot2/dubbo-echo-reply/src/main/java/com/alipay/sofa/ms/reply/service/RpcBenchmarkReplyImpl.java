package com.alipay.sofa.ms.reply.service;

import com.alipay.sofa.ms.reply.context.DynamicDataSourceContext;
import com.alipay.sofa.ms.reply.datasource.DataSourceKey;
import com.alipay.sofa.ms.reply.entity.JdpTbTradeDO;
import com.alipay.sofa.ms.reply.mapper.JdpTbTradeDOMapperExt;
import com.alipay.sofa.ms.service.Request;
import com.alipay.sofa.ms.service.Response;
import com.alipay.sofa.ms.service.RpcBenchmarkReply;
import com.alipay.sofa.pressure.context.PressureContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

public class RpcBenchmarkReplyImpl implements RpcBenchmarkReply {

    private Logger logger = LoggerFactory.getLogger(RpcBenchmarkReplyImpl.class);

    @Resource
    private JdpTbTradeDOMapperExt jdpTbTradeDOMapperExt;

    @Override
    public Response request(Request request) {

        request.updateRouteRecord();

        String message = request.toString();
        JdpTbTradeDO tradeDO = new JdpTbTradeDO();
        tradeDO.setJdpResponse(message);
        tradeDO.setSellerNick(request.getSellerNick());
        tradeDO.setStatus(request.getPressureId());
        // tradeDO.setTid(request.getTid());

        Date now = new Date();
        tradeDO.setCreated(now);
        tradeDO.setModified(now);
        tradeDO.setJdpCreated(now);
        tradeDO.setJdpModified(now);
        tradeDO.setJdpHashcode(String.valueOf(message.hashCode()));
        tradeDO.setBuyerNick("yiji");

        tradeDO.setType(request.getType());

        Response response = new Response();

        try {

            boolean isTest = PressureContext.isPressureTraffic();
            if (isTest) {
                //使用影子库
                DynamicDataSourceContext.setDataSourceKey(DataSourceKey.TEST_DATABASE);
            } else {
                //使用生产库
                DynamicDataSourceContext.setDataSourceKey(DataSourceKey.PROD_DATABASE);
            }

            logger.info("received request " + request + ", isTest: " + isTest);

            jdpTbTradeDOMapperExt.insertSelective(tradeDO);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setThrowable(e);
            logger.error("failed to insert trade.", e);
        }
        return response;
    }

    @Override
    public Response clearAndReset() {

        Response response = new Response();
        response.setSuccess(true);

        try {
            // 清除生产库
            DynamicDataSourceContext.setDataSourceKey(DataSourceKey.PROD_DATABASE);
            jdpTbTradeDOMapperExt.removeAll();
        } catch (Exception e) {
            response.setSuccess(false);
            response.setThrowable(e);
            logger.error("failed to delete prod trade.", e);
            return response;
        }

        try {
            // 清除影子库
            DynamicDataSourceContext.setDataSourceKey(DataSourceKey.TEST_DATABASE);
            jdpTbTradeDOMapperExt.removeAll();
        } catch (Exception e) {
            response.setSuccess(false);
            response.setThrowable(e);
            logger.error("failed to delete test trade.", e);
        } finally {
            DynamicDataSourceContext.removeDataSourceKey();
        }

        return response;
    }

}