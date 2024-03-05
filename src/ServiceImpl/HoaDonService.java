/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModels.HoaDon;
import DomainModels.KhachHang;
import Repositories.HoaDonRepository;
import Services.HoaDonIF;
import ViewModels.QLHoaDon;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caoxu
 */
public class HoaDonService implements HoaDonIF {

    private final HoaDonRepository hdRP;

    public HoaDonService() {
        this.hdRP = new HoaDonRepository();
    }

    @Override
    public List<HoaDon> insert(String MaHD, String MaND, String MaKH, String NgayTao,float TongTien, int TrangThai) {
//        try {
//            return hdRP.insert(MaHD, MaND, MaKH, NgayTao, TongTien, TrangThai);
//        } catch (SQLException ex) {
            return null;
//        }
    }

    @Override
    public List<HoaDon> tim(String Sdt) {
//        try {
//            return hdRP.tim(Sdt);
//        } catch (SQLException ex) {
            return null;
//        }
    }
}
