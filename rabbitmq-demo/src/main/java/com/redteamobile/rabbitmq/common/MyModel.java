package com.redteamobile.rabbitmq.common;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by toutou on 2019/1/1.
 */
public class MyModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String info;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}