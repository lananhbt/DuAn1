package Services;

import ViewModels.ManagePhieuTraHangCT;
import java.util.List;


public interface IManagePhieuTraHangCTService {
    public List<ManagePhieuTraHangCT> ALL();
    public void insert(ManagePhieuTraHangCT p);
    public void update(String ma, ManagePhieuTraHangCT p);
    public List<ManagePhieuTraHangCT> ALLP(String ma);
    public List<ManagePhieuTraHangCT> TimSP(String ma, String maS);
    public void delete(String ma);
}
