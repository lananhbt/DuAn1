package Utilities;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.*;

public class DBConnection {

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "yenlinh1943";
    private static final String SERVER_NAME = "DESKTOP-CSTQHQ6\\SQLEXPRESS";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "QuanLyCuaHangTapHoa";
    private static final boolean USING_SSL = true;
    //public static Connection conn = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    private static String CONNECT_STRING;
    private static Connection conn;
    public static Connection getConnection(){
        if(conn == null){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String dbUser = "sa", dbPass = "Aa@123456",
                        dbUrl = "jdbc:sqlserver://localhost:1433;"
                            +"databaseName=QuanLyCuaHangTapHoa;"
                            +"encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                System.out.println("Kết nối thành công");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
        return conn;
    }
//    static {
//        try {
//            DriverManager.registerDriver(new SQLServerDriver());
//            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            StringBuilder connectStringBuilder = new StringBuilder();
//            connectStringBuilder.append("jdbc:sqlserver://")
//                    .append(SERVER_NAME).append(":").append(PORT).append(";")
//                    .append("databaseName=").append(DATABASE_NAME).append(";")
//                    .append("user=").append(USERNAME).append(";")
//                    .append("password=").append(PASSWORD).append(";");
//            if (USING_SSL) {
//                connectStringBuilder.append("encrypt=true;trustServerCertificate=true;");
//            }
//            CONNECT_STRING = connectStringBuilder.toString();
//            System.out.println("Ket noi thanh cong");
//        } catch (Exception ex) {
//        }
//    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        String dbpn = conn.getMetaData().getDatabaseProductName();
        System.out.println(dbpn);

    }

    //hàm thực thi câu lệnh select
    public static ResultSet Getdata(String cauTruyVan) {
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(cauTruyVan);
            //thực thicaau truy vấn select dc truyền vào từ
            //tham số cautruyvan
            //trả về kết quả là ResultSet
            rs = ps.executeQuery();
            return rs;//trả về resultset nếu thành công
        } catch (SQLException ex) {
            System.out.println("Loi thuc thi truy van");
            return null;
        }

    }

    // hàm thực thi 3 câu lệnh insert delete update
    public static int ExecuteTruyVan(String cauTruyVan) {
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(cauTruyVan);
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Loi thuc thi truy van");
            return -1;
        }
    }

}
