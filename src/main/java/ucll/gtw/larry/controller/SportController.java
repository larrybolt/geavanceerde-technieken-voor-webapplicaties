package ucll.gtw.larry.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ucll.gtw.larry.domain.chat.MessageRepository;
import ucll.gtw.larry.domain.shop.Product;
import ucll.gtw.larry.domain.shop.ProductRepository;
import ucll.gtw.larry.domain.sport.Activity;
import ucll.gtw.larry.domain.user.User;
import ucll.gtw.larry.domain.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SportController extends BaseController {
    @Getter private ProductRepository productRepository;
    @Getter private MessageRepository messageRepository;

    private ArrayList<Activity> activities = new ArrayList<>();

    public SportController(UserRepository userRepository, ProductRepository productRepository, MessageRepository messageRepository) {
        super(userRepository);
        this.productRepository = productRepository;
        this.messageRepository = messageRepository;
        activities.add(new Activity("Maandag", "18:00", "Hockey", "Miyo", "Boom"));
    }

    public void handle(String requestAction, HttpServletRequest request, HttpServletResponse response) {
        isLoggedIn(request);
        if (requestAction.equals("") && request.getMethod().equals("GET")) {
            handleIndex(request, response);
        }
        else if (requestAction.equals("all") && request.getMethod().equals("GET")) {
            handleAll(request, response);
        }
        else if (requestAction.equals("") && request.getMethod().equals("POST")) {
            handleAdd(request, response);
        }
        /*
        else if (requestAction.equals("") && request.getMethod().equals("GET")) {
            handleGetMessages(request, response);
        }
        else if (requestAction.equals("users") && request.getMethod().equals("GET")) {
            handleGetUsers(request, response);
        }
        */
    }

    public void handleIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            //request.setAttribute("messages", getMessageRepository().getForUser(getUser(request)));
            request.getRequestDispatcher("/sport.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAdd(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (isAjax(request)) {
                String payload = request.getReader().lines().collect(Collectors.joining());
                Activity newActivity = new Gson().fromJson(payload, Activity.class);
                activities.add(newActivity);
                response.getWriter().write("{\"success\":true}");
            } else {
                response.getWriter().write("hi not ajax!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Writer writer = response.getWriter();
            response.setContentType("application/json");
            writer.write(gson.toJson(this.activities));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
