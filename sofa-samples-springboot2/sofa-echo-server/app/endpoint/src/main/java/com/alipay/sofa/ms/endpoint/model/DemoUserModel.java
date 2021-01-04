package com.alipay.sofa.ms.endpoint.model;

/**
 * user info model
 * <p/>
 * Created by yangguanchao on 16/8/27.
 */
public class DemoUserModel {

    private int userId;

    private String userName;

    private String realName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DemoUserModel{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
