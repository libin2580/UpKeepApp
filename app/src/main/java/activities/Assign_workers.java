package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.upkeep.upkeep.R;

import java.util.ArrayList;
import java.util.List;

import adapters.Pepleadapter;
import adapters.RecyclerItemClickListener;

/**
 * Created by SIDDEEQ DESIGNER on 1/15/2018.
 */

public class Assign_workers extends AppCompatActivity
{

    TextView toolbar_title;
    ImageView toolbar_backarrow,toolbar_add;

    RecyclerView mRecycller;
    FirebaseFirestore mFirebasestore;
    List<SaveData> new_userlist;
    Pepleadapter adapterpeople;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_workerss);

        final String datas=getIntent().getStringExtra("aaa");


        new_userlist=new ArrayList<>();
        adapterpeople=new Pepleadapter(new_userlist);
        mFirebasestore=FirebaseFirestore.getInstance();

        mRecycller=(RecyclerView)findViewById(R.id.list_people);
        mRecycller.setHasFixedSize(true);
        mRecycller.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecycller.setAdapter(adapterpeople);
        mRecycller.getLayoutManager().setMeasurementCacheEnabled(false);


        toolbar_title=(TextView)findViewById(R.id.toolbar_title);
        toolbar_backarrow=(ImageView)findViewById(R.id.toolbar_image);
        toolbar_add=(ImageView)findViewById(R.id.toolbar_save);
        toolbar_title.setText("People");

        toolbar_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        toolbar_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getApplicationContext(),New_users.class);
                startActivity(i);

            }
        });

        mFirebasestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        SaveData usersdata=doc.getDocument().toObject(SaveData.class);
                        new_userlist.add(usersdata);
                        adapterpeople.notifyDataSetChanged();

                    }
                }
            }
        });


        mRecycller.addOnItemTouchListener
                (
                        new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                String deal_slug = new_userlist.get(position).getEmail();

                                Toast.makeText(getApplicationContext(), deal_slug, Toast.LENGTH_SHORT).show();

                                if(datas.equals("asnwrkr"))

                                {
                                    Add_Workorder.assignworker_settext.setVisibility(View.VISIBLE);
                                    Add_Workorder.assignworker_settext.setText(deal_slug);
                                    finish();
                                }
                                else if(datas.equals("astwrkr"))
                                {
                                    Add_Asset.ast_wrk.setText(deal_slug);
                                    finish();
                                }
                                else
                                {
                                    Add_Workorder.aditionalworker_text.setVisibility(View.VISIBLE);
                                    Add_Workorder.aditionalworker_text.setText(deal_slug);
                                    finish();
                                }
                            }
                        }));
    }
}
