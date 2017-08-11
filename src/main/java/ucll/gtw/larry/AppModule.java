package ucll.gtw.larry;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.behaviors.Caching;
import ucll.gtw.larry.controller.*;
import ucll.gtw.larry.domain.blog.CommentRepository;
import ucll.gtw.larry.domain.blog.DummyBlogData;
import ucll.gtw.larry.domain.blog.PostRepository;
import ucll.gtw.larry.domain.chat.DummyChatData;
import ucll.gtw.larry.domain.chat.MessageRepository;
import ucll.gtw.larry.domain.shop.DummyShopData;
import ucll.gtw.larry.domain.shop.ProductRepository;
import ucll.gtw.larry.domain.user.DummyUserData;
import ucll.gtw.larry.domain.user.UserRepository;

/**
 * Contains logic to initialise all classes
 */
public class AppModule {
    private static AppModule instance = null;
    private PicoContainer picoContainer;
    protected AppModule() {
        System.out.println("loaded instance of app module");
    }

    public static AppModule getInstance() {
        if (instance == null)
            instance = new AppModule();
        return instance;
    }

    public PicoContainer getContainer() {
        if (picoContainer != null) {
            return picoContainer;
        }

        MutablePicoContainer pico = new DefaultPicoContainer(new Caching());

        // services / repositories
        UserRepository userRepository = new UserRepository(); // create a userRepositories,
        DummyUserData.addData(userRepository);                // add dummy data,
        pico.addComponent(userRepository);                    // register in ioc

        ProductRepository productRepository = new ProductRepository();
        DummyShopData.addData(productRepository);
        pico.addComponent(productRepository);

        PostRepository postRepository = new PostRepository();
        CommentRepository commentRepository = new CommentRepository(postRepository);
        DummyBlogData.addData(postRepository, commentRepository, userRepository);
        pico.addComponent(postRepository);
        pico.addComponent(commentRepository);

        MessageRepository messageRepository = new MessageRepository(userRepository);
        DummyChatData.addData(messageRepository);
        pico.addComponent(messageRepository);

        // controllers
        pico.addComponent(ProductController.class);
        pico.addComponent(ShopController.class);
        pico.addComponent(UserController.class);
        pico.addComponent(ChatController.class);

        // Router
        pico.addComponent(Router.class);

        picoContainer = pico;
        return pico;
    }
}
