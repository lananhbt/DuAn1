/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.NguoiDung;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NguoiDungRepository {

    public ArrayList<NguoiDung> listND() {
        ArrayList<NguoiDung> list = new ArrayList<>();
        String query = "SELECT*FROM NguoiDung";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setMaND(rs.getString("MaND"));
                nd.setHo(rs.getString("ho"));
                nd.setTenDem(rs.getString("TenDem"));
                nd.setTen(rs.getString("Ten"));
                nd.setGioiTinh(rs.getString("GioiTinh"));
                nd.setNgaySinh(rs.getString("NgaySinh"));
                nd.setDiaChi(rs.getString("DiaChi"));
                nd.setSdt(rs.getString("SDT"));
                nd.seteMail(rs.getString("Email"));
                nd.setChucVu(rs.getString("ChucVu"));
                list.add(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean them(NguoiDung nguoiDung) {
        String query = "INSERT INTO NguoiDung(MaND, Ho, TenDem, Ten, GioiTinh, NgaySinh, DiaChi, "
                + "SDT, Email, ChucVu) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setObject(1, nguoiDung.getMaND());
            ps.setObject(2, nguoiDung.getHo());
            ps.setObject(3, nguoiDung.getTenDem());
            ps.setObject(4, nguoiDung.getTen());
            ps.setObject(5, nguoiDung.getGioiTinh());
            ps.setObject(6, nguoiDung.getNgaySinh());
            ps.setObject(7, nguoiDung.getDiaChi());
            ps.setObject(8, nguoiDung.getSdt());
            ps.setObject(9, nguoiDung.geteMail());
            ps.setObject(10, nguoiDung.getChucVu());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean sua(NguoiDung nguoiDung, String maND) {
        String query = "UPDATE NguoiDung SET Ho =?, TenDem=?, Ten=?, GioiTinh=?, NgaySinh=?, DiaChi=?, "
                + "SDT=?, Email=?, ChucVu=? WHERE MaND =?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setObject(10, maND);
            ps.setObject(1, nguoiDung.getHo());
            ps.setObject(2, nguoiDung.getTenDem());
            ps.setObject(3, nguoiDung.getTen());
            ps.setObject(4, nguoiDung.getGioiTinh());
            ps.setObject(5, nguoiDung.getNgaySinh());
            ps.setObject(6, nguoiDung.getDiaChi());
            ps.setObject(7, nguoiDung.getSdt());
            ps.setObject(8, nguoiDung.geteMail());
            ps.setObject(9, nguoiDung.getChucVu());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(String maND) {
        String query = "DELETE NguoiDung WHERE MaND = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setObject(1, maND);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public List<NguoiDung> find(String maND) {
        ArrayList<NguoiDung> list = new ArrayList<>();
        String query = "SELECT*FROM NguoiDung WHERE MaND = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareCall(query);
            ps.setString(1, maND);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setMaND(rs.getString("MaND"));
                nd.setHo(rs.getString("ho"));
                nd.setTenDem(rs.getString("TenDem"));
                nd.setTen(rs.getString("Ten"));
                nd.setGioiTinh(rs.getString("GioiTinh"));
                nd.setNgaySinh(rs.getString("NgaySinh"));
                nd.setDiaChi(rs.getString("DiaChi"));
                nd.setSdt(rs.getString("SDT"));
                nd.seteMail(rs.getString("Email"));
                nd.setChucVu(rs.getString("ChucVu"));
                list.add(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public NguoiDung findDN(String maND) {
        ArrayList<NguoiDung> list = new ArrayList<>();
        String query = "SELECT*FROM NguoiDung WHERE MaND = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareCall(query);
            ps.setString(1, maND);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setMaND(rs.getString("MaND"));
                nd.setHo(rs.getString("ho"));
                nd.setTenDem(rs.getString("TenDem"));
                nd.setTen(rs.getString("Ten"));
                nd.setGioiTinh(rs.getString("GioiTinh"));
                nd.setNgaySinh(rs.getString("NgaySinh"));
                nd.setDiaChi(rs.getString("DiaChi"));
                nd.setSdt(rs.getString("SDT"));
                nd.seteMail(rs.getString("Email"));
                nd.setPass(rs.getString("Pass"));
                nd.setChucVu(rs.getString("ChucVu"));
                list.add(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.size() > 0 ? list.get(0) : null;
    }
}
