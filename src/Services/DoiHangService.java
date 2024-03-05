/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Repositories.HoaDonCTRepository;
import ViewModels.ManageHoaDon;
import ViewModels.ManageHoaDonChiTiet;
import ViewModels.QLHoaDon;
import ViewModels.QLSanPham;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface DoiHangService {
    ArrayList<ManageHoaDonChiTiet> listHDCT();
    ArrayList<ManageHoaDonChiTiet> search(String maHD);
    ArrayList<QLSanPham> listSP();
    ArrayList<QLSanPham> searchSP(String maSP);
    ArrayList<ManageHoaDonChiTiet> searchSP_2(String maSP);
    ArrayList<ManageHoaDon> searchKH(String maHD);
    ArrayList<ManageHoaDon> listHDD(String maHD_2);
    ArrayList<ManageHoaDon> listHD();
    public Boolean them(HoaDonChiTiet hdct);
    public Boolean delete(String maSP);
    public Boolean doiHD(HoaDon hd, String maHD);
    public Boolean doi(HoaDonChiTiet hdct, String maHDCT);
}
