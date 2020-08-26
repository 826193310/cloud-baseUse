package com.su.springcloud.entities;

import java.io.Serializable;

/**
 * @Classname Payment
 * @Description TODO
 * @Date 2020/8/2 17:11
 * @Created by SGZ
 */
public class Payment implements Serializable {
    private Long id;
    private String serial;
    private byte[] bs;

    public Payment(Long id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public byte[] getBs() {
        return bs;
    }

    public void setBs(byte[] bs) {
        this.bs = bs;
    }
}