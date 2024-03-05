/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author caoxu
 */
public class NhaPhanPhoi {
    private String MaNPP;
    private String TenNPP;
    private String DiaChi;
    private String Sdt;
    private String MaSP;
    private int TrangThai;

    public NhaPhanPhoi() {
    }

    public NhaPhanPhoi(String MaNPP, String TenNPP, String DiaChi, String Sdt, String MaSP, int TrangThai) {
        this.MaNPP = MaNPP;
        this.TenNPP = TenNPP;
        this.DiaChi = DiaChi;
        this.Sdt = Sdt;
        this.MaSP = MaSP;
        this.TrangThai = TrangThai;
    }

    public String getMaNPP() {
        return MaNPP;
    }

    public void setMaNPP(String MaNPP) {
        this.MaNPP = MaNPP;
    }

    public String getTenNPP() {
        return TenNPP;
    }

    public void setTenNPP(String TenNPP) {
        this.TenNPP = TenNPP;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "NhaPhanPhoi{" + "MaNPP=" + MaNPP + ", TenNPP=" + TenNPP + ", DiaChi=" + DiaChi + ", Sdt=" + Sdt + ", MaSP=" + MaSP + ", TrangThai=" + TrangThai + '}';
    }

    
}
