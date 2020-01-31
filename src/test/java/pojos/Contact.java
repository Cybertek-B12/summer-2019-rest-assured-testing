package pojos;

import com.google.gson.annotations.SerializedName;

public class Contact {

    private int contactId;
    private String emailAddress;
    private String phone;

    @SerializedName("premanentAddress")
    private String permanentAddress;

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", emailAddress='" + emailAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                '}';
    }

    public Contact(int contactId, String emailAddress, String phone, String permanentAddress) {
        this.contactId = contactId;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.permanentAddress = permanentAddress;
    }

    public Contact(String emailAddress, String phone, String permanentAddress) {
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.permanentAddress = permanentAddress;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
}
