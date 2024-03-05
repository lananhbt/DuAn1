/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDon;
import DomainModels.KhachHang;
import Utilities.DBConnection;
import ViewModels.QLHoaDon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caoxu
 */
public class HoaDonRepository {

//    public List<HoaDon> insert(String MaHD, String MaND, String MaKH, String NgayTao, float TongTien, int TrangThai) throws SQLException {
//        ArrayList<HoaDon> k = new ArrayList<>();
//        try {
//            Connection con = DBConnection.getConnection();
//            String sql = "insert HoaDon (MaKH,MaND,MaHD,NgayTao,TongTien,TrangThai) values (?,?,?,?,?,?)";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, MaHD);
//            st.setString(2, MaND);
//            st.setString(3, MaKH);
//            st.setString(4, NgayTao);
//            st.setFloat(5, TongTien);
//            st.setInt(6, TrangThai);
//            HoaDon hd = new HoaDon();
//            hd.setMaHD(MaHD);
//            hd.setMaND(MaND);
//            hd.setMaKH(MaKH);
//            hd.setNgayTao(NgayTao);
//            hd.setTongTien(TongTien);
//            hd.setTrangThai(TrangThai);
//            k.add(hd);
//        } catch (Exception e) {
//            return null;
//        }
//        return k;
//    }
//    public List<HoaDon> tim(String Sdt) throws SQLException {
//        ArrayList<HoaDon> k = new ArrayList<>();
//        try {
//            Connection con = DBConnection.getConnection();
//            String sql = "Select HoaDon.MaHD,HoaDon.MaND,HoaDon.NgayTao,KhachHang.MaKH,HoaDon.TrangThai,HoaDon.TongTien from KhachHang \n"
//                    + "					join HoaDon on HoaDon.MaKH=KhachHang.MaKH where SDT=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, Sdt);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                String MaHD = rs.getString("MaHD");
//                String MaND = rs.getString("MaND");
//                String MaKH = rs.getString("MaKH");
//                String NgayTao = rs.getString("NgayTao");
//                float TongTien = rs.getFloat("TongTien");
//                int TrangThai = rs.getInt("TrangThai");
//                HoaDon hd = new HoaDon();
//                hd.setMaHD(MaHD);
//                hd.setMaND(MaND);
//                hd.setMaKH(MaKH);
//                hd.setNgayTao(NgayTao);
//                hd.setTongTien(TongTien);
//                hd.setTrangThai(TrangThai);
//                k.add(hd);
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return k;
//    }
    public void insert(HoaDon sp) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO HoaDon (MaHD, MaND, MaKH, NgayTao, TongTien, TrangThai) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sp.getMaHD());
            ps.setString(2, sp.getMaND());
            ps.setString(3, sp.getMaKH());
            java.sql.Date date = new java.sql.Date(sp.getNgayTao().getTime());
            ps.setDate(4, date);
            ps.setFloat(5, sp.getTongTien());
            ps.setInt(6, sp.getTrangThai());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HoaDon> All() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaHD, MaND, MaKH, NgayTao, TongTien, TrangThai FROM HoaDon WHERE TrangThai = 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                Date ngTao = rs.getDate("NgayTao");
                float tongTien = rs.getFloat("TongTien");
                int tt = rs.getInt("TrangThai");
                HoaDon sp = new HoaDon(maHD, maND, maKH, ngTao, tongTien, tt);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDon> AllHD() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaHD, MaND, MaKH, NgayTao, TongTien, TrangThai FROM HoaDon";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                Date ngTao = rs.getDate("NgayTao");
                float tongTien = rs.getFloat("TongTien");
                int tt = rs.getInt("TrangThai");
                HoaDon sp = new HoaDon(maHD, maND, maKH, ngTao, tongTien, tt);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDon> AllMa(String ma) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaHD, MaND, MaKH, NgayTao, TongTien, TrangThai FROM HoaDon WHERE MaHD = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                Date ngTao = rs.getDate("NgayTao");
                float tongTien = rs.getFloat("TongTien");
                int tt = rs.getInt("TrangThai");
                HoaDon sp = new HoaDon(maHD, maND, maKH, ngTao, tongTien, tt);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

     public List<HoaDon> TimMaHD(String ma) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaHD, MaND, MaKH, NgayTao, TongTien, TrangThai FROM HoaDon WHERE MaHD_2 = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                Date ngTao = rs.getDate("NgayTao");
                float tongTien = rs.getFloat("TongTien");
                int tt = rs.getInt("TrangThai");
                HoaDon sp = new HoaDon(maHD, maND, maKH, ngTao, tongTien, tt);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<HoaDon> AllMa1(String ma) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaHD, MaND, MaKH, NgayTao, TongTien, TrangThai FROM HoaDon WHERE MaHD = ? AND TrangThai = 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                Date ngTao = rs.getDate("NgayTao");
                float tongTien = rs.getFloat("TongTien");
                int tt = rs.getInt("TrangThai");
                HoaDon sp = new HoaDon(maHD, maND, maKH, ngTao, tongTien, tt);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<HoaDon> AllCho() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaHD FROM HoaDon WHERE TrangThai =0 ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maHD = rs.getString("MaHD");
                HoaDon sp = new HoaDon(maHD);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDon> AllCho1(String ma) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaHD FROM HoaDon WHERE MaHD = ? AND TrangThai =0 ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maHD = rs.getString("MaHD");
                HoaDon sp = new HoaDon(maHD);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void Ma() {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "select TOP 1 SUBSTRING(MaHD, 3, LEN(MaHD)-2) as 's' from HoaDon\n" +
                            "order by s DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM HoaDon";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMa(String ma) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM HoaDon WHERE MaHD =? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateGia(String maHD,double gia) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE HoaDon SET TongTien = TongTien - ?   WHERE MaHD = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, gia);
            ps.setString(2, maHD);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(String maHD, double gia){
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE HoaDon SET TongTien=?, TrangThai = 1   WHERE MaHD = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, gia);
            ps.setString(2, maHD);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
