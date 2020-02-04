package day5.school;

import com.google.gson.annotations.SerializedName;

public class Contact {
    private String emailAddress;
    private String phone;
    @SerializedName("premanentAddress")
    private String permanentAddress;

    public Contact(String emailAddress, String phone, String premanentAddress) {
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.permanentAddress = premanentAddress;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "emailAddress='" + emailAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", premanentAddress='" + permanentAddress + '\'' +
                '}';
    }
}
