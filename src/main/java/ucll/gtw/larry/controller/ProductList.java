package ucll.gtw.larry.controller;

import lombok.Data;
import lombok.Value;
import ucll.gtw.larry.domain.shop.Product;

import java.util.List;

@Value
public class ProductList {
    private List<Product> products;
    private int updateTimeStamp;
    // if I ever need to deserialize it:
    // https://stackoverflow.com/questions/34123721/gson-de-serialization-of-wrapper-class
}
