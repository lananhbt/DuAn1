package Services;

import ViewModels.ManagePhieuTraHang;
import java.util.List;


public interface IManagePhieuTraHangService {
    public List<ManagePhieuTraHang> ALL();
    public List<ManagePhieuTraHang> Tim(String ma);
    public List<ManagePhieuTraHang> TimHD(String ma);
    public void insert(ManagePhieuTraHang p);
    public void update(String ma, ManagePhieuTraHang p);
    public void delete(String ma);
}
