package contactskeeper.achaturvedi.com.contactskeeper;

import java.io.Serializable;
import java.util.Comparator;

//Name: Akash Chaturvedi
//NetId:axc144430@utdallas.edu
//Date:11/1/2015
//this class stores the contact details as an object
public class ContactDataModel implements Comparable,Serializable {
    private String fname;//firstname
    private String lname;//lastname
    private String email;//emailid
    private String phone;//phonenumber
    private String id;//unique id



    //setters

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/20
    public void setFname(String fName) {
        this.fname = fName;
    }


    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/20
    public void setLname(String lName) {
        this.lname = lName;
    }

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/20
    public void setEmail(String email) {
        this.email = email;
    }

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/20
    public void setPhone(String phone) {
        this.phone = phone;
    }

    //getters

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/1/2015
    public String getFname() {
        return this.fname;
    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/1/2015
    public String getLname() {
        return this.lname;
    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/1/2015
    public String getEmail() {
        return this.email;
    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/1/2015
    public String getPhone() {
        return this.phone;
    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/1/2015
    public String getId() {
        return id;
    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/1/2015
    public void setId(String id) {
        this.id = id;
    }

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/20
    //this function is used to sort the contacts in ascending order
    public static Comparator<ContactDataModel> firstNameComparator = new Comparator<ContactDataModel>() {

        public int compare(ContactDataModel c1, ContactDataModel c2) {
            String fName1 = c1.getFname().toUpperCase();
            String fName2 = c2.getFname().toUpperCase();

            //ascending order
            return fName1.compareTo(fName2);

            //descending order
            //return fName2.compareTo(fName1);
        }};

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/1/2015
    @Override
    public int compareTo(Object another) {
        return 0;
    }
}
