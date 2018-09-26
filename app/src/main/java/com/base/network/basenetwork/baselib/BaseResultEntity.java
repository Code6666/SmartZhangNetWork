package com.base.network.basenetwork.baselib;

/**
 * Created by d on 2018/7/19.
 */

public class BaseResultEntity<E> {

    private String msg;
    private int status;
    private E data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
