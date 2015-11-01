package contactskeeper.achaturvedi.com.contactskeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends Activity {

    ListView contactList;
    ContactDataModel contactDataModel;
    ArrayList<ContactDataModel> contactDataModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contactList = (ListView) findViewById(R.id.contactList);
        ContactDataModel c1 = new ContactDataModel();
        ContactDataModel c2 = new ContactDataModel();
        c2.setFname("Akash");
        ContactDataModel c3 = new ContactDataModel();
        ContactDataModel c4 = new ContactDataModel();
        ContactDataModel c5 = new ContactDataModel();
        ContactDataModel c6 = new ContactDataModel();
        contactDataModelArrayList.add(c1);
        contactDataModelArrayList.add(c2);
        contactDataModelArrayList.add(c3);
        contactDataModelArrayList.add(c4);
        contactDataModelArrayList.add(c5);
        contactDataModelArrayList.add(c6);

        ContactDataAdapter contactDataAdapter = new ContactDataAdapter(getApplicationContext(), contactDataModelArrayList);
        contactList.setAdapter(contactDataAdapter);

        Collections.sort(contactDataModelArrayList, ContactDataModel.firstNameComparator);
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
            toDetailsActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toDetailsActivity() {
        Intent intent = new Intent(this, DetailsActivity.class).putExtra("action", "add");
        startActivity(intent);
    }
}
