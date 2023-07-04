package service;

import entity.UserModel;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class LoginService {
    private UserRepository userRepository = new UserRepository();
    public boolean checkLogin(String email, String password, HttpServletRequest req, HttpServletResponse resp){
        List<UserModel> userModelList =  userRepository.findByEmailAndPassword(email,password);
        try{
            String contextPath = req.getContextPath();
            PrintWriter writer = resp.getWriter();
            if(userModelList.size() > 0){
                resp.sendRedirect(contextPath + "/dashboard");
            }else {
                writer.println("Login fail !");
                writer.close();
            }
        }catch (Exception e){
            System.out.println("Error checkLogin " + e);
        }

        return userModelList.size() > 0; // Tối ưu không ần if else
    }
}
