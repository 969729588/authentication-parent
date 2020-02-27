# SpringBoot事件监听

> 本文档讲述如何使用SpringBoot为我们提供的事件监听机制。

> Java中的事件机制一般包括3个部分：EventObject，EventListener和EventSource，Spring中也是如此。
ApplicationEvent以及Listener是Spring为我们提供的一个事件监听、订阅的实现，
内部实现原理是观察者设计模式，设计初衷也是为了系统业务逻辑之间的解耦，
提高可扩展性以及可维护性。

## 1、定义事件源(EventSource)
```java
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

```

## 2、定义事件对象(EventObject)
```java
package com.milepost.authenticationExample.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Ruifu Hua on 2020/2/26.
 * 事件对象
 */
public class EventObj extends ApplicationEvent{

    private Object source;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public EventObj(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public String toString() {
        return "EventObj{" +
                "source=" + source +
                '}';
    }
}

```

## 3、监听事件(EventListener)
```java
package com.milepost.authenticationExample.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/2/26.
 * 事件监听
 */
@Component
public class EventListen {

    /**
     * 监听EventObj事件
     * @param eventObj
     */
    @EventListener
    public void listenerForCustomEvent(EventObj eventObj){
        System.out.println(eventObj);
    }

    /**
     * 监听所有事件
     * @param object
     */
    @EventListener
    public void applicationReady(Object object) {
        System.out.println(object.toString());

        System.out.println("------------");
    }
}

```

## 4、发布事件
```java
/**
 * 注入applicationEventPublisher，用来发布事件
 */
@Autowired
private ApplicationEventPublisher applicationEventPublisher;

/**
* 发布事件
*/
@GetMapping("testPublishEvent")
public String testPublishEvent(){
    CustomEvent customEvent = new CustomEvent(new EventSource("zhangsan", "123456"));
    applicationEventPublisher.publishEvent(customEvent);
    return "testPublishEvent";
}
```
