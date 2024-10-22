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
        System.out.println(ProductService.findAll());

        Product product1 =
                Product
                        .builder()
                        .id(1)
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


        String line = "2,mobile,7000,3,Electrical,2024-12-15,0,true,true,outcome";
        Product product2 = new Product(line);


        System.out.println(product1);
        System.out.println(product2);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        ProductService.saveAll(productList);
        System.out.println(ProductService.findAll());

        System.out.println(product1);
        System.out.println(product1.toCsv());

    }
}
