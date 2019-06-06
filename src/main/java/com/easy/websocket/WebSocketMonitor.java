package com.easy.websocket;

import com.easy.websocket.pojo.HandleSocketMethod;
import com.easy.websocket.util.SpringBeansUtil;
import org.springframework.util.ReflectionUtils;

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

    private ConcurrentHashMap<String, HandleSocketMethod> monitorCenter = new ConcurrentHashMap<>(16);

    /**
     * 添加监控事件
     * @param socketUUID 建立Socket连接时生成的UUID
     * @param handleSocketMethod 监听到消息后需执行的方法
     */
    public synchronized void addMonitorEvent(String socketUUID,HandleSocketMethod handleSocketMethod){
        monitorCenter.put(socketUUID,handleSocketMethod);
    }

    /**
     * 监听到Socket消息处理方法
     */
    public synchronized void monitoredMessage(String socketUUID){
        HandleSocketMethod handleSocketMethod = monitorCenter.get(socketUUID);
        Method method = null;
        try {
            if(handleSocketMethod.isSpringManage()){
                String classPath = handleSocketMethod.getDeclaringClass();
                String beanName = classPath.substring(classPath.lastIndexOf(".")+1);
                Object bean = SpringBeansUtil.getBean(beanName);
                method = ReflectionUtils.findMethod(bean.getClass(),handleSocketMethod.getMethodName());
                ReflectionUtils.invokeMethod(method,bean);
            }else{
                //ClassLoader.getSystemClassLoader().loadClass(handleSocketMethod.getDeclaringClass());
                Class mclass = Class.forName(handleSocketMethod.getDeclaringClass());
                method = ReflectionUtils.findMethod(mclass,handleSocketMethod.getMethodName());
                ReflectionUtils.invokeMethod(method,mclass);
            }
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }






}
