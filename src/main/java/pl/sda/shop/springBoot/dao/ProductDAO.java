package pl.sda.shop.springBoot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sda.shop.springBoot.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductDAO {

//    private List<Product> products;

//    public ProductDAO() {
//        this.products = new ArrayList<>();
//        Product tShirt = new Product(1, "T-shirt", "Cool T-shirt", 29.99, "tshirt.jpg");
//        Product longPants = new Product(2, "Long Pants", "Regular long classic pants", 129.99, "longpants.jpg");
//        Product cap = new Product(3, "cap", "Baseball cap", 9.99, "cap.jpg");
//        products.add(tShirt);
//        products.add(longPants);
//        products.add(cap);
//    }


    // RestTemplate jest wykorzystywany do łączenia i pobierania danych z serwisów restowych
    RestTemplate restTemplate;
    private static String url = "http://localhost:8082/products";

    @Autowired
    public ProductDAO(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getById(long id) {
        ResponseEntity<Product> pullProductById = restTemplate.getForEntity(url + "/" + id, Product.class);
        HttpStatus statusCode = pullProductById.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return pullProductById.getBody();
        } else {
            return null;
        }
//        To jest niepotrzebne - zostało przeniesione na ProductService
//        Optional<Product> any = products.stream().filter(product -> id == product.getId()).findAny();
//        return any.get();
    }

    public List<Product> getAllProducts() {
        ResponseEntity<Product[]> pullProductList = restTemplate.getForEntity(url, Product[].class);
        HttpStatus statusCode = pullProductList.getStatusCode();
        Product[] body = pullProductList.getBody();
        if (statusCode.is2xxSuccessful()) {
            List<Product> collect = Arrays.stream(body).collect(Collectors.toList());
            return collect;
        }
        return null;
    }

    public void addProduct(Product product) {
//        products.add(product);
    }
}
