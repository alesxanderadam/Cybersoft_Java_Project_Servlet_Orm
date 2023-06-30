package service;

import model.RoleModel;
import repository.RoleRepository;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();
    private UserRepository userRepository = new UserRepository();

    public List<RoleModel> showListRole(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<RoleModel> rolesList = userRepository.roles;
            req.setAttribute("roles", rolesList);
            req.getRequestDispatcher("/views/roles/role-table.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error showListRole " + e);
        }
        return userRepository.roles;
    }

    public void addRole(String name, String description) {
        try {
            boolean isSuccess = roleRepository.insertRole(name, description);
            if (isSuccess) {
                userRepository.roles = userRepository.findAllModels("roles", new String[]{"id", "name", "description"}, RoleModel.class);
            }
        } catch (Exception e) {
            System.out.println("Error addRole " + e);
        }
    }

    public boolean showDetailRole(int role_id, HttpServletResponse resp, HttpServletRequest req) {
        RoleModel roleModel = roleRepository.getRoleById(role_id);
        boolean isSuccess = false;
        try {
            if (roleModel != null) {
                isSuccess = true;
                req.setAttribute("roleDetailModel", roleModel);
            }
        } catch (Exception e) {
            System.out.println("Error showDetailRole " + e);
        }
        return isSuccess;
    }

    public void updateRole(String role_id, RoleModel roleModel) {
        try {
            boolean isSuccess = roleRepository.updateRole(role_id, roleModel);
            if (isSuccess) {
                userRepository.roles = userRepository.findAllModels("roles", new String[]{"id", "name", "description"}, RoleModel.class);
            }
        } catch (Exception e) {
            System.out.println("Error updateRole " + e);
        }
    }

    public void deleteRoleSerivce(int role_id) {
        boolean isSuccess = roleRepository.deleteRole(role_id);
        if (isSuccess) {
            userRepository.roles = userRepository.findAllModels("roles", new String[]{"id", "name", "description"}, RoleModel.class);
        }
    }
}
