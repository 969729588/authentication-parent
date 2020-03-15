package com.milepost.authenticationApi.entity.person;

import com.milepost.authenticationApi.entity.user.User;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    /**
     * milepost_auth..person.ID
     * 
     *
     * @mbggenerated
     */
    private String id;

    /**
     * milepost_auth..person.FIRST_NAME
     * 
     *
     * @mbggenerated
     */
    private String firstName;

    /**
     * milepost_auth..person.LAST_NAME
     * 
     *
     * @mbggenerated
     */
    private String lastName;

    /**
     * milepost_auth..person.BIRTH
     * 
     *
     * @mbggenerated
     */
    private Date birth;

    /**
     * milepost_auth..person.SCORE
     * 
     *
     * @mbggenerated
     */
    private Float score;

    /**
     * milepost_auth..person.REMARK
     * 
     *
     * @mbggenerated
     */
    private String remark;

    private User user;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", birth=").append(birth);
        sb.append(", score=").append(score);
        sb.append(", remark=").append(remark);
        sb.append(", user=").append(user);
        sb.append("]");
        return sb.toString();
    }
}