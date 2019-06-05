package com.easy.websocket.util;

import com.easy.websocket.SocketMessage;
import com.easy.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SendSocketMssgUtil.java
 * 
 * comments:	服务器发送Socket信息Util类
 * 
 * @author					YY
 * @creation date		2019年3月22日
 * @version					1.0
 */
public class SendSocketMssgUtil {

  @Autowired
  private WebSocketServer webSocketServer;

  /**
   * 同步发送消息
   * @param socketUUID 建立Socket连接时生成的UUID
   * @param socketMessage 需发送的消息
   */
  public void syncSendMessage(String socketUUID,SocketMessage socketMessage) {
    webSocketServer.sendMessage(socketUUID,socketMessage);
  }

  /**
   * 异步发送消息
   * @param socketUUID 建立Socket连接时生成的UUID
   * @param socketMessage 需发送的消息
   */
  public void asyncSendMessage(String socketUUID, SocketMessage socketMessage) {
    
  }
  
}
