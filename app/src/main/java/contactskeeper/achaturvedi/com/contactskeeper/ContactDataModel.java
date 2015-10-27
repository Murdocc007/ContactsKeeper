package contactskeeper.achaturvedi.com.contactskeeper;

/**
 * Created by Akash on 10/27/2015.
 */
public class ContactDataModel {
    private String fname;
    private String lname;
    private String email;
    private String phone;

    //temporary constructor
    ContactDataModel() {
        fname = "RaviKant";
        lname = "Pandey";
        phone = "(469)774-6884";
    }
    //setters
    public void setFname(String fName) {
        this.fname = fName;
    }

    public void setLname(String lName) {
        this.lname = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //getters
    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }
}
