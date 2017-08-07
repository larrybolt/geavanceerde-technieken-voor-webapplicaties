package ucll.gtw.larry.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ucll.gtw.larry.domain.shop.Product;
import ucll.gtw.larry.domain.shop.ProductRepository;
import ucll.gtw.larry.domain.user.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShopController extends BaseController {
    private ProductRepository productRepository = new ProductRepository();

    public void handleIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (isLoggedIn(request, response)) {
                request.setAttribute("products", productRepository.getAll());
                request.getRequestDispatcher("/shop.jsp").forward(request, response);
            }
            else
                response.sendRedirect("/login");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getJSONProducts(HttpServletRequest request, HttpServletResponse response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            response.getWriter().write(gson.toJson(productRepository.getAll()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
