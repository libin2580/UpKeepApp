package activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upkeep.upkeep.R;

import java.util.HashMap;
import java.util.Map;

import fragmentactivity.RequestFragment;

/**
 * Created by SIDDEEQ DESIGNER on 1/8/2018.
 */

public class Add_Request extends AppCompatActivity
{
    TextView title,save;
    ImageView backarrow;
    EditText work_title,detailed_description;
    Button btnone,btntwo,btnthree,btnfour;
    String pririoty;
    FirebaseFirestore mFirebasestore;
    ProgressDialog mDialogs;
    LinearLayout add_imagess,seemore_img,closed_img,top_layout;
    private PopupWindow mPopupWindow;
    View customView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        customView = inflater2.inflate(R.layout.popup_schedule, null);

        seemore_img = (LinearLayout) customView.findViewById(R.id.seevideo1);
        closed_img = (LinearLayout) customView.findViewById(R.id.clsed);

        title = (TextView) findViewById(R.id.toolbar_title);
        save = (TextView) findViewById(R.id.toolbar_save);
        backarrow = (ImageView) findViewById(R.id.toolbar_image);
        work_title = (EditText) findViewById(R.id.work_title);
        detailed_description = (EditText) findViewById(R.id.work_description);

        add_imagess = (LinearLayout) findViewById(R.id.add_imgs);
        top_layout = (LinearLayout) findViewById(R.id.linear_top);

        mFirebasestore = FirebaseFirestore.getInstance();
        mDialogs = new ProgressDialog(Add_Request.this);
        mDialogs.setMessage("Saving...");

        btnone = (Button) findViewById(R.id.radioButton1);
        btntwo = (Button) findViewById(R.id.radioButton2);
        btnthree = (Button) findViewById(R.id.radioButton3);
        btnfour = (Button) findViewById(R.id.radioButton4);



        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        title.setText("Add Request");

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        add_imagess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayPopupImage();
            }
        });

        seemore_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://help.onupkeep.com/getting-started-with-upkeep/work-order-images";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });

        closed_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnone.setBackgroundColor(getResources().getColor(R.color.colorred));
                btnone.setTextColor(getResources().getColor(R.color.colorwhite));

                btntwo.setBackground(getDrawable(R.drawable.my_border));
                btntwo.setTextColor(getResources().getColor(R.color.colorred));


                btnthree.setBackground(getDrawable(R.drawable.my_border));
                btnthree.setTextColor(getResources().getColor(R.color.colorred));

                btnfour.setBackground(getDrawable(R.drawable.my_border));
                btnfour.setTextColor(getResources().getColor(R.color.colorred));

                pririoty = "None";
                Log.e("priority", pririoty);
            }
        });
        btntwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntwo.setBackgroundColor(getResources().getColor(R.color.colorred));
                btntwo.setTextColor(getResources().getColor(R.color.colorwhite));

                btnone.setBackground(getDrawable(R.drawable.my_border));
                btnone.setTextColor(getResources().getColor(R.color.colorred));

                btnthree.setBackground(getDrawable(R.drawable.my_border));
                btnthree.setTextColor(getResources().getColor(R.color.colorred));

                btnfour.setBackground(getDrawable(R.drawable.my_border));
                btnfour.setTextColor(getResources().getColor(R.color.colorred));

                pririoty = "!";
                Log.e("priority", pririoty);

            }
        });
        btnthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnthree.setBackgroundColor(getResources().getColor(R.color.colorred));
                btnthree.setTextColor(getResources().getColor(R.color.colorwhite));

                btntwo.setBackground(getDrawable(R.drawable.my_border));
                btntwo.setTextColor(getResources().getColor(R.color.colorred));

                btnone.setBackground(getDrawable(R.drawable.my_border));
                btnone.setTextColor(getResources().getColor(R.color.colorred));

                btnfour.setBackground(getDrawable(R.drawable.my_border));
                btnfour.setTextColor(getResources().getColor(R.color.colorred));

                pririoty = "!!";
                Log.e("priority", pririoty);
            }
        });
        btnfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnfour.setBackgroundColor(getResources().getColor(R.color.colorred));
                btnfour.setTextColor(getResources().getColor(R.color.colorwhite));

                btntwo.setBackground(getDrawable(R.drawable.my_border));
                btntwo.setTextColor(getResources().getColor(R.color.colorred));

                btnthree.setBackground(getDrawable(R.drawable.my_border));
                btnthree.setTextColor(getResources().getColor(R.color.colorred));

                btnone.setBackground(getDrawable(R.drawable.my_border));
                btnone.setTextColor(getResources().getColor(R.color.colorred));

                pririoty = "!!!";

                Log.e("priority", pririoty);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mDialogs.show();

                    String title = work_title.getText().toString();
                    String description = detailed_description.getText().toString();

                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("title",title);
                    userMap.put("description",description);
                    userMap.put("priority",pririoty);

                    mFirebasestore.collection("Request").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            mDialogs.dismiss();
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                            finish();

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            mDialogs.dismiss();
                            String error = e.getMessage();
                            Toast.makeText(getApplicationContext(), "Error " + error, Toast.LENGTH_LONG).show();

                        }
                    });

            }
        });
    }


    public void displayPopupImage() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(top_layout, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
