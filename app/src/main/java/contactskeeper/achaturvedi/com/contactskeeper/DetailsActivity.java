package contactskeeper.achaturvedi.com.contactskeeper;

import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DetailsActivity extends Activity {

    LinearLayout buttonContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        buttonContainer = (LinearLayout)findViewById(R.id.button_container);
        String mode = getIntent().getStringExtra("action");
        if(mode.equals("add")) {
            buttonContainer.setVisibility(ViewGroup.INVISIBLE);
        }
    }

}
