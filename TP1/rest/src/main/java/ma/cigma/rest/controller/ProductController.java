package ma.cigma.rest.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import ma.cigma.rest.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ma.cigma.rest.service.model.Product;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService ProductService;


    @GetMapping(value = "/api/products")
    public List<Product> getAll() {
        return ProductService.getAll();
    }

    @GetMapping(value = "/api/products/{id}")
    public Product getById(@PathVariable Long id) {
        return ProductService.getById(id);
    }

    @PostMapping(value = "/api/products")
    public ResponseEntity<Object> create(@Validated @RequestBody Product product) {
        ProductService.create(product);
        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/product/{id}")
    public ResponseEntity<Object> update(@PathVariable (name = "id") Long id,@RequestBody Product product) {
        Product productfound = ProductService.getById(id);
        if(productfound == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        ProductService.update(id, product);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/products/{id}")
    public ResponseEntity<Object> delete(@PathVariable (name = "id") Long id) {
        Product productfound = ProductService.getById(id);
        if(productfound == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        ProductService.delete(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }

    public IProductService getProductService() {
        return ProductService;
    }

    public void setProductService(IProductService productService) {
        this.ProductService = productService;
    }
}
