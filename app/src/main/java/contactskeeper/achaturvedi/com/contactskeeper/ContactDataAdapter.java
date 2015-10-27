package contactskeeper.achaturvedi.com.contactskeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Akash on 10/27/2015.
 */
public class ContactDataAdapter extends ArrayAdapter{

    private final Context context;
    private final ArrayList<ContactDataModel> contactDataModelArrayList;
    TextView fullName, phoneNum;
    public ContactDataAdapter(Context context, ArrayList<ContactDataModel> contactDataModelArrayList) {
        super(context, R.layout.contacts_element, contactDataModelArrayList);
        this.context = context;
        this.contactDataModelArrayList = contactDataModelArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contacts_element, parent, false);
        }

        fullName = (TextView)convertView.findViewById(R.id.fullName);
        fullName.setText(contactDataModelArrayList.get(position).getFname()+" "+contactDataModelArrayList.get(position).getLname());
        phoneNum = (TextView)convertView.findViewById(R.id.phoneNum);
        phoneNum.setText(contactDataModelArrayList.get(position).getPhone());
        return convertView;
    }
}
