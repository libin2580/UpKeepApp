package activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.upkeep.upkeep.MainActivity;
import com.upkeep.upkeep.MainFragment;
import com.upkeep.upkeep.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.RecyclerItemClickListener;
import adapters.Teamassignadapter;
import adapters.Teamsadapter;


public class AssignTeamFragment extends AppCompatActivity
{

    TextView toolbar_title;
    ImageView toolbar_backarrow,toolbar_save;
    RecyclerView mRecycller;
    ProgressDialog mDialogs;
    FirebaseFirestore firebasestore;
    List<TeanmData> new_userlist;
    Teamassignadapter adapterpeople;
    EditText e1,e2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_assign_team);

        new_userlist=new ArrayList<>();
        adapterpeople=new Teamassignadapter(new_userlist);
        firebasestore=FirebaseFirestore.getInstance();
        mDialogs=new ProgressDialog(getApplicationContext());
        mDialogs.setMessage("Saving...");

        toolbar_title=(TextView)findViewById(R.id.toolbar_title);
        toolbar_backarrow=(ImageView)findViewById(R.id.toolbar_image);
        toolbar_save=(ImageView)findViewById(R.id.toolbar_save) ;



        toolbar_title.setText("Select Team");

        mRecycller=(RecyclerView)findViewById(R.id.list_people);
        mRecycller.setHasFixedSize(true);
        mRecycller.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecycller.setAdapter(adapterpeople);
        mRecycller.getLayoutManager().setMeasurementCacheEnabled(false);

        toolbar_backarrow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               finish();
           }
       });


        firebasestore.collection("Team").addSnapshotListener(new EventListener<QuerySnapshot>() {
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

                        TeanmData usersdata=doc.getDocument().toObject(TeanmData.class);
                        new_userlist.add(usersdata);
                        adapterpeople.notifyDataSetChanged();

                    }


                }
            }
        });


        toolbar_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompts, null);

               // View promptsView = li.from(getApplicationContext()).inflate(R.layout.prompts,null,false);

                e1=(EditText)promptsView.findViewById(R.id.input_teamname);
                e2=(EditText)promptsView.findViewById(R.id.input_additionalinfo);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AssignTeamFragment.this);

                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(false)

                        .setPositiveButton("SUBMIT",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        mDialogs.show();
                                        String s1=e1.getText().toString();
                                        String s2=e2.getText().toString();

                                        Map<String,String> userMap=new HashMap<>();
                                        userMap.put("tname",s1);
                                        userMap.put("tinfo",s2);

                                        firebasestore.collection("Team").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference)
                                            {
                                                mDialogs.dismiss();
                                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                mDialogs.dismiss();
                                                String error=e.getMessage();
                                                Toast.makeText(getApplicationContext(),"Error "+error,Toast.LENGTH_LONG).show();

                                            }
                                        });



                                    }
                                })
                        .setNegativeButton("CANCEL",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        dialog.dismiss();

                                    }
                                });


                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorwhite);
                alertDialog.getWindow().setLayout(600, 400);
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
            }
        });

      /* mRecycller.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {


                Toast.makeText(getApplicationContext(), "Single Click on position        :"+mRecycller.getContentDescription(),
                        Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {



            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {



            }
        });
*/


        mRecycller.addOnItemTouchListener
                (
                        new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                String deal_slug = new_userlist.get(position).getTname();

                                Toast.makeText(getApplicationContext(), deal_slug, Toast.LENGTH_SHORT).show();

                                Add_Workorder.assign_teamdata.setText(deal_slug);
                                finish();
                            }
                        })
                );



    }

}
