/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.KhachHang;
import ViewModels.QLKH;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author caoxu
 */
public interface KhachHangIF {

    public List<QLKH> findALL() throws SQLException;

    public List<KhachHang> select1(String ma);

    public List<KhachHang> insert(String Ma, String Ten, String GioiTinh, String DiaChi, String NgaySinh, String SDT, String NgayDki);

    public List<KhachHang> delete(String Ma);
    public List<KhachHang> tim(String Sdt);
    public Boolean themNhanh(KhachHang kh);

    public List<KhachHang> update(String Ma, String Ten, String GioiTinh, String DiaChi, String NgaySinh, String SDT, String NgayDki);
}
