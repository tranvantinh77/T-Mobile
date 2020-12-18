package com.vantinh.projectmobile.Model;

import java.io.Serializable;

public class ThuongHieu implements Serializable {
    private int resourceId;
    private String name;
    private int idthuonghieu;

    public ThuongHieu() {
    }

    public ThuongHieu(int resourceId, String name, int idthuonghieu) {
        this.resourceId = resourceId;
        this.name = name;
        this.idthuonghieu = idthuonghieu;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdthuonghieu() {
        return idthuonghieu;
    }

    public void setIdthuonghieu(int idthuonghieu) {
        this.idthuonghieu = idthuonghieu;
    }
}
