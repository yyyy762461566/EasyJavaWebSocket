package com.easy.websocket.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @description: 获取Spring所管理Bean的工具类
 * @author: yy
 * @Date: 2019/06/05 14:30
 * @Version 1.0
 **/
@Component
public class  SpringBeansUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 设置applicationContext
     * 覆盖ApplicationContextAware方法
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        if(Objects.isNull(SpringBeansUtil.applicationContext)){
            SpringBeansUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String name){
        return getApplicationContext().getBean(lowerFistChar(name));
    }

    public static <T> T getBean(String var1, Class<T> var2) {
        return (T) getApplicationContext().getBean(lowerFistChar(var1), var2);
    }

    private static String lowerFistChar(String str){
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return String.valueOf(ch);
    }

    private static String upperFistChar(String str){
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return String.valueOf(ch);
    }

}
