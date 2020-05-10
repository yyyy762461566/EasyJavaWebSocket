package com.easy.websocket.pojo;

import javax.websocket.Session;
import java.util.Date;

/**
 * Socket.java
 * <p>
 * comments:	WebSocket业务Bean
 *
 * @author YY
 * @creation date        2019年3月19日
 * @version 1.0
 */
public class Socket {

    private String socketUuid;

    private String businessFlag;

    private Session session;

    private boolean isKeepConnect;

    private Date lastActiveTime;

    public Socket() {
    }

    public Socket(String socketUuid, String businessFlag, Session session, boolean isKeepConnect, Date lastActiveTime) {
        this.socketUuid = socketUuid;
        this.businessFlag = businessFlag;
        this.session = session;
        this.isKeepConnect = isKeepConnect;
        this.lastActiveTime = lastActiveTime;
    }

    public String getSocketUuid() {
        return socketUuid;
    }

    public void setSocketUuid(String socketUuid) {
        this.socketUuid = socketUuid;
    }

    public String getBusinessFlag() {
        return businessFlag;
    }

    public void setBusinessFlag(String businessFlag) {
        this.businessFlag = businessFlag;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isKeepConnect() {
        return isKeepConnect;
    }

    public void setKeepConnect(boolean isKeepConnect) {
        this.isKeepConnect = isKeepConnect;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }


}

