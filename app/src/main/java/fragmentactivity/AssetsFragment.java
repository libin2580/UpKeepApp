package fragmentactivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

import activities.Add_Asset;
import activities.Location_details;
import activitynew.AllassetsFragment;
import activitynew.AssetlocFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssetsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AssetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssetsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public AssetsFragment() {
        // Required empty public constructor
    }
   /* public static TextView asstes;*/
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssetsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssetsFragment newInstance(String param1, String param2) {
        AssetsFragment fragment = new AssetsFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_assets, container, false);

        MainFragment.title_new1.setText("Assets");
        MainFragment.filters.setVisibility(View.VISIBLE);
        MainFragment.menuitem1.setVisibility(View.VISIBLE);
        MainFragment.menuoption.setVisibility(View.VISIBLE);
        MainFragment.sp1.setVisibility(View.GONE);
        MainFragment.linear_one.setVisibility(View.VISIBLE);
        MainFragment.linear_two.setVisibility(View.VISIBLE);
        MainFragment.spinnerselection.setVisibility(View.VISIBLE);

        MainFragment.text_onee.setText("ALL ASSETS");
        MainFragment.text_twoo.setText("BY LOCATION");

      /*  asstes=(TextView)rootView.findViewById(R.id.get_assets);
        asstes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(), Add_Asset.class);
                startActivity(i);
            }
        });*/

        MainFragment.filters.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.uparrow);
        MainFragment.filters.setImageBitmap(bitmap);

        AllassetsFragment aa1 = new AllassetsFragment();
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();


        MainFragment.linear_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllassetsFragment aa1 = new AllassetsFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();

            }
        });
        MainFragment.linear_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetlocFragment aa1 = new AssetlocFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();


            }
        });


       /* MainFragment.menuoption.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.sortatoz);
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
        });*/

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
