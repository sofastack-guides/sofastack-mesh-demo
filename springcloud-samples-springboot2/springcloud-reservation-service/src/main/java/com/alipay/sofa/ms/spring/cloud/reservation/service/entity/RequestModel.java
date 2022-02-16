package com.alipay.sofa.ms.spring.cloud.reservation.service.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RequestModel implements Serializable {
    public Map<String, String> attachments;
    public List<Parameter> parameters;

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public RequestModel setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
        return this;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public RequestModel setParameters(
            List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }
}