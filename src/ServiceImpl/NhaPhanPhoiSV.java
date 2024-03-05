/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;


import DomainModels.NhaPhanPhoi;
import Repositories.NhaPhanPhoiRP;
import Services.NhaPhanPhoiIF;
import ViewModels.QLNPP;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author caoxu
 */
public class NhaPhanPhoiSV implements NhaPhanPhoiIF{
     private final NhaPhanPhoiRP NPPRP;

    public NhaPhanPhoiSV() {
        this.NPPRP = new NhaPhanPhoiRP();
    }

    public List<QLNPP> findALL(){
        List<NhaPhanPhoi> npp = NPPRP.select();
        List<QLNPP> list = new ArrayList<>();
        for (NhaPhanPhoi n : npp) {
            QLNPP ql = new QLNPP(
                    n.getMaNPP(),
                    n.getTenNPP(),
                    n.getDiaChi(),
                    n.getSdt(),
                    n.getMaSP(),
                    n.getTrangThai()
            );
            list.add(ql);
        }
        return list;
    }

    public List<NhaPhanPhoi> insert(String Ma, String Ten, String DiaChi, String SDT, String maSP, int Trangthai) {
        try {
            return NPPRP.insert(Ma, Ten, DiaChi, SDT, maSP, Trangthai);
        } catch (Exception e) {
            return null;
        }
    }

    public List<NhaPhanPhoi> select1(String ma) {
        try {
            return NPPRP.select1(ma);
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public List<NhaPhanPhoi> select2(String ma) {
        try {
            return NPPRP.select2(ma);
        } catch (Exception e) {
            return null;
        }
    }
    public List<NhaPhanPhoi> update(String Ma, String Ten, String DiaChi, String SDT, String maSP, int Trangthai) {
        try {
            return NPPRP.update(Ma,Ten, DiaChi, SDT, maSP, Trangthai);
        } catch (Exception e) {
            return null;
        }
    }

    public List<NhaPhanPhoi> delete(String Ma) {
        try {
            return NPPRP.delete(Ma);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<NhaPhanPhoi> click() {
         try {
            return NPPRP.select();
        } catch (Exception e) {
            return null;
        }
    }
}
