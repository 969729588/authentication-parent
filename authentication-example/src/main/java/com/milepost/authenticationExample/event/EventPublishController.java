package com.milepost.authenticationExample.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ruifu Hua on 2020/2/26.
 */
@RestController
@RequestMapping("/eventPublish")
public class EventPublishController {

    /**
     * 发布事件
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 测试事件发布
     *
     * @return
     */
    @GetMapping("testPublishEvent")
    public String testPublishEvent(EventSource eventSource){
        EventObj eventObj = new EventObj(eventSource);
        applicationEventPublisher.publishEvent(eventObj);
        return "testPublishEvent";
    }
}
