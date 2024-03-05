/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import DomainModels.SanPham;
import Utilities.DBConnection;
import ViewModels.ManageHoaDonChiTiet;
import ViewModels.QLSanPham;
import java.lang.constant.Constable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Admin
 */
public class DoiHangRepository {

    public ArrayList<HoaDonChiTiet> search(String maHD) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT MaHDCT,MaSP, SoLuong, DonGia, ThanhTien  FROM  HoaDonChiTiet WHERE MaHD = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                String maHDCT = rs.getString("MaHDCT");
                String maSP = rs.getString("MaSP");
                int soLuong = rs.getInt("SoLuong");
                float giaBan = rs.getFloat("DonGia");
                float thanhTien = rs.getFloat("ThanhTien");
                HoaDonChiTiet ct = new HoaDonChiTiet(maHDCT, maSP, soLuong, giaBan, thanhTien);
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> listHDD(String maHD_2) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT MaHD, MaHD_2, MaND, MaKH FROM HoaDon WHERE maHD_2 = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD_2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                HoaDon hd = new HoaDon(maHD, maHD_2, maKH, maND);
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonChiTiet> searchSP_2(String maSP) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT MaSP, SoLuong, DonGia, ThanhTien FROM HoaDonChiTiet WHERE MaSP = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maSP);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int soLuong = rs.getInt("SoLuong");
                float giaBan = rs.getFloat("DonGia");
                float thanhTien = rs.getFloat("ThanhTien");
                HoaDonChiTiet sp = new HoaDonChiTiet(maSP, soLuong, giaBan, thanhTien);
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonChiTiet> listHDCT() {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT  MaHDCT, MaSP,SoLuong, DonGia, ThanhTien  FROM  HoaDonChiTiet";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet ct = new HoaDonChiTiet();
                ct.setMaHDCT(rs.getString("MaHDCT"));
                ct.setMaSP(rs.getString("MaSP"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setGiaBan(rs.getFloat("DonGia"));
                ct.setThanhTien(rs.getFloat("ThanhTien"));
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<SanPham> listSP() {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT MaSP, TenSP, SoLuong, GiaBan FROM SanPham";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                float giaBan = rs.getFloat("GiaBan");
                SanPham sp = new SanPham(maSP, tenSP, soLuong, giaBan);
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> listHD() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT MaHD, MaND, MaKH, MaHD_2 FROM HoaDon WHERE LEFT(MaHD_2,3) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "HDD");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                String maHD_2 = rs.getString("MaHD_2");
                HoaDon hd = new HoaDon(maHD, maKH, maND, maHD_2);
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
//    public ArrayList<HoaDonChiTiet> listHDD(){
//    
//        }

    public ArrayList<SanPham> searchSP(String maSP) {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT MaSP, TenSP, SoLuong, GiaBan FROM SanPham WHERE MaSP = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maSP);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String tenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                float giaBan = rs.getFloat("GiaBan");
                SanPham sp = new SanPham(maSP, tenSP, soLuong, giaBan);
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> searchKH(String maHD) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT MaKH, MaND FROM HoaDon WHERE MaHD = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString("MaKH");
                String maND = rs.getString("MaND");
                HoaDon hd = new HoaDon(maHD, maKH, maND);
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean them(HoaDonChiTiet hdct) {
        String sql = "INSERT INTO HoaDonChiTiet(MaSP, SoLuong, GiaBan, ThanhTien) VALUES(?,?,?,?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hdct.getMaSP());
            ps.setObject(2, hdct.getSoLuong());
            ps.setObject(3, hdct.getGiaBan());
            ps.setObject(4, hdct.getThanhTien());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean doiHD(HoaDon hd, String maHD) {
        String sql = "UPDATE HoaDon SET MaHD_2 = ? WHERE MaHD = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMaHDcu());
            ps.setObject(2, hd.getMaHD());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(String maSP) {
        String sql = "DELETE HoaDonChiTiet WHERE MaSP = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, maSP);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean doi(HoaDonChiTiet hdct, String maHDCT) {
        String sql = "UPDATE HoaDonChiTiet SET MaSP = ?, SoLuong = ?, DonGia = ?, ThanhTien = ? WHERE MaHDCT = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, hdct.getMaSP());
            ps.setObject(2, hdct.getSoLuong());
            ps.setObject(3, hdct.getGiaBan());
            ps.setObject(4, hdct.getThanhTien());
            ps.setObject(5, hdct.getMaHDCT());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
