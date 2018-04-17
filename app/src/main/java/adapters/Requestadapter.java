package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upkeep.upkeep.R;

import java.util.List;

import activities.PartData;
import activities.RequestData;

/**
 * Created by SIDDEEQ DESIGNER on 1/30/2018.
 */

public class Requestadapter extends RecyclerView.Adapter<Requestadapter.ViewHolder>
{

    public List<RequestData> userdata;
    public Requestadapter(List<RequestData>userdata)
    {

        this.userdata=userdata;

    }

    @Override
    public Requestadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_request,parent,false);


        return new Requestadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Requestadapter.ViewHolder holder, int position) {
        holder.text1.setText(userdata.get(position).getTitle());
        holder.text2.setText("1 minute");
        holder.text3.setText("By: "+"rr@gmail.com");
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
            text3=(TextView)itemView.findViewById(R.id.text_list3);
        }
    }

}

