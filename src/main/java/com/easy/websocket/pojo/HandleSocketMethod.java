package com.easy.websocket.pojo;

import java.util.Objects;

/**
 * @description: 处理Socket消息方法
 * @author: yy
 * @Date: 2019/06/05 11:48
 * @Version 1.0
 **/
public class HandleSocketMethod {

    private String declaringClass;
    private String methodName;
    private boolean springManage;

    public HandleSocketMethod(String declaringClass, String methodName, boolean springManage) throws ClassNotFoundException {
        if (Objects.nonNull(Objects.requireNonNull(declaringClass, "Declaring class is null"))) {
            try {
                Class.forName(declaringClass);
            } catch (ClassNotFoundException e) {
                throw e;
            }
        }
        this.declaringClass = declaringClass;
        this.methodName = Objects.requireNonNull(methodName, "Method name is null");
        this.springManage = Objects.requireNonNull(springManage, "SpringManage name is null");
    }

    public String getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(String declaringClass) {
        this.declaringClass = declaringClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public boolean isSpringManage() {
        return springManage;
    }

    public void setSpringManage(boolean springManage) {
        springManage = springManage;
    }
}
