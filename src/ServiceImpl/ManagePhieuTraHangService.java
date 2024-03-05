package ServiceImpl;

import DomainModels.PhieuTraHang;
import Repositories.PhieuTraHangRepository;
import Services.IManagePhieuTraHangService;
import ViewModels.ManagePhieuTraHang;
import java.util.ArrayList;
import java.util.List;


public class ManagePhieuTraHangService implements IManagePhieuTraHangService{

    private PhieuTraHangRepository PhieuTraHangRepo;
    private List<ManagePhieuTraHang> list;

    public ManagePhieuTraHangService() {
        this.PhieuTraHangRepo = new PhieuTraHangRepository();
    }
    @Override
    public List<ManagePhieuTraHang> ALL() {
        list = new ArrayList<>();
        List<PhieuTraHang> List_CH = this.PhieuTraHangRepo.All();
        for (PhieuTraHang o : List_CH) {
            list.add(new ManagePhieuTraHang(o.getMaHD(), o.getMaKH(), o.getMaND(), o.getMaPTH(), o.getNgayTra(), o.getTienTraLaiKhach(), o.getLyDoTra()));
        }
        return list;
    }

    @Override
    public List<ManagePhieuTraHang> Tim(String ma) {
        list = new ArrayList<>();
        List<PhieuTraHang> List_CH = this.PhieuTraHangRepo.Tim(ma);
        for (PhieuTraHang o : List_CH) {
            list.add(new ManagePhieuTraHang(o.getMaHD(), o.getMaKH(), o.getMaND(), o.getMaPTH(), o.getNgayTra(), o.getTienTraLaiKhach(), o.getLyDoTra()));
        }
        return list;
    }
    @Override
    public List<ManagePhieuTraHang> TimHD(String ma) {
        list = new ArrayList<>();
        List<PhieuTraHang> List_CH = this.PhieuTraHangRepo.TimMaHD(ma);
        for (PhieuTraHang o : List_CH) {
            list.add(new ManagePhieuTraHang(o.getMaHD(), o.getMaKH(), o.getMaND(), o.getMaPTH(), o.getNgayTra(), o.getTienTraLaiKhach(), o.getLyDoTra()));
        }
        return list;
    }
    @Override
    public void insert(ManagePhieuTraHang p) {
        PhieuTraHang s = new PhieuTraHang (p.getMaHD(), p.getMaKH(), p.getMaND(), p.getMaPTH(), p.getNgayTra(), p.getTienTraLaiKhach(), p.getLyDoTra());
        this.PhieuTraHangRepo.insert(s);
    }

    @Override
    public void update(String ma, ManagePhieuTraHang p) {
        PhieuTraHang s = new PhieuTraHang (p.getMaHD(), p.getMaKH(), p.getMaND(), p.getMaPTH(), p.getNgayTra(), p.getTienTraLaiKhach(), p.getLyDoTra());
        this.PhieuTraHangRepo.update(ma, s);
    }

    @Override
    public void delete(String ma) {
    this.PhieuTraHangRepo.delete(ma);
    }
    
}
