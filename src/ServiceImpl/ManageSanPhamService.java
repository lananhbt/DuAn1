package ServiceImpl;

import DomainModels.SanPham;
import Repositories.ISanPhamRepository;
import Repositories.SanPhamRepository;
import Services.IManageSanPhamService;
import ViewModels.QLSanPham;
import java.util.ArrayList;
import java.util.List;

public class ManageSanPhamService implements IManageSanPhamService {

    private ISanPhamRepository SanPhamRepo;
    private List<QLSanPham> list;

    public ManageSanPhamService() {
        this.SanPhamRepo = new SanPhamRepository();
    }

    @Override
    public List<QLSanPham> ALL() {
        list = new ArrayList<>();
        List<SanPham> List_CH = this.SanPhamRepo.All();
        for (SanPham o : List_CH) {
            list.add(new QLSanPham(o.getMaSP(), o.getMaLSP(), o.getTenSP(), o.getSoLuong(), o.getGiaNhap(), o.getGiaBan(), o.getHanSuDung(), o.getMaQR()));
        }
        return list;
    }

    @Override
    public void insert(QLSanPham sp) {
        SanPham s = new SanPham(sp.getMaSP(), sp.getMaLSP(), sp.getTenSP(), sp.getSoLuong(), sp.getGiaNhap(), sp.getGiaBan(), sp.getHanSuDung());
        this.SanPhamRepo.insert(s);
    }

    @Override
    public void update(String maSP, QLSanPham sp) {
        SanPham s = new SanPham(sp.getMaSP(), sp.getMaLSP(), sp.getTenSP(), sp.getSoLuong(), sp.getGiaNhap(), sp.getGiaBan(), sp.getHanSuDung(), sp.getMaQR());
        this.SanPhamRepo.update(maSP, s);
    }

    @Override
    public void delete(String maSP) {
        this.SanPhamRepo.delete(maSP);
    }

    @Override
    public void updateSL(String maSP) {
        this.SanPhamRepo.updateSL(maSP);
    }
    @Override
    public List<QLSanPham> getByCode(String code) {                
        list = new ArrayList<>();
        List<SanPham> List_CH = SanPhamRepo.findByCode(code);
        for (SanPham o : List_CH) {
            list.add(new QLSanPham(o.getMaSP(), o.getMaLSP(), o.getTenSP(), 
                    o.getSoLuong(), o.getGiaNhap(), o.getGiaBan(), o.getHanSuDung()));
        }
        return list;
    }

    @Override
    public void updateSLGH(int soLg, String maSP) {
        this.SanPhamRepo.updateSLGH(soLg, maSP);
    }

    @Override
    public void updateSLTH(int soLg, String maSP) {
        this.SanPhamRepo.updateSLTH(soLg, maSP);
    }
}
