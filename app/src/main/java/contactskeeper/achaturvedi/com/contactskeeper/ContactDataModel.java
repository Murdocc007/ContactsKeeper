package contactskeeper.achaturvedi.com.contactskeeper;

import java.util.Comparator;

/**
 * Created by Akash on 10/27/2015.
 */
public class ContactDataModel implements Comparable {
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



    public static Comparator<ContactDataModel> firstNameComparator = new Comparator<ContactDataModel>() {

        public int compare(ContactDataModel c1, ContactDataModel c2) {
            String fName1 = c1.getFname().toUpperCase();
            String fName2 = c2.getFname().toUpperCase();

            //ascending order
            return fName1.compareTo(fName2);

            //descending order
            //return fName2.compareTo(fName1);
        }};

    @Override
    public int compareTo(Object another) {
        return 0;
    }
}
