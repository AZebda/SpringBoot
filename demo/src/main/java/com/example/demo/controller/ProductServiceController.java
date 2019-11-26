package com.example.demo.controller;
 
import com.example.demo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.HashMap;
import java.util.Map;
 
 
@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo;
    static {
        productRepo = new HashMap<>();
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Product1");
        productRepo.put(honey.getId(), honey);
 
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Product2");
        productRepo.put(almond.getId(), almond);
    }

    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product with id:" + product.getId() + " is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product with id:" + product.getId() + " is updated successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product with id:" + id + " is deleted successsfully", HttpStatus.OK);
    }
}