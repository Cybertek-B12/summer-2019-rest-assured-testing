package day5.school;

public class Company {
    private String companyName;
    private String startDate;
    private String title;
    private Address address;

    public Company(Address address, String companyName, String startDate, String title) {
        this.companyName=companyName;
        this.startDate = startDate;
        this.title = title;
        this.address = address;
    }

    @Override
    public String toString() {
        return "CompanyAddress{" +
                "companyName='" + companyName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", title='" + title + '\'' +
                ", address=" + address +
                '}';
    }
}
