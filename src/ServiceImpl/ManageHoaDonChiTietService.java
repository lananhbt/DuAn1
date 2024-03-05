package ServiceImpl;

import DomainModels.HoaDonChiTiet;
import Repositories.HoaDonCTRepository;
import Services.IManageHoaDonChiTiet;
import ViewModels.ManageHoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;


public class ManageHoaDonChiTietService implements IManageHoaDonChiTiet{

    private HoaDonCTRepository hdRepo;
        private List<ManageHoaDonChiTiet> list;

    public ManageHoaDonChiTietService() {
        this.hdRepo = new HoaDonCTRepository();
    }
    @Override
    public void insert(ManageHoaDonChiTiet sp) {
        HoaDonChiTiet s = new HoaDonChiTiet(sp.getMaHDCT(), sp.getMaHD(), sp.getMaSP(), sp.getSoLuong(), sp.getGiaBan(), sp.getThanhTien());
        this.hdRepo.insert(s);
    }

    @Override
    public List<ManageHoaDonChiTiet> All(String ma) {
        list = new ArrayList<>();
        List<HoaDonChiTiet> List_CH = this.hdRepo.AllSP(ma);
        for (HoaDonChiTiet o : List_CH) {
            list.add(new ManageHoaDonChiTiet(o.getMaHDCT(),o.getMaSP(),o.getSoLuong(), o.getGiaBan(), o.getThanhTien()));
        }
        return list;
    }

    @Override
    public void insertSP(ManageHoaDonChiTiet sp) {
        HoaDonChiTiet s = new HoaDonChiTiet(sp.getMaHD(), sp.getMaSP(), sp.getSoLuong(), sp.getGiaBan(), sp.getThanhTien());
        this.hdRepo.insertSP(s);
    }

    @Override
    public void deleteALL() {
        this.hdRepo.deleteALL();
    }

    @Override
    public void deleteMaHD(String ma) {
        this.hdRepo.deleteMaHD(ma);
    }

    @Override
    public void deleteMaSP(String ma) {
        this.hdRepo.delete(ma);
    }
    
    @Override
    public void updateSLTH(String maHD, String maSP, int so){
        this.hdRepo.updateSLTH(maHD, maSP, so);
    }
    @Override
    public List<ManageHoaDonChiTiet> AllMa(String ma) {
        list = new ArrayList<>();
        List<HoaDonChiTiet> List_CH = this.hdRepo.AllMa(ma);
        for (HoaDonChiTiet o : List_CH) {
            list.add(new ManageHoaDonChiTiet(o.getMaHDCT(), o.getMaHD(), o.getMaSP(), o.getSoLuong(), o.getGiaBan(),o.getThanhTien()));
        }
        return list;
    }

    @Override
    public List<ManageHoaDonChiTiet> getAll() {
        list = new ArrayList<>();
        List<HoaDonChiTiet> List_CH = this.hdRepo.getAll();
        for (HoaDonChiTiet o : List_CH) {
            list.add(new ManageHoaDonChiTiet(o.getMaHDCT(), o.getMaHD(), o.getMaSP(), o.getSoLuong(), o.getGiaBan(),o.getThanhTien()));
        }
        return list;
    }

    @Override
    public List<ManageHoaDonChiTiet> AllMaSP(String maHD, String maSP) {
        list = new ArrayList<>();
        List<HoaDonChiTiet> List_CH = this.hdRepo.AllMaSP(maHD, maSP);
        for (HoaDonChiTiet o : List_CH) {
            list.add(new ManageHoaDonChiTiet(o.getMaHDCT(), o.getMaHD(), o.getMaSP(), o.getSoLuong(), o.getGiaBan(),o.getThanhTien()));
        }
        return list;
    }
    
}
