package com.vantinh.projectmobile.Model;

import java.io.Serializable;

public class SanPhamSale implements Serializable {
    public int ID;
    public String Ten;
    public Integer Gia;
    public Integer Giasale;
    public String Hinhanh;
    public String Thongsokithuat;
    public String Mota;
    public int Status;

    public SanPhamSale(int ID, String ten, Integer gia, Integer giasale, String hinhanh, String thongsokithuat, String mota, int status) {
        this.ID = ID;
        Ten = ten;
        Gia = gia;
        Giasale = giasale;
        Hinhanh = hinhanh;
        Thongsokithuat = thongsokithuat;
        Mota = mota;
        Status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getHinhanh() {
        return Hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        Hinhanh = hinhanh;
    }

    public Integer getGia() {
        return Gia;
    }

    public void setGia(Integer gia) {
        Gia = gia;
    }

    public Integer getGiasale() {
        return Giasale;
    }

    public void setGiasale(Integer giasale) {
        Giasale = giasale;
    }

    public String getThongsokithuat() {
        return Thongsokithuat;
    }

    public void setThongsokithuat(String thongsokithuat) {
        Thongsokithuat = thongsokithuat;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
