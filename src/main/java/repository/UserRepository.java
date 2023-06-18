package repository;

import config.MysqlConfig;
import model.RoleModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends UtilsRepository {
    public List<UserModel> findByEmailAndPassword(String email, String password){
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "select * from users u where u.email = ? and u.password = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                UserModel userModel = new UserModel();

                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        }catch (Exception e){
            System.out.println("Error findByEmailAndPassword : " + e.getMessage());
        }finally {
           try {
                if(connection != null){
                    connection.close();
                }
           }catch (Exception e){
               System.out.println("Error findByEmailAndPassword finally " + e.getMessage());
           }
        }
        return userModelList;
    }

    public List<UserModel> users = findAllModels("users", new String[]{"id", "email", "fullname", "role_id"}, UserModel.class);

    public List<RoleModel> roles = findAllModels("roles", new String[]{"id", "name", "description"}, RoleModel.class);

    public UserModel getUserById(int user_id) {
        String[] columnNames = {"id", "email","password","fullname","avatar","role_id"};
        String idColumnName = "id";

        return findModelsByIds("users", columnNames, idColumnName, user_id, UserModel.class);
    }

    public boolean insertUser(String fullname, String email, String password, String role_id ){
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT INTO users( email, fullname, password, role_id) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,fullname);
            statement.setString(3,password);
            statement.setInt(4,Integer.parseInt(role_id));

            isSuccess = statement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Error insertUser : " + e.getMessage());
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (Exception e){
                System.out.println("Error insertUser finally " + e.getMessage());
            }
        }
        return isSuccess;
    }

    public boolean updateUser(Object user_id,UserModel model){
        String[] columnNames = {"email","password","fullname","role_id"};
        String idColumnName = "id";

        return updateModelsById("users",columnNames,idColumnName,user_id,model);
    }

    public boolean deleteByUserId(int user_id){
        String sql = "DELETE FROM users u WHERE u.id = ?";
        return deleteById(user_id, "users",sql);
    }
}
