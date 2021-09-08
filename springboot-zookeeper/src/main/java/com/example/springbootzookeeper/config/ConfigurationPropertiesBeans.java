package com.example.springbootzookeeper.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Component
public class ConfigurationPropertiesBeans implements BeanPostProcessor {

    private Map<String,List<FieldPair>> fieldMapper=new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class clz=bean.getClass();
        //如果某个bean声明了RefreshScop注解，说明需要进行动态更新
        if(clz.isAnnotationPresent(RefreshScope.class)){
            for(Field field:clz.getDeclaredFields()){
                //得到带有Value注解的filed @Value("${name}")
                Value value=field.getAnnotation(Value.class);
                if(value==null){
                    continue;
                }
                List<String> keyList=getPropertyKey(value.value(),0);
                for (String key:keyList){
                    fieldMapper.computeIfAbsent(key,(k)->new ArrayList())
                            .add(new FieldPair(bean,field,value.value()));
                }
            }
        }
        return bean;
    }
    //@Value("{xxx:${yyy}}")
    private List<String> getPropertyKey(String value,int begin){
        int start=value.indexOf("${",begin)+2;
        if(start<2){
            return new ArrayList<>();
        }
        int middle=value.indexOf(":",start);
        int end=value.indexOf("}",start);
        String key;
        if(middle>0&&middle<end){
            key=value.substring(start,middle);
        }else{
            key=value.substring(start,end);
        }
        List<String> keys=getPropertyKey(value,end);
        keys.add(key);
        return keys;
    }

    public Map<String,List<FieldPair>> getFieldMapper(){
        return fieldMapper;
    }
}
