package repository;

import config.MysqlConfig;
import entity.JobModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class JobRepository extends UtilsRepository {
     public List<JobModel> jobLists = findAllModels("jobs",new String[]{"id", "name", "start_date", "end_date"}, JobModel.class);

     public JobModel getJobById(int job_id) {
          String[] columnNames = {"id", "name","start_date","end_date"};
          String idColumnName = "id";

          return findModelsByIds("jobs", columnNames, idColumnName, job_id, JobModel.class);
     }

     public void updateJob(Object job_id, JobModel model){
          String[] columnNames = {"name","start_date","end_date"};
          String idColumnName = "id";

          updateModelsById("jobs", columnNames, idColumnName, job_id, model);
     }

     public boolean insertIntoJob(String name, Date start_date, Date end_date){
          boolean isSuccess = false;
          try(Connection connection = MysqlConfig.getConnection()) {
               String sql = "INSERT INTO jobs (name, start_date, end_date) VALUES (?,?,?)";
               PreparedStatement statement = connection.prepareStatement(sql);
               statement.setString(1,name);
               statement.setDate(2,start_date);
               statement.setDate(3,end_date);

               isSuccess = statement.executeUpdate() > 0;
          }catch (Exception error){
               System.out.println("Error insert new task");
          }
          return isSuccess;
     };

     public boolean deleteJob(int job_id) {
          return deleteById(job_id, "jobs", "DELETE FROM jobs WHERE jobs.id = ?");
     }
}

