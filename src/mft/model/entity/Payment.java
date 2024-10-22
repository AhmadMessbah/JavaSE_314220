package mft.model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.PaymentType;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Payment {
    private int id;
    private String title;
    private int amount;
    private String dateTime;
    private String description;
    private PaymentType paymentType;

    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s,%s", id,title,amount,dateTime,description,paymentType);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
