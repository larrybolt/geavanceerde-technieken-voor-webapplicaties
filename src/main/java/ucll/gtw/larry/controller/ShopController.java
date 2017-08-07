package ucll.gtw.larry.controller;

import ucll.gtw.larry.domain.user.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShopController extends BaseController {
    public void handleIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (isLoggedIn(request, response))
                request.getRequestDispatcher("/shop.jsp").forward(request, response);
            else
                response.sendRedirect("/login");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
