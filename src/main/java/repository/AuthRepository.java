package repository;

import config.MysqlConfig;
import entity.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthRepository extends UtilsRepository {
    public List<UserModel> Authentication(String email, String password) {
        List<UserModel> userList = new ArrayList<>();
        try (Connection connection = MysqlConfig.getConnection()) {
            String sql_query_user = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?";
            PreparedStatement statement = connection.prepareStatement(sql_query_user);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userList.add(userModel);
            }
        } catch (Exception error) {
            System.out.println("Error Authentication: " + error.getMessage());
        }
        return userList;
    }
}
