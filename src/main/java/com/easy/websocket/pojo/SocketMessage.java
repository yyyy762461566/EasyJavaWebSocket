package com.easy.websocket.pojo;

import com.easy.websocket.SocketMessageTypeEnum;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Required;

/**
 * SocketMessage.java
 * <p>
 * comments:	Socket消息实体
 *
 * @author YY
 * @creation date        2019年3月22日
 * @version 1.0
 */
public class SocketMessage {

    /**
     * 消息码
     */
    private String code;

    /**
     * 业务标识
     */
    private String busnessFlag;

    /**
     * 发送给客户端的消息类型
     */
    private SocketMessageTypeEnum messageType;

    /**
     * 发送给客户端的消息
     */
    private Object message;

    public SocketMessage(String messageCode, String busnessFlag, @NotNull SocketMessageTypeEnum messageType, @NotNull Object message) {
        this.code = messageCode;
        this.busnessFlag = busnessFlag;
        this.messageType = messageType;
        this.message = message;
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

