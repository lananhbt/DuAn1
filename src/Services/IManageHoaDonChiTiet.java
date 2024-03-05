
package Services;

import ViewModels.ManageHoaDonChiTiet;
import java.util.List;


public interface IManageHoaDonChiTiet {
    public void insert(ManageHoaDonChiTiet sp);
    public void insertSP(ManageHoaDonChiTiet sp);
    public List<ManageHoaDonChiTiet> All(String ma);
    public List<ManageHoaDonChiTiet> AllMa(String ma);
    public List<ManageHoaDonChiTiet> AllMaSP(String maHD , String maSP);
    public List<ManageHoaDonChiTiet> getAll();
    public void deleteALL();
    public void deleteMaHD(String ma);
    public void deleteMaSP(String ma);
    public void updateSLTH(String maHD, String maSP, int so);
            
}
