package ucll.gtw.larry;

import com.google.gson.Gson;
import org.picocontainer.PicoContainer;
import ucll.gtw.larry.domain.blog.Comment;
import ucll.gtw.larry.domain.blog.CommentRepository;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@ServerEndpoint("/")
public class AppWebSocket {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    final private PicoContainer pico = AppModule.getInstance().getContainer();
    private CommentRepository commentRepository;

    public AppWebSocket(){
        super();
        commentRepository = pico.getComponent(CommentRepository.class);
    }

    @OnOpen
    public void onOpen(Session session){
        sendMessageToAll("User has connected");
        try {
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session){
        Gson gson = new Gson();
        Comment comment = gson.fromJson(message, Comment.class);
        Properties data = gson.fromJson(message, Properties.class);
        int postId = Integer.parseInt(data.getProperty("postId"));
        commentRepository.add(postId, comment);
        /*
        ideally I take the comment, and send that to all instead, but more work for serialize/deserialize
         */
        sendMessageToAll(message);
    }

    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
    }

    private void sendMessageToAll(String message){
        for(Session s : sessions){
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
