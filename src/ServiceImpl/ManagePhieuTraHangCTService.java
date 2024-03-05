package ServiceImpl;

import DomainModels.PhieuTraHangCT;
import Repositories.PhieuTraHangCTRepository;
import Services.IManagePhieuTraHangCTService;
import ViewModels.ManagePhieuTraHangCT;
import java.util.ArrayList;
import java.util.List;


public class ManagePhieuTraHangCTService implements IManagePhieuTraHangCTService{

    private PhieuTraHangCTRepository PhieuTraHangCTRepo;
    private List<ManagePhieuTraHangCT> list;

    public ManagePhieuTraHangCTService() {
        this.PhieuTraHangCTRepo = new PhieuTraHangCTRepository();
    }
    @Override
    public List<ManagePhieuTraHangCT> ALL() {
        list = new ArrayList<>();
        List<PhieuTraHangCT> List_CH = this.PhieuTraHangCTRepo.All();
        for (PhieuTraHangCT o : List_CH) {
            list.add(new ManagePhieuTraHangCT(o.getMaPTH(), o.getMaSP(), o.getSoLuong(), o.getDonGia(), o.getThanhTien()));
        }
        return list;
    }

    @Override
    public List<ManagePhieuTraHangCT> ALLP(String ma) {
        list = new ArrayList<>();
        List<PhieuTraHangCT> List_CH = this.PhieuTraHangCTRepo.AllP(ma);
        for (PhieuTraHangCT o : List_CH) {
            list.add(new ManagePhieuTraHangCT(o.getMaPTH(), o.getMaSP(), o.getSoLuong(), o.getDonGia(), o.getThanhTien()));
        }
        return list;
    }
    
    @Override
    public void insert(ManagePhieuTraHangCT p) {
        PhieuTraHangCT s = new PhieuTraHangCT(p.getMaPTH(), p.getMaSP(), p.getSoLuong(), p.getDonGia(), p.getThanhTien());
        this.PhieuTraHangCTRepo.insert(s);
    }

    @Override
    public void update(String ma, ManagePhieuTraHangCT p) {
        PhieuTraHangCT s = new PhieuTraHangCT(p.getMaPTH(), p.getMaSP(), p.getSoLuong(), p.getDonGia(), p.getThanhTien());
        this.PhieuTraHangCTRepo.update(ma, s);
    }

    @Override
    public void delete(String ma) {
        this.PhieuTraHangCTRepo.delete(ma);
    }

    @Override
    public List<ManagePhieuTraHangCT> TimSP(String ma, String maS) {
        list = new ArrayList<>();
        List<PhieuTraHangCT> List_CH = this.PhieuTraHangCTRepo.TimSP(ma, maS);
        for (PhieuTraHangCT o : List_CH) {
            list.add(new ManagePhieuTraHangCT(o.getMaSP(), o.getSoLuong(), o.getDonGia(), o.getThanhTien()));
        }
        return list;
    }
    
}
