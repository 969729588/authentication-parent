# 从SpringIOC容器中获取bean

> 本例子演示如何通过父类、注解从SpringIOC容器中获取bean，然后调用bean的方法，一般有以下用途：

* 框架提供一个接口，程序运行过程中会调用这个接口的实现类的方法来获取某些资源。
* 开发者提供这个接口的实现类并放入到SpringIOC容器中。

## 1、定义接口
SomeBean

## 2、定义注解
SomeAnnotation

## 3、定义实现类
SomeBeanImpl1
SomeBeanImpl2
SomeBeanImpl3

标注@Component注解，加入到SpringIOC容器中。

## 4、SomeService
监听ApplicationReadyEvent事件，然后获取bean。
