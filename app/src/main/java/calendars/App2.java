package calendars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.upkeep.upkeep.R;

/**
 * Created by SIDDEEQ DESIGNER on 1/4/2018.
 */

public class App2 extends Activity
{
    Button loginbutton,signupbutton;
    String txtusername,txtpassword;
    EditText username,password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbb);

        loginbutton=(Button)findViewById(R.id.login);
        signupbutton=(Button)findViewById(R.id.signuppp);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtusername=username.getText().toString();
                txtpassword=password.getText().toString();

                ParseUser.logInInBackground(txtusername, txtpassword, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user!=null)
                        {
                            Intent i=new Intent(getApplicationContext(),App.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"The user doesnt exist, Please signup",Toast.LENGTH_LONG).show();

                        }

                    }
                });


            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtusername=username.getText().toString();
                txtpassword=password.getText().toString();

                if(txtusername.equals("")&&txtpassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please complete the signup form",Toast.LENGTH_LONG).show();
                }
                else
                {
                    ParseUser users=new ParseUser();
                    users.setUsername(txtusername);
                    users.setPassword(txtpassword);

                    users.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {



                            if(e==null)
                            {
                                Toast.makeText(getApplicationContext(),"Successfully signup",Toast.LENGTH_LONG).show();
                            }
                            else
                            {

                                Log.e("@@@@@@@@@@", String.valueOf(e));
                                Log.e("@@@@@@@@@@", String.valueOf(txtusername));
                                Log.e("@@@@@@@@@@", String.valueOf(txtpassword));


                                Toast.makeText(getApplicationContext(),"Signup error",Toast.LENGTH_LONG).show();
                            }

                        }
                    }) ;
                }

            }
        });


    }
}
