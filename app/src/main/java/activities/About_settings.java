package activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.upkeep.upkeep.R;

/**
 * Created by SIDDEEQ DESIGNER on 2/14/2018.
 */

public class About_settings extends AppCompatActivity
{
    ImageView back_arrow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_settings);

        back_arrow=(ImageView)findViewById(R.id.toolbar_image);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }
}
