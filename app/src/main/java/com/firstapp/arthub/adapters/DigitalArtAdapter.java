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
import com.firstapp.arthub.DetailActivity_DigitalArt;
import com.firstapp.arthub.R;
import com.firstapp.arthub.models.colorpencillistModel;
import com.firstapp.arthub.models.digitalartModel;
import com.firstapp.arthub.models.drawinglistModel;

import java.util.List;

public class DigitalArtAdapter extends RecyclerView.Adapter<DigitalArtAdapter.ViewHolder> {

    private Context context;
    private List<digitalartModel> digitalartModels;

    public DigitalArtAdapter(Context context,List<digitalartModel> digitalartModels) {
        this.context = context;
        this.digitalartModels =  digitalartModels;

    }

    @NonNull
    @Override
    public DigitalArtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.digitalart_fragment_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DigitalArtAdapter.ViewHolder holder, int position) {

        final digitalartModel temp = digitalartModels.get(position);
        Glide.with(context).load(digitalartModels.get(position).getImage_url()).into(holder.imageView);
        holder.date.setText(digitalartModels.get(position).getLast_date());
        holder.fee.setText(digitalartModels.get(position).getEntry_fee());
        holder.compdigitalart_ID.setText(digitalartModels.get(position).getCompid());
        holder.topic.setText(digitalartModels.get(position).getTopic());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity_DigitalArt.class);
                intent.putExtra("topicda",temp.getTopic());
                intent.putExtra("feeda",temp.getEntry_fee());
                intent.putExtra("imageda",temp.getImage_url());
                intent.putExtra("Compid",temp.getCompid());
                intent.putExtra("last_dateda",temp.getLast_date());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return digitalartModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView date, fee, topic;
        LinearLayout linearLayout;
        TextView compdigitalart_ID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_digitalart);
            fee = itemView.findViewById(R.id.entryfeedigitalart);
            date = itemView.findViewById(R.id.digitalart_Lastdate);
            topic = itemView.findViewById(R.id.topic_digitalart);
            linearLayout = itemView.findViewById(R.id.linear_da);
            compdigitalart_ID = itemView.findViewById(R.id.compdigitalart_ID);
        }
    }
}
