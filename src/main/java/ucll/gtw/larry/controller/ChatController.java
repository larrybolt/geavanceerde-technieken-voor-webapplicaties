package ucll.gtw.larry.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import ucll.gtw.larry.domain.chat.Message;
import ucll.gtw.larry.domain.chat.MessageRepository;
import ucll.gtw.larry.domain.user.Role;
import ucll.gtw.larry.domain.user.User;
import ucll.gtw.larry.domain.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class ChatController extends BaseController {

    @Getter private MessageRepository messageRepository;

    public ChatController(UserRepository userRepository, MessageRepository messageRepository) {
        super(userRepository);
        this.messageRepository = messageRepository;
    }

    public void handle(String requestAction, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!isLoggedIn(request)) {
                response.sendRedirect("/login");
                return;
            }
            if (requestAction.equals("") && request.getMethod().equals("POST")) {
                handleMessage(request, response);
            }
            else if (requestAction.equals("") && request.getMethod().equals("GET")) {
                handleGetMessages(request, response);
            }
            else if (requestAction.equals("users") && request.getMethod().equals("GET")) {
                handleGetUsers(request, response);
            }
            else {
                response.getWriter().write("action" + requestAction);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void handleGetUsers(HttpServletRequest request, HttpServletResponse response) {
        Writer writer = null;
        try {
            if (getUser(request).getRole() != Role.ADMIN) {
                writer.write("{\"error\": \"Only administrators can get a list of all users\"}");
                return;
            }
            writer = response.getWriter();
            ArrayList<User> users = (ArrayList<User>)(getUserRepository().getAll());
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            writer.write(gson.toJson(users));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleGetMessages(HttpServletRequest request, HttpServletResponse response) {
        Writer writer = null;
        try {
            writer = response.getWriter();
            String lastString = request.getParameter("last");
            int last = 0;
            if (lastString != null) {
                last = Integer.parseInt(lastString);
            }
            ArrayList<Message> messages = getMessageRepository().getForUser(getUser(request), last);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            writer.write(gson.toJson(messages));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!isLoggedIn(request)) {
                response.sendRedirect("/login");
                return;
            }
            if (isAjax(request)) {
                String payload = request.getReader().lines().collect(Collectors.joining());
                Properties postData = new Gson().fromJson(payload, Properties.class);
                String message = postData.getProperty("message");
                int fromUserId = getUser(request).getUserId();
                int toUserId;
                if (postData.getProperty("toUserId") != null && getUser(request).getRole() == Role.SUPPORT) {
                    toUserId = Integer.parseInt(postData.getProperty("toUserId"));
                } else {
                    toUserId = getMessageRepository().getSupportUserIdForUser(fromUserId);
                }
                getMessageRepository().add(fromUserId, toUserId, message);
                response.getWriter().write("{\"success\":true}");
            } else {
                response.getWriter().write("hi not ajax!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
