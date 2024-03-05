/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.NguoiDung;
import ViewModels.NguoiDungViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface NguoiDungService {
    ArrayList<NguoiDungViewModel> listND();
    public List<NguoiDungViewModel> find(String maND);
    public NguoiDung findND(String maND);
    public Boolean them(NguoiDung nd);
    public Boolean sua(NguoiDung nguoiDung, String maND);
    public Boolean delete(String maND);
}
