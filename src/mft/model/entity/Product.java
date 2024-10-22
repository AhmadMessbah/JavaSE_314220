package mft.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;


import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private Category category;
    private LocalDate expireDate;
    private boolean discount;
    private boolean catalogue;
    private boolean image;
    private TransactionType transactionType;

    public Product(String data) {
        String[] productData = data.split(",");
        id = Integer.parseInt(productData[0]);
        name = productData[1];
        price = Integer.parseInt(productData[2]);
        quantity = Integer.parseInt(productData[3]);
        category = Category.valueOf(productData[4]);
        expireDate = LocalDate.parse(productData[5]);
        discount = Integer.parseInt(productData[6]) != 0;
        catalogue = Boolean.parseBoolean(productData[7]);
        image = Boolean.parseBoolean(productData[8]);
        transactionType = TransactionType.valueOf(productData[9]);
    }

    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", id, name, price, quantity, category, expireDate, discount, catalogue, image, transactionType);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
