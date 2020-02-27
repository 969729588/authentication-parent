package com.milepost.authenticationExample.event;

/**
 * Created by Ruifu Hua on 2020/2/26.
 * 事件源
 */
public class EventSource {
    private String username;
    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "EventSource{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public EventSource(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }
}
