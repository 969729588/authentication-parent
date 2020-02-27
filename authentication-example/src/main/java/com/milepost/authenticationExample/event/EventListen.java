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
