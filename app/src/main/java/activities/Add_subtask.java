package activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.upkeep.upkeep.R;

/**
 * Created by SIDDEEQ DESIGNER on 1/15/2018.
 */

public class Add_subtask extends AppCompatActivity
{
    TextView title_text;
    ImageView backarrow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubtask);
        title_text=(TextView)findViewById(R.id.toolbar_title);
        backarrow=(ImageView)findViewById(R.id.toolbar_image);

        title_text.setText("New Form Item");

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        final CheckedTextView ctv = (CheckedTextView) findViewById(R.id.my_checkedtextview);
        final CheckedTextView ctv1 = (CheckedTextView) findViewById(R.id.my_checkedtextview1);
        final CheckedTextView ctv2 = (CheckedTextView) findViewById(R.id.my_checkedtextview2);
        final CheckedTextView ctv3 = (CheckedTextView) findViewById(R.id.my_checkedtextview3);
        final CheckedTextView ctv4 = (CheckedTextView) findViewById(R.id.my_checkedtextview4);

        ctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ctv.isChecked())
                {
                    ctv.setCheckMarkDrawable(R.drawable.tick);
                    ctv1.setCheckMarkDrawable(R.color.colorwhite);
                    ctv2.setCheckMarkDrawable(R.color.colorwhite);
                    ctv3.setCheckMarkDrawable(R.color.colorwhite);
                    ctv4.setCheckMarkDrawable(R.color.colorwhite);

                }
            }
        });

        ctv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ctv1.isChecked())
                {
                    ctv.setCheckMarkDrawable(R.color.colorwhite);
                    ctv1.setCheckMarkDrawable(R.drawable.tick);
                    ctv2.setCheckMarkDrawable(R.color.colorwhite);
                    ctv3.setCheckMarkDrawable(R.color.colorwhite);
                    ctv4.setCheckMarkDrawable(R.color.colorwhite);

                }

            }
        });
        ctv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ctv2.isChecked())
                {
                    ctv.setCheckMarkDrawable(R.color.colorwhite);
                    ctv1.setCheckMarkDrawable(R.color.colorwhite);
                    ctv2.setCheckMarkDrawable(R.drawable.tick);
                    ctv3.setCheckMarkDrawable(R.color.colorwhite);
                    ctv4.setCheckMarkDrawable(R.color.colorwhite);

                }

            }
        });
        ctv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(ctv3.isChecked())
                {
                    ctv.setCheckMarkDrawable(R.color.colorwhite);
                    ctv1.setCheckMarkDrawable(R.color.colorwhite);
                    ctv2.setCheckMarkDrawable(R.color.colorwhite);
                    ctv3.setCheckMarkDrawable(R.drawable.tick);
                    ctv4.setCheckMarkDrawable(R.color.colorwhite);

                }

            }
        });
        ctv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ctv4.isChecked())
                {
                    ctv.setCheckMarkDrawable(R.color.colorwhite);
                    ctv1.setCheckMarkDrawable(R.color.colorwhite);
                    ctv2.setCheckMarkDrawable(R.color.colorwhite);
                    ctv3.setCheckMarkDrawable(R.color.colorwhite);
                    ctv4.setCheckMarkDrawable(R.drawable.tick);

                }

            }
        });
    }
}
