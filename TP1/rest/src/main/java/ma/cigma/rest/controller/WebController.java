package ma.cigma.rest.controller;

import ma.cigma.rest.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import ma.cigma.rest.service.model.Product;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private IProductService productService;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
    //when can we use RestTemplate container instead of the service directly
    @GetMapping(value = "/products")
    public String ListProducts(Model model)
    {
        String url = "http://localhost:8080/api/products";
        List <Product> products = productService.getAll();

        model.addAttribute("products", products);
        return "products";
    }
}
