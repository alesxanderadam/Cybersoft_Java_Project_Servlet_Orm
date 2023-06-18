package service;

import model.Roles;
import model.StatusModel;
import model.TaskModel;
import model.UserModel;
import repository.StatusRepository;
import repository.TaskRepository;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private TaskRepository taskRepository = new TaskRepository();
    private StatusRepository statusRepository = new StatusRepository();

    public List<UserModel> showAllUser(HttpServletRequest req,HttpServletResponse resp){
        List<UserModel> userModelList =  userRepository.users;
        try{
            req.setAttribute("userModelList", userModelList);
            req.getRequestDispatcher("/user-table.jsp").forward(req,resp);
        }catch (Exception e){
            System.out.println("Error showAllUser " + e);
        }
        return userRepository.users;
    }
    public List<StatusModel> showAllStatus(HttpServletRequest req, HttpServletResponse resp){
        List<StatusModel> statusModels =  statusRepository.statuses;
        try{
            req.setAttribute("statusModelList", statusModels);
        }catch (Exception e){
            System.out.println("Error showAllUser " + e);
        }
        return statusModels;
    }
    public UserModel showDetailUser(int user_id ,HttpServletResponse resp, HttpServletRequest req){
        UserModel user = userRepository.getUserById(user_id);
        try{
            if(user != null){
                req.setAttribute("userDetailModel", user);
            }
        }catch (Exception e){
            System.out.println("Error showAllUser " + e);
        }
        return user;
    }
    public List<TaskModel> showDetailTaskByUserId(int user_id, HttpServletRequest req, HttpServletResponse resp){
        List<TaskModel> taskModel = taskRepository.getTaskByUserId(user_id);
        try{
            if(taskModel.size() > 0){
                req.setAttribute("userTaskDetail", taskModel);
            }
            req.getRequestDispatcher("/user-details.jsp").forward(req,resp);
        }catch (Exception error){
            System.out.println("Error show DetailTaskByUserId " + error);
        }
        return taskModel;
    }
    public List<Roles> showListRole(HttpServletRequest req, HttpServletResponse resp){
        List<Roles> rolesList = userRepository.roles;
        try{
            req.setAttribute("roleList", rolesList);
          }catch (Exception e){
            System.out.println("Error showAllUser " + e);
    }
        return userRepository.roles;
    }
    public boolean userAdd(String fullname, String email, String password, String role_id) {
        boolean isSuccess = userRepository.insertUser(fullname, email, password, role_id);
        if (isSuccess) {
            userRepository.users = userRepository.findAllModels("users", new String[]{"id", "email", "fullname", "role_id"}, UserModel.class);
        }
        return isSuccess;
    }
    public boolean userUpdate(Object user_id,UserModel userModel){
        boolean isSuccess = userRepository.updateUser(user_id,userModel);
        userRepository.users = userRepository.findAllModels("users", new String[]{"id", "email", "fullname", "role_id"}, UserModel.class);
        return isSuccess;
    }
    public boolean deleteUser(int user_id){
        boolean isSuccess = userRepository.deleteByUserId(user_id);
        if (isSuccess) {
            userRepository.users = userRepository.findAllModels("users", new String[]{"id", "email", "fullname", "role_id"}, UserModel.class);
        }
        return isSuccess;
    }
}
