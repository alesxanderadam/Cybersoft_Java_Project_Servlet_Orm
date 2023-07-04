package controller;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    private static final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getProfileUser(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateStatusTaskUser(req,resp);
    }

    public void getProfileUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            req.getRequestDispatcher("/views/information-user/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/notfound");
        }
    }

    public void updateStatusTaskUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int status_id = Integer.parseInt(req.getParameter("status_id"));
        int task_id = Integer.parseInt(req.getParameter("task_id"));
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        boolean isSuccess = userService.userUpdateStatusTask(status_id, task_id, user_id);
        if (isSuccess) {
            req.setAttribute("successMessage", "Cập nhật thành công!");
        }
    }
}
