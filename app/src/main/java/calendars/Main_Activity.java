package calendars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

/**
 * Created by SIDDEEQ DESIGNER on 1/4/2018.
 */

public class Main_Activity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser()))
        {
            Intent i=new Intent(getApplicationContext(),App2.class);
            startActivity(i);
            finish();
        }
        else
        {
            ParseUser currentuser=ParseUser.getCurrentUser();

            if(currentuser!=null)
            {
                Intent i=new Intent(getApplicationContext(),App.class);
                startActivity(i);
                finish();
            }
            else
            {
                Intent i=new Intent(getApplicationContext(),App2.class);
                startActivity(i);
                finish();
            }


        }
    }
}
