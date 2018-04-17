package adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upkeep.upkeep.MainActivity;
import com.upkeep.upkeep.R;

import java.util.List;

import activities.SaveData;
import activities.TeanmData;

/**
 * Created by SIDDEEQ DESIGNER on 1/12/2018.
 */

public class Teamsadapter extends RecyclerView.Adapter<Teamsadapter.ViewHolder> {

    public List<TeanmData> userdata;
    public ImageView imagess;

    public Teamsadapter(List<TeanmData> userdata) {

        this.userdata = userdata;

    }

    @Override
    public Teamsadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teams, parent, false);


        return new Teamsadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tname.setText(userdata.get(position).getTname());
        holder.tinfo.setText(userdata.get(position).getTinfo());

    }

    @Override
    public int getItemCount() {
        return userdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView tinfo, tname;
        FirebaseFirestore firebasestore;

        // TextView userName;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tname = (TextView) itemView.findViewById(R.id.textViewname);
            tinfo = (TextView) itemView.findViewById(R.id.textViewinfo);
            imagess = (ImageView) itemView.findViewById(R.id.imgges);
            firebasestore=FirebaseFirestore.getInstance();

            imagess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    PopupMenu popup = new PopupMenu(v.getRootView().getContext(), imagess);
                    popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {

                            String names=tname.getText().toString();
                            switch (item.getItemId()) {
                                case R.id.editt:
                                    return true;
                                case R.id.delt:

                                    firebasestore.collection("Team").document("Firstteam")
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(v.getRootView().getContext(), "Data deleted !",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            })

                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });


                                   /* firebasestore.collection("Team").getParent()
                                            .delete().addOnSuccessListener(new OnSuccessListener< Void >() {
                                        @Override
                                        public void onSuccess(Void aVoid) {




                                            Toast.makeText(v.getRootView().getContext(), "Data deleted !",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
*/
                                    return true;

                                default:
                                    return false;
                            }
                        }
                    });

                    popup.show();
                }

            });
        }


    }
}


