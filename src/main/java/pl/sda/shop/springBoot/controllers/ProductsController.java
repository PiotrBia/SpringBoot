package pl.sda.shop.springBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.sda.shop.springBoot.dao.ProductDAO;
import pl.sda.shop.springBoot.model.Product;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ProductsController {

    @Autowired
    private ProductDAO productDAO;

    @Value("${upload.file}")
    public String uploadDir;

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("myList", productDAO.getAllProducts());
        return "product_list";
    }

    // Jeżeli {productId} jest takie samo, to nie trzeba przy @PathVariable dodatkowo określać do czego będziemy przypisywać.
    // Jeżeli jest kilka albo nazwy są różne to należy określić @PathVariable('nazwa')
    @GetMapping("/product/{productId}")
    public String getProductDetailsById(Model model, @PathVariable int productId) {
        for (Product product : productDAO.getAllProducts()) {
            if (product.getId() == productId) {
                model.addAttribute("myProduct", product);
                break;
            }
        }
        return "myProductById";
    }

    @GetMapping("/admin")
    public String getProductForm(Model model){
        model.addAttribute("newProduct", new Product());
        return "new_product_form";
    }

    @PostMapping("/addProduct")
    public String newProductFromForm(Product product,@RequestParam("imageFile") MultipartFile file) {
        productDAO.addProduct(product);
        uploadFile(file);
        return "redirect:/products";
    }

    private void uploadFile(MultipartFile file) {
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
