package day5.school;

public class Address {
    private String city;
    private String state;
    private String street;
    private Integer zipCode;

    public Address(String city, String state, String street, Integer zipCode) {
        this.city = city;
        this.state = state;
        this.street = street;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", street='" + street + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
