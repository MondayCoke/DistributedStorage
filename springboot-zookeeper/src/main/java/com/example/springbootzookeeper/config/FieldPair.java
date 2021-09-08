package com.example.springbootzookeeper.config;

import org.springframework.core.env.Environment;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Field;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 * //用来实现属性值的替换
 **/
public class FieldPair {

    private PropertyPlaceholderHelper propertyPlaceholderHelper=
            new PropertyPlaceholderHelper("${","}",":",true);

    private Object bean;
    private Field field;
    private String value;

    public FieldPair(Object bean, Field field, String value) {
        this.bean = bean;
        this.field = field;
        this.value = value;
    }

    public void resetValue(Environment environment){
        boolean access=field.isAccessible();
        if(!access){
            field.setAccessible(true);
        }
        String resetValue=propertyPlaceholderHelper.replacePlaceholders(value,environment::getProperty);
        try {
            //反射修改bean的属性值
            field.set(bean,resetValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
