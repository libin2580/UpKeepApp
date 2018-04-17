package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upkeep.upkeep.R;

import java.util.List;

import activities.PartData;

/**
 * Created by SIDDEEQ DESIGNER on 1/23/2018.
 */

public class Partadapter extends RecyclerView.Adapter<Partadapter.ViewHolder>
{

    public List<PartData> userdata;
    public Partadapter(List<PartData>userdata)
    {

        this.userdata=userdata;

    }

    @Override
    public Partadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layoutpart,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text1.setText(userdata.get(position).getPname());
        holder.text2.setText(userdata.get(position).getPdes()+", $"+userdata.get(position).getPcost());
        holder.text3.setText("Running Low! "+userdata.get(position).getPqnt());
    }


    @Override
    public int getItemCount() {
        return userdata.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        View mView;

        TextView text1,text2,text3;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

            text1=(TextView)itemView.findViewById(R.id.text_list);
            text2=(TextView)itemView.findViewById(R.id.text_list1);
            text3=(TextView)itemView.findViewById(R.id.text_list2);
        }
    }

}

