package service;

import entity.UserModel;
import repository.AuthRepository;
import utils.SesstionUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AuthService {
    public static final AuthRepository authRepository = new AuthRepository();

    public void signIn(HttpServletRequest req, HttpServletResponse resp, String email, String password, String rememberMe) throws IOException {
        List<UserModel> listUser = authRepository.Authentication(email, password);
        HttpSession session = req.getSession();
        if (listUser.size() > 0) {
            for (UserModel user: listUser) {
//                Cookie emailCookie = new Cookie("user_email", email);
//                Cookie roleCookie = new Cookie("user_role_id", String.valueOf(user.getRoleId()));
//                emailCookie.setMaxAge(24 * 60 * 60);
//                roleCookie.setMaxAge(24 * 60 * 60);
//
//                resp.addCookie(emailCookie);
//                resp.addCookie(roleCookie);
                SesstionUtil.getInstance().setSesstionUtil(req,"email",email);
                SesstionUtil.getInstance().setSesstionUtil(req,"infUserLogin",user);
                if (rememberMe != null && rememberMe.equals("on")) {
                    session.setAttribute("password", password);
                }
                if(user.getRoleId() == 1){
                    resp.sendRedirect(req.getContextPath() + "/user");
                } else if (user.getRoleId() == 2) {
                    resp.sendRedirect(req.getContextPath() + "/");
                } else resp.sendRedirect(req.getContextPath() + "/");
            }
        }else{
            resp.sendRedirect(req.getContextPath() + "/auth/signIn?message=username_password_invalid&alert=danger");
        }
    }

}
