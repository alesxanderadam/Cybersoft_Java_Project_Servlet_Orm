package repository;

import config.MysqlConfig;
import model.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

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
