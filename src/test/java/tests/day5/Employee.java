package day5;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Employee {

    @SerializedName("employee_id")
    private int employeeId;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;
    private int salary;

    public Employee(int employeeId, String firstName, String lastName, int salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employeeId +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
