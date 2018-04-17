package activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upkeep.upkeep.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SIDDEEQ DESIGNER on 1/10/2018.
 */

public class Add_category extends AppCompatActivity
{
    private ListView lvCheckBox;
    private Button btnCheckAll, btnClearALl;
    private String[] arr = {"None","Damage", "Electrical", "Inspection", "Meter Reading", "Preventative", "Project", "Safety", "Upgrade"};
    TextView title,save;
    ImageView backarrow;
    ProgressDialog mprogress;
    String text;
    FirebaseFirestore firebasestore;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

      /*  btnCheckAll = (Button)findViewById(R.id.btnCheckAll);
        btnClearALl = (Button)findViewById(R.id.btnClearAll);
*/
        firebasestore=FirebaseFirestore.getInstance();
        lvCheckBox = (ListView)findViewById(R.id.lvCheckBox);
        lvCheckBox.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvCheckBox.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, arr));
        title=(TextView)findViewById(R.id.toolbar_title);
        save=(TextView)findViewById(R.id.toolbar_save);
        backarrow=(ImageView)findViewById(R.id.toolbar_image);

        mprogress=new ProgressDialog(this);
        mprogress.setMessage("Saving...");

        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        title.setText("Category");

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }
        });


        lvCheckBox.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,int position, long arg3) {
                         text = lvCheckBox.getItemAtPosition(position).toString();

                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                    }
                }
        );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // mprogress.show();

                Map<String,String> userMap=new HashMap<>();
                userMap.put("name",text);

                Add_Workorder.subcategory.setVisibility(View.VISIBLE);
                Add_Workorder.subcategory.setText(text);

                if(text.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_LONG).show();

                }
                firebasestore.collection("Category").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        //mprogress.dismiss();
                        //Toast.makeText(getApplicationContext(),"Success"+text,Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //mprogress.dismiss();
                        String error=e.getMessage();
                       // Toast.makeText(getApplicationContext(),"Error "+error,Toast.LENGTH_LONG).show();

                    }
                });


            }
        });

       /* btnCheckAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                for(int i=0 ; i < lvCheckBox.getAdapter().getCount(); i++)
                {
                    lvCheckBox.setItemChecked(i, true);
                }
            }
        });

        btnClearALl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for(int i=0 ; i < lvCheckBox.getAdapter().getCount(); i++)
                {
                    lvCheckBox.setItemChecked(i, false);
                }
            }
        });*/
    }
}