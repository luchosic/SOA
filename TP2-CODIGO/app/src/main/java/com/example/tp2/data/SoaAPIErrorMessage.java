package com.example.tp2.data;

public class SoaAPIErrorMessage {

    private String msg;
    private Boolean status;

    public SoaAPIErrorMessage(String msg, Boolean status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
