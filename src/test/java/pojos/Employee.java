package pojos;

import com.google.gson.annotations.SerializedName;

public class Employee {

    @SerializedName("employee_id")
    private String  employeeId;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private  String email;

    @SerializedName("phone_number")
    private String phoneNumber;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId + '\n' +
                ", firstName=" + firstName + '\n' +
                ", lastName=" + lastName + '\n' +
                ", email=" + email + '\n' +
                ", phoneNumber=" + phoneNumber + '\n' +
                '}';
    }

    public Employee(String employeeId, String firstName, String lastName, String email, String phoneNumber) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
