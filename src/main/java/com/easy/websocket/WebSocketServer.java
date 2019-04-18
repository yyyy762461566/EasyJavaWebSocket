package com.easy.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocketServer.java
 * 
 * comments:	WebSocket服务
 * 
 * @author					YY
 * @creation date		2019年3月18日
 * @version					1.0
 */
@Component
@ServerEndpoint("/webSocket/{SID}/{isKeepConnect}")
public class WebSocketServer {
  
  private ConcurrentHashMap<String, Socket> sockets = new ConcurrentHashMap<>();

  public static WebSocketRegistry webSocketRegistry;

  public static WebSocketMonitor webSocketMonitor;

  @OnOpen
  public void onOpen(@PathParam(value="SID") String sid,@PathParam(value = "isKeepConnect") boolean isKeepConnect, Session session){
    Socket socket = new Socket(sid,null,session,isKeepConnect,new Date());
    sockets.put(sid, socket);
    try {
      webSocketRegistry.registryClient(sid);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //收到消息时执行
  @OnMessage
  public void onMessage(String message, Session session) throws IOException {
    webSocketMonitor.monitoredMessage(this.getSocketUUID8SessionId(session));
  }

  /**
   * 根据SocketUUID发送消息
   * @param sid SocketUUID 
   * @param message 需要发送的消息
   */
  public void sendMessage(String sid,String message) {
    synchronized(this){
      try {
        sockets.get(sid).getSession().getBasicRemote().sendText(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  /**
   * 通过SocketUUID获取Socket业务bean
   * @param socketUuid 标识Socket连接的UUID
   * @return
   */
  public synchronized Socket getSocket(String socketUuid){
    return sockets.get(socketUuid);
  }

  /**
   * 根据SessionId获取SocketUUID
   * @param session
   * @return
   */
  private String getSocketUUID8SessionId(Session session){
    for(Map.Entry<String,Socket> entry : sockets.entrySet()){
      Socket temp = entry.getValue();
      if(session.getId().equals(temp.getSession().getId())){
        return entry.getKey();
      }
    }
    return null;
  }

}
