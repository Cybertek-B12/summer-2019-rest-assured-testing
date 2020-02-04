package day5;

public class Spartan {
    private String gender;
    private int phone;

    @Override
    public String toString() {
        return "Spartan{" +
                "gender='" + gender + '\'' +
                ", phone=" + phone +
                ", name='" + name + '\'' +
                '}';
    }

    public Spartan(String gender, int phone, String name) {
        this.gender = gender;
        this.phone = phone;
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String  name;


}
