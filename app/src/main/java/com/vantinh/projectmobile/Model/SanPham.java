package com.vantinh.projectmobile.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    public int ID;
    public String Ten;
    public String Hinhanh;
    public String Hinhanh2;
    public String Hinhanh3;
    public String Hinhanh4;
    public Integer Gia;
    public String Thongsokithuat;
    public String Mota;
    public int IDloaisanpham;
    public int IDsanpham;
    public int Status;

    public SanPham() {
    }

    // constructor phu kien
    public SanPham(int ID, String ten, String hinhanh, String hinhanh2, String hinhanh3, String hinhanh4, Integer gia, String thongsokithuat, String mota, int IDsanpham) {
        this.ID = ID;
        Ten = ten;
        Hinhanh = hinhanh;
        Hinhanh2 = hinhanh2;
        Hinhanh3 = hinhanh3;
        Hinhanh4 = hinhanh4;
        Gia = gia;
        Thongsokithuat = thongsokithuat;
        Mota = mota;
        this.IDsanpham = IDsanpham;
    }

    // constructor điện thoiaj, laptop
    public SanPham(int ID, String ten, String hinhanh, String hinhanh2, String hinhanh3, String hinhanh4, Integer gia, String thongsokithuat, String mota, int IDloaisanpham, int IDsanpham) {

        this.ID = ID;
        Ten = ten;
        Hinhanh = hinhanh;
        Hinhanh2 = hinhanh2;
        Hinhanh3 = hinhanh3;
        Hinhanh4 = hinhanh4;
        Gia = gia;
        Thongsokithuat = thongsokithuat;
        Mota = mota;
        this.IDloaisanpham = IDloaisanpham;
        this.IDsanpham = IDsanpham;
    }

    // constructor sản phẩm nổi bật
    public SanPham(int ID, String ten, String hinhanh, String hinhanh2, String hinhanh3, String hinhanh4, Integer gia, String thongsokithuat, String mota, int IDloaisanpham, int IDsanpham, int status) {
        this.ID = ID;
        Ten = ten;
        Hinhanh = hinhanh;
        Hinhanh2 = hinhanh2;
        Hinhanh3 = hinhanh3;
        Hinhanh4 = hinhanh4;
        Gia = gia;
        Thongsokithuat = thongsokithuat;
        Mota = mota;
        this.IDloaisanpham = IDloaisanpham;
        this.IDsanpham = IDsanpham;
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

    public String getHinhanh2() {
        return Hinhanh2;
    }

    public void setHinhanh2(String hinhanh2) {
        Hinhanh2 = hinhanh2;
    }

    public String getHinhanh3() {
        return Hinhanh3;
    }

    public void setHinhanh3(String hinhanh3) {
        Hinhanh3 = hinhanh3;
    }

    public String getHinhanh4() {
        return Hinhanh4;
    }

    public void setHinhanh4(String hinhanh4) {
        Hinhanh4 = hinhanh4;
    }

    public Integer getGia() {
        return Gia;
    }

    public void setGia(Integer gia) {
        Gia = gia;
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

    public int getIDloaisanpham() {
        return IDloaisanpham;
    }

    public void setIDloaisanpham(int IDsanphamdienthoai) {
        this.IDloaisanpham = IDsanphamdienthoai;
    }

    public int getIDsanpham() {
        return IDsanpham;
    }

    public void setIDsanpham(int IDsanpham) {
        this.IDsanpham = IDsanpham;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

}
