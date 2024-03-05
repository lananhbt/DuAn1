package ViewModels;


public class ManageHoaDonChiTiet {
    private String maHDCT;
    private String maHD;
    private String maSP;
    private String tenSP;
    private int soLuong;
    private float giaBan;
    private float thanhTien;
    private String maCu;

    public ManageHoaDonChiTiet() {
    }

    public ManageHoaDonChiTiet(String maSP, String tenSP, int soLuong, float giaBan, float thanhTien) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }

    public ManageHoaDonChiTiet(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public ManageHoaDonChiTiet(String maHDCT, String maHD, String maSP, String tenSP, int soLuong, float giaBan, float thanhTien) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }

    public ManageHoaDonChiTiet(String maSP, int soLuong, float giaBan, float thanhTien) {
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }
//    public ManageHoaDonChiTiet(String maHD, String maSP,  int soLuong, float giaBan, float thanhTien) {
//        this.maHD = maHD;
//        this.maSP = maSP;
//        this.soLuong = soLuong;
//        this.giaBan = giaBan;
//        this.thanhTien = thanhTien;
//    }

    public ManageHoaDonChiTiet(String maHDCT, String maHD, String maSP, int soLuong, float giaBan, float thanhTien) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }
    

    public ManageHoaDonChiTiet(String maHDCT, String maHD, String maSP,  int soLuong, float giaBan, float thanhTien, String maCu) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
        this.maCu = maCu;
    }
    
    public ManageHoaDonChiTiet(String maSP, String tenSP, int soLuong, float giaBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
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

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public float getThanhTien() {
        return giaBan*soLuong;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaCu() {
        return maCu;
    }

    public void setMaCu(String maCu) {
        this.maCu = maCu;
    }

    
}
