package com.alipay.sofa.ms.endpoint.exception;

/**
 * CommonException
 * <p/>
 * Created by yangguanchao on 16/11/18.
 */
public class CommonException extends Exception{

    /***
     * you should define you errorCode here, this is only an example
     */
    private String errorCode = "CommonError";

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
