package activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upkeep.upkeep.R;

import java.util.HashMap;
import java.util.Map;

import locations.Add_map;
import locations.AppLocationService;
import locations.LocationAddress;

import locations.Mapsactivity;
import locations.MyActivity;

/**
 * Created by SIDDEEQ DESIGNER on 1/8/2018.
 */

public class Location_details extends AppCompatActivity
{
    TextView title,save;
    ImageView backarrow;
    EditText loc_name,loc_address;
    Switch locSwitch;
    LinearLayout use_current_loc;
    AppLocationService appLocationService;
    FirebaseFirestore mFirebasestore;
    ProgressDialog mDialogs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationdetails);

        appLocationService = new AppLocationService(
                Location_details.this);


        title = (TextView) findViewById(R.id.toolbar_title);
        save = (TextView) findViewById(R.id.toolbar_save);
        backarrow = (ImageView) findViewById(R.id.toolbar_image);

        loc_name = (EditText) findViewById(R.id.locname);
        loc_address = (EditText) findViewById(R.id.locaddress);
        locSwitch = (Switch) findViewById(R.id.locsimpleSwitch);
        use_current_loc = (LinearLayout) findViewById(R.id.layout3);

        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        mFirebasestore = FirebaseFirestore.getInstance();
        mDialogs = new ProgressDialog(Location_details.this);
        mDialogs.setMessage("Saving...");

        title.setText("Location Details");
        save.setText("SAVE");

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        locSwitch.setChecked(false);

        locSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    save.setText("NEXT");
                } else {
                    save.setText("SAVE");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (save.getText().equals("SAVE")) {

                    mDialogs.show();

                    String lname = loc_name.getText().toString();
                    String laddress = loc_address.getText().toString();


                    Log.e("@@@@@@@", lname);
                    Log.e("@@@@@@@", laddress);


                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("locname", lname);
                    userMap.put("locaddress", laddress);
                    //userMap.put("coordinates", pririoty);

                    mFirebasestore.collection("Location").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            mDialogs.dismiss();
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
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
                else
                {
                    if(loc_name.getText().length()==0)
                    {
                        if(loc_address.getText().length()==0)
                        {
                            Toast.makeText(getApplicationContext(),"Make sure you fill out all the information!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {

                        Intent in = new Intent(getApplicationContext(), Add_map.class);
                        startActivity(in);
                    }
                }
            }

        });


        use_current_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Location location = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new Location_details.GeocoderHandler());
                } else {
                    showSettingsAlert();
                }

            }
        });
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                Location_details.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        Location_details.this.startActivity(intent);}
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
       alertDialog.show();
    }

    public class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle=message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            loc_name.setText(locationAddress);
            loc_address.setText(locationAddress);
        }

    }
}
