package activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upkeep.upkeep.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SIDDEEQ DESIGNER on 1/9/2018.
 */

public class New_users extends AppCompatActivity
{
    EditText user_email,user_pswrd;
    LinearLayout submit_user;
    TextView title;
    ImageView backarrow;
    FirebaseFirestore firebasestore;
   // public static DatabaseReference databaserefrence;
    ProgressDialog mprogress;
    ImageView saving;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newusers);

        firebasestore=FirebaseFirestore.getInstance();

       // databaserefrence= FirebaseDatabase.getInstance().getReference().child("Users");
        mprogress=new ProgressDialog(this);
        mprogress.setMessage("Saving...");

        title = (TextView) findViewById(R.id.toolbar_title);
        saving=(ImageView)findViewById(R.id.toolbar_save);

        // save=(TextView)findViewById(R.id.toolbar_save);
        backarrow = (ImageView) findViewById(R.id.toolbar_image);

        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        title.setText("New User");


        user_email = (EditText) findViewById(R.id.newuser_email);
        user_pswrd = (EditText) findViewById(R.id.newuser_password);
        submit_user = (LinearLayout) findViewById(R.id.submit_new_user);

        submit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mprogress.show();
                String s1=user_email.getText().toString();
                String s2=user_pswrd.getText().toString();

                Map<String,String>userMap=new HashMap<>();
                userMap.put("email",s1);
                userMap.put("name",s2);


                firebasestore.collection("Users").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        mprogress.dismiss();
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        mprogress.dismiss();
                        String error=e.getMessage();
                        Toast.makeText(getApplicationContext(),"Error "+error,Toast.LENGTH_LONG).show();

                    }
                });



            }
        });

        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


}
