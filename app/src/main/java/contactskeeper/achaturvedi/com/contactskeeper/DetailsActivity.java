package contactskeeper.achaturvedi.com.contactskeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.util.Log;
import android.widget.Toast;

public class DetailsActivity extends Activity {

    Button modifyButton, deleteButton, addButton;
    EditText fNameField, lNameField, emailField, phoneField;
    LinearLayout buttonContainer;
    ContactDataModel cdm;
    int id;
    private static final String tag="here";

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        addButton=(Button)findViewById(R.id.addButton);
        modifyButton=(Button)findViewById(R.id.modifyButton);
        deleteButton=(Button)findViewById(R.id.deleteButton);


        fNameField=(EditText)findViewById(R.id.newfirstNameField);
        lNameField=(EditText) findViewById(R.id.newlastNameField);
        emailField=(EditText)findViewById(R.id.newemailIdField);
        phoneField=(EditText)findViewById(R.id.newphoneNumberField);
        buttonContainer=(LinearLayout)findViewById(R.id.buttonsContainer);

        Bundle homedata=getIntent().getExtras();
        String action=homedata.getString("action");

        //there would be two different functions here based on the action
        //the corresponding function will be called
        if (action.equals("modify")) {
            cdm = (ContactDataModel)getIntent().getSerializableExtra("dataObject");
            fillDataFields(cdm);
            buttonContainer.setVisibility(ViewGroup.VISIBLE);
            addButton.setVisibility(View.INVISIBLE);
            getActionBar().setTitle("Modify/Remove Contact");
        }else if (action.equals("add")){
            buttonContainer.setVisibility(ViewGroup.INVISIBLE);
            addButton.setVisibility(View.VISIBLE);
            getActionBar().setTitle("Add New Contact");
        }else {
            return;
        }

        Log.i(tag, "firstname");
        addButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Log.i(tag, "firstname");
                        addDataToFile();
                    }
                }
        );

        modifyButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Log.i(tag, "firstname");
                        modifyDataInFile(cdm);
                    }
                }
        );

        deleteButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Log.i(tag, "firstname");
                        deleteData();

                    }
                }
        );

//        Button cancelButton=(Button)findViewById(R.id.newcancelButton);
//        cancelButton.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        DetailsActivity.this.finish();
//                    }
//                }
//        );

    }


    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    //adds the field data to the txt file
    public void addDataToFile() {
        if (validateFields()) {
            ContactDataModel cdm = new ContactDataModel();
            FileWriter fw=new FileWriter(this.getApplicationContext());

            cdm.setFname(fNameField.getText().toString());
            cdm.setLname(lNameField.getText().toString());
            cdm.setEmail(emailField.getText().toString());
            cdm.setPhone(phoneField.getText().toString());

            //int id=Integer.parseInt(fw.getMaxId());
            if(fw.getMaxId() == null){
                id=1;
            }else{
                id=Integer.parseInt(fw.getMaxId());
            }
            cdm.setId(String.valueOf(id+1));

            ArrayList<ContactDataModel> contactList=fw.getContactObject();
            contactList.add(cdm);

            fw.setContactObject(contactList);

            //removeDataFromFields();
            Toast.makeText(getApplicationContext(), "Contact saved", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    /*read this particular object from the fields and modify the arraylist and write to
    file*/
    public void modifyDataInFile(ContactDataModel cdm) {
        if (validateFields()) {
            //first read all data from the file and
           // ArrayList<ContactDataModel> contactDataModelArrayList = new ArrayList<>();
            FileWriter fw=new FileWriter(this.getApplicationContext());

            final ArrayList<ContactDataModel> dataList=fw.getContactObject();
            int i = 0;
            for (ContactDataModel temp:dataList)
            {
                if(temp.getId().equals(cdm.getId())) {
                    //modify this item and add modified item in the list
                    //temp = dataList.get(i);
                    dataList.get(i).setFname(fNameField.getText().toString());
                    dataList.get(i).setLname(lNameField.getText().toString());
                    dataList.get(i).setEmail(emailField.getText().toString());
                    dataList.get(i).setPhone(phoneField.getText().toString());

                    break;
                }
                i++;
            }
            fw.setContactObject(dataList);
            //removeDataFromFields();
            Toast.makeText(getApplicationContext(), "Contact modified", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    public void deleteData() {
        if (validateFields()) {
            //first read all data from the file and
            // ArrayList<ContactDataModel> contactDataModelArrayList = new ArrayList<>();
            FileWriter fw=new FileWriter(this.getApplicationContext());

            final ArrayList<ContactDataModel> dataList=fw.getContactObject();
            int i = 0;
            for (ContactDataModel temp:dataList)
            {
                if(temp.getId().equals(cdm.getId())) {
                    //remove this item from the list
                    dataList.remove(i);
                    break;
                }
                i++;
            }
            fw.setContactObject(dataList);
            //removeDataFromFields();
            Toast.makeText(getApplicationContext(), "Contact removed", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //Name: Aditya Mahajan
    //NetId:axm156630@utdallas.edu
    //Date:11/1/2015
    //checks whether the firstname has been filled or not
    public boolean validateFields() {


        EditText firstName = (EditText) findViewById(R.id.newfirstNameField);
        if (firstName.getText().toString().length() == 0) {

            firstName.setError("First name is required!");
            return false;
        }

//        EditText emailId = (EditText) findViewById(R.id.newemailIdField);
//        if(emailId.getText().toString().length() == 0)
//        {
//            if (!isEmailValid(emailId.toString())) {
//                emailId.setError("Please insert correct email id");
//                return false;
//            }
//        }
//
//        EditText phone = (EditText) findViewById(R.id.newphoneNumberField);
//        if (!isValidPhoneNumber(phone.toString())) {
//            phone.setError("Phone number is invalid");
//            return false;
//        }

        return true;
    }


    //Name: Aditya Mahajan
    //NetId:axm156630@utdallas.edu
    //Date:11/1/2015
    //checks the validity of the email id
    public boolean isEmailValid(String email)
    {
        if (email == null || TextUtils.isEmpty(email)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }


    //Name: Aditya Mahajan
    //NetId:axm156630@utdallas.edu
    //Date:11/1/2015
    //checks the validity of the phone number
    public  boolean isValidPhoneNumber(String target) {
        if (target == null || TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    /* This function will fill all the data fields with the passed object values */
    public void fillDataFields(ContactDataModel cdm) {
        fNameField.setText(cdm.getFname());
        lNameField.setText(cdm.getLname());
        emailField.setText(cdm.getEmail());
        phoneField.setText(cdm.getPhone());
    }
    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    //removes all the data from the fields
    public void removeDataFromFields() {
        fNameField.setText("");
        lNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
