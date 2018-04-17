package adapters;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upkeep.upkeep.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import activities.RequestData;
import activities.WorkorderData;

/**
 * Created by SIDDEEQ DESIGNER on 2/5/2018.
 */

public class Workorderadapter extends RecyclerView.Adapter<Workorderadapter.ViewHolder>
{

    public List<WorkorderData> userdata;
    public Workorderadapter(List<WorkorderData>userdata)
    {

        this.userdata=userdata;

    }

    @Override
    public Workorderadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_workorder,parent,false);


        return new Workorderadapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(Workorderadapter.ViewHolder holder, int position) {

        String priorities=userdata.get(position).getPriority();


        if(priorities.equals("None"))
        {
            priorities="";
        }
        else
        {
            priorities=priorities;
        }

        holder.text1.setText(userdata.get(position).getWrkorder());
        holder.text2.setText(userdata.get(position).getAssignast());
        holder.text3.setText(userdata.get(position).getAddwrkr()+" Due");
        holder.text4.setText(priorities);
    }

    @Override
    public int getItemCount()
    {
        return userdata.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        View mView;

        TextView text1,text2,text3,text4;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

            text1=(TextView)itemView.findViewById(R.id.text_list);
            text2=(TextView)itemView.findViewById(R.id.text_list1);
            text3=(TextView)itemView.findViewById(R.id.text_list3);
            text4=(TextView)itemView.findViewById(R.id.priorities);
        }
    }

}

