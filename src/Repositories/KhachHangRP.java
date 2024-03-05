/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.KhachHang;
import Utilities.DBConnection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caoxu
 */
public class KhachHangRP {

    public List<KhachHang> select(){
        ArrayList<KhachHang> k = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from KhachHang";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Ma = rs.getString("MaKH");
                String Ten = rs.getString("TenKH");
                String GioiTinh = rs.getString("GioiTinh");
                String DiaChi = rs.getString("DiaChi");
                String NgaySinh = rs.getString("NgaySinh");
                String Sdt = rs.getString("Sdt");
                String NgayDki = rs.getString("NgayDangKy");
                KhachHang kh = new KhachHang();
                kh.setMaKH(Ma);
                kh.setTenKH(Ten);
                kh.setGioiTinh(GioiTinh);
                kh.setDiaChi(DiaChi);
                kh.setNgaySinh(NgaySinh);
                kh.setSdt(Sdt);
                kh.setNgayDki(NgayDki);
                k.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public List<KhachHang> select1(String ma) throws SQLException {
        ArrayList<KhachHang> k = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        String sql = "select * from KhachHang where MaKH =?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, ma);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String Ma = rs.getString("MaKH");
            String Ten = rs.getString("TenKH");
            String GioiTinh = rs.getString("GioiTinh");
            String DiaChi = rs.getString("DiaChi");
            String NgaySinh = rs.getString("NgaySinh");
            String Sdt = rs.getString("Sdt");
            String NgayDki = rs.getString("NgayDangKy");
            KhachHang kh = new KhachHang();
            kh.setMaKH(Ma);
            kh.setTenKH(Ten);
            kh.setGioiTinh(GioiTinh);
            kh.setDiaChi(DiaChi);
            kh.setNgaySinh(NgaySinh);
            kh.setSdt(Sdt);
            kh.setNgayDki(NgayDki);
            k.add(kh);
        }

        return k;
    }

    public List<KhachHang> insert(String Ma, String Ten, String GioiTinh, String DiaChi, String NgaySinh, String SDT, String NgayDki) {
        ArrayList<KhachHang> k = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert KhachHang (MaKH,TenKH,GioiTinh,DiaChi,NgaySinh,SDT,NgayDangKy) values (?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, Ma);
            st.setString(2, Ten);
            st.setString(3, GioiTinh);
            st.setString(4, DiaChi);
            st.setString(5, NgaySinh);
            st.setString(6, SDT);
            st.setString(7, NgayDki);
            st.executeUpdate();
            KhachHang kh = new KhachHang();
            kh.setMaKH(Ma);
            kh.setTenKH(Ten);
            kh.setGioiTinh(GioiTinh);
            kh.setDiaChi(DiaChi);
            kh.setNgaySinh(NgaySinh);
            kh.setSdt(SDT);
            kh.setNgayDki(NgayDki);
            k.add(kh);
        } catch (Exception e) {
            return null;
        }
        return k;
    }

    public List<KhachHang> update(String Ma, String Ten, String GioiTinh, String DiaChi, String NgaySinh, String SDT, String NgayDki) throws SQLException {
        ArrayList<KhachHang> k = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        String sql = "update KhachHang set TenKH=?,GioiTinh=?,DiaChi=?, NgaySinh=?, SDT=?, NgayDangKy=? where MaKH=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(7, Ma);
        st.setString(1, Ten);
        st.setString(2, GioiTinh);
        st.setString(3, DiaChi);
        st.setString(4, NgaySinh);
        st.setString(5, SDT);
        st.setString(6, NgayDki);
        st.executeUpdate();
        KhachHang kh = new KhachHang();
        kh.setMaKH(Ma);
        kh.setTenKH(Ten);
        kh.setGioiTinh(GioiTinh);
        kh.setDiaChi(DiaChi);
        kh.setNgaySinh(NgaySinh);
        kh.setSdt(SDT);
        kh.setNgayDki(NgayDki);
        k.add(kh);
        return k;
    }

    public List<KhachHang> delete(String Ma) throws SQLException {
        ArrayList<KhachHang> k = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        String sql = "delete from KhachHang where MaKH=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, Ma);
        st.executeUpdate();
        KhachHang kh = new KhachHang();
        kh.setMaKH(Ma);
        k.add(kh);
        return k;
    }

    public Boolean themNhanh(KhachHang kh) {
        String query = "INSERT INTO KhachHang(MaKH,TenKH,SDT) VALUES(?,?,?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setObject(1, kh.getMaKH());
            ps.setObject(2, kh.getTenKH());
            ps.setObject(3, kh.getSdt());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<KhachHang> tim(String Sdt) {
        ArrayList<KhachHang> k = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "Select MaKH from KhachHang where SDT=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, Sdt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String MaKH = rs.getString("MaKH");
                KhachHang kh = new KhachHang();
                kh.setMaKH(MaKH);
                k.add(kh);
            }
        } catch (Exception e) {
            return null;
        }
        return k;
    }

}
