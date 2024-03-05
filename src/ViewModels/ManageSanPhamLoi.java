package ViewModels;


public class ManageSanPhamLoi {
    private String maSPL;
    private String maSP;
    private String tenSP;
    private String lyDoLoi;

    public ManageSanPhamLoi() {
    }

    public ManageSanPhamLoi(String maSPL, String maSP, String tenSP, String lyDoLoi) {
        this.maSPL = maSPL;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.lyDoLoi = lyDoLoi;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    

    public ManageSanPhamLoi(String maSPL) {
        this.maSPL = maSPL;
    }

    

    public String getMaSPL() {
        return maSPL;
    }

    public void setMaSPL(String maSPL) {
        this.maSPL = maSPL;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getLyDoLoi() {
        return lyDoLoi;
    }

    public void setLyDoLoi(String lyDoLoi) {
        this.lyDoLoi = lyDoLoi;
    }
    
}
