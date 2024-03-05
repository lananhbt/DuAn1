package DomainModels;

import java.util.Date;


public class PhieuTraHang {
    private String maHD;
    private String maKH;
    private String  maND ;
    private String  maPTH ;
    private Date  ngayTra;
    private double tienTraLaiKhach ;
    private String  lyDoTra ;

    public PhieuTraHang() {
    }

    public PhieuTraHang(String maHD, String maKH, String maND, String maPTH, Date ngayTra, double tienTraLaiKhach, String lyDoTra) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maND = maND;
        this.maPTH = maPTH;
        this.ngayTra = ngayTra;
        this.tienTraLaiKhach = tienTraLaiKhach;
        this.lyDoTra = lyDoTra;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getMaPTH() {
        return maPTH;
    }

    public void setMaPTH(String maPTH) {
        this.maPTH = maPTH;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public double getTienTraLaiKhach() {
        return tienTraLaiKhach;
    }

    public void setTienTraLaiKhach(double tienTraLaiKhach) {
        this.tienTraLaiKhach = tienTraLaiKhach;
    }

    public String getLyDoTra() {
        return lyDoTra;
    }

    public void setLyDoTra(String lyDoTra) {
        this.lyDoTra = lyDoTra;
    }

    
    
    
}
