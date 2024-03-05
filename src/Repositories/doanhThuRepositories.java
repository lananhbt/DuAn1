/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.doanhThu;
import Utilities.DBConnection;
import java.math.BigDecimal;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class doanhThuRepositories implements IsDoanhThuRepositories {

    List<doanhThu> listDoanhThus;
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<doanhThu> getAll() {
        
        listDoanhThus = new ArrayList<>();
        
        try {
            connection = DBConnection.getConnection();
            String sql = " SELECT HoaDon.MaHD, HoaDon.NgayTao, HoaDonChiTiet.ThanhTien "
                    + " FROM HoaDon INNER JOIN HoaDonChiTiet ON HoaDonChiTiet.MaHD = HoaDon.MaHD";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("maHD");
                Date ngayTao = rs.getDate("NgayTao");
                BigDecimal thanhTien = rs.getBigDecimal("ThanhTien");

                listDoanhThus.add(new doanhThu(maHoaDon, ngayTao, thanhTien));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listDoanhThus;
    }

    @Override
    public List<doanhThu> getShearch(String dateTao) {

         listDoanhThus = new ArrayList<>();
         
        try {
            connection = DBConnection.getConnection();
            String sql = " SELECT HoaDon.MaHD, HoaDon.NgayTao, HoaDonChiTiet.ThanhTien "
                    + " FROM HoaDon INNER JOIN HoaDonChiTiet ON HoaDonChiTiet.MaHD = HoaDon.MaHD WHERE NgayTao = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, dateTao);
            rs = ps.executeQuery();
            while (rs.next()) {

                String maHoaDon = rs.getString("maHD");
                Date ngayTao = rs.getDate("NgayTao");
                BigDecimal thanhTien = rs.getBigDecimal("ThanhTien");

                listDoanhThus.add(new doanhThu(maHoaDon, ngayTao, thanhTien));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listDoanhThus;
    }

}
