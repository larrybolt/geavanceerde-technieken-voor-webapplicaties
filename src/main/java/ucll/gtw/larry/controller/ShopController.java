package ucll.gtw.larry.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ucll.gtw.larry.domain.chat.MessageRepository;
import ucll.gtw.larry.domain.shop.Product;
import ucll.gtw.larry.domain.shop.ProductRepository;
import ucll.gtw.larry.domain.user.User;
import ucll.gtw.larry.domain.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class ShopController extends BaseController {
    @Getter private ProductRepository productRepository;
    @Getter private MessageRepository messageRepository;

    public ShopController(UserRepository userRepository, ProductRepository productRepository, MessageRepository messageRepository) {
        super(userRepository);
        this.productRepository = productRepository;
        this.messageRepository = messageRepository;
    }

    public void handleIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (isLoggedIn(request)) {

                request.setAttribute("messages", getMessageRepository().getForUser(getUser(request)));
                request.setAttribute("products", getProductRepository().getFrom(0));
                request.setAttribute("products_lastupdatetimestamp", getProductRepository().getLastUpdateTimeStamp());
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
        try {
            String fromString = request.getParameter("from");
            int from = 0;
            if (fromString != null) {
                from = Integer.parseInt(fromString);
            }
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Writer writer = response.getWriter();
            writer.write(gson.toJson(getProductRepository().getFrom(from)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
