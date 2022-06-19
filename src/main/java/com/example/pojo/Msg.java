package com.example.pojo;

import java.io.Serializable;

public class Msg implements Serializable {
    private static final long serialVersionUID = -5872917581716806078L;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
