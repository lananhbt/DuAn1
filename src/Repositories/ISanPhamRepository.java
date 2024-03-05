package Repositories;

import DomainModels.SanPham;
import java.util.List;

public interface ISanPhamRepository {

    public List<SanPham> All();

    public void insert(SanPham sp);

    public void update(String maSP, SanPham sp);

    public void updateSL(String maSP);
    
    public void updateSLGH(int soLg, String maSP);
    
    public void updateSLTH(int soLg, String maSP);

    public void delete(String maSP);

    public List<SanPham> findByCode(String maSP);
}
