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

import activities.Add_parts;
import activities.New_users;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PeopleTeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PeopleTeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeopleTeamFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PeopleTeamFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PeopleTeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PeopleTeamFragment newInstance(String param1, String param2) {
        PeopleTeamFragment fragment = new PeopleTeamFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_people_team, container, false);

        MainFragment.title_new1.setText("People");
        MainFragment.filters.setVisibility(View.GONE);
        MainFragment.menuitem1.setVisibility(View.VISIBLE);
        MainFragment.menuoption.setVisibility(View.VISIBLE);
        MainFragment.sp1.setVisibility(View.GONE);
        MainFragment.linear_one.setVisibility(View.VISIBLE);
        MainFragment.linear_two.setVisibility(View.VISIBLE);
        MainFragment.spinnerselection.setVisibility(View.VISIBLE);

        MainFragment.text_onee.setText("PEOPLE");
        MainFragment.text_twoo.setText("TEAMS");


        MainFragment.menuoption.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.addaditionalwrkr);
        MainFragment.menuoption.setImageBitmap(bitmap1);

        People_Sub_Fragment aa1 = new People_Sub_Fragment();
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();

       /* MainFragment.menuoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(), New_users.class);
                startActivity(i);
            }
        });*/

        MainFragment.linear_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People_Sub_Fragment aa1 = new People_Sub_Fragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();


            }
        });
        MainFragment.linear_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamFragment aa1 = new TeamFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();


            }
        });


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
