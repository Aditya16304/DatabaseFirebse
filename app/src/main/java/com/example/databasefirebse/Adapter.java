package com.example.databasefirebse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> models;
    public Adapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.roomDetail.setText(models.get(i).getRoomDetail());
        viewHolder.complaint.setText(models.get(i).getComplaint());
        viewHolder.ch1.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView roomDetail,complaint;
        CheckBox ch1,ch2,ch3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomDetail=itemView.findViewById(R.id.roomDetailsText);
            complaint=itemView.findViewById(R.id.complaintDetailsText);
            ch1=itemView.findViewById(R.id.check1);
            ch2=itemView.findViewById(R.id.check2);
            ch3=itemView.findViewById(R.id.check3);
        }
    }
}
