package ucll.gtw.larry.domain.shop;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data public class Product implements Comparable<Product> {
    private int productId;
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private String imageUrl;
    @NonNull private BigDecimal price;
    @NonNull private String stock;

    @Override
    public int compareTo(Product product) {
        return product.getProductId()-this.getProductId();
    }
}
