package ucll.gtw.larry;

import lombok.AllArgsConstructor;
import ucll.gtw.larry.controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@AllArgsConstructor
public class Router {
	private static final long serialVersionUID = 1L;

	private UserController userController;
    private ShopController shopController;
    private ProductController productController;
    private ChatController chatController;

	protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String requestResource;
        String requestAction =  "";
        if (requestURI.equals("/"))
            requestResource = "index";
        else {
            String[] requestURIparts = requestURI.split("/");
            requestResource = requestURIparts[1];
            if (requestURIparts.length > 2) {
                requestAction = requestURIparts[2];
            } else {
                requestAction = "";
            }
        }

        switch (requestResource) {
            // index
            case "index":
                shopController.handleIndex(request, response);
                break;

            // shortcuts
            case "login": userController.handleLogin(request, response); break;
            case "signup": userController.handleSignup(request, response); break;
            case "logout": userController.handleLogout(request, response); break;

            case "products":
                productController.handle(requestAction, request, response);
            break;

            case "chat":
                chatController.handle(requestAction, request, response);
                break;

            case "products.json":
                shopController.getJSONProducts(request, response);
                break;
            default:
                request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
