package com.upkeep.upkeep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import activities.RequestData;
import activities.WorkorderData;
import adapters.Requestadapter;
import adapters.Workorderadapter;
import fragmentactivity.Activity_settingsFragment;
import fragmentactivity.AssetsFragment;
import fragmentactivity.CalendarFragment;
import fragmentactivity.DashboardFragment;
import fragmentactivity.LocationFragment;
import fragmentactivity.MessagesFragment;
import fragmentactivity.MeterFragment;
import fragmentactivity.PartsInventoryFragment;
import fragmentactivity.PeopleTeamFragment;
import fragmentactivity.RequestFragment;
import fragmentactivity.WorkorderFragment;

/**
 * Created by SIDDEEQ DESIGNER on 1/2/2018.
 */

public class MainFragment extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener , NavigationView.OnNavigationItemSelectedListener,
        CalendarFragment.OnFragmentInteractionListener, DashboardFragment.OnFragmentInteractionListener, RequestFragment.OnFragmentInteractionListener,
        MeterFragment.OnFragmentInteractionListener, LocationFragment.OnFragmentInteractionListener, WorkorderFragment.OnFragmentInteractionListener
{

    public static Spinner sp1;
    public static boolean doubleBackToExitPressedOnce = false;
    public static LinearLayout navOpen,spinnerselection,filter11,menuitem1,linear_one,linear_two;
    public static NavigationView navigationView;
    public static DrawerLayout drawer;
    private static final int TIME_DELAY = 2000;
    public static LinearLayout title_new;
    public static TextView title_new1,text_onee,text_twoo;
    public static ImageView filters,menuoption;
    private PopupWindow mPopupWindow;
    View customViewImage;
    LinearLayout top_layout, seemore,closed;


    LinearLayout nav_work,nav_calendar,nav_dashboard,nav_req,nav_loc,nav_assets,nav_parts,nav_meters,nav_people,nav_message,nav_setting,nav_contact;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainfragment);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        customViewImage = inflater2.inflate(R.layout.popup_export, null);


        /*String user_mailid=getIntent().getStringExtra("emails");

        Log.e("*********************",user_mailid);

*/

        seemore=(LinearLayout)customViewImage.findViewById(R.id.seevideo1);
        sp1 = (Spinner) findViewById(R.id.spinner);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        top_layout = (LinearLayout) findViewById(R.id.linear_exportts);

        navigationView = (NavigationView) findViewById(R.id.navigation_drawer_container);
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setBackgroundColor(getResources().getColor(R.color.colorred));

        title_new = (LinearLayout) findViewById(R.id.title1);
        title_new1 = (TextView) findViewById(R.id.title_text);
        navOpen = (LinearLayout) findViewById(R.id.nav_open);

        nav_work = (LinearLayout) findViewById(R.id.nav_work);
        nav_calendar = (LinearLayout) findViewById(R.id.nav_calendar);
        nav_dashboard = (LinearLayout) findViewById(R.id.nav_dashboard);
        nav_req = (LinearLayout) findViewById(R.id.nav_req);
        nav_loc = (LinearLayout) findViewById(R.id.nav_locations);
        nav_assets = (LinearLayout) findViewById(R.id.nav_assets);

        linear_one=(LinearLayout) findViewById(R.id.linear_one);
        linear_two=(LinearLayout) findViewById(R.id.linear_two);
        closed=(LinearLayout)customViewImage.findViewById(R.id.clsed);
        text_onee=(TextView) findViewById(R.id.text_one);
        text_twoo=(TextView) findViewById(R.id.text_two);

        nav_parts = (LinearLayout) findViewById(R.id.nav_parts);
        nav_meters = (LinearLayout) findViewById(R.id.nav_meters);
        nav_people = (LinearLayout) findViewById(R.id.nav_people);
        nav_message = (LinearLayout) findViewById(R.id.nav_message);
        nav_setting = (LinearLayout) findViewById(R.id.nav_settings);
        nav_contact = (LinearLayout) findViewById(R.id.nav_contacts);

        filter11 = (LinearLayout) findViewById(R.id.filterss);
        menuitem1 = (LinearLayout) findViewById(R.id.menusss);

        filters = (ImageView) findViewById(R.id.prev_filter);
        menuoption = (ImageView) findViewById(R.id.icon_menu);
        spinnerselection = (LinearLayout) findViewById(R.id.newspinner);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.menuarrow);
        MainFragment.filters.setImageBitmap(bitmap);

        menuoption.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.menutoolbar);
        menuoption.setImageBitmap(bitmap1);

      //  tab_barnew=(LinearLayout)findViewById(R.id.tab1);

        WorkorderFragment aa1 = new WorkorderFragment();
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, aa1).commit();


        nav_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                CalendarFragment aaa = new CalendarFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, aaa).commit();
            }
        });
        nav_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                DashboardFragment aab = new DashboardFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, aab).commit();
            }
        });
        nav_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                WorkorderFragment aa1 = new WorkorderFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, aa1).commit();


            }
        });
        nav_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                RequestFragment aac = new RequestFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, aac).commit();
            }
        });
        nav_meters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                MeterFragment aad = new MeterFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, aad).commit();
            }
        });
        navOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(navigationView);
            }
        });


        nav_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                LocationFragment aae = new LocationFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, aae).commit();
            }
        });

        nav_assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                AssetsFragment rrr = new AssetsFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, rrr).commit();
            }
        });

        nav_parts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                PartsInventoryFragment rrs = new PartsInventoryFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, rrs).commit();
            }
        });
        nav_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                PeopleTeamFragment rrd = new PeopleTeamFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, rrd).commit();
            }
        });

        nav_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                MessagesFragment rrdd = new MessagesFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, rrdd).commit();
            }
        });

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/how-do-i-create-a-report-for-a-set-of-work-orders-on-an-asset-or-a-given-time-period";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        nav_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.closeDrawer(GravityCompat.START);
                Activity_settingsFragment rdd=new Activity_settingsFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame, rdd).commit();


            }
        });



        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Due Date");
        spinnerArray.add("Worker Assigned To");
        spinnerArray.add("Last Updated");
        spinnerArray.add("Status");
        spinnerArray.add("Asset Assigned");
        spinnerArray.add("Team Assigned");
        spinnerArray.add("Location Assigned");

        sp1.setTag("Due Date");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MainFragment.this, R.layout.textt, spinnerArray);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_selector);
        //spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        sp1.setAdapter(spinnerArrayAdapter);
      /*  ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/



    }

    public void showPopup(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(MainFragment.this);
        popup.inflate(R.menu.main);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_act:
                return true;
            case R.id.menu_exp:
                displayPopupImage();
                return true;
            default:
                return false;
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


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        com.nispok.snackbar.Snackbar.with(MainFragment.this) // context
                .text(R.string.Press_again_to_exit) // text to display
                .show(MainFragment.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;

            }
        }, 2000);


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return true;
    }


    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}
