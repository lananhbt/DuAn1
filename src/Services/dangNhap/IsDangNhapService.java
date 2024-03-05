/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.dangNhap;

import DomainModels.DangNhap.dangNhapModel;
import DomainModels.NguoiDung;
import Repositories.dangNhap.dangNhapRepositories;
import ViewModels.NguoiDungViewModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IsDangNhapService {

    public boolean getAllDangNhap(String userName, String pass);
    //public NguoiDungViewModel getUser(String userName);

    public boolean find_DoiMK(String mkcu, String mkmoi, String nhaplai);
    
    public boolean find_UpdateMK(String MK, String MaND);

}
