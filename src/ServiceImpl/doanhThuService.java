/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import DomainModels.doanhThu;
import Repositories.doanhThuRepositories;
import Services.IsDoanhThuService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class doanhThuService implements IsDoanhThuService {

    private doanhThuRepositories repositories;

    public doanhThuService() {
        repositories = new doanhThuRepositories();
    }
    

    @Override
    public List<doanhThu> find_AllDoanhThu() {
        try {
             return repositories.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
       
    }

    @Override
    public List<doanhThu> find_Shearch(String dateTao) {
        try {
             return repositories.getShearch(dateTao);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
       
    }

}
