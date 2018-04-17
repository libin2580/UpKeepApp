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
import android.widget.Button;

import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

import activities.Location_details;
import adapters.ListviewFragmentnew;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PartlocFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PartlocFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartlocFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PartlocFragment() {
        // Required empty public constructor
    }
    Button btn_one,btn_two;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PartlocFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PartlocFragment newInstance(String param1, String param2) {
        PartlocFragment fragment = new PartlocFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_partloc, container, false);

        AllpartsFragment.parts.setText("+ Location");

        AllpartsFragment.parts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getActivity(), Location_details.class);
                startActivity(in);
            }
        });

        btn_one=(Button)rootView.findViewById(R.id.btn1);
        btn_two=(Button)rootView.findViewById(R.id.btn2);

        btn_one=(Button)rootView.findViewById(R.id.btn1) ;

        btn_one.setBackgroundColor(getResources().getColor(R.color.colorred));
        btn_one.setTextColor(getResources().getColor(R.color.colorwhite));

        btn_two.setBackground(getActivity().getDrawable(R.drawable.my_border));
        btn_two.setTextColor(getResources().getColor(R.color.colorred));

        ListviewFragmentnew aa1 = new ListviewFragmentnew();
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frme12, aa1).commit();

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_one.setBackgroundColor(getResources().getColor(R.color.colorred));
                btn_one.setTextColor(getResources().getColor(R.color.colorwhite));

                btn_two.setBackground(getActivity().getDrawable(R.drawable.my_border));
                btn_two.setTextColor(getResources().getColor(R.color.colorred));

                ListviewFragmentnew aa1 = new ListviewFragmentnew();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frme12, aa1).commit();

            }
        });

btn_two.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        btn_one.setText("A");
        btn_two.setText("B");
    }
});


        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_two.setBackgroundColor(getResources().getColor(R.color.colorred));
                btn_two.setTextColor(getResources().getColor(R.color.colorwhite));

                btn_one.setBackground(getActivity().getDrawable(R.drawable.my_border));
                btn_one.setTextColor(getResources().getColor(R.color.colorred));

                MapviewFragment aa1 = new MapviewFragment();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frme12, aa1).commit();

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
