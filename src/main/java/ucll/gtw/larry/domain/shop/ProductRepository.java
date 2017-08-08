package ucll.gtw.larry.domain.shop;

import java.math.BigDecimal;
import java.util.*;

public class ProductRepository {
    private Map<Integer, Product> products = new TreeMap<>();

    public ProductRepository() {
        add(new Product(
                "Tesla Roadster",
                "Tesla Roadster Super Card",
                "/static/photos/roadster.png",
                BigDecimal.valueOf(101500),
                "in stock"
        ));
        add(new Product(
                "Macbook Pro",
                "Apple's icons Macbook Pro",
                "/static/photos/macbook.jpeg",
                BigDecimal.valueOf(1200),
                "in stock"
        ));
        add(new Product(
                "iPhone",
                "The iPhone, first device with a decent touch-screen.",
                "/static/photos/iphone.jpeg",
                BigDecimal.valueOf(800),
                "in stock"
        ));
        add(new Product(
                "Bose Sound System",
                "The best sound, for the best (ahum) highest price.",
                "/static/photos/bose.jpg",
                BigDecimal.valueOf(550),
                "in stock"
        ));
        add(new Product(
                "SpaceX Falcon Heavy",
                "How long will it take before we will be able to buy a ticket to mars?",
                "/static/photos/falcon.png",
                BigDecimal.valueOf(80000000),
                "in stock"
        ));
        add(new Product(
                "Macbook rain dock",
                "This is one of those things that is so handy, once you have it",
                "/static/photos/mstand.jpg",
                BigDecimal.valueOf(40),
                "none left"
        ));
    }

    // C
    public void add(Product p) {
        int productId = products.size()+1;
        p.setProductId(productId);
        products.put(p.getProductId(), p);
    }

    // R
    public Product get(int productId) {
        return products.get(productId);
    }

    public ArrayList<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    public ArrayList<Product> getFrom(int from) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product p : this.products.values()) {
            if (p.getProductId() >= from)
                products.add(p);
        }
        return products;
    }

    // U
    public void update(Product p){
        products.replace(p.getProductId(), p);
    }

    // D
    public void remove(int productId) {
        products.remove(productId);
    }
}
