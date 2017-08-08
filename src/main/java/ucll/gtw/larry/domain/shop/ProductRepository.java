package ucll.gtw.larry.domain.shop;

import java.math.BigDecimal;
import java.util.*;

public class ProductRepository {
    private Map<Integer, Product> products = new TreeMap<>();

    public ProductRepository() {

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
