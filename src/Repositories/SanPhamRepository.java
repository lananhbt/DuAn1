package Repositories;

import DomainModels.SanPham;
import java.util.List;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.sql.*;

public class SanPhamRepository implements ISanPhamRepository {

    @Override
    public List<SanPham> All() {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "select MaSP, MaLSP, TenSP, SoLuong, GiaNhap, GiaBan, HanSuDung, MaQR from SanPham ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maSP = rs.getString("MaSP");
                String maLSP = rs.getString("MaLSP");
                String tenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                float giaNhap = rs.getFloat("GiaNhap");
                float giaBan = rs.getFloat("GiaBan");
                Date hanSuDung = rs.getDate("HanSuDung");
                String maQR = rs.getString("MaQR");

                SanPham sp = new SanPham(maSP, maLSP, tenSP, soLuong, giaNhap, giaBan, hanSuDung, maQR);

                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(SanPham sp) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO SanPham (MaSP, MaLSP, TenSP, SoLuong, GiaNhap, GiaBan, HanSuDung) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getMaLSP());
            ps.setString(3, sp.getTenSP());
            ps.setInt(4, sp.getSoLuong());
            ps.setFloat(5, sp.getGiaNhap());
            ps.setFloat(6, sp.getGiaBan());
            java.sql.Date date = new java.sql.Date(sp.getHanSuDung().getTime());
            ps.setDate(7, date);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String maSP, SanPham sp) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE SanPham SET MaSP=?, MaLSP=?, TenSP=?, SoLuong=?, GiaNhap=?, GiaBan=?, HanSuDung=?, MaQR=?  WHERE MaSP=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getMaLSP());
            ps.setString(3, sp.getTenSP());
            ps.setInt(4, sp.getSoLuong());
            ps.setFloat(5, sp.getGiaNhap());
            ps.setFloat(6, sp.getGiaBan());
            java.sql.Date date = new java.sql.Date(sp.getHanSuDung().getTime());
            ps.setDate(7, date);
            ps.setString(8, sp.getMaQR());
            ps.setString(9, maSP);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateSL(String maSP) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE SanPham SET SoLuong = SoLuong -1  WHERE MaSP=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maSP);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateSLGH(int soLg, String maSP) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE SanPham SET SoLuong = SoLuong - ?  WHERE MaSP=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, soLg);
            ps.setString(2, maSP);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateSLTH(int soLg, String maSP) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE SanPham SET SoLuong = SoLuong + ?  WHERE MaSP=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, soLg);
            ps.setString(2, maSP);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(String maSP) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM SanPham  WHERE MaSP=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maSP);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<SanPham> findByCode(String maSP) {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM SanPham where MaSP = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,maSP);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                
                String maLSP = rs.getString("MaLSP");
                String tenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                float giaNhap = rs.getInt("GiaNhap");
                float giaBan = rs.getFloat("GiaBan");
                Date hanSuDung = rs.getDate("HanSuDung");
                
                SanPham sp = new SanPham(maSP, maLSP, tenSP, soLuong, giaNhap, giaBan, hanSuDung);

                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    
}
