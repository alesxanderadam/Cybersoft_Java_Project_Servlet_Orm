package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "notFoundController" , urlPatterns = {"/notfound", "/unauthorization"})
public class NotFoundController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/notfound":
                req.getRequestDispatcher("/views/error-page/404-NotFound.jsp").forward(req,resp);
                break;
            case "/unauthorization":
                req.getRequestDispatcher("/views/error-page/401-NotFound.jsp").forward(req,resp);
                break;
        }
    }
}
