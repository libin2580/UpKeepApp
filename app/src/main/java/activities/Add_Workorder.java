package activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upkeep.upkeep.MainActivity;
import com.upkeep.upkeep.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import activitynew.AllassetsFragment;
import fragmentactivity.AssetsFragment;
import fragmentactivity.CalendarFragment;
import fragmentactivity.RequestFragment;
import fragmentactivity.TeamFragment;

/**
 * Created by SIDDEEQ DESIGNER on 1/5/2018.
 */

public class Add_Workorder extends Activity {
    public static TextView save_btn,title, save, txtdate, txtdatenew, category, subcategory,assign_teamdata,assignworker_settext,aditionalworker_text;
    ImageView backarrow;
    LinearLayout due_date, end_date, assignschedule;
    CustomDateTimePicker custom, custom1;
    final Context context = this;
    private PopupWindow mPopupWindow;
    private LinearLayout mRelativeLayout;
    View customView,customViewImage,customViewForm,customViewFile,customViewsign;
    LinearLayout top_layout,assign_toteam;
    LinearLayout seemore,closed,addimage,seemore_img,closed_img,add_forms,seemore_form,closed_form,addfile,seemore_files,closed_file,add_sign,
            seemore_sign,closed_sign,assign_workers,add_aditional_workers,add_sub_tasks,assign_assetss;

    Button btn1,btn2,btn3,btn4;
    EditText edit1,edit2;
    FirebaseFirestore mFirebasestore;
    ProgressDialog mDialogs;

    String pririoty;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_workorder);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        customView = inflater2.inflate(R.layout.popup_schedule, null);
        customViewImage = inflater2.inflate(R.layout.popup_image, null);
        customViewForm= inflater2.inflate(R.layout.popup_templates, null);
        customViewFile= inflater2.inflate(R.layout.popup_uploads, null);
        customViewsign= inflater2.inflate(R.layout.popup_signature, null);

        assign_toteam=(LinearLayout)findViewById(R.id.assigntoteams);
        assign_assetss=(LinearLayout)findViewById(R.id.assign_assets);



        seemore=(LinearLayout)customView.findViewById(R.id.seevideo1);
        closed=(LinearLayout)customView.findViewById(R.id.clsed);

        assign_workers=(LinearLayout)findViewById(R.id.assign_workers);
        assignworker_settext=(TextView)findViewById(R.id.textassignworker);

        add_aditional_workers=(LinearLayout)findViewById(R.id.add_additionalworkers);
        aditionalworker_text=(TextView)findViewById(R.id.textaddaditionalwrkrs);

        edit1=(EditText)findViewById(R.id.wrkordertitle);
        edit2=(EditText)findViewById(R.id.detaileddescription);

        add_sub_tasks=(LinearLayout)findViewById(R.id.addsubtasks);
        addimage=(LinearLayout)findViewById(R.id.add_image);
        addfile=(LinearLayout)findViewById(R.id.add_files);
        add_forms=(LinearLayout) findViewById(R.id.addforms);
        add_sign=(LinearLayout) findViewById(R.id.addsignature);
        assign_teamdata=(TextView)findViewById(R.id.textassignteam);
        seemore_img=(LinearLayout)customViewImage.findViewById(R.id.seevideo1);
        closed_img=(LinearLayout)customViewImage.findViewById(R.id.clsed);

        seemore_form=(LinearLayout)customViewForm.findViewById(R.id.seevideo1);
        closed_form=(LinearLayout)customViewForm.findViewById(R.id.clsed);

        seemore_files=(LinearLayout)customViewFile.findViewById(R.id.seevideo1);
        closed_file=(LinearLayout)customViewFile.findViewById(R.id.clsed);

        seemore_sign=(LinearLayout)customViewsign.findViewById(R.id.seevideo1);
        closed_sign=(LinearLayout)customViewsign.findViewById(R.id.clsed);

        btn1=(Button)findViewById(R.id.radioButton1);
        btn2=(Button)findViewById(R.id.radioButton2);
        btn3=(Button)findViewById(R.id.radioButton3);
        btn4=(Button)findViewById(R.id.radioButton4);

        title = (TextView) findViewById(R.id.toolbar_title);
        top_layout = (LinearLayout) findViewById(R.id.top_layout);
        save = (TextView) findViewById(R.id.toolbar_save);
        txtdate = (TextView) findViewById(R.id.textdates);
        txtdatenew = (TextView) findViewById(R.id.textdatesend);
        backarrow = (ImageView) findViewById(R.id.toolbar_image);
        due_date = (LinearLayout) findViewById(R.id.layoutduedate);
        end_date = (LinearLayout) findViewById(R.id.layoutenddate);
        assignschedule = (LinearLayout) findViewById(R.id.assignschedule);
        mRelativeLayout = (LinearLayout) findViewById(R.id.popup1);

        category = (TextView) findViewById(R.id.category);
        subcategory = (TextView) findViewById(R.id.categorysub);

        save_btn=(TextView)findViewById(R.id.savebtn);

        mFirebasestore = FirebaseFirestore.getInstance();
        mDialogs = new ProgressDialog(Add_Workorder.this);
        mDialogs.setMessage("Saving...");

       /* Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
*/
        title.setText("Add Work Order");
       /* topToolBar.setLogo(R.drawable.backicon);
        topToolBar.setTitle(R.string.addorder);
        topToolBar.setTitleTextColor(R.color.caldroid_white);
        */

       /* addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(),Add_subtask.class);
                startActivity(in);
            }
        });*/

       save_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               mDialogs.show();

               String data1 = edit1.getText().toString();
               String data2 = edit2.getText().toString();
               String data3 = assignworker_settext.getText().toString();

               String data4 = aditionalworker_text.getText().toString();
               String data8= txtdate.getText().toString();
               String data9 = txtdatenew.getText().toString();
               String data10= subcategory.getText().toString();
               String data11 = assign_teamdata.getText().toString();

               Map<String, String> userMap = new HashMap<>();
               userMap.put("wrkorder",data1);
               userMap.put("desc",data2);
               userMap.put("priority",pririoty);

               userMap.put("assignwrkr",data3);
               userMap.put("addwrkr",data4);
               userMap.put("duedate",data8);

               userMap.put("enddate",data9);
               userMap.put("category",data10);
               userMap.put("assignteam",data11);

               mFirebasestore.collection("Workers").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
               {
                   @Override
                   public void onSuccess(DocumentReference documentReference) {
                       mDialogs.dismiss();
                       Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                       finish();
                   }

               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {

                       mDialogs.dismiss();
                       String error = e.getMessage();
                       Toast.makeText(getApplicationContext(), "Error " + error, Toast.LENGTH_LONG).show();

                   }
               });

           }

       });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialogs.show();

                String data1 = edit1.getText().toString();
                String data2 = edit2.getText().toString();
                String data3 = assignworker_settext.getText().toString();

                String data4 = aditionalworker_text.getText().toString();
                String data8= txtdate.getText().toString();
                String data9 = txtdatenew.getText().toString();
                String data10= subcategory.getText().toString();
                String data11 = assign_teamdata.getText().toString();

                Map<String, String> userMap = new HashMap<>();
                userMap.put("wrkorder",data1);
                userMap.put("desc",data2);
                userMap.put("priority",pririoty);

                userMap.put("assignwrkr",data3);
                userMap.put("addwrkr",data4);
                userMap.put("duedate",data8);

                userMap.put("enddate",data9);
                userMap.put("category",data10);
                userMap.put("assignteam",data11);

                mFirebasestore.collection("Workers").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                mDialogs.show();
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                finish();

                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        mDialogs.dismiss();
                       String error=e.getMessage();
                        Toast.makeText(getApplicationContext(), "Error " + error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        assign_assetss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(),Add_Asset.class);
                startActivity(in);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn1.setBackgroundColor(getResources().getColor(R.color.colorred));
                btn1.setTextColor(getResources().getColor(R.color.colorwhite));

                btn2.setBackground(getDrawable(R.drawable.my_border));
                btn2.setTextColor(getResources().getColor(R.color.colorred));

                btn3.setBackground(getDrawable(R.drawable.my_border));
                btn3.setTextColor(getResources().getColor(R.color.colorred));

                btn4.setBackground(getDrawable(R.drawable.my_border));
                btn4.setTextColor(getResources().getColor(R.color.colorred));

                pririoty = "None";
                Log.e("priority", pririoty);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn2.setBackgroundColor(getResources().getColor(R.color.colorred));
                btn2.setTextColor(getResources().getColor(R.color.colorwhite));

                btn1.setBackground(getDrawable(R.drawable.my_border));
                btn1.setTextColor(getResources().getColor(R.color.colorred));

                btn3.setBackground(getDrawable(R.drawable.my_border));
                btn3.setTextColor(getResources().getColor(R.color.colorred));

                btn4.setBackground(getDrawable(R.drawable.my_border));
                btn4.setTextColor(getResources().getColor(R.color.colorred));

                pririoty = "!";
                Log.e("priority", pririoty);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn3.setBackgroundColor(getResources().getColor(R.color.colorred));
                btn3.setTextColor(getResources().getColor(R.color.colorwhite));

                btn1.setBackground(getDrawable(R.drawable.my_border));
                btn1.setTextColor(getResources().getColor(R.color.colorred));

                btn2.setBackground(getDrawable(R.drawable.my_border));
                btn2.setTextColor(getResources().getColor(R.color.colorred));

                btn4.setBackground(getDrawable(R.drawable.my_border));
                btn4.setTextColor(getResources().getColor(R.color.colorred));

                pririoty = "!!";
                Log.e("priority", pririoty);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn4.setBackgroundColor(getResources().getColor(R.color.colorred));
                btn4.setTextColor(getResources().getColor(R.color.colorwhite));

                btn1.setBackground(getDrawable(R.drawable.my_border));
                btn1.setTextColor(getResources().getColor(R.color.colorred));

                btn2.setBackground(getDrawable(R.drawable.my_border));
                btn2.setTextColor(getResources().getColor(R.color.colorred));

                btn3.setBackground(getDrawable(R.drawable.my_border));
                btn3.setTextColor(getResources().getColor(R.color.colorred));

                pririoty = "!!!";

                Log.e("priority", pririoty);
            }
        });


        add_sub_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(),Add_subtask.class);
                startActivity(in);
            }
        });

       assign_workers.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent i=new Intent(getApplicationContext(),Assign_workers.class);
               i.putExtra("aaa","asnwrkr");
               startActivity(i);
           }
       });

        add_aditional_workers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),Assign_workers.class);
                i.putExtra("aaa","addwrkr");
                startActivity(i);
            }

        });

        custom1 = new CustomDateTimePicker(this,
                new CustomDateTimePicker.ICustomDateTimeListener()
                {

                    @Override
                    public void onSet(Dialog dialog, Calendar calendarSelected,
                                      Date dateSelected, int year, String monthFullName,
                                      String monthShortName, int monthNumber, int date,
                                      String weekDayFullName, String weekDayShortName,
                                      int hour24, int hour12, int min, int sec,
                                      String AM_PM)
                    {
                        txtdatenew.setVisibility(View.VISIBLE);
                        txtdatenew.setText(calendarSelected
                                .get(Calendar.DAY_OF_MONTH)
                                + "-" + (monthShortName) + "-" + year
                                + ", " + hour12 + ":" + min
                                + " " + AM_PM);
                    }


                    @Override
                    public void onCancel()
                    {

                    }
                });
        custom = new CustomDateTimePicker(this,
                new CustomDateTimePicker.ICustomDateTimeListener() {

                    @Override
                    public void onSet(Dialog dialog, Calendar calendarSelected,
                                      Date dateSelected, int year, String monthFullName,
                                      String monthShortName, int monthNumber, int date,
                                      String weekDayFullName, String weekDayShortName,
                                      int hour24, int hour12, int min, int sec,
                                      String AM_PM)
                    {

                        txtdate.setVisibility(View.VISIBLE);
                        txtdate.setText(calendarSelected
                                .get(Calendar.DAY_OF_MONTH)
                                + "-" + (monthShortName) + "-" + year
                                + ", " + hour12 + ":" + min
                                + " " + AM_PM);

                    }

                    @Override
                    public void onCancel() {

                    }
                });

        custom.set24HourFormat(false);
        custom.setDate(Calendar.getInstance());
        custom1.set24HourFormat(false);
        custom1.setDate(Calendar.getInstance());

        findViewById(R.id.layoutduedate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                custom.showDialog();
            }
        });

        findViewById(R.id.layoutenddate).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        custom1.showDialog();
                    }
                });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), Add_category.class);
                startActivity(i);
            }
        });

        assign_toteam.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i=new Intent(getApplicationContext(),AssignTeamFragment.class);
               startActivity(i);
            }
        });

        assignschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.popup_schedule);
                dialog.show();*/
               displayPopup();
               /* Intent in = new Intent(getApplicationContext(), Schedule_popups.class);
                startActivity(in);*/
            }
        });

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://help.onupkeep.com/getting-started-with-upkeep/getting-your-account-set-up/how-do-i-create-preventative-maintenance-pms-work-orders";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });


        seemore_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://help.onupkeep.com/getting-started-with-upkeep/work-order-images";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });

        closed_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                displayPopupImage();
            }
        });
        add_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                displayPopupsign();
            }
        });
        addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                displayPopupFiles();
            }
        });
        add_forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                displayPopupForm();
            }
        });
        seemore_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/how-do-i-use-form-templates-to-create-inspections-and-checklists";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        closed_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }
        });

        seemore_files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/how-do-i-upload-files-to-work-orders-or-assets";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });

        closed_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

        seemore_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://help.onupkeep.com/additional-support/extra-support/how-does-the-signature-capture-work";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });

        closed_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

    }

    public void displayPopup() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(top_layout, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void displayPopupFiles() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customViewFile, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(top_layout, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void displayPopupsign() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customViewsign, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(top_layout, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void displayPopupImage() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customViewImage, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(top_layout, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void displayPopupForm() {

        try {
            System.out.println("inside display popup");
            mPopupWindow = new PopupWindow(customViewForm, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(R.style.popupAnimation);


            mPopupWindow.showAtLocation(top_layout, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
