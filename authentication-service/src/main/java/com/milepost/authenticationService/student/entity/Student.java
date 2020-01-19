package com.milepost.authenticationService.student.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.milepost.authenticationService.classes.entity.Classes;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    /**
     * student.ID
     * 
     *
     * @mbggenerated
     */
    private String id;

    /**
     * student.NAME
     * 姓名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * student.BIRTH
     * 出生日期
     *
     * @mbggenerated
     */
    //如果再主配置文件中配置了spring.mvc.date-format属性之后，springboot会自动配置日期类型转换器并制定日期转换格式，所以这里就不用写DateTimeFormat注解了。
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//将前端传过来的字符串形式的日期转换成Date
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")//返回给前端时候，格式化Date的格式
    private Date birth;

    /**
     * student.STU_NO
     * 学号
     *
     * @mbggenerated
     */
    private String stuNo;

    /**
     * student.SCORE
     * 分数
     *
     * @mbggenerated
     */
    private Float score;

    /**
     * student.REMARK
     * 评价
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * student.CLASSES_ID
     * 班级id
     *
     * @mbggenerated
     */
    private String classesId;

    private Classes classes;

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo == null ? null : stuNo.trim();
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

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stuNo='" + stuNo + '\'' +
                ", birth=" + birth +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", classesId='" + classesId + '\'' +
                ", classes=" + classes +
                '}';
    }
}