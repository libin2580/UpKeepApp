package activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.upkeep.upkeep.R;

import calendars.App;

/**
 * Created by SIDDEEQ DESIGNER on 1/11/2018.
 */

public class Schedule_popups extends Activity
{
    LinearLayout seemore,closed;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_schedule);

        seemore=(LinearLayout)findViewById(R.id.seevideo1);
        closed=(LinearLayout)findViewById(R.id.clsed);

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        String url = "http://help.onupkeep.com/getting-started-with-upkeep/getting-your-account-set-up/how-do-i-create-preventative-maintenance-pms-work-orders";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);


            }
        });

        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
