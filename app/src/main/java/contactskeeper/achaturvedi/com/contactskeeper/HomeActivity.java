package contactskeeper.achaturvedi.com.contactskeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends Activity {

    ListView contactList;
    ContactDataModel contactDataModel;
    ArrayList<ContactDataModel> contacyDataModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contactList = (ListView) findViewById(R.id.contactList);
        ContactDataModel c1 = new ContactDataModel();
        ContactDataModel c2 = new ContactDataModel();
        ContactDataModel c3 = new ContactDataModel();
        ContactDataModel c4 = new ContactDataModel();
        ContactDataModel c5 = new ContactDataModel();
        ContactDataModel c6 = new ContactDataModel();
        contacyDataModelArrayList.add(c1);
        contacyDataModelArrayList.add(c2);
        contacyDataModelArrayList.add(c3);
        contacyDataModelArrayList.add(c4);
        contacyDataModelArrayList.add(c5);
        contacyDataModelArrayList.add(c6);

        ContactDataAdapter contactDataAdapter = new ContactDataAdapter(getApplicationContext(), contacyDataModelArrayList);
        contactList.setAdapter(contactDataAdapter);
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
