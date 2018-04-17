package fragmentactivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.zzahn;
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

import activities.Add_Asset;
import activities.Add_parts;
import activities.Location_details;
import activities.PartData;
import activities.TeanmData;
import adapters.Partadapter;
import adapters.Teamsadapter;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllpartsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllpartsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllpartsFragment extends Fragment implements ZXingScannerView.ResultHandler
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AllpartsFragment() {
        // Required empty public constructor
    }

    RecyclerView mRecycller;
    ProgressDialog mDialogs;
    FirebaseFirestore firebasestore;
    List<PartData> new_userlist;
    Partadapter adapterpeople;
    ImageView qrscanning;
    String vString;
    LayoutInflater inflater;
    public static EditText searchViews;
    private ZXingScannerView mScannerView;
    public static TextView parts;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllpartsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllpartsFragment newInstance(String param1, String param2) {
        AllpartsFragment fragment = new AllpartsFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_allparts, container, false);

        parts=(TextView)rootView.findViewById(R.id.get_assets);

        parts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(), Add_parts.class);
                startActivity(i);
            }
        });
       parts.setText("+ Add Part");

        parts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getActivity(), Add_parts.class);
                startActivity(in);
            }
        });

        MainFragment.title_new1.setText("Parts/Inventory");
        MainFragment.filters.setVisibility(View.VISIBLE);
        MainFragment.menuitem1.setVisibility(View.VISIBLE);
        MainFragment.menuoption.setVisibility(View.VISIBLE);
        MainFragment.sp1.setVisibility(View.GONE);
        MainFragment.linear_one.setVisibility(View.VISIBLE);
        MainFragment.linear_two.setVisibility(View.VISIBLE);
        MainFragment.spinnerselection.setVisibility(View.VISIBLE);

        MainFragment.text_onee.setText("ALL PARTS");
        MainFragment.text_twoo.setText("BY LOCATION");


        MainFragment.filters.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.uparrow);
        MainFragment.filters.setImageBitmap(bitmap);


        MainFragment.menuoption.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.sortatoz);
        MainFragment.menuoption.setImageBitmap(bitmap1);

        new_userlist=new ArrayList<>();
        adapterpeople=new Partadapter(new_userlist);
        firebasestore=FirebaseFirestore.getInstance();
        mDialogs=new ProgressDialog(getActivity());
        mDialogs.setMessage("Saving......");


        firebasestore=FirebaseFirestore.getInstance();

        searchViews=(EditText)rootView.findViewById(R.id.search_view);
        qrscanning=(ImageView)rootView.findViewById(R.id.image_qrcodes);
        mRecycller=(RecyclerView)rootView.findViewById(R.id.list_invnry);
        mRecycller.setHasFixedSize(true);
        mRecycller.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycller.setAdapter(adapterpeople);
        mRecycller.getLayoutManager().setMeasurementCacheEnabled(false);

        mRecycller.getLayoutManager().setMeasurementCacheEnabled(false);


       // String resultscan = getArguments().getString("aaa");
       // searchViews.setText(resultscan);


        /*Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null)
        {
            searchViews.setText(" "+bundle.getString("aaa"));
        }*/


        qrscanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QrScanner();
            }
        });


        firebasestore.collection("PartsInventory").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        PartData usersdata=doc.getDocument().toObject(PartData.class);
                        new_userlist.add(usersdata);
                        adapterpeople.notifyDataSetChanged();

                    }

                }
            }
        });

        searchViews.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

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
    public void QrScanner(){
        mScannerView = new ZXingScannerView(getActivity());   // Programmatically initialize the scanner view<br />
        getActivity().setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.<br />
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(final com.google.zxing.Result result) {

        Log.e("resultssssssssss",result.toString());

        mScannerView.removeAllViews();
        mScannerView.stopCamera();
        //setContentView(R.layout.activity_add_part);


        vString = result.getText();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //Add_parts.p_barcode.setText(result.getText());
                searchViews.setText(result.getText().toString());
                Toast.makeText(getActivity(),vString,Toast.LENGTH_LONG).show();
               // getActivity().finish();


               /* AllpartsFragment aa1 = new AllpartsFragment();
                Bundle data = new Bundle();//create bundle instance
                data.putString("aaa", result.getText().toString());//put string to pass with a key value
                aa1.setArguments(data);*/


               // getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right, 0, 0, R.animator.slide_out_left).replace(R.id.frame2, aa1).commit();

               /* Intent intent = new Intent(getActivity(), AllpartsFragment.class);
                intent.putExtra("aaa",result.getText().toString());
                startActivity(intent);*/

               /* Bundle bundle = new Bundle();
                bundle.putString("aaa",result.getText().toString()); // Put anything what you want

                AllpartsFragment fragment2 = new AllpartsFragment();
                fragment2.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_allpart, fragment2)
                        .commit();*/

            }
        });

    }


}
