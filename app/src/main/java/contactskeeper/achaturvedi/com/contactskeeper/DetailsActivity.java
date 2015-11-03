package contactskeeper.achaturvedi.com.contactskeeper;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
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
    int id;
    private static final String tag="here";

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
            ContactDataModel cdm = (ContactDataModel)getIntent().getSerializableExtra("dataObject");
            fillDataFields(cdm);
            buttonContainer.setVisibility(ViewGroup.VISIBLE);
            addButton.setVisibility(View.INVISIBLE);
        }else if (action.equals("add")){
            buttonContainer.setVisibility(ViewGroup.INVISIBLE);
            addButton.setVisibility(View.VISIBLE);
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
                        modifyDataInFile();
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

            removeDataFromFields();
            Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT);

        }
    }

    /*read this particular object from the fields and modify the arraylist and write to
    file*/
    public void modifyDataInFile() {
        if (validateFields()) {

        }
    }

    public void deleteData() {

    }
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

    public boolean isEmailValid(String email)
    {
        if (email == null || TextUtils.isEmpty(email)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }


    public  boolean isValidPhoneNumber(String target) {
        if (target == null || TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }


    /* This function will fill all the data fields with the passed object values */
    public void fillDataFields(ContactDataModel cdm) {
        fNameField.setText(cdm.getFname());
        lNameField.setText(cdm.getLname());
        emailField.setText(cdm.getEmail());
        phoneField.setText(cdm.getPhone());
    }

    public void removeDataFromFields() {
        fNameField.setText("");
        lNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }
}
