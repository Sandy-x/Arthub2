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
import com.firstapp.arthub.DetailActivity_DigitalArt;
import com.firstapp.arthub.R;
import com.firstapp.arthub.models.artoftheweekModel;
import com.firstapp.arthub.models.digitalartModel;

import java.util.List;

public class ArtoftheweekAdapter extends RecyclerView.Adapter<ArtoftheweekAdapter.ViewHolder> {
    private Context context;
    private List<artoftheweekModel> artoftheweekModelList;

    public ArtoftheweekAdapter(Context context,List<artoftheweekModel> artoftheweekModelList) {
        this.context = context;
        this.artoftheweekModelList =  artoftheweekModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artoftheweellists,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  artoftheweekModel temp = artoftheweekModelList.get(position);
        Glide.with(context).load(artoftheweekModelList.get(position).getDrawing()).into(holder.imageView);
        holder.artistname.setText(artoftheweekModelList.get(position).getArtistName());

    }

    @Override
    public int getItemCount() {
        return artoftheweekModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView artistname;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewaotw);
            artistname = itemView.findViewById(R.id.textviewaotw);
            linearLayout = itemView.findViewById(R.id.linearLayoutaotw);
        }
    }
}
