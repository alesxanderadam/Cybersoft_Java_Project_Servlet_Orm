package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class MysqlConfig {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
//    public static final String url = "jdbc:mysql://localhost:3308/crm_app";
//    public static final String username = "root";
//    public static final String password = "1234";
    public static final String url = resourceBundle.getString("url");
    public static final String username = resourceBundle.getString("user");
    public static final String password = resourceBundle.getString("password");

    public static Connection getConnection(){
        Connection connection = null;
        try{
            //Chỉ định Driver sử dụng
            Class.forName(resourceBundle.getString("driverName"));
            //Tạo kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(url,username, password);
        }catch (Exception e){
            System.out.println("Lỗi kết nối tới cở sở dữ liệu : " + e.getMessage());
        }
        return connection;
    }

}
