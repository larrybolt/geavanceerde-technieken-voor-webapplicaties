package ucll.gtw.larry.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import ucll.gtw.larry.domain.shop.Product;
import ucll.gtw.larry.domain.shop.ProductRepository;
import ucll.gtw.larry.domain.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Collectors;

public class ProductController extends BaseController {

    @Getter private ProductRepository productRepository;

    public ProductController(UserRepository userRepository, ProductRepository productRepository) {
        super(userRepository);
        this.productRepository = productRepository;
    }

    public void handle(String requestAction, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (requestAction.equals("") && request.getMethod().equals("POST")) {
                handleCreate(request, response);
            } else if (requestAction.equals("all")) {
                handleReadAll(request, response);
            }
            else {
                response.getWriter().write("action" + requestAction);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void handleReadAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            Writer writer = response.getWriter();
            String lastString = request.getParameter("last");
            int last = 0;
            if (lastString != null) {
                last = Integer.parseInt(lastString);
            }
            if (last >= getProductRepository().getLastUpdateTimeStamp()) {
                response.getWriter().write("{ \"changes\": false }");
                return;
            }
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            writer.write(gson.toJson(new ProductList(getProductRepository().getAll(), getProductRepository().getLastUpdateTimeStamp())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCreate(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (isAjax(request)) {
                String payload = request.getReader().lines().collect(Collectors.joining());
                Product newProduct = new Gson().fromJson(payload, Product.class);
                getProductRepository().add(newProduct);
                response.getWriter().write("{\"success\":true}");
            } else {
                response.getWriter().write("hi not ajax!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
