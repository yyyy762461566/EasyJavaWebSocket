package com.easy.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.MessageFormat;
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
@ServerEndpoint("/webSocket/{SID}/{isKeepConnect}")
@Component
public class WebSocketServer {
  
  private ConcurrentHashMap<String, Socket> sockets = new ConcurrentHashMap<>();

  @OnOpen
  public void onOpen(@PathParam(value="SID") String sid,@PathParam(value = "isKeepConnect") boolean isKeepConnect, Session session){
    Socket socket = new Socket(sid,null,session,isKeepConnect,new Date());
    sockets.put(sid, socket);
    try {
      WebSocketRegistry.getInstance().registryClient(sid);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //收到消息时执行
  @OnMessage
  public void onMessage(String message, Session session) throws IOException {
    WebSocketMonitor.getInstance().monitoredMessage(this.getSocketUUID8SessionId(session));
  }

  /**
   * 根据SocketUUID发送消息
   * @param sid SocketUUID 
   * @param message 需要发送的消息
   */
  public synchronized void sendMessage(String sid,SocketMessage message) {
    try {
      String messageFormat = MessageFormat.format("{MESSAGE_TYPE:'{0}',MESSAGE:'{1}'}", message.getMessageType(), message.getMessage());
      sockets.get(sid).getSession().getBasicRemote().sendText(messageFormat);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
