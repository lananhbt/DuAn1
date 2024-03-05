/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories.dangNhap;

import DomainModels.DangNhap.dangNhapModel;
import DomainModels.NguoiDung;
import ViewModels.NguoiDungViewModel;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public interface IsDangNhapRepositories {

    public boolean getAllDangNhap(String userName, String pass);
    //public NguoiDungViewModel getUser(String userName);

    public boolean SuaMK(String MK, String MaND);
    
    public boolean doiMatKhau(String mkcu, String mkmoi, String nhapLai);
}
