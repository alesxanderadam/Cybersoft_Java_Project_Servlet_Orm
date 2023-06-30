package repository;

import config.MysqlConfig;
import model.RoleModel;
import model.TaskModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RoleRepository extends UtilsRepository {
    public boolean insertRole(String name, String description) {
        Connection connection = null;
        boolean isAddRoleSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT INTO roles ( name, description ) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, description);

            isAddRoleSuccess = statement.executeUpdate() > 0;
        } catch (Exception error) {
            System.out.println("Error connect insertRole" + error.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception error) {
                System.out.println("Error insertRole finally: " + error.getMessage());

            }
        }
        return isAddRoleSuccess;
    }

    public boolean deleteRole(int role_id) {
        return deleteById(role_id, "roles", "DELETE FROM roles WHERE roles.id = ?");
    }

    public boolean updateRole(String role_id, RoleModel roleModel){
        String[] columnNames = {"name", "description"};
        String idColumnName = "id";

        return updateModelsById("roles", columnNames, idColumnName, role_id, roleModel);
    }

    public RoleModel getRoleById(int role_id) {
        String[] columnNames = {"id", "name", "description"};
        String idColumnName = "id";

        return findModelsByIds("roles", columnNames, idColumnName, role_id, RoleModel.class);
    }
}
