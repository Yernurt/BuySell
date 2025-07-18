/*package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String products(@RequestParam(name="title", required = false) String title, Model model, Principal principal){
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("product/{id}")
    @Transactional
    public String productInfo(@PathVariable Long id, Model model){
        Product product=productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(Product product,Principal principal) throws IOException {
        productService.saveProduct(principal, product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }



    @PatchMapping("product/update/{id}")
    public String patchProduct(@PathVariable Long id, @ModelAttribute Product partialUpdateProduct) {
        productService.patchProduct(id, partialUpdateProduct);
        return "redirect:/" ;
    }
}*/

package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String title) {
        return productService.listProducts(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product, Principal principal) {
        productService.saveProduct(principal, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product partialUpdateProduct) {
        productService.patchProduct(id, partialUpdateProduct);
        return ResponseEntity.ok(partialUpdateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}



