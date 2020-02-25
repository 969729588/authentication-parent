package com.milepost.authenticationUi.test.getBeans;

import com.milepost.core.spring.ApplicationContextProvider;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/2/26.
 *
 * 1、获取SpringIOC容器中的bean，(通过父类，注解等)
 * 2、获取方法上的注解信息，
 * 3、调用bean的指定方法，
 * 4、获取代理对象的target
 */
@Service
public class SomeService {

    /**
     * 监听ApplicationReadyEvent事件
     * @param applicationReadyEvent
     */
    @EventListener
    public void listner(ApplicationReadyEvent applicationReadyEvent) throws Exception {
        ApplicationContext context = ApplicationContextProvider.getContext();
        Map<String, SomeBean> someBeanMap = context.getBeansOfType(SomeBean.class);
        for(Map.Entry<String, SomeBean> entry : someBeanMap.entrySet()){
            String beanName = entry.getKey();
            SomeBean someBean = entry.getValue();
            if(AopUtils.isAopProxy(someBean)){
                someBean = (SomeBean)AopProxyUtils.getSingletonTarget(someBean);
            }

            Method doSomethingMethod = someBean.getClass().getMethod("doSomething", new Class[]{String.class});
            Method getSomethingMethod = someBean.getClass().getMethod("getSomething", new Class[]{String.class});
            SomeAnnotation someAnnotation = (SomeAnnotation)getSomethingMethod.getAnnotation(SomeAnnotation.class);
            String pro = someAnnotation.pro();
            Object doSomethingMethodR = doSomethingMethod.invoke(someBean, "传入参数");
            System.out.println(doSomethingMethodR);
            Object getSomethingMethodR = getSomethingMethod.invoke(someBean, "传入参数" + pro);
            System.out.println(getSomethingMethodR);
        }
    }

    /**
     * 获取代理对象的target
     * @param proxy
     * @return
     * @throws Exception
     */
    private Object getTarget(Object proxy) throws Exception {
        return !AopUtils.isAopProxy(proxy)?proxy:(AopUtils.isJdkDynamicProxy(proxy)?this.getJdkDynamicProxyTargetObject(proxy):this.getCglibProxyTargetObject(proxy));
    }

    /**
     * 通过反射获取Cglib代理对象的target
     * @param proxy
     * @return
     * @throws Exception
     */
    private Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        return target;
    }

    /**
     * 通过反射获取JdkDynamic代理对象的target
     * @param proxy
     * @return
     * @throws Exception
     */
    private Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy)h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();
        return target;
    }
}
