package com.easy.websocket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocketMonitor.java
 * 
 * comments:	WebSocket监听器
 * 
 * @author					YY
 * @creation date		2019年4月4日
 * @version					1.0
 */
public class WebSocketMonitor {

    private static WebSocketMonitor instance;

    private WebSocketMonitor() {}

    public static synchronized WebSocketMonitor getInstance(){
        if(instance == null) {
            instance = new WebSocketMonitor();
        }
        return instance;
    }

    private ConcurrentHashMap<String, StackTraceElement> monitorCenter = new ConcurrentHashMap<>();

    /**
     * 添加监控事件
     * @param socketUUID
     * @param ste 监听到消息后需执行的方法
     */
    public void addMonitorEvent(String socketUUID,StackTraceElement ste){
        StackTraceElement temp = new StackTraceElement("GroupController","aa",null,0);
        monitorCenter.put(socketUUID,ste);
    }

    /**
     * 监听到Socket消息处理方法
     */
    public void monitoredMessage(String socketUUID){
        StackTraceElement stackTraceElement = monitorCenter.get(socketUUID);
        Class mclass = null;
        Method method = null;
        try {
            mclass = ClassLoader.getSystemClassLoader().loadClass("com.tristore.helper.controller.GroupController"); // .forName("GroupController.class");
            method = mclass.getMethod(stackTraceElement.getMethodName());
            method.invoke(mclass,"测试");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }






}
