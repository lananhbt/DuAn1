/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModels.doanhThu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IsDoanhThuRepositories {
    
    public List<doanhThu> getAll();
    
    public List<doanhThu> getShearch(String dateTao);
}
