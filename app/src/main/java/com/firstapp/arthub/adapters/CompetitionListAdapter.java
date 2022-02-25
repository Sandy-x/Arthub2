package com.firstapp.arthub.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firstapp.arthub.Home_colorpencil;
import com.firstapp.arthub.Home_digitalart;
import com.firstapp.arthub.Home_mandala;
import com.firstapp.arthub.Home_photography;
import com.firstapp.arthub.R;
import com.firstapp.arthub.home_drawing;
import com.firstapp.arthub.Home_painting;
import com.firstapp.arthub.models.competitionlistModel;

import java.util.List;

public class CompetitionListAdapter extends RecyclerView.Adapter<CompetitionListAdapter.ViewHolder> {

    private Context context;
    private List<competitionlistModel> competitionlistModels;

    public CompetitionListAdapter(Context context, List<competitionlistModel> competitionlistModels) {
        this.context = context;
        this.competitionlistModels = competitionlistModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.homeimages_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final competitionlistModel temp = competitionlistModels.get(position);
        Glide.with(context).load(competitionlistModels.get(position).getImg_url()).into(holder.Imageurl);
        holder.nameofcomp.setText(competitionlistModels.get(position).getName());

        switch (position){
            case 0:
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Home_painting.class);
                        intent.putExtra("topic",temp.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, home_drawing.class);
                        intent.putExtra("topic",temp.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Home_mandala.class);
                        intent.putExtra("topic",temp.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            case 3:
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Home_digitalart.class);
                        intent.putExtra("topic",temp.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            case 4:
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Home_photography.class);
                        intent.putExtra("topic",temp.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            case 5:
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Home_colorpencil.class);
                        intent.putExtra("topic",temp.getName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;


            default:
        }

    }

    @Override
    public int getItemCount() {
        return competitionlistModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Imageurl;
        TextView nameofcomp;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Imageurl = itemView.findViewById(R.id.listofcompetetion);
            nameofcomp = itemView.findViewById(R.id.competetion_name);
            linearLayout = itemView.findViewById(R.id.linear_m);

        }
    }
}

