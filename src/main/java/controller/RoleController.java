package controller;

import model.RoleModel;
import model.TaskModel;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "roleController", urlPatterns = {"/role", "/role/add", "/role/update", "/role/delete"})
public class RoleController extends HttpServlet {
    private static final RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/role":
                roleService.showListRole(req, resp);
                break;
            case "/role/add":
                addRoleController(req, resp);
                break;
            case "/role/update":
                int role_id = Integer.parseInt(req.getParameter("role_id"));
                if(role_id == 0){
                    req.getRequestDispatcher("/notfound").forward(req, resp);
                }else{
                    boolean isSuccess = roleService.showDetailRole(role_id, resp, req);
                    if(!isSuccess){
                        req.getRequestDispatcher("/notfound").forward(req, resp);
                    }else{
                        req.getRequestDispatcher("/views/roles/role-update.jsp").forward(req, resp);
                    }
                }
                break;
            case "/role/delete":
                deleteRoleController(Integer.parseInt(req.getParameter("role_id")), req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/role":
                roleService.showListRole(req, resp);
                break;
            case "/role/add":
                addRoleController(req, resp);
                break;
            case "/role/update":
                updateRoleController(req,resp);
                break;
            default:
                break;
        }
    }

    public void addRoleController(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String role_name = req.getParameter("role_name");
            String desc = req.getParameter("description");
            roleService.addRole(role_name, desc);
        }
        req.getRequestDispatcher("/views/roles/role-add.jsp").forward(req, resp);
    }

    public void deleteRoleController(int role_id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roleService.deleteRoleSerivce(role_id);
        req.getRequestDispatcher("/views/roles/role-table.jsp").forward(req, resp);
    }

    public void updateRoleController(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleModel roleModel = new RoleModel();
        String role_id = req.getParameter("role_id");
        String role_name = req.getParameter("role_name");
        String desc = req.getParameter("description");
        roleModel.setName(role_name);
        roleModel.setDescription(desc);
        roleService.updateRole(role_id ,roleModel);
        req.getRequestDispatcher("/views/roles/role-add.jsp").forward(req, resp);
    }
}
