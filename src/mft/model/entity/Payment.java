package mft.model.entity;
import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mft.model.entity.PaymentType;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Payment {
    private int id;
    private String title;
    private int amount;
    private LocalDate dateTime;
    private String description;
    private PaymentType paymentType;




    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
