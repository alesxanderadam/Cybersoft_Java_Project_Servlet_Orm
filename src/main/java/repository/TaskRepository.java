package repository;

import config.MysqlConfig;
import model.TaskModel;
import model.UserModel;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository extends UtilsRepository {
    public List<TaskModel> getTaskByUserId(int user_id){
        Connection connection = null;
        ArrayList<TaskModel> taskModelArrayList = new ArrayList<>();
        try {
            String sql = "select tasks.id, tasks.user_id, tasks.name, status.id as status_id, tasks.start_date, tasks.end_date from tasks inner join users on tasks.user_id = users.id inner join status on tasks.status_id = status.id where users.id = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                TaskModel taskModel = new TaskModel();

                taskModel.setId(resultSet.getInt("id"));
                taskModel.setUser_id(resultSet.getInt("user_id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStatus_id(resultSet.getInt("status_id"));
                taskModel.setStart_date(resultSet.getDate("start_date"));
                taskModel.setEnd_date(resultSet.getDate("end_date"));

                taskModelArrayList.add(taskModel);
            }
        }catch (Exception e){
            System.out.println("Error getTaskByUserId : " + e.getMessage());
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (Exception e){
                System.out.println("Error getTaskByUserId finally " + e.getMessage());
            }
        }
        return taskModelArrayList;
    }
}
