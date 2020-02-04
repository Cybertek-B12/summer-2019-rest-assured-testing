package day5;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location {
    private Integer location_id;
    private String street_address;
    private String postal_code;
    private String city;
    private String state_province;
    private String country_id;

    public Location(Integer location_id, String street_address, String postal_code, String city, String state_province, String country_id) {
        this.location_id = location_id;
        this.street_address = street_address;
        this.postal_code = postal_code;
        this.city = city;
        this.state_province = state_province;
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "location_id=" + location_id +
                ", street_address='" + street_address + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", city='" + city + '\'' +
                ", state_province='" + state_province + '\'' +
                ", country_id='" + country_id + '\'' +
                '}';
    }
}
