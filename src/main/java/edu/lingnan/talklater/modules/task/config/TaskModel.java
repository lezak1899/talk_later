package edu.lingnan.talklater.modules.task.config;

import edu.lingnan.talklater.utils.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * 通过反射去获得需要执行的类以及对应的方法
 */
@Slf4j
public class TaskModel implements Runnable{

    String springBeanName;
    String methodName;

    public TaskModel(String springBeanName, String methodName){
        this.springBeanName = springBeanName;
        this.methodName = methodName;
    }

    @Override
    public void run() {
        try{
            Object obj = SpringBeanUtil.getBean(springBeanName);
            Class clazz = obj.getClass();
            Method method = clazz.getMethod(methodName);
            method.invoke(obj);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
