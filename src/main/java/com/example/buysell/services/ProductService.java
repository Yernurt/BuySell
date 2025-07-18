package com.example.buysell.services;

import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.repositories.ProductRepository;
import com.example.buysell.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private List<Product> products = new ArrayList<>();

    public List<Product> listProducts(String title) {
        if(title!=null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }


    public  void saveProduct(Principal principal, Product product )  {
        product.setUser(getUserByPrincipal(principal));
        log.info("Saving new Product. Title: {}; Auther email{}",product.getTitle(),product.getUser().getEmail());
        productRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal==null) return new User();
        return userRepository.findByEmail(principal.getName());
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }




    public void patchProduct(Long id, Product partialUpdateProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            if (partialUpdateProduct.getTitle() != null) {
                existingProduct.setTitle(partialUpdateProduct.getTitle());
            }
            if (partialUpdateProduct.getDescription() != null) {
                existingProduct.setDescription(partialUpdateProduct.getDescription());
            }
            if (partialUpdateProduct.getPrice() != null) {
                existingProduct.setPrice(partialUpdateProduct.getPrice());
            }
            if (partialUpdateProduct.getCity() != null) {
                existingProduct.setCity(partialUpdateProduct.getCity());
            }
            productRepository.save(existingProduct);
        }
    }
}
