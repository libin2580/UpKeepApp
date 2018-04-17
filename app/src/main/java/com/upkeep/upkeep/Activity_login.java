package com.upkeep.upkeep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import calendars.App;

/**
 * Created by SIDDEEQ DESIGNER on 1/1/2018.
 */

public class Activity_login extends AppCompatActivity
{
    LinearLayout signnow;
    EditText edit_name,edit_pass;
    private FirebaseAuth auth;
    private ProgressDialog progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Activity_login.this, MainFragment.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        signnow=(LinearLayout)findViewById(R.id.signfree);
        edit_name=(EditText)findViewById(R.id.input_name);
        edit_pass=(EditText)findViewById(R.id.input_pass);

        auth = FirebaseAuth.getInstance();
        progressBar = new ProgressDialog(this);

        signnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              final String  txtusername=edit_name.getText().toString();
                final String txtpassword=edit_pass.getText().toString();



                if (TextUtils.isEmpty(txtusername)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(txtpassword)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setMessage("Login Please Wait...");
                progressBar.show();

                //authenticate user
                auth.signInWithEmailAndPassword(txtusername, txtpassword)
                        .addOnCompleteListener(Activity_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (txtpassword.length() < 6) {
                                        edit_pass.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Activity_login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Activity_login.this, MainFragment.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });


              /*  ParseUser.logInInBackground(txtusername, txtpassword, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user!=null)
                        {
                            Intent i=new Intent(getApplicationContext(), MainFragment.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"The user doesnt exist, Please signup",Toast.LENGTH_LONG).show();

                        }

                    }
                });
*/



    }

}
