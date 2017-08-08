package ucll.gtw.larry;

import org.picocontainer.PicoContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello world!
 *
 */
@WebServlet("/")
public class App extends HttpServlet
{
    final private PicoContainer pico = AppModule.newContainer();
    private Router router;

    public App() {
        super();
        router = pico.getComponent(Router.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        router.handle(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        router.handle(request, response);
    }
}
