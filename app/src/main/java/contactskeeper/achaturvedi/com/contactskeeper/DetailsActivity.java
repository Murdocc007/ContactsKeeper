package contactskeeper.achaturvedi.com.contactskeeper;

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

public class DetailsActivity extends Activity {

    private static final String tag="here";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Button saveButton=(Button)findViewById(R.id.newsaveButton);
        Log.i(tag, "firstname");
        saveButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Log.i(tag, "firstname");
                        saveData();
                    }
                }
        );

        Button cancelButton=(Button)findViewById(R.id.newcancelButton);
        cancelButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Log.v(tag,"asdfa");
                        DetailsActivity.this.finish();
                    }
                }
        );

    }


    public void saveData() {
        if (validateFields()) {
            EditText str;
            ContactDataModel cdm = new ContactDataModel();
            FileWriter fw=new FileWriter(this.getApplicationContext());

            str = (EditText) findViewById(R.id.newfirstNameField);
            cdm.setFname(str.getText().toString());

            str = (EditText) findViewById(R.id.newlastNameField);
            cdm.setLname(str.getText().toString());

            str = (EditText) findViewById(R.id.newemailIdField);
            cdm.setEmail(str.getText().toString());

            str = (EditText) findViewById(R.id.newphoneNumberField);
            cdm.setPhone(str.getText().toString());

            ArrayList<ContactDataModel> contactList=fw.getContactObject();
            contactList.add(cdm);

            fw.setContactObject(contactList);

        }
    }

    public boolean validateFields() {
        Log.i(tag,"firstname");

        EditText firstName = (EditText) findViewById(R.id.newfirstNameField);
        if (firstName.getText().toString().length() == 0) {
            Log.i(tag,"firstname");
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



}
