package service;

import dto.TaskDto;
import entity.TaskModel;
import entity.UserModel;
import repository.TaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();

    public void showAllTask(HttpServletRequest req, HttpServletResponse resp){
        List<TaskDto> taskModelList =  taskRepository.findAllTasks();
        try{
            if(taskModelList.size() > 0){
                for (TaskDto task: taskModelList) {
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String start_date = outputFormat.format(task.getStart_date());
                        String end_date = outputFormat.format(task.getEnd_date());

                        task.setStart_date_string(start_date);
                        task.setEnd_date_string(end_date);
                }
                req.setAttribute("taskModelList", taskModelList);
            }
        }catch (Exception e){
            System.out.println("Error showAllUser " + e);
        }
    }

    public void addTask(String name, Date start_date, Date end_date, int user_id, int job_id, int status_id){
        taskRepository.insertTask(name, start_date, end_date, user_id, job_id, status_id);
    }

    public boolean showDetailTask(int task_id , HttpServletResponse resp, HttpServletRequest req){
        TaskModel task = taskRepository.getTaskById(task_id);
        boolean isHaveTask = false;
        try{
            if(task != null){
                isHaveTask = true;
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                String start_date = outputFormat.format(task.getStart_date());
                String end_date = outputFormat.format(task.getEnd_date());

                task.setStart_date_string(start_date);
                task.setEnd_date_string(end_date);
                req.setAttribute("taskDetailModel", task);
            }
        }catch (Exception e){
            System.out.println("Error showAllTask " + e);
        }
        return isHaveTask;
    }

    public void taskUpdate(Object task_id, TaskModel taskModel){
        taskRepository.updateTask(task_id, taskModel);
        taskRepository.tasks = taskRepository.findAllModels("tasks", new String[]{"id", "name", "start_date", "end_date", "user_id", "job_id", "status_id"}, TaskModel.class);
    }

    public boolean deleteTask(int task_id){
        boolean isSuccess = taskRepository.deleteTaskById(task_id);
        if (isSuccess) {
            taskRepository.tasks = taskRepository.findAllModels("tasks", new String[]{"id", "name", "start_date", "end_date", "user_id", "job_id", "status_id"}, TaskModel.class);
        }
        return isSuccess;
    }
}
