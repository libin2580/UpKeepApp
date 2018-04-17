package fragmentactivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;

import activities.New_users;
import activities.SaveData;
import adapters.Pepleadapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link People_Sub_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link People_Sub_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class People_Sub_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public People_Sub_Fragment() {
        // Required empty public constructor
    }

    RecyclerView mRecycller;
    FirebaseFirestore mFirebasestore;
    List<SaveData>new_userlist;
    Pepleadapter adapterpeople;
    TextView aaa;


    /*ListView list_new;
    List<SaveData>new_arrayalist;
    DatabaseReference databaseArtists;*/
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment People_Sub_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static People_Sub_Fragment newInstance(String param1, String param2) {
        People_Sub_Fragment fragment = new People_Sub_Fragment();
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

   /* @Override
    public void onStart()
    {

        super.onStart();
        databaseArtists = FirebaseDatabase.getInstance().getReference("Users");
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                new_arrayalist.clear();

                for(DataSnapshot newdatasnapshot : dataSnapshot.getChildren())
                {

                    SaveData saveDatas=newdatasnapshot.getValue(SaveData.class);
                    new_arrayalist.add(saveDatas);

                }

               // Log.e("####################",new_arrayalist.toString());
                Pepleadapter adapters=new Pepleadapter(getActivity(),new_arrayalist);
                //adapter.notifyDataSetChanged();
                list_new.setAdapter(adapters);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_people__sub_, container, false);
        new_userlist=new ArrayList<>();
        adapterpeople=new Pepleadapter(new_userlist);

        MainFragment.title_new1.setText("People");
        MainFragment.filters.setVisibility(View.GONE);
        MainFragment.menuitem1.setVisibility(View.VISIBLE);
        MainFragment.menuoption.setVisibility(View.VISIBLE);
        MainFragment.sp1.setVisibility(View.GONE);
        MainFragment.linear_one.setVisibility(View.VISIBLE);
        MainFragment.linear_two.setVisibility(View.VISIBLE);
        MainFragment.spinnerselection.setVisibility(View.VISIBLE);

       MainFragment.text_twoo.setText("TEAMS");
       MainFragment.text_onee.setText("PEOPLE");

       mRecycller=(RecyclerView)rootView.findViewById(R.id.list_people);
       mRecycller.setHasFixedSize(true);
       mRecycller.setLayoutManager(new LinearLayoutManager(getActivity()));
       mRecycller.setAdapter(adapterpeople);
       mRecycller.getLayoutManager().setMeasurementCacheEnabled(false);

       mFirebasestore=FirebaseFirestore.getInstance();

        aaa=(TextView)rootView. findViewById(R.id.get_locc);

        aaa.setVisibility(View.GONE);

        MainFragment.menuoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getActivity(),New_users.class);
                startActivity(in);
            }
        });

       mFirebasestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

               if(e!=null)
               {
                   Log.e("TAG",e.getMessage());
               }
               for(DocumentChange doc: documentSnapshots.getDocumentChanges())
               {
                   if(doc.getType()==DocumentChange.Type.ADDED)
                   {
                       SaveData usersdata=doc.getDocument().toObject(SaveData.class);
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
