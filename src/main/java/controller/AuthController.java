package controller;

import entity.UserModel;
import repository.AuthRepository;
import service.AuthService;
import utils.SesstionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(name = "authController", urlPatterns = {"/auth/signIn", "/auth/checkout"})
public class AuthController extends HttpServlet {
    private final AuthService authService = new AuthService();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/auth/signIn":
                String message = req.getParameter("message");
                String alert = req.getParameter("alert");
                if(message != null && alert != null){
                    req.setAttribute("message",resourceBundle.getString(message));
                    req.setAttribute("alert",alert);
                }
                req.getRequestDispatcher("/views/auth/sign-in.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/auth/signIn":
                signIn(req, resp);
                break;
            case "/auth/checkout":
                String action = req.getParameter("action");
                if (action != null && action.equals("logout")) {
                    SesstionUtil.getInstance().removeSesstionUtil(req, "email");
                    SesstionUtil.getInstance().removeSesstionUtil(req, "infUserLogin");
                }
                resp.sendRedirect(req.getContextPath() + "/auth/signIn");
                break;
            default:
                break;
        }
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        authService.signIn(req, resp, email, password, rememberMe);
    }
}
