package Services;

import ViewModels.ManageSanPhamLoi;
import java.util.List;


public interface IManageSanPhamLoiService {
    public List<ManageSanPhamLoi> ALL();
    public List<ManageSanPhamLoi> Loc(String ma);
    public void insert(ManageSanPhamLoi sp);
    public void update(String ma, ManageSanPhamLoi sp);
    public void delete(String ma);
}
