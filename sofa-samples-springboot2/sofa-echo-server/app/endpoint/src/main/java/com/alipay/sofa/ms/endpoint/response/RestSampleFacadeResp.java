package com.alipay.sofa.ms.endpoint.response;

/**
 * the wrapper of the response data in the format of json
 * <p/>
 * Created by yangguanchao on 16/11/18.
 */
public class RestSampleFacadeResp<T> extends AbstractFacadeResp {

    /**
     * the data in the response
     */
    private T data;


    public RestSampleFacadeResp() {
        super(true);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestSampleFacadeResp{" +
                "data=" + data +
                '}';
    }
}
