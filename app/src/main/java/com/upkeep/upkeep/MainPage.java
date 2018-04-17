package com.upkeep.upkeep;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

/**
 * Created by SIDDEEQ DESIGNER on 1/1/2018.
 */

public class MainPage extends AppCompatActivity
{
    TextView txtView;
    LinearLayout signupfree;
    SliderLayout sliderLayout;
    DefaultSliderView defaultSliderView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_secondpage);

     /*   ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
*/
        txtView = (TextView)findViewById(R.id.textid1);
        sliderLayout=(SliderLayout)findViewById(R.id.slidenew);

        String text = "<font color=#000000>Already have an account? </font> <font color=#F2090D>Log in.</font>";
        txtView.setText(Html.fromHtml(text));

        signupfree=(LinearLayout)findViewById(R.id.signfree);

        new FetchBanners().execute();

        signupfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainPage.this,Activity_Signup.class);
                startActivity(i);

            }
        });

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainPage.this,Activity_login.class);
                startActivity(i);

            }
        });

    }
    public class FetchBanners extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {

            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                int array[] = {R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner1};
                int size = array.length;
                for (int i = 0; i < size; i++) {
                    defaultSliderView = new DefaultSliderView(getApplicationContext());
                    int url = array[i];
                    System.out.println("url : " + url);
                    defaultSliderView.image(url);
                    sliderLayout.addSlider(defaultSliderView);

                }
                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Fade);
                sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);

                sliderLayout.getPagerIndicator().setDefaultIndicatorColor(R.color.colorred, R.color.colorred);
                sliderLayout.getPagerIndicator().setMinimumHeight(10);
                sliderLayout.stopAutoCycle();
                sliderLayout.setScrollBarSize(300);
                sliderLayout.setDuration(1500);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
