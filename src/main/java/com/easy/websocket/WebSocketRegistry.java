package com.easy.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocketRegistry.java
 * 
 * comments:	WebSocket注册中心
 * 
 * @author					YY
 * @creation date		2019年4月4日
 * @version					1.0
 */
@Component
public class WebSocketRegistry {

    private ConcurrentHashMap<String, Map<String,Object>> registryCenter = new ConcurrentHashMap<>();

    @Autowired
    private WebSocketMonitor webSocketMonitor;

    /**
     * 服务器平台
     */
    private static final String SERVER_PLATFORM = "server";

    /**
     * 客户端平台
     */
    private static final String CLIENT_PLATFORM = "client";

    /**
     * 注册客户端
     * @param socketUUID 建立Socket的连接时生成的UUID
     * @return
     */
    public void registryClient(String socketUUID) throws Exception{
        this.registryBeforAction(socketUUID,this.CLIENT_PLATFORM);
        this.whetherStartMonitor(socketUUID);
    }

    /**
     * 注册服务端
     * @param socketUUID 建立Socket的连接时生成的UUID
     * @return
     */
    public void registryServer(String socketUUID) throws Exception{
        this.registryBeforAction(socketUUID,this.SERVER_PLATFORM);
        this.whetherStartMonitor(socketUUID);
    }

    /**
     * 注册之前需执行的动作
     * @param socketUUID 建立Socket的连接时生成的UUID
     * @param platform 注册平台(区分是服务端注册还是客户端注册)
     */
    private void registryBeforAction(String socketUUID,String platform) throws Exception{
        if(SERVER_PLATFORM.equals(platform) | CLIENT_PLATFORM.equals(platform)){
            if(registryCenter.containsKey(socketUUID)){
                this.platformRegistry(socketUUID,platform);
            }else{
                this.initRegistry(socketUUID);
            }
        }else {
            throw new NoSuchFieldException("Unsupported platform:"+platform);
        }
    }

    /**
     * 根据SocketUUID初始化注册表
     * @param socketUUID 建立Socket的连接时生成的UUID
     */
    private void initRegistry(String socketUUID){
        Map<String,Object> registryMap = new HashMap<>();
        registryMap.put("clientIsRegistry",false);
        registryMap.put("serverIsRegistry",false);
        registryCenter.put(socketUUID,registryMap);
    }

    /**
     * 在注册表中注册相关平台
     * @param socketUUID 建立Socket的连接时生成的UUID
     * @param platform 需注册的平台
     */
    private void platformRegistry(String socketUUID,String platform){
        if (CLIENT_PLATFORM.equals(platform)) {
            registryCenter.get(socketUUID).put("clientIsRegistry", true);
        } else {
            registryCenter.get(socketUUID).put("serverIsRegistry", true);
        }
    }

    /**
     * 验证Socket连接是否满足监控条件
     * @param socketUUID 建立Socket的连接时生成的UUID
     */
    private void whetherStartMonitor(String socketUUID){
        Map<String, Object> registryInfo = registryCenter.get(socketUUID);
        if(Boolean.valueOf(registryInfo.get("clientIsRegistry").toString()) | Boolean.valueOf(registryInfo.get("serverIsRegistry").toString())){

            /*StackTraceElement stack[] = (new Throwable()).getStackTrace();
            for (StackTraceElement ste : stack ) {
                System.out.println(ste.getClassName()+"."+ste.getMethodName()+"(...);");
                System.out.println(ste.getMethodName());
                System.out.println(ste.getFileName());
                System.out.println(ste.getLineNumber());
            }*/
            StackTraceElement ste = new StackTraceElement("GroupController","handleSocketMessage",null,0);
            registryCenter.get(socketUUID).put("monitorIsStart",true);
            webSocketMonitor.addMonitorEvent(socketUUID,ste);
        }
    }



}
