package mft.model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Contact {
    private int id;
    private String state;
    private String city;
    private String region;
    private String address;
    private String postalCode;
    private String phone;
    private String description;

    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s", id,state,city,region,address,postalCode,phone,description);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}