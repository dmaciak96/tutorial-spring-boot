package com.example.springboottutorial.listeners;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class ListenersRegistration implements BeanPostProcessor {

    private final PreInsertListener preInsertListener;
    private final PostLoadListener postLoadListener;
    private final PreUpdateListener preUpdateListener;

    public ListenersRegistration(PreInsertListener preInsertListener, PostLoadListener postLoadListener, PreUpdateListener preUpdateListener) {
        this.preInsertListener = preInsertListener;
        this.postLoadListener = postLoadListener;
        this.preUpdateListener = preUpdateListener;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LocalContainerEntityManagerFactoryBean) {
            var lemf = (LocalContainerEntityManagerFactoryBean) bean;
            var sessionFactory = (SessionFactoryImpl) lemf.getNativeEntityManagerFactory();
            var registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);

            registry.appendListeners(EventType.POST_LOAD, postLoadListener);
            registry.appendListeners(EventType.PRE_INSERT, preInsertListener);
            registry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);
        }
        return bean;
    }
}
