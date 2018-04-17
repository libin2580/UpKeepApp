package activitynew;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

import fragmentactivity.ListviewFragment;
import fragmentactivity.MapviewFragment;

/**
 * Created by SIDDEEQ DESIGNER on 1/23/2018.
 */

public class Location_add_part extends AppCompatActivity
{
    ImageView img_backarrow;
    Button btnone,btntwo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_locc__add);

        btnone=(Button)findViewById(R.id.btn1);
        btntwo=(Button)findViewById(R.id.btn2);


      //  final String select_locc=getIntent().getStringExtra("aaa");

        //Log.e("eeeeeeeeeeee",select_locc);

        img_backarrow=(ImageView)findViewById(R.id.toolbar_image);

        img_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        btnone.setBackgroundColor(getResources().getColor(R.color.colorred));
        btnone.setTextColor(getResources().getColor(R.color.colorwhite));

        btntwo.setBackground(getDrawable(R.drawable.my_border));
        btntwo.setTextColor(getResources().getColor(R.color.colorred));


       /* ListviewFragment aa1 = new ListviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("aaa", select_locc);
        aa1.setArguments(bundle);
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.loccadd, aa1).commit();

*/
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnone.setBackgroundColor(getResources().getColor(R.color.colorred));
                btnone.setTextColor(getResources().getColor(R.color.colorwhite));

                btntwo.setBackground(getDrawable(R.drawable.my_border));
                btntwo.setTextColor(getResources().getColor(R.color.colorred));

                ListviewFragment aa1 = new ListviewFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.loccadd, aa1).commit();

            }
        });

        btntwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntwo.setBackgroundColor(getResources().getColor(R.color.colorred));
                btntwo.setTextColor(getResources().getColor(R.color.colorwhite));

                btnone.setBackground(getDrawable(R.drawable.my_border));
                btnone.setTextColor(getResources().getColor(R.color.colorred));



                MapviewFragment aa1 = new MapviewFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.loccadd, aa1).commit();


            }
        });




    }
}
