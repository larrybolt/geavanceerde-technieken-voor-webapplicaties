package ucll.gtw.larry.controller;

import ucll.gtw.larry.domain.user.InvalidLogin;
import ucll.gtw.larry.domain.user.User;
import ucll.gtw.larry.domain.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController extends BaseController {

    public void handleLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getMethod().equals("GET")) {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else if (request.getMethod().equals("POST")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (username == null || password == null) {
                    request.setAttribute("error", "Invalid login");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
                try {
                    User user = getUserRepository().loginUser(username, password);
                    request.getSession().setAttribute("userid", user.getUserId());
                    request.setAttribute("user", user);
                    response.sendRedirect("/");
                } catch (InvalidLogin error) {
                    request.setAttribute("username", username);
                    request.setAttribute("error", error.getMessage());
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("userid");
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleSignup(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
