package mft.test;

import mft.model.entity.Product;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;

import java.time.LocalDate;

public class ProductTest {
    public static void main(String[] args) {
        Product product =
                Product
                        .builder()
                        .id(1)
                        .name("Asus 1230")
                        .price(1800)
                        .quantity(3)
                        .discount(false)
                        .category(Category.Electrical)
                        .transactionType(TransactionType.outcome)
                        .expireDate(LocalDate.now())
                        .image(false)
                        .catalogue(true)
                        .build();

        System.out.println(product);

//        String line = "2,mobile,7000,3,Electrical,2024-12-15,0,true,true,outcome";
//
//        Product product = new Product(line);
//
//        System.out.println(product);
//
//        System.out.println(product.toCsv());

    }
}
