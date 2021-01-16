package com.vantinh.projectmobile.Model;


import java.io.Serializable;

public class ListPhoto implements Serializable {
    public String hinhanh;

    public ListPhoto(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
