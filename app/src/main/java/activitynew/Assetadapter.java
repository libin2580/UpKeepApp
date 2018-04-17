package activitynew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.upkeep.upkeep.R;

import java.util.List;

import activities.PartData;
import adapters.Partadapter;

/**
 * Created by SIDDEEQ DESIGNER on 1/30/2018.
 */

public class Assetadapter extends RecyclerView.Adapter<Assetadapter.ViewHolder>
{

    public List<AssetData> userdata;
    public ImageView asset_update_delete;



    public Assetadapter(List<AssetData>userdata)
    {

        this.userdata=userdata;
    }

    @Override
    public Assetadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_assets,parent,false);

        return new Assetadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Assetadapter.ViewHolder holder, int position) {
        holder.text1.setText(userdata.get(position).getAstname());
        holder.text2.setText(userdata.get(position).getAsttype()+" Location:"+userdata.get(position).getAstloc());

        /*holder.asset_update_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Selected","Update and Delete");
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return userdata.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        View mView;

        TextView text1,text2;


        public ViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

            text1=(TextView)itemView.findViewById(R.id.text_list);
            text2=(TextView)itemView.findViewById(R.id.text_list1);
            asset_update_delete=(ImageView)itemView.findViewById(R.id.asset_update_delete);
        }
    }

}

