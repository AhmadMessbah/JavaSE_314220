package mft.model.service;

import mft.model.entity.Product;
import mft.model.respository.ProductRepository;

import java.io.IOException;
import java.util.List;

// Business Logic
public class ProductService {
    public static void saveAll(List<Product> productList) throws IOException {
        ProductRepository repository = new ProductRepository();
//        repository.saveAll(productList);
    }


    public static List<Product > findAll() throws Exception {
        ProductRepository repository = new ProductRepository();
        return null; // repository.findAll();
    }
}
