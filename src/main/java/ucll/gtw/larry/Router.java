package ucll.gtw.larry;

import ucll.gtw.larry.controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserController userController = new UserController();
    private ShopController shopController = new ShopController();

	public Router() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    handle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        switch (requestURI) {
            case "/login":
                userController.handleLogin(request, response);
            break;
            case "/signup":
                userController.handleSignup(request, response);
                break;
            case "/logout":
                userController.handleLogout(request, response);
                break;
            case "/":
                shopController.handleIndex(request, response);
                break;
            default:
                request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
