package day5.school;

public class Student {
    private Integer admissionNo;
    private Integer batch;
    private String birthDate;
    private Company company;
    private Contact contact;
    private String firstName;
    private String gender;
    private String joinDate;
    private String lastName;
    private String major;
    private String password;
    private String section;
    private String subject;


    public Student(Integer admissionNo, Integer batch, String birthDate, Company company, Contact contact, String firstName, String gender, String joinDate, String lastName, String major, String password, String section, String subject) {
        this.admissionNo = admissionNo;
        this.batch = batch;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.gender = gender;
        this.joinDate = joinDate;
        this.lastName = lastName;
        this.major = major;
        this.password = password;
        this.section = section;
        this.subject = subject;
        this.company=company;
        this.contact=contact;
    }

    @Override
    public String toString() {
        return "Student{" +
                "admissionNo=" + admissionNo +
                ", batch=" + batch +
                ", birthDate='" + birthDate + '\'' +
                ", company=" + company +
                ", contact=" + contact +
                ", firstName='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", lastName='" + lastName + '\'' +
                ", major='" + major + '\'' +
                ", password='" + password + '\'' +
                ", section='" + section + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
