package com.milepost.authenticationApi.entity.user;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * milepost_auth..user.id
     * 
     *
     * @mbggenerated
     */
    private String id;

    /**
     * milepost_auth..user.username
     * 用户名，登录名，是唯一键
     *
     * @mbggenerated
     */
    private String username;

    /**
     * milepost_auth..user.truename
     * 用户真是姓名
     *
     * @mbggenerated
     */
    private String truename;

    /**
     * milepost_auth..user.mobile
     * 手机号码
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * milepost_auth..user.email
     * 邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * milepost_auth..user.password
     * 密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * milepost_auth..user.activated
     * 激活状态，1激活，0未激活
     *
     * @mbggenerated
     */
    private Boolean activated;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", truename=").append(truename);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", activated=").append(activated);
        sb.append("]");
        return sb.toString();
    }
}