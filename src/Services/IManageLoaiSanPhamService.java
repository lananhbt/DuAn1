package Services;

import ViewModels.QLLoaiSanPham;
import java.util.List;

public interface IManageLoaiSanPhamService {

    public List<QLLoaiSanPham> ALL();

    public void insert(QLLoaiSanPham lsp);

    public void update(String maLSP, QLLoaiSanPham lsp);

    public void delete(String maLSP);
}
