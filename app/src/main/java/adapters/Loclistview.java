package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upkeep.upkeep.R;

import java.util.List;

import activities.SaveData;
import activities.Saveloc;

/**
 * Created by SIDDEEQ DESIGNER on 1/20/2018.
 */

public class Loclistview extends RecyclerView.Adapter<Loclistview.ViewHolder>
{

    public List<Saveloc> userdata;
    public Loclistview(List<Saveloc>userdata)
    {

        this.userdata=userdata;

    }

    @Override
    public Loclistview.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout1,parent,false);


        return new Loclistview.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Loclistview.ViewHolder holder, int position) {


        holder.userAddress1.setText(userdata.get(position).getLocaddress());
        holder.userAddress2.setText(userdata.get(position).getLocname());

    }

    @Override
    public int getItemCount() {
        return userdata.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        View mView;

        TextView userAddress1,userAddress2;
        // TextView userName;


        public ViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

            userAddress1=(TextView)itemView.findViewById(R.id.text_address1);
            userAddress2=(TextView)itemView.findViewById(R.id.text_address2);
        }
    }

}

