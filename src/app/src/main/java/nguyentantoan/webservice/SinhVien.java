package nguyentantoan.webservice;

import java.io.Serializable;

public class SinhVien implements Serializable{
    private int Id;
    private String ThietBi;
    private int TrangThai;
    private String GhiChu;

    public SinhVien(int id, String thietBi, int trangThai, String ghiChu) {
        Id = id;
        ThietBi = thietBi;
        TrangThai = trangThai;
        GhiChu = ghiChu;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getThietBi() {
        return ThietBi;
    }

    public void setThietBi(String thietBi) {
        ThietBi = thietBi;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {TrangThai = trangThai; }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}

