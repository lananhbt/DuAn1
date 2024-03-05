package Services;

import ViewModels.ManageHoaDon;
import java.util.List;


public interface IManageHoaDon {
    public void insert(ManageHoaDon sp);
    public List<ManageHoaDon> All() ;
    public List<ManageHoaDon> AllHD() ;
    public List<ManageHoaDon> AllMa( String ma) ;
    public List<ManageHoaDon> TimMaHD( String ma) ;
    public List<ManageHoaDon> AllMa1( String ma) ;
    public List<ManageHoaDon> AllCho() ;
    public List<ManageHoaDon> AllCho1(String ma) ;
    public void Ma() ;
    public void deleteMa(String ma);
    public void delete();
    public void updateGia(String maHD,double gia);
    public void update(String maHD,double gia);
}
