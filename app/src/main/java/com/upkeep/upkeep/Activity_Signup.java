package com.upkeep.upkeep;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

/**
 * Created by SIDDEEQ DESIGNER on 1/1/2018.
 */

public class Activity_Signup extends AppCompatActivity implements View.OnClickListener {
    TextView txtView,textView1;
    LinearLayout signup;
    EditText editusername,editpaswrd;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        txtView = (TextView)findViewById(R.id.account_signup);
        textView1 = (TextView)findViewById(R.id.text4);
        editusername = (EditText) findViewById(R.id.input_name);
        editpaswrd = (EditText) findViewById(R.id.input_pass);

        signup=(LinearLayout)findViewById(R.id.signupnow);

        progressDialog = new ProgressDialog(this);
      /*  ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/

        String text = "<font color=#8f9396>Already have an account? </font> <font color=#F2090D>Log in.</font>";
        txtView.setText(Html.fromHtml(text));

        String text1 = "<font color=#8f9396>*To join existing UpKeep team, do not</font>";
        String text2="<font color=#8f9396>sign up yet! Please have the team</font>";
        String text3 = "<font color=#8f9396>administrator log in to UpKeep and send you</font>";
        String text4="<font color=#8f9396>an invite.</font>";

        textView1.setText(Html.fromHtml(text1+"<br>"+text2+"<br>"+text3+"<br>"+text4));

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Activity_Signup.this,Activity_login.class);
                startActivity(i);


            }
        });
        signup.setOnClickListener(this);

      /*  signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  Intent i=new Intent(Activity_Signup.this,Activity_buisinesstype.class);
                startActivity(i);*//*


                final String txtusername=editusername.getText().toString();
                final String  txtpassword=editpaswrd.getText().toString();

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
                        public void done(com.parse.ParseException e) {



                            if(e==null)
                            {

                                Toast.makeText(getApplicationContext(),"Successfully signup",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(), Activity_login.class);
                                startActivity(i);
                                finish();
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

*/
    }
    private void registerUser(){

        final String txtusername=editusername.getText().toString();
        final String  txtpassword=editpaswrd.getText().toString();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(txtusername)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(txtpassword)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(txtusername, txtpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        //checking if success

                        if(task.isSuccessful()){
                            //display some message here
                           Toast.makeText(Activity_Signup.this,"Successfully Registered",Toast.LENGTH_LONG).show();

                        }else{
                            //display some message here
                            Toast.makeText(Activity_Signup.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

            }

    @Override
    public void onClick(View v) {
        registerUser();
    }
}
