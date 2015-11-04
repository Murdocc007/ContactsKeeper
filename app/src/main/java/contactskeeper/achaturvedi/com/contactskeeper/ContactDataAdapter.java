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
    //Name: Aditya Mahajan
    //NetId: axm156630
    //Date: 11/1/2015
    //class constructor, takes the contactdatamodel arraylist and the context of activity
    public ContactDataAdapter(Context context, ArrayList<ContactDataModel> contactDataModelArrayList) {
        super(context, R.layout.contacts_element, contactDataModelArrayList);
        this.context = context;
        this.contactDataModelArrayList = contactDataModelArrayList;
    }


    //Name: Akash Chaturvedi
    //NetId:axc144430@utdallas.edu
    //Date:11/1/2015
    //getViewfunction gets called everytime a contact goes out of the scope of the screen and when a new contact data
    //comes in the view
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
