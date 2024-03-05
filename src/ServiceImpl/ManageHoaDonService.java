package ServiceImpl;

import DomainModels.HoaDon;
import Repositories.HoaDonRepository;
import Services.IManageHoaDon;
import ViewModels.ManageHoaDon;
import java.util.ArrayList;
import java.util.List;


public class ManageHoaDonService implements IManageHoaDon{

    private HoaDonRepository hdRepo;
    private List<ManageHoaDon> list;

    public ManageHoaDonService() {
        this.hdRepo = new HoaDonRepository();
    }
    
    @Override
    public void insert(ViewModels.ManageHoaDon sp) {
        HoaDon s = new HoaDon(sp.getMaHD(), sp.getMaND(),sp.getMaKH(), sp.getNgayTao(), sp.getTongTien(), sp.getTrangThai());
        this.hdRepo.insert(s);
    }

    @Override
    public List<ManageHoaDon> All() {
        list = new ArrayList<>();
        List<HoaDon> List_CH = this.hdRepo.All();
        for (HoaDon o : List_CH) {
            list.add(new ManageHoaDon(o.getMaHD(), o.getMaND(), o.getMaKH() ,o.getNgayTao(),o.getTongTien(), o.getTrangThai()));
        }
        return list;
    }

    @Override
    public List<ManageHoaDon> AllCho() {
        list = new ArrayList<>();
        List<HoaDon> List_CH = this.hdRepo.AllCho();
        for (HoaDon o : List_CH) {
            list.add(new ManageHoaDon(o.getMaHD()));
        }
        return list;
    }

    @Override
    public List<ManageHoaDon> AllCho1(String ma) {
        list = new ArrayList<>();
        List<HoaDon> List_CH = this.hdRepo.AllCho1(ma);
        for (HoaDon o : List_CH) {
            list.add(new ManageHoaDon(o.getMaHD()));
        }
        return list;
    }

    @Override
    public List<ManageHoaDon> AllHD() {
        list = new ArrayList<>();
        List<HoaDon> List_CH = this.hdRepo.AllHD();
        for (HoaDon o : List_CH) {
            list.add(new ManageHoaDon(o.getMaHD(), o.getMaND(), o.getMaKH() ,o.getNgayTao(),o.getTongTien(), o.getTrangThai()));
        }
        return list;
    }

    @Override
    public void deleteMa(String ma) {
        this.hdRepo.deleteMa(ma);
    }

    @Override
    public void delete() {
        this.hdRepo.delete();
    }

    @Override
    public List<ManageHoaDon> AllMa(String ma) {
        list = new ArrayList<>();
        List<HoaDon> List_CH = this.hdRepo.AllMa(ma);
        for (HoaDon o : List_CH) {
            list.add(new ManageHoaDon(o.getMaHD(), o.getMaND(), o.getMaKH() ,o.getNgayTao(),o.getTongTien(), o.getTrangThai()));
        }
        return list;
    }
    @Override
    public List<ManageHoaDon> TimMaHD(String ma) {
        list = new ArrayList<>();
        List<HoaDon> List_CH = this.hdRepo.TimMaHD(ma);
        for (HoaDon o : List_CH) {
            list.add(new ManageHoaDon(o.getMaHD(), o.getMaND(), o.getMaKH() ,o.getNgayTao(),o.getTongTien(), o.getTrangThai()));
        }
        return list;
    }
    @Override
    public List<ManageHoaDon> AllMa1(String ma) {
        list = new ArrayList<>();
        List<HoaDon> List_CH = this.hdRepo.AllMa(ma);
        for (HoaDon o : List_CH) {
            list.add(new ManageHoaDon(o.getMaHD(), o.getMaND(), o.getMaKH() ,o.getNgayTao(),o.getTongTien(), o.getTrangThai()));
        }
        return list;
    }

    @Override
    public void Ma() {
        this.hdRepo.Ma();
    }
    @Override
    public void updateGia(String maHD,double gia){
        this.hdRepo.updateGia(maHD, gia);
    }
    @Override
    public void update(String maHD,double gia){
        this.hdRepo.update(maHD, gia);
    }
}
