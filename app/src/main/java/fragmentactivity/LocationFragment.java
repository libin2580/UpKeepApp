package fragmentactivity;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

import java.util.ArrayList;

import activities.Add_Request;
import activities.Add_Workorder;
import activities.Location_details;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LocationFragment() {
        // Required empty public constructor
    }
   /* TextView input_data;
    ImageView get_image;*/
   private FragmentTabHost mTabHost;
  // public static TextView locations;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        MainFragment.title_new1.setText("Locations");
        MainFragment.filters.setVisibility(View.VISIBLE);
        MainFragment.menuitem1.setVisibility(View.VISIBLE);
        MainFragment.menuoption.setVisibility(View.VISIBLE);
        MainFragment.sp1.setVisibility(View.GONE);
        MainFragment.linear_one.setVisibility(View.VISIBLE);
        MainFragment.linear_two.setVisibility(View.VISIBLE);
        MainFragment.spinnerselection.setVisibility(View.VISIBLE);

        MainFragment.text_onee.setText("LIST VIEW");
        MainFragment.text_twoo.setText("MAP VIEW");

       // locations=(TextView)rootView.findViewById(R.id.get_locc);

       /* LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(getActivity());
        tv.setText("LIST VIEW");
        tv.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.BOTTOM);
        tv.setTextSize(14);
        tv.setTextColor(Color.WHITE);
      *//*  tv.setLayoutParams(lpView);*//*
        lpView.gravity = Gravity.CENTER_HORIZONTAL;
        tv.setLayoutParams(lpView);
        MainFragment.linear_one.addView(tv);

        TextView tv1 = new TextView(getActivity());
        tv1.setText("MAP VIEW");
        //tv1.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.BOTTOM);
        lpView.gravity = Gravity.CENTER_HORIZONTAL;
        tv.setLayoutParams(lpView);

        tv1.setTextSize(14);
        tv1.setTextColor(Color.WHITE);
        tv1.setLayoutParams(lpView);
        MainFragment.linear_two.addView(tv1);
*/

       /* locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(), Location_details.class);
                startActivity(i);
            }
        });*/

        MainFragment.filters.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.uparrow);
        MainFragment.filters.setImageBitmap(bitmap);

        MainFragment.menuoption.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1=BitmapFactory.decodeResource(getResources(),R.drawable.sortatoz);
        MainFragment.menuoption.setImageBitmap(bitmap1);

        ListviewFragment aa1 = new ListviewFragment();
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();


        MainFragment.linear_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListviewFragment aa1 = new ListviewFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();
            }
        });

        MainFragment.linear_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapviewFragment aa1 = new MapviewFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();

            }
        });


       MainFragment.filters.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

        // MainFragment.tab_barnew.setVisibility(View.VISIBLE);

       /* input_data=(TextView)rootView.findViewById(R.id.text_requests);
        get_image=(ImageView) rootView.findViewById(R.id.get_started);*/

        String text1 = "<font color=#585B5D>Locations help you organize</font>";
        String text2="<font color=#585B5D>groups of Assets.</font>";
        String text3 = "<font color=#585B5D>Press the "+" button at the</font>";
        String text4 = "<font color=#585B5D>bottom right to create a location</font>";
/*

        input_data.setText(Html.fromHtml(text1+"<br>"+text2+"<br>"+text3+"<br>"+text4));

        Animation rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotation_right);
        get_image.startAnimation(rotation);
*/

        return rootView;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public android.support.v4.app.Fragment newInstance(String s) {

        return null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
