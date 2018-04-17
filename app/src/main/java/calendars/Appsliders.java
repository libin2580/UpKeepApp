package calendars;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.upkeep.upkeep.R;

/**
 * Created by SIDDEEQ DESIGNER on 1/5/2018.
 */

public class Appsliders extends AppCompatActivity {
    SliderLayout sliderLayout;
    DefaultSliderView defaultSliderView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa);
        sliderLayout=(SliderLayout)findViewById(R.id.slidenew);
        defaultSliderView = new DefaultSliderView(getApplicationContext());
        new FetchBanners().execute();

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

                int array[] = {R.drawable.banner1, R.drawable.banner1, R.drawable.banner1};
                int size = array.length;
                for (int i = 0; i < size; i++)
                {
                    int url = array[i];
                    System.out.println("url : " + url);
                    defaultSliderView.image(url);
                    sliderLayout.addSlider(defaultSliderView);
                }
                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Fade);
                sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);

                sliderLayout.setScrollBarSize(300);
                sliderLayout.setDuration(1500);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
