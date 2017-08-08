package ucll.gtw.larry;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import ucll.gtw.larry.controller.*;
import ucll.gtw.larry.domain.shop.DummyShopData;
import ucll.gtw.larry.domain.shop.ProductRepository;
import ucll.gtw.larry.domain.user.DummyUserData;
import ucll.gtw.larry.domain.user.UserRepository;

/**
 * Contains logic to initialise all classes
 */
public class AppModule {
    public static PicoContainer newContainer() {
        final MutablePicoContainer pico = new DefaultPicoContainer();


        // services / repositories
        UserRepository userRepository = new UserRepository(); // create a userRepositories,
        DummyUserData.addData(userRepository);                // add dummy data,
        pico.addComponent(userRepository);                    // register in ioc

        ProductRepository productRepository = new ProductRepository();
        DummyShopData.addData(productRepository);
        pico.addComponent(productRepository);

        // controllers
        pico.addComponent(ProductController.class);
        pico.addComponent(ShopController.class);
        pico.addComponent(UserController.class);

        // Router
        pico.addComponent(Router.class);

        return pico;
    }
}
