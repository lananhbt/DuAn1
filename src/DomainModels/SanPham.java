package DomainModels;

import java.util.Date;

public class SanPham {

    private String MaSP;
    private String MaLSP;
    private String TenSP;
    private int soLuong;
    private float giaNhap;
    private float giaBan;
    private Date hanSuDung;
    private String maQR;

    public SanPham() {
    }

    public SanPham(String MaSP, String MaLSP, String TenSP, int soLuong, float giaNhap, float giaBan, Date hanSuDung, String maQR) {
        this.MaSP = MaSP;
        this.MaLSP = MaLSP;
        this.TenSP = TenSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.hanSuDung = hanSuDung;
        this.maQR = maQR;
    }

    public SanPham(String MaSP, int soLuong) {
        this.MaSP = MaSP;
        this.soLuong = soLuong;
    }

    public SanPham(String maSP, String maLSP, String tenSP, int soLuong, float giaNhap, float giaBan, Date hanSuDung) {
        this.MaSP = maSP;
        this.MaLSP = maLSP;
        this.TenSP = tenSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.hanSuDung = hanSuDung;
    }

    public SanPham(String MaSP, String TenSP, int soLuong, float giaNhap) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
    }

    

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaLSP() {
        return MaLSP;
    }

    public void setMaLSP(String MaLSP) {
        this.MaLSP = MaLSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
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
