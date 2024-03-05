package ViewModels;

import java.util.Date;

public class ManageHoaDon {
    private String maHD;
    private String maND;
    private String maKH;
    private String maHDcu;
    private Date ngayTao;
    private float tongTien;
    private int trangThai;

    public ManageHoaDon() {
    }

    public ManageHoaDon(String maHD, String maND, String maKH, Date ngayTao, float tongTien, int trangThai) {
        this.maHD = maHD;
        this.maND = maND;
        this.maKH = maKH;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public ManageHoaDon(String maHD, String maND, String maKH) {
        this.maHD = maHD;
        this.maND = maND;
        this.maKH = maKH;
    }

    public ManageHoaDon(String maHD, String maND, String maKH, String maHDcu) {
        this.maHD = maHD;
        this.maND = maND;
        this.maKH = maKH;
        this.maHDcu = maHDcu;
    }

    public ManageHoaDon(String maHD) {
        this.maHD = maHD;
    }

    public String getMaHDcu() {
        return maHDcu;
    }

    public void setMaHDcu(String maHDcu) {
        this.maHDcu = maHDcu;
    }

    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
