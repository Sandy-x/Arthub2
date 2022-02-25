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
import com.firstapp.arthub.DeatailCompetetion;
import com.firstapp.arthub.DetailActivity_MandalaArt;
import com.firstapp.arthub.R;
import com.firstapp.arthub.models.digitalartModel;
import com.firstapp.arthub.models.drawinglistModel;
import com.firstapp.arthub.models.mandalaartlistModel;

import java.util.List;

public class MandalaArtAdapter extends RecyclerView.Adapter<MandalaArtAdapter.ViewHolder>  {

    private Context context;
    private List<mandalaartlistModel> mandalaartlistModels;

    public MandalaArtAdapter(Context context,List<mandalaartlistModel> mandalaartlistModels) {
        this.context = context;
        this.mandalaartlistModels =  mandalaartlistModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mandala_fragment_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final mandalaartlistModel temp = mandalaartlistModels.get(position);
        Glide.with(context).load(mandalaartlistModels.get(position).getImage_url()).into(holder.imageView);
        holder.date.setText(mandalaartlistModels.get(position).getLast_date());
        holder.fee.setText(mandalaartlistModels.get(position).getEntry_fee());
        holder.compMA_ID.setText(mandalaartlistModels.get(position).getCompid());
        holder.topic.setText(mandalaartlistModels.get(position).getTopic());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity_MandalaArt.class);
                intent.putExtra("topicmart",temp.getTopic());
                intent.putExtra("feemart",temp.getEntry_fee());
                intent.putExtra("imagemart",temp.getImage_url());
                intent.putExtra("compID",temp.getCompid());
                intent.putExtra("last_datemart",temp.getLast_date());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mandalaartlistModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView date, fee, topic,compMA_ID;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_mandalaart);
            fee = itemView.findViewById(R.id.entryfeemandalaart);
            date = itemView.findViewById(R.id.mandalaart_Lastdate);
            topic = itemView.findViewById(R.id.topic_mandalaart);
            linearLayout = itemView.findViewById(R.id.linear_ma);
            compMA_ID = itemView.findViewById(R.id.compMA_ID);
        }
    }
}
