package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Product;
import com.ecommerce.app.paging.ProductPaging;
import com.ecommerce.app.search.ProductCriteriaSearch;
import com.ecommerce.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allProducts")
    public ResponseEntity<Page<Product>> findAll(ProductPaging productPaging , ProductCriteriaSearch productCriteriaSearch) {
        return new ResponseEntity<>(productService.findAll(productPaging , productCriteriaSearch) , HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> products() {
        return new ResponseEntity<>(productService.listProducts() , HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/{promotionId}/listProducts")
    public ResponseEntity<List<Product>> listProduct(@PathVariable Long categoryId , @PathVariable Long promotionId) {
        return new ResponseEntity<>(productService.listAll(categoryId , promotionId) , HttpStatus.OK);
    }

    @PostMapping("/{categoryId}/{promotionId}/save")
    public ResponseEntity<Product> saveProduct(@ModelAttribute Product product,
                                               @PathVariable Long categoryId,
                                               @PathVariable Long promotionId,
                                               @RequestParam MultipartFile file) throws IOException {
        Product createProduct = productService.save(categoryId, promotionId ,  product , file);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("{categoryId}/{promotionId}/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id,
                          @PathVariable Long categoryId,
                           @PathVariable Long promotionId) {
        return new ResponseEntity<>(productService.get(id , categoryId , promotionId) , HttpStatus.OK);
    }
    @PutMapping("{categoryId}/{promotionId}/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id ,
                                                 @PathVariable Long categoryId ,
                                                 @PathVariable Long promotionId,
                                                 @RequestParam MultipartFile file
            , @ModelAttribute Product product) throws IOException {

        Product updateProduct = productService.update(categoryId , promotionId , id , product , file);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(updateProduct);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
