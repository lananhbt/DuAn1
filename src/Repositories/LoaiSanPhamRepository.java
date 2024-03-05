package Repositories;

import DomainModels.LoaiSanPham;
import java.util.List;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.sql.*;

public class LoaiSanPhamRepository implements ILoaiSanPhamRepository {

    @Override
    public List<LoaiSanPham> All() {
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "select MaLSP, TenLSP, MoTa from LoaiSanPham";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                String maLSP = rs.getString("MaLSP");
                String tenLSP = rs.getString("TenLSP");
                String moTa = rs.getString("MoTa");
                LoaiSanPham lsp = new LoaiSanPham(maLSP, tenLSP, moTa);
                list.add(lsp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(LoaiSanPham lsp) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO LoaiSanPham (MaLSP, TenLSP, MoTa) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lsp.getMaLSP());
            ps.setString(2, lsp.getTenLSP());
            ps.setString(3, lsp.getMoTa());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String maLSP, LoaiSanPham lsp) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE LoaiSanPham SET MaLSP=?, TenLSP=?, MoTa=?  WHERE MaLSP=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lsp.getMaLSP());
            ps.setString(2, lsp.getTenLSP());
            ps.setString(3, lsp.getMoTa());
            ps.setString(4, maLSP);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String maLSP) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM LoaiSanPham  WHERE maLSP=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maLSP);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
