package contactskeeper.achaturvedi.com.contactskeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import android.util.Log;

public class HomeActivity extends Activity {


    ListView contactList;//listview of the contact list
    ContactDataModel contactDataModel;
    ArrayList<ContactDataModel> contactDataModelArrayList = new ArrayList<>();
    ContactDataAdapter contactDataAdapter;
    @Override

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/20
    //setting the content view
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contactList = (ListView) findViewById(R.id.contactList);
    }


    @Override
    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/2/2015
    //used to set the main screen everytime onresume is called
    protected void onResume() {
        super.onResume();
        FileWriter fw=new FileWriter(this.getApplicationContext());
        final ArrayList<ContactDataModel> dataList=fw.getContactObject();//reading the txt file for contact details
        contactDataModelArrayList.removeAll(contactDataModelArrayList);//clearing the contactdatalist arraylist

        //appending the new objects to the array list
        for (ContactDataModel temp:dataList)
        {
            contactDataModelArrayList.add(temp);
        }

        //setting the contactDataModel arraylist to the contactdataAdapter
        contactDataAdapter = new ContactDataAdapter(getApplicationContext(), contactDataModelArrayList);
        contactList.setAdapter(contactDataAdapter);


        //Collections.sort(contactDataModelArrayList, ContactDataModel.firstNameComparator);

        //clicking on any contact will open the details view in Modify Mode
        contactList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactDataModel temp = (ContactDataModel) parent.getAdapter().getItem(position);
                toDetailsActivity("modify",temp);
            }
        });
    }


    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addContact) {
            toDetailsActivity("add",null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/2/2015
    //function that redirects to the details activity page, where we  can see the details of a contact
    public void toDetailsActivity(String action,ContactDataModel cdm) {
        Intent intent = new Intent(this, DetailsActivity.class).putExtra("action", action);
        intent.putExtra("dataObject",cdm);
        startActivity(intent);
    }

}
