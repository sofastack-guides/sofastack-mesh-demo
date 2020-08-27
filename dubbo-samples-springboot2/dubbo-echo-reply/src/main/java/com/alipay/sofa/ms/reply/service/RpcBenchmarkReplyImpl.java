package com.alipay.sofa.ms.reply.service;

import com.alipay.sofa.ms.reply.context.DynamicDataSourceContext;
import com.alipay.sofa.ms.reply.datasource.DataSourceKey;
import com.alipay.sofa.ms.reply.entity.JdpTbTradeDO;
import com.alipay.sofa.ms.reply.mapper.JdpTbTradeDOMapperExt;
import com.alipay.sofa.ms.service.Request;
import com.alipay.sofa.ms.service.Response;
import com.alipay.sofa.ms.service.RpcBenchmarkReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class RpcBenchmarkReplyImpl implements RpcBenchmarkReply {

    private Logger logger = LoggerFactory.getLogger(RpcBenchmarkReplyImpl.class);

    @Resource
    private JdpTbTradeDOMapperExt jdpTbTradeDOMapperExt;

    @Override
    public Response request(Request request) {

        String message = request.toString();

        JdpTbTradeDO tradeDO = new JdpTbTradeDO();
        tradeDO.setJdpResponse(message);
        tradeDO.setSellerNick(request.getSellerNick());

        Response response = new Response();

        try {
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
            DynamicDataSourceContext.setDataSourceKey(DataSourceKey.PROD_DATABASE);
            jdpTbTradeDOMapperExt.removeAll();
        } catch (Exception e) {
            response.setSuccess(false);
            response.setThrowable(e);
            logger.error("failed to delete prod trade.", e);
            return response;
        }

        try {
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