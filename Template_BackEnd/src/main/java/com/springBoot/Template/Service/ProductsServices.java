package com.springBoot.Template.Service;

import com.springBoot.Template.Model.Products;
import com.springBoot.Template.Repository.ProductsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.logging.Logger;

// Products Service Class
@Service
public class ProductsServices {

    // Logging Configuration For This Class
    private static final Logger logger = Logger.getLogger(ProductsServices.class.getName());

    // Repository Class Dependency
    private final ProductsRepository repository;

    // Constructor injection for Repository Class Dependency
    public ProductsServices(ProductsRepository repository) {
        this.repository = repository;
    }

    // Get All Products Services Method
    public ResponseEntity<List<Products>> getProducts() {
        try {
            productsInsert();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
            List<Products> products = repository.findAll();
        if(products.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get a single product by name
    public ResponseEntity<Products> getOneProducts(String name) {
        Optional<Products> product = repository.findByName(name.substring(9, name.length() - 2));
        return product.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Search for products based on partial name match
    public ResponseEntity<List<String>> SearchProducts(String searchName) {
        List<Products> products = repository.findByNameContainingIgnoreCase(searchName);
        return ResponseEntity.ok(products.stream().map(Products::getName).collect(Collectors.toList()));
    }

    // If Products Table is Empty that Method Insert Data For Table
    public void productsInsert() throws IOException {
        List<Products> products = repository.findAll();
        if (products.isEmpty()) {
            Path path = Paths.get("../ProductsImages/download.jpg");
            List<Products> productsList = new ArrayList<>();
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                long id = 1L;
                String productName = "Product-" + ch;
                int price = generateRandomOddNumber();
                byte[] imageBytes = Files.readAllBytes(path);
                productsList.add(new Products(id ,imageBytes, productName, price, new Date(System.currentTimeMillis())));
                ++id;
            }
            repository.saveAll(productsList);
        } else {
            logger.info("Already Products Inserted ......!");
        }
    }

    // Generate Random Prices Number for Products Prices Method
    private int generateRandomOddNumber() {
        int price = ThreadLocalRandom.current().nextInt(1, 101);
        return price % 2 == 0 ? price + 1 : price;
    }

}

