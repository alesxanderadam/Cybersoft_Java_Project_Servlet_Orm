package service;

import model.Roles;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();
    private UserRepository userRepository = new UserRepository();
    public List<Roles> showListRole(HttpServletRequest req, HttpServletResponse resp){
        try{
            List<Roles> rolesList =  userRepository.roles;
            req.setAttribute("roles", rolesList);
            req.getRequestDispatcher("role-table.jsp").forward(req,resp);
        }catch (Exception e){
            System.out.println("Error showAllUser " + e);
        }
        return userRepository.roles;
    }
    public boolean addRole(String name, String description){
        try {

        }catch (Exception e){
            System.out.println("Error addRole service " + e);
        }
        return roleRepository.insertRole(name,description);
    }
}
