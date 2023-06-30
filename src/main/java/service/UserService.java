package service;

import model.RoleModel;
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
    private final UserRepository userRepository = new UserRepository();
    private final TaskRepository taskRepository = new TaskRepository();
    private final StatusRepository statusRepository = new StatusRepository();

    public void showAllUser(HttpServletRequest req, HttpServletResponse resp){
        List<UserModel> userModelList =  userRepository.getAllUserCustom();
        try{
            req.setAttribute("userModelList", userModelList);
        }catch (Exception e){
            System.out.println("Error showAllUser " + e);
        }
    }

    public void showAllStatus(HttpServletRequest req, HttpServletResponse resp){
        List<StatusModel> statusModels =  statusRepository.statuses;
        try{
            req.setAttribute("statusModelList", statusModels);
        }catch (Exception e){
            System.out.println("Error showAllUser " + e);
        }
    }

    public boolean showDetailUser(int user_id , HttpServletResponse resp, HttpServletRequest req){
        UserModel user = userRepository.getUserById(user_id);
        boolean isHaveUser = false;
        try{
            if(user != null){
                isHaveUser = true;
                req.setAttribute("userDetailModel", user);
            }
        }catch (Exception e){
            System.out.println("Error showAllUser " + e);
        }
        return isHaveUser;
    }

    public void showDetailTaskByUserId(int user_id, HttpServletRequest req, HttpServletResponse resp){
        List<TaskModel> taskModel = taskRepository.getTaskByUserId(user_id);
        try{
            if(taskModel.size() > 0){
                req.setAttribute("userTaskDetail", taskModel);
            }
        }catch (Exception error){
            System.out.println("Error show DetailTaskByUserId " + error);
        }
    }

    public void showListRole(HttpServletRequest req, HttpServletResponse resp){
        List<RoleModel> rolesList = userRepository.roles;
        try{
            req.setAttribute("roleList", rolesList);
          }catch (Exception e){
            System.out.println("Error showAllUser " + e);
    }
    }

    public void userAdd(String fullname, String email, String password, String role_id) {
        boolean isSuccess = userRepository.insertUser(fullname, email, password, role_id);
        if (isSuccess) {
            userRepository.users = userRepository.findAllModels("users", new String[]{"id", "email", "fullname", "role_id"}, UserModel.class);
        }
    }

    public void userUpdate(Object user_id, UserModel userModel){
        userRepository.updateUser(user_id,userModel);
        userRepository.users = userRepository.findAllModels("users", new String[]{"id", "email", "fullname", "role_id"}, UserModel.class);
    }

    public boolean deleteUser(int user_id){
        boolean isSuccess = userRepository.deleteByUserId(user_id);
        if (isSuccess) {
            userRepository.users = userRepository.findAllModels("users", new String[]{"id", "email", "fullname", "role_id"}, UserModel.class);
        }
        return isSuccess;
    }
}
