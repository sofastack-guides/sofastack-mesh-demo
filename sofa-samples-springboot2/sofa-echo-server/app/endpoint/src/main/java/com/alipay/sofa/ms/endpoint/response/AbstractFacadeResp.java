package com.alipay.sofa.ms.endpoint.response;

/**
 * common response data
 * <p>
 * Created by yangguanchao on 16/09/07.
 */
public class AbstractFacadeResp {

    /**
     * the tag to representation the response is a success response or not
     */
    private boolean success = false;

    /**
     * inner error info
     */
    private String resultMsg;

    /**
     * the requestId
     */
    private String requestId;

    public AbstractFacadeResp(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
