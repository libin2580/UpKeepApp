package locations;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.upkeep.upkeep.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by SIDDEEQ DESIGNER on 1/17/2018.
 */

public class Add_map extends FragmentActivity implements OnMapReadyCallback
{
    TextView tool_title;
    ImageView toolbar_images;

    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private TextView resutText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

      /*  if (Build.VERSION.SDK_INT < 22)
            setStatusBarTranslucent(false);
        else
            setStatusBarTranslucent(true);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        resutText = (TextView) findViewById(R.id.dragg_result);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        configureCameraIdle();


        tool_title=(TextView)findViewById(R.id.toolbar_title);
        toolbar_images=(ImageView)findViewById(R.id.toolbar_image);
        tool_title.setText("Location Details");

        toolbar_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void configureCameraIdle()
    {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle()
            {
                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(Add_map.this);

                try {
                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    if (addressList != null && addressList.size() > 0)
                    {
                        String locality = addressList.get(0).getAddressLine(0);
                        String country = addressList.get(0).getCountryName();
                        String lati=String.valueOf(addressList.get(0).getLatitude());
                        String longi=String.valueOf(addressList.get(0).getLongitude());

                        if (!locality.isEmpty() && !country.isEmpty())
                            resutText.setText(lati + "  " + longi);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap=googleMap;
        mMap.setOnCameraIdleListener(onCameraIdleListener);

    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}

