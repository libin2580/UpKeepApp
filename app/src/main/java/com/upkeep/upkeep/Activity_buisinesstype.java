package com.upkeep.upkeep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by SIDDEEQ DESIGNER on 1/1/2018.
 */

public class Activity_buisinesstype extends AppCompatActivity
{
    TextView txtView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buisinesstype);

        txtView = (TextView)findViewById(R.id.text3);

       /* ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
*/
        String text = "<font color=#8f9396>Thanks for signing up!</font>";
        String text2="<font color=#000000>Next, customize your UpKeep workflow by selecting your buisiness type</font>";

        txtView.setText(Html.fromHtml(text+"<br>"+text2));

    }
}
