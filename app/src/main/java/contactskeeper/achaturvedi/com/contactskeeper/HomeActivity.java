package contactskeeper.achaturvedi.com.contactskeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import android.util.Log;

public class HomeActivity extends Activity {

    private static final String tag="fuck";

    ListView contactList;
    ContactDataModel contactDataModel;
    ArrayList<ContactDataModel> contactDataModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contactList = (ListView) findViewById(R.id.contactList);


        FileWriter fw=new FileWriter(this.getApplicationContext());
        ArrayList<ContactDataModel> dataList=fw.getContactObject();

        for (ContactDataModel temp:dataList)
        {
            contactDataModelArrayList.add(temp);
        }

        ContactDataAdapter contactDataAdapter = new ContactDataAdapter(getApplicationContext(), contactDataModelArrayList);
        contactList.setAdapter(contactDataAdapter);

        Collections.sort(contactDataModelArrayList, ContactDataModel.firstNameComparator);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tag, "onResume");
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
