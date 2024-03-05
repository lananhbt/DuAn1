/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import DomainModels.NguoiDung;

/**
 *
 * @author lanan
 */
public class Help {

    public static NguoiDung USER = null;

    /**
     * * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void logoff() {
        Help.USER = null;
    }

}
