/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author caoxu
 */
public class QLKH {

    private String MaKH;
    private String TenKH;
    private String GioiTinh;
    private String DiaChi;
    private String NgaySinh;
    private String Sdt;
    private String NgayDki;

    public QLKH() {
    }

    public QLKH(String MaKH, String TenKH, String GioiTinh, String DiaChi, String NgaySinh, String Sdt, String NgayDki) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.Sdt = Sdt;
        this.NgayDki = NgayDki;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getNgayDki() {
        return NgayDki;
    }

    public void setNgayDki(String NgayDki) {
        this.NgayDki = NgayDki;
    }

    @Override
    public String toString() {
        return "QLKH{" + "MaKH=" + MaKH + ", TenKH=" + TenKH + ", GioiTinh=" + GioiTinh + ", DiaChi=" + DiaChi + ", NgaySinh=" + NgaySinh + ", Sdt=" + Sdt + ", NgayDki=" + NgayDki + '}';
    }

    
    
    
}
