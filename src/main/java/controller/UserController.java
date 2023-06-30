package controller;

import model.UserModel;
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

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        boolean isSuccess = userService.deleteUser(user_id);
        if (isSuccess) {
            System.out.println("Delete success");
        }
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
            Part imagePart = req.getPart("imageFile");
            if (imagePart != null && imagePart.getSize() > 0) {
                // Lưu tệp hình ảnh vào thư mục lưu trữ
                String uploadDir = "/admin_pixel/plugins/images/users";
                String fileName = imagePart.getSubmittedFileName();
                imagePart.write(uploadDir + File.separator + fileName);

                // Thực hiện các xử lý khác liên quan đến tệp hình ảnh nếu cần
                // Ví dụ: lưu đường dẫn tệp hình ảnh vào cơ sở dữ liệu
            }
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
            req.getRequestDispatcher("/user").forward(req, resp);
        }
    }

    private void showAddUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.showListRole(req, resp);
        req.getRequestDispatcher("admin_pixel/views/users/user-add.jsp").forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role_id = req.getParameter("role_id");
            userService.userAdd(fullname, email, password, role_id);
        }
        req.getRequestDispatcher("/views/users/user-add.jsp").forward(req, resp);
    }

    private void findAllTaskAndUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_idParam = req.getParameter("user_id");

        if (user_idParam == null || user_idParam.isEmpty()) {
            req.getRequestDispatcher("/notfound").forward(req, resp);

            return;
        }

        int user_id = Integer.parseInt(user_idParam);

        boolean isHaveUser = userService.showDetailUser(user_id, resp, req);

        if (isHaveUser) {
            userService.showAllStatus(req, resp);
            userService.showDetailTaskByUserId(user_id, req, resp);
            req.getRequestDispatcher("/views/users/user-details.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/notfound").forward(req, resp);

        }
    }
}