package ViewModels;

import java.util.Date;

public class QLSanPham {

    private String maSP;
    private String maLSP;
    private String tenSP;
    private int soLuong;
    private float giaNhap;
    private float giaBan;
    private Date hanSuDung;
    private String maQR;

    public QLSanPham() {
    }

    public QLSanPham(String maSP, String maLSP, String tenSP, int soLuong, float giaNhap, float giaBan, Date hanSuDung, String maQR) {
        this.maSP = maSP;
        this.maLSP = maLSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.hanSuDung = hanSuDung;
        this.maQR = maQR;
    }
    public QLSanPham(String maSP, String maLSP, String tenSP, int soLuong, float giaNhap, float giaBan, Date hanSuDung) {
        this.maSP = maSP;
        this.maLSP = maLSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.hanSuDung = hanSuDung;
    }

    public QLSanPham(String maSP, String tenSP, int soLuong, float giaBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(String maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public String getMaQR() {
        return maQR;
    }

    public void setMaQR(String maQR) {
        this.maQR = maQR;
    }

}
