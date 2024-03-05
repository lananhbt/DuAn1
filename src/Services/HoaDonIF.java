/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.HoaDon;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author caoxu
 */
public interface HoaDonIF {
    public List<HoaDon> insert(String MaHD, String MaND, String MaKH, String NgayTao,float TongTien, int TrangThai); 
    public List<HoaDon> tim(String Sdt); 
}
