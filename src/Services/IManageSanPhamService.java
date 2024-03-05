package Services;

import ViewModels.QLSanPham;
import java.util.List;

public interface IManageSanPhamService {

    public List<QLSanPham> ALL();

    public void insert(QLSanPham sp);

    public void update(String maSP, QLSanPham sp);

    public void updateSL(String maSP);
    public void updateSLGH(int soLg, String maSP);
    public void updateSLTH(int soLg, String maSP);

    public void delete(String maSP);

    public List<QLSanPham> getByCode(String code);
}
