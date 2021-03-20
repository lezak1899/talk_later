package edu.lingnan.talklater.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取指定名称的Bean对象
     *
     * @param beanName bean名称
     * @return 对象实例
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 获取指定名称的Bean对象
     *
     * @param beanName bean名称
     * @param args     bean初始化参数
     * @return 对象实例
     */
    public static Object getBean(String beanName, Object... args) {
        return applicationContext.getBean(beanName, args);
    }

    /**
     * 获取指定类的Bean对象
     *
     * @param clazz 指定类Class
     * @param <T>   获取到的bean对象
     * @return 对象实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取指定类的Bean对象
     *
     * @param clazz 指定类Class
     * @param args  bean初始化参数
     * @param <T>   获取到的bean对象
     * @return 对象实例
     */
    public static <T> T getBean(Class<T> clazz, Object... args) {
        return applicationContext.getBean(clazz, args);
    }

    /**
     * 根据指定名称和类型来获取Bean对象
     *
     * @param beanName     bean名称
     * @param requiredType 指定类Class
     * @param <T>          获取到的bean对象
     * @return 对象实例
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return applicationContext.getBean(beanName, requiredType);
    }

    /**
     * 根据类型获取此类型的Bean的集合
     *
     * @param type 指定类Class
     * @return 对象实例Map
     */
    public static Map<String, ?> getBeansOfType(Class<?> type) {
        Map<String, ?> beans = applicationContext.getBeansOfType(type);
        if (beans == null) {
            return new HashMap<String, Object>();
        }
        return beans;
    }

    /**
     * 根据指定的Bean类型创建Bean对象
     * @param beanClass beanClass Bean的class类型
     * @param <T> 指定的Bean类型
     * @return 自定类型的Bean对象
     * @throws BeansException 创建Bean异常
     */
    public static <T> T createBean(Class<T> beanClass) throws BeansException{
        return applicationContext.getAutowireCapableBeanFactory().createBean(beanClass);
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext实例
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationcontext) throws BeansException {
        applicationContext = applicationcontext;
    }

}
