package repository;

import config.MysqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RoleRepository {
    public boolean insertRole(String name, String description) {
        Connection connection = null;
        boolean isAddRoleSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT INTO roles ( name, description ) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,description);

            isAddRoleSuccess = statement.executeUpdate() > 0;
        }catch (Exception error){
            System.out.println("Error connect insertRole" + error.getMessage());
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (Exception error){
                System.out.println("Error insertRole finally: " + error.getMessage());

            }
        }
        return isAddRoleSuccess;
    }
}
