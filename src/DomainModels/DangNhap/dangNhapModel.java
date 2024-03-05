/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels.DangNhap;

/**
 *
 * @author Admin
 */
public class dangNhapModel {
    
    private String username;
    private String password;
    private int chucVu;

    public dangNhapModel() {
    }

    public dangNhapModel(String username, String password, int chucVu) {
        this.username = username;
        this.password = password;
        this.chucVu = chucVu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
