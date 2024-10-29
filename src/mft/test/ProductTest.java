package mft.test;

import mft.model.entity.Product;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;
import mft.model.service.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ProductTest {
    public static void main(String[] args) throws Exception {
//        System.out.println(ProductService.findAll());

        Product product1 =
                Product
                        .builder()
                        .name("Asus 1230")
                        .price(1800)
                        .quantity(3)
                        .discount(0)
                        .category(Category.Electrical)
                        .transactionType(TransactionType.outcome)
                        .expireDate(LocalDate.now())
                        .image(false)
                        .catalogue(true)
                        .build();

//        System.out.println(ProductService.findAll());
        System.out.println(ProductService.findById(2));
        System.out.println(ProductService.findById(200));
    }
}
