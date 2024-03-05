package Repositories;

import DomainModels.HoaDonChiTiet;
import Utilities.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonCTRepository {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    List<HoaDonChiTiet> listHoaDonChiTiets;

    public void insert(HoaDonChiTiet sp) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO HoaDonChiTiet (MaHDCT, MaHD, MaSP, SoLuong, DonGia, ThanhTien) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sp.getMaHDCT());
            ps.setString(2, sp.getMaHD());
            ps.setString(3, sp.getMaSP());
            ps.setInt(4, sp.getSoLuong());
            ps.setFloat(5, sp.getGiaBan());
            ps.setFloat(6, sp.getThanhTien());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertSP(HoaDonChiTiet sp) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong, DonGia, ThanhTien) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sp.getMaHD());
            ps.setString(2, sp.getMaSP());
            ps.setFloat(3, sp.getSoLuong());
            ps.setFloat(4, sp.getGiaBan());
            ps.setFloat(5, sp.getThanhTien());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<HoaDonChiTiet> AllSP(String ma) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaHDCT, MaSP, SoLuong, DonGia, ThanhTien FROM HoaDonChiTiet WHERE MaHD = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maS = rs.getString("MaHDCT");
                String maSP = rs.getString("MaSP");
                int soLuong = rs.getInt("SoLuong");
                float donGia = rs.getFloat("DonGia");
                float thanhTien = rs.getFloat("ThanhTien");
                HoaDonChiTiet sp = new HoaDonChiTiet(maS, maSP, soLuong, donGia, thanhTien);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonChiTiet> AllMa(String ma) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM HoaDonChiTiet WHERE MaHD = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maS = rs.getString("MaHDCT");
                String maSP = rs.getString("MaSP");
                String maHD = rs.getString("MaHD");
                int soLuong = rs.getInt("SoLuong");
                float donGia = rs.getFloat("DonGia");
                float thanhTien = rs.getFloat("ThanhTien");
                HoaDonChiTiet sp = new HoaDonChiTiet(maS, maHD, maSP, soLuong, donGia, thanhTien);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonChiTiet> AllMaSP(String maHD , String maSP) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM HoaDonChiTiet WHERE MaHD = ? AND MaSP=? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maHD);
            ps.setString(2, maSP);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maS = rs.getString("MaHDCT");
                String maSP1 = rs.getString("MaSP");
                String maHD1 = rs.getString("MaHD");
                int soLuong = rs.getInt("SoLuong");
                float donGia = rs.getFloat("DonGia");
                float thanhTien = rs.getFloat("ThanhTien");
                HoaDonChiTiet sp = new HoaDonChiTiet(maS, maHD1, maSP1, soLuong, donGia, thanhTien);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<HoaDonChiTiet> getAll() {

        listHoaDonChiTiets = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT MaSP, MaHD, MaHDCT, SoLuong, DonGia, ThanhTien  FROM  HoaDonChiTiet ORDER BY MaHD";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                String maSP = rs.getString("MaSP");
                String maHD = rs.getString("MaHD");
                String maHDCT = rs.getString("MaHDCT");
                int soLuong = rs.getInt("SoLuong");
                float donGia = rs.getFloat("DonGia");
                float thanhTien = rs.getFloat("ThanhTien");

                listHoaDonChiTiets.add(new HoaDonChiTiet(maHDCT, maHD, maSP, soLuong, donGia, thanhTien));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listHoaDonChiTiets;
    }

    public List<HoaDonChiTiet> shearchMaSP(String maSP) {

        listHoaDonChiTiets = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT MaSP, MaHD, MaHDCT, SoLuong, DonGia, ThanhTien"
                    + " FROM  HoaDonChiTiet WHERE MaSP = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, maSP);
            rs = ps.executeQuery();
            while (rs.next()) {

                String maSPP = rs.getString("MaSP");
                String maHD = rs.getString("MaHD");
                String maHDCT = rs.getString("MaHDCT");
                int soLuong = rs.getInt("SoLuong");
                float donGia = rs.getFloat("DonGia");
                float thanhTien = rs.getFloat("ThanhTien");

                listHoaDonChiTiets.add(new HoaDonChiTiet(maHDCT, maHD, maSPP, soLuong, donGia, thanhTien));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listHoaDonChiTiets;

    }

    public void updateSLTH(String maHD, String maSP, int so) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE HoaDonChiTiet SET SoLuong = SoLuong - ?  WHERE MaSP = ? AND MaHD = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, so);
            ps.setString(2, maSP);
            ps.setString(3, maHD);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String maSP) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM HoaDonChiTiet  WHERE MaSP=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maSP);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteALL() {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM HoaDonChiTiet";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMaHD(String ma) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM HoaDonChiTiet WHERE MaHD =? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ma);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
