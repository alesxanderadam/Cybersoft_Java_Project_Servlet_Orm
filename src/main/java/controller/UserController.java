package controller;

import model.UserModel;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userController",urlPatterns = {"/user", "/user/add","/user/update","/user/details", "/user/delete"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getServletPath();
        switch (path){
            case "/user":
                findAllUser(req,resp);
                break;
            case "/user/details":
                int user_id = Integer.parseInt(req.getParameter("user_id"));
                userService.showDetailUser(user_id,resp,req);
                userService.showAllStatus(req,resp);
                userService.showDetailTaskByUserId(Integer.parseInt(req.getParameter("user_id")), req, resp);
                break;
            case "/user/add":
                userService.showListRole(req,resp);
                addUser(req,resp);
                break;
            case "/user/update":
                userService.showListRole(req,resp);
                findDetailUser(req,resp);
                break;
            case "/user/delete":
                deleteUser(req,resp);
                break;
            default:
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/user":
                findAllUser(req,resp);
                break;
            case "/user/add":
                addUser(req,resp);
                break;
            case "/user/update":
                updateUser(req,resp);
                break;
            default:
                break;
        }
    }
    private void deleteUser(HttpServletRequest req, HttpServletResponse resp){
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        boolean isSuccess = userService.deleteUser(user_id);
        if (isSuccess) {
            System.out.println("Dele success");
        }
    }
    private void findAllUser(HttpServletRequest req, HttpServletResponse resp){
        userService.showAllUser(req,resp);
    }
    private void findDetailUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        userService.showDetailUser(user_id,resp,req);
        req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
    }
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            String method = req.getMethod();
            if(method.equalsIgnoreCase("post")){
                UserModel userModel = new UserModel();
                String user_id = req.getParameter("user_id");
                String fullname = req.getParameter("fullname");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String role_id = req.getParameter("role_id");
                userModel.setFullname(fullname);
                userModel.setEmail(email);
                userModel.setPassword(password);
                userModel.setRoleId(Integer.parseInt(role_id));
                userService.userUpdate(user_id, userModel);
                req.getRequestDispatcher("/user").forward(req,resp);
            }
    }
    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if(method.equalsIgnoreCase("post")){
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role_id = req.getParameter("role_id");
            userService.userAdd(fullname, email,password,role_id);
        }
        req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
    }
}
