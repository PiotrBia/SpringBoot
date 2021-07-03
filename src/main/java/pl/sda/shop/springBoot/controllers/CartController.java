package pl.sda.shop.springBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sda.shop.springBoot.dao.ProductDAO;
import pl.sda.shop.springBoot.model.Cart;
import pl.sda.shop.springBoot.model.Product;

@Controller
public class CartController {

    @Autowired
    private Cart cart;

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/addtocart/{id}")
    public String addProductToCart(@PathVariable int id) {
        Product byId = productDAO.getById(id);
        cart.addProductToCart(byId);
        return "redirect:/product/list";
    }

    @GetMapping("/cart")
    public String getCartList(Model model) {
        model.addAttribute("myCartList", cart.getCartList());
        return "cart";
    }

}
