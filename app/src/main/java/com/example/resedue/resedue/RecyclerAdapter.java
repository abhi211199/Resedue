package com.example.resedue.resedue;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resedue.resedue.R;
import com.example.resedue.resedue.users;


import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>{
    private List<users> list;
    private Context context;
    public RecyclerAdapter(Context context,List<users> list) {
        this.list = list;
        this.context = context;
    }
    @Override

    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        users mylist = list.get(position);
        holder.name.setText("Name  :  "+mylist.getName());
        holder.add.setText("Address  :  "+mylist.getAddress());
        holder.ref.setText("Reference No.  :  "+mylist.getRef());
        holder.status.setText("Status  :  "+mylist.getStatus());
        holder.wt.setText("Weight of Grains:"+mylist.getWt());
        holder.ph.setText("Contact No.:"+mylist.getMob());
        holder.sell.setText("Priority of Seller :  "+mylist.getSell());
        if(mylist.getStatus().equals("REJECTED"))
        {
            holder.status.setTextColor(Color.RED);
        }
        else if(mylist.getStatus().equals("PENDING"))holder.status.setTextColor(Color.GRAY);
        else if(mylist.getStatus().equals("ACCEPTED"))holder.status.setTextColor(Color.GREEN);
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }
//@Override
//public int getItemCount() {
//    return list.size();
//}

    class MyHolder extends RecyclerView.ViewHolder {
        TextView ref,name,add,wt,sell,ph,status;
        CardView cardView ;

        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            add= (TextView) itemView.findViewById(R.id.add);
            ref=(TextView) itemView.findViewById(R.id.ref);
            wt=(TextView) itemView.findViewById(R.id.wt);
            sell=(TextView) itemView.findViewById(R.id.sell);
            ph=(TextView) itemView.findViewById(R.id.ph);
            status=(TextView) itemView.findViewById(R.id.status);
            cardView=(CardView)itemView.findViewById(R.id.card);


        }

    }

}
