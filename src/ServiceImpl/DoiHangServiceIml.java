/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import DomainModels.SanPham;
import Repositories.DoiHangRepository;
import Services.DoiHangService;
import ViewModels.ManageHoaDon;
import ViewModels.ManageHoaDonChiTiet;
import ViewModels.QLHoaDon;
import ViewModels.QLSanPham;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DoiHangServiceIml implements DoiHangService {

    private DoiHangRepository doiHangRepo = new DoiHangRepository();

    @Override
    public ArrayList<ManageHoaDonChiTiet> listHDCT() {
        ArrayList<HoaDonChiTiet> ds = doiHangRepo.listHDCT();
        ArrayList<ManageHoaDonChiTiet> listView = new ArrayList<>();
        for (HoaDonChiTiet hdct : ds) {
            ManageHoaDonChiTiet hdctView = new ManageHoaDonChiTiet(
                    hdct.getMaHDCT(),
                    hdct.getMaSP(),
                    hdct.getSoLuong(),
                    hdct.getGiaBan(),
                    hdct.getThanhTien());
            listView.add(hdctView);
        }
        return listView;
    }

    @Override
    public ArrayList<ManageHoaDon> listHDD(String maHD_2) {
        ArrayList<ManageHoaDon> list = new ArrayList<>();
        ArrayList<HoaDon> list_HDD = doiHangRepo.listHDD(maHD_2);
        for(HoaDon hdd : list_HDD){
            list.add(new ManageHoaDon(hdd.getMaHD(), hdd.getMaHDcu(), hdd.getMaKH(), hdd.getMaND()));
        }
        return list;
    }
    
    @Override
    public ArrayList<ManageHoaDonChiTiet> searchSP_2(String maSP) {
        ArrayList<ManageHoaDonChiTiet> list = new ArrayList<>();
        ArrayList<HoaDonChiTiet> list_SP = doiHangRepo.searchSP_2(maSP);
        for (HoaDonChiTiet sp : list_SP) {
            list.add(new ManageHoaDonChiTiet(sp.getMaSP(),sp.getSoLuong(), sp.getGiaBan(), sp.getThanhTien()));
        }
        return list;
    }

    @Override
    public ArrayList<ManageHoaDonChiTiet> search(String maHD) {
        ArrayList<ManageHoaDonChiTiet> list = new ArrayList<>();
        ArrayList<HoaDonChiTiet> list_HD = doiHangRepo.search(maHD);
        for (HoaDonChiTiet sp : list_HD) {
            list.add(new ManageHoaDonChiTiet(sp.getMaHDCT(), sp.getMaHD(), sp.getMaSP(), sp.getSoLuong(),
                    sp.getGiaBan(), sp.getThanhTien()));
        }
        return list;
    }

    @Override
    public ArrayList<QLSanPham> listSP() {
        ArrayList<SanPham> ds = doiHangRepo.listSP();
        ArrayList<QLSanPham> listView = new ArrayList<>();
        for (SanPham sp : ds) {
            QLSanPham spView = new QLSanPham(sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getGiaBan());
            listView.add(spView);
        }
        return listView;
    }

    @Override
    public ArrayList<QLSanPham> searchSP(String maSP) {
        ArrayList<QLSanPham> list = new ArrayList<>();
        ArrayList<SanPham> list_SP = doiHangRepo.searchSP(maSP);
        for (SanPham sp : list_SP) {
            list.add(new QLSanPham(sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getGiaBan()));
        }
        return list;
    }

    @Override
    public ArrayList<ManageHoaDon> searchKH(String maHD) {
        ArrayList<ManageHoaDon> list = new ArrayList<>();
        ArrayList<HoaDon> list_KH = doiHangRepo.searchKH(maHD);
        for (HoaDon hd : list_KH) {
            list.add(new ManageHoaDon(hd.getMaHD(), hd.getMaKH(), hd.getMaND()));
        }
        return list;
    }

    @Override
    public ArrayList<ManageHoaDon> listHD() {
        ArrayList<ManageHoaDon> list = new ArrayList<>();
        ArrayList<HoaDon> list_HD = doiHangRepo.listHD();
        for (HoaDon hd : list_HD) {
            list.add(new ManageHoaDon(hd.getMaHD(), hd.getMaND(), hd.getMaKH(), hd.getMaHDcu()));
        }
        return list;
    }

    @Override
    public Boolean them(HoaDonChiTiet hdct) {
        doiHangRepo.them(hdct);
        return true;
    }

    @Override
    public Boolean delete(String maSP) {
        doiHangRepo.delete(maSP);
        return true;
    }

    @Override
    public Boolean doiHD(HoaDon hd, String maHD) {
        doiHangRepo.doiHD(hd, maHD);
        return true;
    }

    @Override
    public Boolean doi(HoaDonChiTiet hdct, String maHDCT) {
        doiHangRepo.doi(hdct, maHDCT);
        return true;
    }

}
