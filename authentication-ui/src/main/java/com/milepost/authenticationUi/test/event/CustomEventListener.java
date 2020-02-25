package com.milepost.authenticationUi.test.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/2/26.
 * 事件监听
 */
@Component
public class CustomEventListener {

    @EventListener
    public void listenerForCustomEvent(CustomEvent customEvent){
        System.out.println(customEvent);
    }
}
