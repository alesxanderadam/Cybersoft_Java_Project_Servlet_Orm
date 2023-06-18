package controller;

import model.Roles;
import repository.UserRepository;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "roleController", urlPatterns = {"/role", "/role/add"})
public class RoleController extends HttpServlet {
    private RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/role":
                roleService.showListRole(req,resp);
                break;
            case "/role/add":
                addRoleController(req,resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/role":
                roleService.showListRole(req,resp);
                break;
            case "/role/add":
                addRoleController(req,resp);
                break;
            default:
                break;
        }
    }

    public void addRoleController(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String method = req.getMethod();
        if(method.equalsIgnoreCase("post")){
            String role_name = req.getParameter("role_name");
            String desc = req.getParameter("description");
            roleService.addRole(role_name,desc);
        }
        req.getRequestDispatcher("/role-add.jsp").forward(req,resp);
    }
}
