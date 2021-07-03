package pl.sda.shop.springBoot.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private List<Product> cartList;

    public Cart() {
        this.cartList = new ArrayList<>();
    }

    public List<Product> getCartList() {
        return cartList;
    }

    public void addProductToCart(Product product) {
        cartList.add(product);
    }

}
