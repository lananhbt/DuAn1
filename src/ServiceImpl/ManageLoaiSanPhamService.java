package ServiceImpl;

import DomainModels.LoaiSanPham;
import Repositories.ILoaiSanPhamRepository;
import Repositories.LoaiSanPhamRepository;
import Services.IManageLoaiSanPhamService;
import ViewModels.QLLoaiSanPham;
import java.util.ArrayList;
import java.util.List;

public class ManageLoaiSanPhamService implements IManageLoaiSanPhamService {

    private ILoaiSanPhamRepository LoaiSanPhamRepo;
    private List<QLLoaiSanPham> list;

    public ManageLoaiSanPhamService() {
        this.LoaiSanPhamRepo = new LoaiSanPhamRepository();
    }

    @Override
    public List<QLLoaiSanPham> ALL() {
        list = new ArrayList<>();
        List<LoaiSanPham> List_CH = this.LoaiSanPhamRepo.All();
        for (LoaiSanPham o : List_CH) {
            list.add(new QLLoaiSanPham(o.getMaLSP(), o.getTenLSP(), o.getMoTa()));
        }
        return list;
    }

    @Override
    public void insert(QLLoaiSanPham lsp) {
        LoaiSanPham s = new LoaiSanPham(lsp.getMaLSP(), lsp.getTenLSP(), lsp.getMoTa());
        this.LoaiSanPhamRepo.insert(s);
    }

    @Override
    public void update(String id, QLLoaiSanPham lsp) {
        LoaiSanPham s = new LoaiSanPham(lsp.getMaLSP(), lsp.getTenLSP(), lsp.getMoTa());
        this.LoaiSanPhamRepo.update(id, s);
    }

    @Override
    public void delete(String id) {
        this.LoaiSanPhamRepo.delete(id);
    }

}
