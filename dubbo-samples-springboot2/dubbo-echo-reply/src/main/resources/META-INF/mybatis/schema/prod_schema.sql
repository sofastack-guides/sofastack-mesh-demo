CREATE SCHEMA `pro_sys_info` ;

CREATE TABLE `jdp_tb_trade` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(64) NOT NULL,
  `type` varchar(64) DEFAULT NULL,
  `seller_nick` varchar(32) DEFAULT NULL,
  `buyer_nick` varchar(256) DEFAULT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL,
  `jdp_hashcode` varchar(128) NOT NULL,
  `jdp_response` mediumtext,
  `jdp_created` datetime NOT NULL,
  `jdp_modified` datetime NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `seller_jdpmodified` (`seller_nick`,`jdp_modified`),
  KEY `jdpmodified` (`jdp_modified`),
  KEY `seller_modified` (`seller_nick`,`modified`),
  KEY `modified` (`modified`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单推送库'