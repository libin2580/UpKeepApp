package fragmentactivity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.upkeep.upkeep.MainActivity;
import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

import java.util.ArrayList;
import java.util.List;

import activities.Add_Request;
import activities.RequestData;
import activitynew.AssetData;
import activitynew.Assetadapter;
import adapters.Requestadapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RequestFragment() {
        // Required empty public constructor
    }
    TextView input_data,add_rqst,request_text;
    ImageView get_image;
    LinearLayout request_linear;


    RecyclerView mRecycller;
    FirebaseFirestore firebasestore;
    List<RequestData> new_userlist;
    Requestadapter adapterpeople;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestFragment newInstance(String param1, String param2) {
        RequestFragment fragment = new RequestFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_request, container, false);




        new_userlist=new ArrayList<>();
        adapterpeople=new Requestadapter(new_userlist);
        firebasestore=FirebaseFirestore.getInstance();


        mRecycller=(RecyclerView)rootView.findViewById(R.id.list_req);
        mRecycller.setHasFixedSize(true);
        mRecycller.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycller.setAdapter(adapterpeople);
        mRecycller.getLayoutManager().setMeasurementCacheEnabled(false);


        MainFragment.title_new1.setText("Requests");
        MainFragment.menuitem1.setVisibility(View.GONE);
        MainFragment.spinnerselection.setVisibility(View.GONE);
        MainFragment.filters.setVisibility(View.GONE);
       // MainFragment.tab_barnew.setVisibility(View.GONE);
        MainFragment.sp1.setVisibility(View.VISIBLE);
        MainFragment.linear_one.setVisibility(View.GONE);
        MainFragment.linear_two.setVisibility(View.GONE);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.menuarrow);
        MainFragment.filters.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        MainFragment.filters.setImageBitmap(bitmap);

        MainFragment.menuoption.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.menutoolbar);
        MainFragment.menuoption.setImageBitmap(bitmap1);


       // input_data=(TextView)rootView.findViewById(R.id.text_requests);
        add_rqst=(TextView) rootView.findViewById(R.id.add_request);
        get_image=(ImageView) rootView.findViewById(R.id.get_started);

        request_text=(TextView)rootView.findViewById(R.id.texts1);
        request_linear=(LinearLayout) rootView.findViewById(R.id.linear1);

       /* String text1 = "<font color=#585B5D>Request must be approved and</font>";
        String text2="<font color=#585B5D>assigned by an admin user. Press</font>";
        String text3 = "<font color=#585B5D>the "+" button at the bottom to</font>";
        String text4 = "<font color=#585B5D>create a request.</font>";

        input_data.setText(Html.fromHtml(text1+"<br>"+text2+"<br>"+text3+"<br>"+text4));
*/
      /*  Animation rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        get_image.startAnimation(rotation);*/



      mRecycller.setVisibility(View.VISIBLE);
      request_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow111, 0);
      final int counts=mRecycller.getChildCount();
      request_text.setText(counts+" REQUESTS");


      request_linear.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              if(mRecycller.getVisibility()==View.VISIBLE)
              {

                  mRecycller.setVisibility(View.GONE);
                  request_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uparrow111, 0);
                  request_text.setText(counts+" REQUESTS");
              }
              else
              {
                  mRecycller.setVisibility(View.VISIBLE);
                  request_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.downarrow111, 0);
                  request_text.setText(counts+" REQUESTS");
              }

          }
      });

        add_rqst.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(), Add_Request.class);
                startActivity(i);

            }
        });


        firebasestore.collection("Request").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(e!=null)
                {
                    Log.d("errror",e.getMessage());
                }

                for(DocumentChange doc: documentSnapshots.getDocumentChanges())
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                        RequestData usersdata=doc.getDocument().toObject(RequestData.class);
                        new_userlist.add(usersdata);
                        adapterpeople.notifyDataSetChanged();

                    }

                }
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
