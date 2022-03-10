package com.akram.product.service;

import com.akram.product.dto.product.ProductDto;
import com.akram.product.exception.ProductIdNotFoundException;
import com.akram.product.exception.ProductNameFoundException;
import com.akram.product.exception.ProductNameNotFoundException;
import com.akram.product.model.Product;
import com.akram.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<ProductDto> getAll() {
        List<ProductDto> allProducts = new ArrayList<>();
        for (Product p : productRepo.findAll()) {
            allProducts.add(ProductDto.builder()
                    .id(p.getId())
                    .balance(p.getBalance())
                    .name(p.getName())
                    .price(p.getPrice())
                    .build());
        }
        return allProducts;
    }

    public String addProduct(ProductDto productDto) {
        try {
            getByName(productDto.getName());
            throw new ProductNameFoundException(productDto.getName());
        } catch (ProductNameNotFoundException ex) {


            Product product = Product.builder()
                    .name(productDto.getName())
                    .price(productDto.getPrice())
                    .balance(productDto.getBalance())
                    .build();
            product = productRepo.save(product);
            return "Product added successfully with id " + product.getId();
        }
    }

    Product getByName(String name) {
        Optional<Product> product = productRepo.findByName(name);
        if (product.isPresent() == false)
            throw new ProductNameNotFoundException(name);
        return product.get();
    }

    public String delete(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent())
            throw new ProductIdNotFoundException(id);
        productRepo.deleteById(id);
        return "Product deleted successfully";
    }

    public Product get(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        if (!product.isPresent())
            throw new ProductIdNotFoundException(productId);
        return product.get();
    }

    void deductBalance(Product product, Integer count) {
        product.setBalance(product.getBalance() - count);
        productRepo.save(product);
    }

    void deductBalance(Long productId, Integer count) {
        Product product = get(productId);
        product.setBalance(product.getBalance() - count);
        productRepo.save(product);
    }

    void adjustBalance(Product product, Integer count) {
        product.setBalance(product.getBalance()+count);
        productRepo.save(product);
    }
}
