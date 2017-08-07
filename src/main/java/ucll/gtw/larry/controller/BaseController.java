package ucll.gtw.larry.controller;

import lombok.Data;
import lombok.Getter;
import ucll.gtw.larry.domain.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {
    @Getter private UserRepository userRepository = new UserRepository();

    protected boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("userid") == null) {
            return false;
        }
        else {
            int userId = (int)request.getSession().getAttribute("userid");
            request.setAttribute("user", userRepository.get(userId));
            return true;
        }
    }
}
