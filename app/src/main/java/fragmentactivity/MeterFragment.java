package fragmentactivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MeterFragment() {
        // Required empty public constructor
    }
    TextView input_data;
    LinearLayout video_txt;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeterFragment newInstance(String param1, String param2) {
        MeterFragment fragment = new MeterFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_meter, container, false);

        MainFragment.title_new1.setText("Meters");
        MainFragment.menuitem1.setVisibility(View.GONE);
        MainFragment.spinnerselection.setVisibility(View.GONE);
        MainFragment.filters.setVisibility(View.GONE);
        //MainFragment.tab_barnew.setVisibility(View.GONE);
        MainFragment.sp1.setVisibility(View.VISIBLE);
        MainFragment.linear_one.setVisibility(View.GONE);
        MainFragment.linear_two.setVisibility(View.GONE);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.menuarrow);
        MainFragment.filters.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        MainFragment.filters.setImageBitmap(bitmap);

        MainFragment.menuoption.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1=BitmapFactory.decodeResource(getResources(),R.drawable.menutoolbar);
        MainFragment.menuoption.setImageBitmap(bitmap1);

        video_txt=(LinearLayout)rootView.findViewById(R.id.metervideo);
        input_data=(TextView)rootView.findViewById(R.id.input_pass);

        String text1 = "<font color=#585B5D>Use meters to track the usage and</font>";
        String text2="<font color=#585B5D>condition of equipment and assets</font>";

        input_data.setText(Html.fromHtml(text1+"<br>"+text2));

        video_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://help.onupkeep.com/getting-started-with-upkeep/how-to-use-upkeep-features/how-can-a-work-order-be-scheduled-according-to-meter-readings-and-not-just-time-interval-based";
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
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
