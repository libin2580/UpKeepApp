package activitynew;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

import java.util.ArrayList;
import java.util.List;

import activities.Add_parts;
import activities.Saveloc;
import adapters.Loclistview;
import adapters.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListviewFragmentasset.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListviewFragmentasset#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListviewFragmentasset extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListviewFragmentasset() {
        // Required empty public constructor
    }

    RecyclerView mRecycller;
    FirebaseFirestore mFirebasestore;
    List<Saveloc> new_userlist;
    Loclistview adapterpeople;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListviewFragmentasset newInstance(String param1, String param2) {
        ListviewFragmentasset fragment = new ListviewFragmentasset();
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
        View rootView = inflater.inflate(R.layout.fragment_people__sub_, container, false);
        new_userlist=new ArrayList<>();
        adapterpeople=new Loclistview(new_userlist);

      //  final String strtext = getArguments().getString("locc");

        MainFragment.title_new1.setText("Asset by Locations");
        MainFragment.filters.setVisibility(View.VISIBLE);
        MainFragment.menuitem1.setVisibility(View.VISIBLE);
        MainFragment.menuoption.setVisibility(View.VISIBLE);
        MainFragment.sp1.setVisibility(View.GONE);
        MainFragment.linear_one.setVisibility(View.VISIBLE);
        MainFragment.linear_two.setVisibility(View.VISIBLE);
        MainFragment.spinnerselection.setVisibility(View.VISIBLE);

        MainFragment.text_onee.setText("ALL ASSETS");
        MainFragment.text_twoo.setText("BY LOCATION");

        MainFragment.filters.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.uparrow);
        MainFragment.filters.setImageBitmap(bitmap);


        MainFragment.menuoption.setColorFilter(getActivity().getResources().getColor(R.color.colorwhite));
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.sortatoz);
        MainFragment.menuoption.setImageBitmap(bitmap1);

        mRecycller=(RecyclerView)rootView.findViewById(R.id.list_people);
        mRecycller.setHasFixedSize(true);
        mRecycller.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycller.setAdapter(adapterpeople);
        mRecycller.getLayoutManager().setMeasurementCacheEnabled(false);

        mFirebasestore=FirebaseFirestore.getInstance();

        mFirebasestore.collection("Location").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(e!=null)
                {
                    Log.d("TAG",e.getMessage());
                }

                for(DocumentChange doc: documentSnapshots.getDocumentChanges())
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                        // String emails=doc.getDocument().getString("email");
                        // String names=doc.getDocument().getString("name");

                        Saveloc usersdata=doc.getDocument().toObject(Saveloc.class);
                        new_userlist.add(usersdata);
                        adapterpeople.notifyDataSetChanged();

                    }


                }
            }
        });


        mRecycller.addOnItemTouchListener
                (
                        new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                String deal_slug = new_userlist.get(position).getLocaddress();

                                Toast.makeText(getActivity(), deal_slug, Toast.LENGTH_SHORT).show();


                              /*  if(strtext.equals("locadd")) {*/
                                    Add_parts.p_loc.setVisibility(View.VISIBLE);
                                    Add_parts.p_loc.setText(deal_slug);
                                    getActivity().finish();
                               /* }
                                else
                                {
                                    Toast.makeText(getActivity(),"No data selected",Toast.LENGTH_LONG).show();
                                }
*/
                            }
                        }));


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
