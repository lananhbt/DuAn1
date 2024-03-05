/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModels.NguoiDung;
import Repositories.NguoiDungRepository;
import Services.NguoiDungService;
import ViewModels.NguoiDungViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NguoiDungServiceImpl implements NguoiDungService{

    private NguoiDungRepository nguoiDungRepo = new NguoiDungRepository();
    @Override
    public ArrayList<NguoiDungViewModel> listND() {
        ArrayList<NguoiDung> ds = this.nguoiDungRepo.listND();
        ArrayList<NguoiDungViewModel> listViewModel = new ArrayList<>();
        for (NguoiDung nd : ds) {
            NguoiDungViewModel ndViewModel = new NguoiDungViewModel(
                    nd.getMaND(),
                    nd.getHo(),
                    nd.getTenDem(),
                    nd.getTen(),
                    nd.getGioiTinh(),
                    nd.getNgaySinh(),
                    nd.getDiaChi(),
                    nd.getSdt(), 
                    nd.geteMail(),
                    nd.getChucVu());
            listViewModel.add(ndViewModel);
        }
        return listViewModel;
    }

    @Override
    public Boolean them(NguoiDung nd) {
        nguoiDungRepo.them(nd);
        return true;
    }

    @Override
    public Boolean sua(NguoiDung cv, String maND) {
        nguoiDungRepo.sua(cv, maND);
        return true;
    }

    @Override
    public Boolean delete(String maND) {
        nguoiDungRepo.delete(maND);
        return true;
    }   

    @Override
    public List<NguoiDungViewModel> find(String maND) {
        ArrayList<NguoiDungViewModel> listMa = new ArrayList<>();
        List<NguoiDung> List_CH = this.nguoiDungRepo.find(maND);
        for (NguoiDung nd : List_CH) {
            listMa.add(new NguoiDungViewModel(nd.getMaND(),nd.getHo(),nd.getTenDem(),nd.getTen(),nd.getGioiTinh(),nd.getNgaySinh(),nd.getDiaChi(),nd.getSdt(), nd.geteMail(),nd.getChucVu()));
        }
        return listMa;
    }

    @Override
    public NguoiDung findND(String maND) {
        return nguoiDungRepo.findDN(maND);
    }

}
