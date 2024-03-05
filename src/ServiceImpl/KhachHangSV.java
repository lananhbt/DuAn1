/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;


import DomainModels.KhachHang;
import Repositories.KhachHangRP;
import Services.KhachHangIF;
import ViewModels.QLKH;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author caoxu
 */
public class KhachHangSV implements KhachHangIF {

    private final KhachHangRP khRP;

    public KhachHangSV() {
        this.khRP = new KhachHangRP();
    }

    public List<QLKH> findALL(){
        List<KhachHang> kh = khRP.select();
        List<QLKH> list = new ArrayList<>();
        for (KhachHang khachHang : kh) {
            QLKH ql = new QLKH(
                    khachHang.getMaKH(),
                    khachHang.getTenKH(),
                    khachHang.getGioiTinh(),
                    khachHang.getDiaChi(),
                    khachHang.getNgaySinh(),
                    khachHang.getSdt(),
                    khachHang.getNgayDki()
            );
            list.add(ql);
        }
        return list;
    }

    public List<KhachHang> insert(String Ma, String Ten, String GioiTinh, String DiaChi, String NgaySinh, String SDT, String NgayDki) {
        try {        
            return khRP.insert(Ma, Ten, GioiTinh, DiaChi, NgaySinh, SDT, NgayDki);
        } catch (Exception e) {
            return null;
        }
    }

    public List<KhachHang> select1(String ma) {
        try {
          
            return khRP.select1(ma);
        } catch (Exception e) {
            return null;
        }
    }

    public List<KhachHang> update(String Ma, String Ten, String GioiTinh, String DiaChi, String NgaySinh, String SDT, String NgayDki) {
        try {
            return khRP.update(Ma, Ten, GioiTinh, DiaChi, NgaySinh, SDT, NgayDki);
        } catch (Exception e) {
            return null;
        }
    }

    public List<KhachHang> delete(String Ma) {
        try {
            return khRP.delete(Ma);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean themNhanh(KhachHang kh) {
        khRP.themNhanh(kh);
        return true;
    }

    @Override
    public List<KhachHang> tim(String Sdt) {
        return khRP.tim(Sdt);
    }
}
