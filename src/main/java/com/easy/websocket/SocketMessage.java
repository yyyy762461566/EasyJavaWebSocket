package com.easy.websocket;

/**
 * SocketMessage.java
 * 
 * comments:	Socket消息实体
 * 
 * @author					YY
 * @creation date		2019年3月22日
 * @version					1.0
 */
public class SocketMessage {
  
  private Socket socket;
  
  private String code;
  
  private String busnessFlag;
  
  private SocketMessageTypeEnum messageType;
  
  private Object message;

  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getBusnessFlag() {
    return busnessFlag;
  }

  public void setBusnessFlag(String busnessFlag) {
    this.busnessFlag = busnessFlag;
  }

  public SocketMessageTypeEnum getMessageType() {
    return messageType;
  }

  public void setMessageType(SocketMessageTypeEnum messageType) {
    this.messageType = messageType;
  }

  public Object getMessage() {
    return message;
  }

  public void setMessage(Object message) {
    this.message = message;
  }
  
}

