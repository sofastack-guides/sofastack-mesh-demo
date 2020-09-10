package com.alipay.sofa.ms.reply.mapper;

import org.springframework.stereotype.Repository;


@Repository
public interface JdpTbTradeDOMapperExt extends JdpTbTradeDOMapper {

    void removeAll();

}