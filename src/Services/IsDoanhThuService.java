/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.doanhThu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IsDoanhThuService {
    
    public List<doanhThu> find_AllDoanhThu();
    
     public List<doanhThu> find_Shearch(String dateTao);
    
}
