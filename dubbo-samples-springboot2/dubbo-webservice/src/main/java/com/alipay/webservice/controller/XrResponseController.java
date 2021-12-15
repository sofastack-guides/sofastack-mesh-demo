package com.alipay.webservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class XrResponseController {

    @RequestMapping("")
    public String getRespMsg() {
        return "00000706<Service><Header><ServiceCode>CIMT000070</ServiceCode><Message>from spring "
                + "cloud</Message><ChannelId>C48</ChannelId><ExternalReference>2021121416133613538</ExternalReference"
                + "><OriginalChannelId>C49</OriginalChannelId><OriginalReference>06221113270051159201000092010000"
                + "</OriginalReference><RequestTime>20210622111327543</RequestTime><Version>1"
                + ".0</Version><RequestType>1</RequestType><Encrypt>0</Encrypt><TradeDate>20210617</TradeDate"
                + "><RequestBranchCode>CN0010001</RequestBranchCode><RequestOperatorId>FB.ICP"
                + ".X01</RequestOperatorId><RequestOperatorType>1</RequestOperatorType><TermType>00000</TermType"
                + "><TermNo>0000000000</TermNo></Header><Body><Response><CustNo>3001504094</CustNo></Response></Body"
                + "></Service>";
    }

}