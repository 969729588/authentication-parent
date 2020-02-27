package com.milepost.authenticationExample.configurationAndComponent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 测试@Configuration和@Component的区别
 * Created by Ruifu Hua on 2020/2/27.
 */
@Configuration
//@Component
public class Config {

    @Bean
    public Driver driver1(){
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("driver");
        //当使用@Configuration时，下面的car()方法实际不会去调用下面@Bean注解标注的car()方法，而是返回创建car时的那个对象
        //当使用@Component时，下面的car()方法实际还会去调用下面@Bean注解标注的car()方法，造成实例化两个car对象的其概况。
        driver.setCar(car());
        return driver;
    }

    @Bean
    public Driver driver2(Car car){
        //无论@Configuration还是@Component，这里的car都是下面的@Bean标注的car()方法返回的car对象。
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("driver");
        driver.setCar(car);
        return driver;
    }

    @Bean
    public Car car(){
        Car car = new Car();
        car.setId(1);
        car.setName("car");
        return car;
    }
}

class Driver{
    private Integer id;
    private String name;
    private Car car;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}



class Car{
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
