package com.upkeep.upkeep;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toolbar;

import permission.CheckAndRequestPermission;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

     /*   ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/

        img=(ImageView)findViewById(R.id.image1);
        CheckAndRequestPermission c=new CheckAndRequestPermission();
        c.checkAndRequestPermissions(MainActivity.this);

        Animation rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
        img.startAnimation(rotation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i=new Intent(MainActivity.this,MainPage.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
}
