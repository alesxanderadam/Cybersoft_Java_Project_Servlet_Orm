package controller;

import entity.UserModel;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user/add", "/user/update", "/user/details", "/user/delete"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UserController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getServletPath();
        switch (path) {
            case "/user":
                findAllUser(req, resp);
                break;
            case "/user/details":
                findAllTaskAndUser(req, resp);
                break;
            case "/user/add":
                showAddUserPage(req, resp);
                break;
            case "/user/update":
                showUpdateUserPage(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/user":
                findAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/update":
                updateUser(req, resp);
                break;
            default:
                break;
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        String fileName = req.getParameter("imageFile");
        if (fileName != null) {
            String appRootDir = getServletContext().getRealPath("/");
            String uploadDir = appRootDir + "plugins/images/users";
            String absoluteUploadDir = new File(uploadDir).getAbsolutePath();
            String filePath = absoluteUploadDir + File.separator + fileName;
            File file = new File(filePath);
            if (file.exists()) {
                boolean deleteResult = file.delete();
                if(deleteResult) {
                    System.out.println("Xoa anh thanh cong " + file);
                } else {
                    System.out.println("Xoa anh that bai " + file);
                }
            }
        }
        userService.deleteUser(user_id);
        resp.sendRedirect(req.getContextPath() + "/user");
    }


    private void findAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.showAllUser(req, resp);
        req.getRequestDispatcher("/views/users/user-table.jsp").forward(req, resp);
    }

    private void showUpdateUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_idParam = req.getParameter("user_id");

        if (user_idParam == null || user_idParam.isEmpty()) {
            req.getRequestDispatcher("/notfound").forward(req, resp);
            return;
        }

        int user_id = Integer.parseInt(user_idParam);

        boolean isHaveUser = userService.showDetailUser(user_id, resp, req);

        if (!isHaveUser) {
            req.getRequestDispatcher("/notfound").forward(req, resp);
            return;
        }

        userService.showListRole(req, resp);
        req.getRequestDispatcher("/views/users/user-update.jsp").forward(req, resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            UserModel userModel = new UserModel();
            String user_id = req.getParameter("user_id");
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role_id = req.getParameter("role_id");
            Part imagePart = req.getPart("imageFile");
            if (imagePart != null && imagePart.getSize() > 0) {
                String appRootDir = getServletContext().getRealPath("/");
                String uploadDir = appRootDir + "plugins/images/users";
                String absoluteUploadDir = new File(uploadDir).getAbsolutePath();
                String fileName = imagePart.getSubmittedFileName();
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                String formattedDate = dateFormat.format(currentDate);

                String newFileName = formattedDate + "_" + fileName;
                String filePath = absoluteUploadDir + File.separator + newFileName;
                imagePart.write(filePath);
                userModel.setAvatar(newFileName);
            }
            userModel.setFullname(fullname);
            userModel.setEmail(email);
            userModel.setPassword(password);
            userModel.setRoleId(Integer.parseInt(role_id));
            userService.userUpdate(user_id, userModel);
            req.getRequestDispatcher("/user").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/user");
        }
    }

    private void showAddUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.showListRole(req, resp);
        req.getRequestDispatcher("/views/users/user-add.jsp").forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role_id = req.getParameter("role_id");
            Part imagePart = req.getPart("imageFile");
            if (imagePart != null && imagePart.getSize() > 0) {
                String appRootDir = getServletContext().getRealPath("/");
                String uploadDir = appRootDir + "plugins/images/users";
                String absoluteUploadDir = new File(uploadDir).getAbsolutePath();
                String fileName = imagePart.getSubmittedFileName();
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                String formattedDate = dateFormat.format(currentDate);

                String newFileName = formattedDate + "_" + fileName;
                String filePath = absoluteUploadDir + File.separator + newFileName;
                imagePart.write(filePath);
                userService.userAdd(fullname, email, password, newFileName, role_id);
            }else {
                userService.userAdd(fullname, email, password, null, role_id);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/user");
    }

    private void findAllTaskAndUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_idParam = req.getParameter("user_id");

        if (user_idParam == null || user_idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/notfound");
            return;
        }

        int user_id = Integer.parseInt(user_idParam);

        boolean isHaveUser = userService.showDetailUser(user_id, resp, req);

        if (isHaveUser) {
            userService.showAllStatus(req, resp);
            userService.showDetailTaskByUserId(user_id, req, resp);
            req.getRequestDispatcher("/views/users/user-details.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/notfound");
        }
    }
}