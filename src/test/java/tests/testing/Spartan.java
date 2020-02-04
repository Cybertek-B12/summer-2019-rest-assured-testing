package tests.testing;

import com.google.gson.annotations.SerializedName;

/*
{
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
}
 */
public class Spartan {
    @SerializedName("id")
    private Integer spartanID;


    private String name;
    private String gender;
    private Long phone;

    public Spartan() {
    }

    public Spartan(Integer id, String name, String gender, Long phone) {
        this.spartanID = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + spartanID +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }



    public void setSpartanID(Integer spartanID) {
        this.spartanID = spartanID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getSpartanID() {
        return spartanID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Long getPhone() {
        return phone;
    }
}
