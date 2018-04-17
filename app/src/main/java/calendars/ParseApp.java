package calendars;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.upkeep.upkeep.R;

/**
 * Created by SIDDEEQ DESIGNER on 1/4/2018.
 */

public class ParseApp extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

       // Parse.initialize(this,"eFSP2lMW8jS5fOyoP8vBjW6CxkoXoGpE9RHBB311","klOJY4qIN9yDe5QPEuyVqSnG0Xn3unMAX0qN6iTf");

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server("https://parseapi.back4app.com/")
                .build());
        ParseUser.enableAutomaticUser();
        ParseACL defauAcl=new ParseACL();
        defauAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defauAcl,true);
    }
}
