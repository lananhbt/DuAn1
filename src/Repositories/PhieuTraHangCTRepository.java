package Repositories;

import DomainModels.PhieuTraHangCT;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PhieuTraHangCTRepository {

    public List<PhieuTraHangCT> All() {
        ArrayList<PhieuTraHangCT> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM PhieuTraHangChiTiet";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maPth = rs.getString("MaPTH");
                String maSP = rs.getString("MaSP");
                int soLuong = rs.getInt("SoLuong");
                double gia = rs.getDouble("DonGia");
                double tien = rs.getDouble("ThanhTien");
                PhieuTraHangCT p = new PhieuTraHangCT(maPth, maSP, soLuong, gia, tien);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PhieuTraHangCT> AllP(String ma) {
        ArrayList<PhieuTraHangCT> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM PhieuTraHangChiTiet WHERE MaPTH = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maPth = rs.getString("MaPTH");
                String maSP = rs.getString("MaSP");
                int soLuong = rs.getInt("SoLuong");
                double gia = rs.getDouble("DonGia");
                double tien = rs.getDouble("ThanhTien");
                PhieuTraHangCT p = new PhieuTraHangCT(maPth, maSP, soLuong, gia, tien);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PhieuTraHangCT> TimSP(String ma, String maS) {
        ArrayList<PhieuTraHangCT> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM PhieuTraHangChiTiet WHERE MaPTH = ? AND MaSP = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.setString(2, maS);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maSP = rs.getString("MaSP");
                int soLuong = rs.getInt("SoLuong");
                double gia = rs.getDouble("DonGia");
                double tien = rs.getDouble("ThanhTien");
                PhieuTraHangCT p = new PhieuTraHangCT(maSP, soLuong, gia, tien);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(PhieuTraHangCT p) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO PhieuTraHangChiTiet (MaPTH, MaSP, SoLuong, DonGia, ThanhTien) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, p.getMaPTH());
            ps.setString(2, p.getMaSP());
            ps.setInt(3, p.getSoLuong());
            ps.setDouble(4, p.getDonGia());
            ps.setDouble(5, p.getThanhTien());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String ma, PhieuTraHangCT p) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE PhieuTraHangChiTiet SET MaSP=?, SoLuong=?, DonGia=?, ThanhTien=? WHERE MaPTH=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, p.getMaSP());
            ps.setInt(2, p.getSoLuong());
            ps.setDouble(3, p.getDonGia());
            ps.setDouble(4, p.getThanhTien());
            ps.setString(5, ma);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String ma) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM PhieuTraHangChiTiet  WHERE MaPTH=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
