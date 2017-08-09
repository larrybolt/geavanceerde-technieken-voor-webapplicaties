package ucll.gtw.larry.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import ucll.gtw.larry.domain.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class BaseController {
    @Getter private UserRepository userRepository;

    protected boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("userid") == null) {
            return false;
        }
        else {
            int userId = (int)request.getSession().getAttribute("userid");
            request.setAttribute("user", getUserRepository().get(userId));
            return true;
        }
    }

    protected boolean isAjax(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null;
    }
}
