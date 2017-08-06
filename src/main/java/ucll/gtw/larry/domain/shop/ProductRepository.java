package ucll.gtw.larry.domain.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private Map<Integer, Product> products = new HashMap<>();

    // C
    public void add(Product p) {
        int productId = products.size();
        p.setProductId(productId);
        products.put(p.getProductId(), p);
    }

    // R
    public Product get(int productId) {
        return products.get(productId);
    }

    public List<Product> getAll() {
        return (List<Product>) products.values();
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
