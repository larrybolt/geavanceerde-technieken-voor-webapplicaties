package ucll.gtw.larry.domain.shop;

import java.math.BigDecimal;

public class DummyShopData {
    public static void addData(ProductRepository productRepository){
        productRepository.add(new Product(
                "Tesla Roadster",
                "Tesla Roadster Super Card",
                "/static/photos/roadster.png",
                BigDecimal.valueOf(101500),
                "in stock"
        ));
        productRepository.add(new Product(
                "Macbook Pro",
                "Apple's icons Macbook Pro",
                "/static/photos/macbook.jpeg",
                BigDecimal.valueOf(1200),
                "in stock"
        ));
        /*
        productRepository.add(new Product(
                "iPhone",
                "The iPhone, first device with a decent touch-screen.",
                "/static/photos/iphone.jpeg",
                BigDecimal.valueOf(800),
                "in stock"
        ));
        productRepository.add(new Product(
                "Bose Sound System",
                "The best sound, for the best (ahum) highest price.",
                "/static/photos/bose.jpg",
                BigDecimal.valueOf(550),
                "in stock"
        ));
        productRepository.add(new Product(
                "SpaceX Falcon Heavy",
                "How long will it take before we will be able to buy a ticket to mars?",
                "/static/photos/falcon.png",
                BigDecimal.valueOf(80000000),
                "in stock"
        ));
        productRepository.add(new Product(
                "Macbook rain dock",
                "This is one of those things that is so handy, once you have it",
                "/static/photos/mstand.jpg",
                BigDecimal.valueOf(40),
                "none left"
        ));
        */
    }
}
