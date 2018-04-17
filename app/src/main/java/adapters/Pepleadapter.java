package adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.upkeep.upkeep.R;

import java.util.List;

import activities.SaveData;
import fragmentactivity.People_Sub_Fragment;

/**
 * Created by SIDDEEQ DESIGNER on 1/9/2018.
 */

public class Pepleadapter extends RecyclerView.Adapter<Pepleadapter.ViewHolder>
{

    public List<SaveData>userdata;
    public Pepleadapter(List<SaveData>userdata)
    {

        this.userdata=userdata;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.userEmail.setText(userdata.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return userdata.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        View mView;

        TextView userEmail;
       // TextView userName;


        public ViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

             userEmail=(TextView)itemView.findViewById(R.id.text_list);
        }
    }
   /* private Activity context;
    private List<SaveData>arrList;

    public Pepleadapter(Activity context, List<SaveData>arrList)
    {
        super(context, R.layout.list_layout,arrList);
        this.context=context;
        this.arrList=arrList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflaters=context.getLayoutInflater();
        View listviewitem=inflaters.inflate(R.layout.list_layout,null,true);

        TextView textemail=(TextView)listviewitem.findViewById(R.id.text_list);

        SaveData artist=arrList.get(position);
        textemail.setText(artist.getName());
        return listviewitem;
    }*/
}

