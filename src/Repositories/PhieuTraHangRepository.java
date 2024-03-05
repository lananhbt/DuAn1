package Repositories;

import DomainModels.PhieuTraHang;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PhieuTraHangRepository {

    public List<PhieuTraHang> All() {
        ArrayList<PhieuTraHang> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM PhieuTraHang";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maHD = rs.getString("MaHD");
                String maKH = rs.getString("MaKH");
                String maND = rs.getString("MaND");
                String maPTH = rs.getString("MaPTH");
                Date NgayTra = rs.getDate("NgayTra");
                double tien = rs.getDouble("TienTraLaiKhach");
                String loi = rs.getString("LyDoTra");
                PhieuTraHang p = new PhieuTraHang(maHD, maKH, maND, maPTH, NgayTra, tien, loi);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PhieuTraHang> Tim(String ma) {
        ArrayList<PhieuTraHang> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM PhieuTraHang WHERE MaPTH = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maPTH = rs.getString("MaPTH");
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                Date ngTao = rs.getDate("NgayTra");
                float tongTien = rs.getFloat("TienTraLaiKhach");
                String lyDo = rs.getString("LyDoTra");
                PhieuTraHang sp = new PhieuTraHang(maHD, maKH, maND, maPTH, ngTao, tongTien, lyDo);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(PhieuTraHang p) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO PhieuTraHang (MaHD, MaKH,MaND, MaPTH, NgayTra, TienTraLaiKhach, LyDoTra) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, p.getMaHD());
            ps.setString(2, p.getMaKH());
            ps.setString(3, p.getMaND());
            ps.setString(4, p.getMaPTH());
            java.sql.Date date = new java.sql.Date(p.getNgayTra().getTime());
            ps.setDate(5, date);
            ps.setDouble(6, p.getTienTraLaiKhach());
            ps.setString(7, p.getLyDoTra());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PhieuTraHang> TimMaHD(String ma) {
        ArrayList<PhieuTraHang> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM PhieuTraHang WHERE MaHD = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maPTH = rs.getString("MaPTH");
                String maHD = rs.getString("MaHD");
                String maND = rs.getString("MaND");
                String maKH = rs.getString("MaKH");
                Date ngTao = rs.getDate("NgayTra");
                float tongTien = rs.getFloat("TienTraLaiKhach");
                String lyDo = rs.getString("LyDoTra");
                PhieuTraHang sp = new PhieuTraHang(maHD, maKH, maND, maPTH, ngTao, tongTien, lyDo);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void update(String ma, PhieuTraHang p) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE  PhieuTraHang SET MaHD=?, MaKH=?,MaND=?, NgayTra=?, TienTraLaiKhach=?, LyDoTra=? WHERE MaPTH=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, p.getMaHD());
            ps.setString(2, p.getMaKH());
            ps.setString(3, p.getMaND());
            ps.setDate(4, (Date) p.getNgayTra());
            ps.setDouble(5, p.getTienTraLaiKhach());
            ps.setString(6, p.getLyDoTra());
            ps.setString(7, ma);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String ma) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM PhieuTraHang  WHERE MaPTH=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
