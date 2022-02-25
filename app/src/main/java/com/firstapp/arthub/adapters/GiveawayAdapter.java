package com.firstapp.arthub.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firstapp.arthub.R;
import com.firstapp.arthub.models.GiveawayModel;
import com.firstapp.arthub.models.artoftheweekModel;

import java.util.List;

public class GiveawayAdapter  extends RecyclerView.Adapter<GiveawayAdapter.ViewHolder>{
    private Context context;
    private List<GiveawayModel> giveawayModels;

    public GiveawayAdapter(Context context,List<GiveawayModel> giveawayModels) {
        this.context = context;
        this.giveawayModels =  giveawayModels;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.giveawaylist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  GiveawayModel temp = giveawayModels.get(position);
        Glide.with(context).load(giveawayModels.get(position).getImageview()).into(holder.imageView);
        holder.textView.setText(giveawayModels.get(position).getUrl());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(temp.getUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return giveawayModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Imageview_giveawaylist);
            textView = itemView.findViewById(R.id.textview_giveaway);
            linearLayout = itemView.findViewById(R.id.linearLayoutgiveaway);
        }
    }
}
