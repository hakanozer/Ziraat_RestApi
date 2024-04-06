package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductRestController {

    final ProductService productService;

    @PostMapping("add")
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @GetMapping("list")
    public List<Product> list() {
       return productService.list();
    }

    @GetMapping("single/{pid}")
    public Product single(@PathVariable Long pid) {
        return productService.single(pid);
    }

}
