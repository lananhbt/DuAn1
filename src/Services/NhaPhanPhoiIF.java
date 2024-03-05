/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.NhaPhanPhoi;
import ViewModels.QLNPP;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author caoxu
 */
public interface NhaPhanPhoiIF {

    public List<QLNPP> findALL() throws SQLException;

    public List<NhaPhanPhoi> select1(String ma);
    public List<NhaPhanPhoi> select2(String ma);

    public List<NhaPhanPhoi> insert(String Ma, String Ten, String DiaChi, String SDT, String maSP, int Trangthai);

    public List<NhaPhanPhoi> delete(String Ma);

    public List<NhaPhanPhoi> update(String Ma, String Ten, String DiaChi, String SDT, String maSP, int Trangthai);

    public List<NhaPhanPhoi> click();
}
