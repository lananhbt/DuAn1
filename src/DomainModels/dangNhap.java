/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author lanan
 */
public class dangNhap {
    private static String currentLoginUsername;

    public dangNhap() {
    }
    
    

    public static void setCurrentLoginUsername(String currentLoginUsername) {
        dangNhap.currentLoginUsername = currentLoginUsername;
    }
    public static String getCurrentLoginUsername() {
        return currentLoginUsername;
    }
}
