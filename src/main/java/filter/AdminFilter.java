package filter;

import constant.SystemConstant;
import entity.UserModel;
import utils.SesstionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

//        if (session.getAttribute("email") == null && !requestURI.endsWith("/auth/signIn")) {
@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {
    private final SystemConstant systemConstant = new SystemConstant();
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        ServletContext context = fConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();
        UserModel userLogin = (UserModel) SesstionUtil.getInstance().getSessionUtil(req, "infUserLogin");

        if (url.startsWith("/user") || url.startsWith("/role")) {
            if (userLogin != null) {
                    switch (userLogin.getRoleId()){
                        case SystemConstant.ADMIN:
                            chain.doFilter(request,response);
                            break;
                        default:
                            resp.sendRedirect(req.getContextPath() + "/auth/signIn?message=un_authorization&alert=warning");
                            break;
                    }
            } else {
                resp.sendRedirect(req.getContextPath() + "/auth/signIn");
            }
        } else if (url.startsWith("/groupwork") || url.startsWith("/task")) {
            if (userLogin != null) {
                switch (userLogin.getRoleId()){
                    case SystemConstant.ADMIN:
                    case SystemConstant.MANAGER:
                        chain.doFilter(request,response);
                        break;
                    default:
                        resp.sendRedirect(req.getContextPath() + "/auth/signIn?message=un_authorization&alert=warning");
                        break;
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/auth/signIn");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

}
