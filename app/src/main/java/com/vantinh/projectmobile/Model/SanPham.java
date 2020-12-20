package com.vantinh.projectmobile.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    public int ID;
    public String Ten;
    public String Hinhanh;
    public Integer Gia;
    public String Thongsokithuat;
    public String Mota;
    public int IDsanphamdienthoai;
    public int IDsanpham;
    public int Status;

    // constructor phu kien
    public SanPham(int ID, String ten, String hinhanh, Integer gia, String thongsokithuat, String mota, int IDsanpham) {
        this.ID = ID;
        Ten = ten;
        Hinhanh = hinhanh;
        Gia = gia;
        Thongsokithuat = thongsokithuat;
        Mota = mota;
        this.IDsanpham = IDsanpham;
    }

    // constructor điện thoiaj, laptop
    public SanPham(int ID, String ten, String hinhanh, Integer gia, String thongsokithuat, String mota, int IDsanphamdienthoai, int IDsanpham) {
        this.ID = ID;
        Ten = ten;
        Hinhanh = hinhanh;
        Gia = gia;
        Thongsokithuat = thongsokithuat;
        Mota = mota;
        this.IDsanphamdienthoai = IDsanphamdienthoai;
        this.IDsanpham = IDsanpham;
    }

    // constructor sản phẩm nổi bật
    public SanPham(int ID, String ten, String hinhanh, Integer gia, String thongsokithuat, String mota, int IDsanphamdienthoai, int IDsanpham, int status) {
        this.ID = ID;
        Ten = ten;
        Hinhanh = hinhanh;
        Gia = gia;
        Thongsokithuat = thongsokithuat;
        Mota = mota;
        this.IDsanphamdienthoai = IDsanphamdienthoai;
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

    public int getIDsanphamdienthoai() {
        return IDsanphamdienthoai;
    }

    public void setIDsanphamdienthoai(int IDsanphamdienthoai) {
        this.IDsanphamdienthoai = IDsanphamdienthoai;
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
