package ucll.gtw.larry.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import ucll.gtw.larry.domain.user.User;
import ucll.gtw.larry.domain.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class BaseController {
    @Getter private UserRepository userRepository;

    protected boolean isLoggedIn(HttpServletRequest request) {
        if (request.getSession().getAttribute("userid") == null) {
            return false;
        }
        else {
            int userId = (int)request.getSession().getAttribute("userid");
            User user = getUserRepository().get(userId);
            request.setAttribute("user", user);
            user.wasOnline();
            return true;
        }
    }

    protected User getUser(HttpServletRequest request){
        if (request.getSession().getAttribute("userid") == null) {
            return null;
        }
        else {
            int userId = (int)request.getSession().getAttribute("userid");
            return getUserRepository().get(userId);
        }
    }

    protected boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isAjax(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null;
    }
}
