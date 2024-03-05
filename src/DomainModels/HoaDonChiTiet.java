package DomainModels;

public class HoaDonChiTiet {
    private String maHDCT;
    private String maHD;
    private String maSP;
    private String tenSP;
    private int soLuong;
    private float giaBan;
    private float thanhTien;
    private String maCu;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHD, String maSP, int soLuong, float giaBan, float thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }

    public HoaDonChiTiet(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public HoaDonChiTiet(String maSP,  int soLuong, float giaBan, float thanhTien) {
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }

    public HoaDonChiTiet(String maHDCT, String maHD, String maSP,  int soLuong, float giaBan, float thanhTien, String maCu) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
        this.maCu = maCu;
    }

    public HoaDonChiTiet(String maHDCT, String maHD, String maSP, int soLuong, float giaBan, float thanhTien) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
    }
    
    
    

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaCu() {
        return maCu;
    }

    public void setMaCu(String maCu) {
        this.maCu = maCu;
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

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }
    
}
