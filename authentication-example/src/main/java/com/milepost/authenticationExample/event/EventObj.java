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
