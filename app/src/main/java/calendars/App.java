package calendars;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.upkeep.upkeep.R;

/**
 * Created by SIDDEEQ DESIGNER on 1/4/2018.
 */

public class App extends Activity
{

    Button logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaaa);

        ParseUser currentUser=ParseUser.getCurrentUser();
        String struser=currentUser.getUsername().toString();
        TextView txtuser=(TextView)findViewById(R.id.txtuser);
        txtuser.setText("You are logged in as"+struser);

        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logOut();
                finish();
            }
        });
    }
}