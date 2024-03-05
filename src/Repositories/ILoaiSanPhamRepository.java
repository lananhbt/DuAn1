package Repositories;

import DomainModels.LoaiSanPham;
import java.util.List;

public interface ILoaiSanPhamRepository {

    public List<LoaiSanPham> All();

    public void insert(LoaiSanPham lsp);

    public void update(String maLSP, LoaiSanPham lsp);

    public void delete(String maLSP);
}
