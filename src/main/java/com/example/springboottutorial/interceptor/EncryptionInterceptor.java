package com.example.springboottutorial.interceptor;

import com.example.springboottutorial.service.EncryptionService;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class EncryptionInterceptor implements Interceptor {

    public static final String ON_SAVE = "onSave";
    public static final String ON_FLUSH_DIRTY = "onFlushDirty";
    public static final String ON_LOAD = "onLoad";
    private final EncryptionService encryptionService;

    public EncryptionInterceptor(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        var newState = processFields(entity, state, propertyNames, ON_SAVE);
        return Interceptor.super.onSave(entity, id, newState, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        var newState = processFields(entity, state, propertyNames, ON_LOAD);
        return Interceptor.super.onLoad(entity, id, newState, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        var newState = processFields(entity, currentState, propertyNames, ON_FLUSH_DIRTY);
        return Interceptor.super.onFlushDirty(entity, id, newState, previousState, propertyNames, types);
    }

    private Object[] processFields(Object entity, Object[] state, String[] propertyNames, String type) {
        for (var field : getAnnotatedFields(entity)) {
            for (var i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals(field)) {
                    if (StringUtils.hasText(state[i].toString())) {
                        if (ON_SAVE.equals(type) || ON_FLUSH_DIRTY.equals(type)) {
                            state[i] = encryptionService.encrypt(state[i].toString());
                        } else if (ON_LOAD.equals(type)) {
                            state[i] = encryptionService.decrypt(state[i].toString());
                        }
                    }
                }
            }
        }
        return state;
    }

    private List<String> getAnnotatedFields(Object entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> !Objects.isNull(field.getAnnotation(EncryptedString.class)))
                .map(Field::getName)
                .toList();
    }
}
