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


    ListView contactList;
    ContactDataModel contactDataModel;
    ArrayList<ContactDataModel> contactDataModelArrayList = new ArrayList<>();
    ContactDataAdapter contactDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contactList = (ListView) findViewById(R.id.contactList);
    }


    @Override
    protected void onResume() {
        super.onResume();
        FileWriter fw=new FileWriter(this.getApplicationContext());
        final ArrayList<ContactDataModel> dataList=fw.getContactObject();

        for (ContactDataModel temp:dataList)
        {
            contactDataModelArrayList.add(temp);
        }

        contactDataAdapter = new ContactDataAdapter(getApplicationContext(), contactDataModelArrayList);
        contactList.setAdapter(contactDataAdapter);

        //Collections.sort(contactDataModelArrayList, ContactDataModel.firstNameComparator);

        contactList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactDataModel temp = (ContactDataModel) parent.getAdapter().getItem(position);
                toDetailsActivity("modify",temp);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

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

    public void toDetailsActivity(String action,ContactDataModel cdm) {
        Intent intent = new Intent(this, DetailsActivity.class).putExtra("action", action);
        intent.putExtra("dataObject",cdm);
        startActivity(intent);
    }

}
