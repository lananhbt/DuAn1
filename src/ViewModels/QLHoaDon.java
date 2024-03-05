/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author caoxu
 */
public class QLHoaDon {

    private String MaND;
    private String MaKH;
    private String MaHD;
    private String NgayTao;
    private float TongTien;
    private int TrangThai;

    public QLHoaDon() {
    }

    public QLHoaDon(String MaND, String MaKH, String MaHD, String NgayTao, float TongTien, int TrangThai) {
        this.MaND = MaND;
        this.MaKH = MaKH;
        this.MaHD = MaHD;
        this.NgayTao = NgayTao;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public String getMaND() {
        return MaND;
    }

    public void setMaND(String MaND) {
        this.MaND = MaND;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "QLHoaDon{" + "MaND=" + MaND + ", MaKH=" + MaKH + ", MaHD=" + MaHD + ", NgayTao=" + NgayTao + ", TongTien=" + TongTien + ", TrangThai=" + TrangThai + '}';
    }

   
    
    
}
