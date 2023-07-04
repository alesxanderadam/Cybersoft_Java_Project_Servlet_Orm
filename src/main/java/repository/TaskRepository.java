package repository;

import config.MysqlConfig;
import dto.TaskDto;
import entity.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class TaskRepository extends UtilsRepository {
    public List<TaskModel> getTaskByUserId(int user_id){
        Connection connection = null;
        ArrayList<TaskModel> taskModelArrayList = new ArrayList<>();
        try {
            String sql = "select tasks.id, tasks.user_id, tasks.name, jobs.name as job_name, status.id as status_id, tasks.start_date, tasks.end_date from tasks inner join users on tasks.user_id = users.id inner join status on tasks.status_id = status.id inner join jobs on tasks.job_id = jobs.id where users.id = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                TaskModel taskModel = new TaskModel();

                taskModel.setId(resultSet.getInt("id"));
                taskModel.setUser_id(resultSet.getInt("user_id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setJob_name(resultSet.getString("job_name"));
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

    public List<TaskModel> tasks = findAllModels("tasks", new String[]{"id", "name", "start_date", "end_date", "user_id", "job_id", "status_id"}, TaskModel.class);

    public List<TaskDto> findAllTasks() {
        ArrayList<TaskDto> taskModels = new ArrayList<>();
        try (Connection connection = MysqlConfig.getConnection()) {
            String sql = "select tasks.id, tasks.name, jobs.name as job_name, users.fullname as user_name, tasks.start_date, tasks.end_date, status.name as status_name from tasks inner join users on tasks.user_id = users.id inner join status on tasks.status_id = status.id inner join jobs on tasks.job_id = jobs.id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TaskDto customTaskModel = new TaskDto();
                customTaskModel.setId(resultSet.getInt("id"));
                customTaskModel.setName(resultSet.getString("name"));
                customTaskModel.setJob_name(resultSet.getString("job_name"));
                customTaskModel.setUser_name(resultSet.getString("user_name"));
                customTaskModel.setStart_date(resultSet.getDate("start_date"));
                customTaskModel.setEnd_date(resultSet.getDate("end_date"));
                    customTaskModel.setStatus_name(resultSet.getString("status_name"));
                taskModels.add(customTaskModel);
            }
        } catch (Exception error) {
            System.out.println("error getall tasks " + error);
        }
        return taskModels;
    };

    public boolean insertTask(String name, Date start_date, Date end_date, int user_id,  int job_id, int status_id){
        boolean isAddTaskSuccess = false;
        try(Connection connection = MysqlConfig.getConnection()){
            String sql  = "INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setDate(2,start_date);
            statement.setDate(3,end_date);
            statement.setInt(4,user_id);
            statement.setInt(5,job_id);
            statement.setInt(6,status_id);

            isAddTaskSuccess = statement.executeUpdate() > 0;
        }catch (Exception error){
            System.out.println("Error insert task repository " + error);
        }
        return isAddTaskSuccess;
    }

    public TaskModel getTaskById(int task_id) {
        String[] columnNames = {"id", "name", "start_date", "end_date", "user_id", "job_id", "status_id"};
        String idColumnName = "id";

        return findModelsByIds("tasks", columnNames, idColumnName, task_id, TaskModel.class);
    }

    public void updateTask(Object task_id, TaskModel model){
        String[] columnNames = {"name", "start_date", "end_date", "user_id", "job_id", "status_id"};
        String idColumnName = "id";

        updateModelsById("tasks", columnNames, idColumnName, task_id, model);
    }

    public boolean updateStatusTask(int status_id, int task_id, int user_id){
        boolean isSuccess = false;
        try(Connection connection = MysqlConfig.getConnection()) {
            String sql = "update tasks set status_id = ? where id = ? and user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status_id);
            statement.setInt(2, task_id);
            statement.setInt(3, user_id);
            int rowsAffected = statement.executeUpdate();
            isSuccess = rowsAffected > 0;
        }catch (Exception e){
            System.out.println("Error getTaskByUserId : " + e.getMessage());
        }
        return isSuccess;
    }

    public boolean deleteTaskById(int task_id){
        String sql = "DELETE FROM tasks t WHERE t.id = ?";
        return deleteById(task_id, "tasks",sql);
    }
}
