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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firstapp.arthub.DetailActivity_PencilColor;
import com.firstapp.arthub.R;
import com.firstapp.arthub.freecompPayment;
import com.firstapp.arthub.models.colorpencillistModel;
import com.firstapp.arthub.models.freecompetetionModel;

import java.util.List;

public class FreeCompAdapter extends RecyclerView.Adapter<FreeCompAdapter.ViewHolder> {
    private Context context;
    private List<freecompetetionModel> freecompetetionModelList;

    public FreeCompAdapter(Context context,List<freecompetetionModel> freecompetetionModelList) {
        this.context = context;
        this.freecompetetionModelList =  freecompetetionModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.freecompetetionlists,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final freecompetetionModel  temp = freecompetetionModelList.get(position);
        Glide.with(context).load(freecompetetionModelList.get(position).getBackgroundimg()).into(holder.imageView);
        holder.date.setText(freecompetetionModelList.get(position).getLastdate());
        holder.topic.setText(freecompetetionModelList.get(position).getTopic());
        holder.refernceimg.setText(freecompetetionModelList.get(position).getReferenceimg());
        holder.prize.setText(freecompetetionModelList.get(position).getPrize());
        holder.description.setText(freecompetetionModelList.get(position).getDescription());
        holder.category.setText(freecompetetionModelList.get(position).getCategory());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, freecompPayment.class);
                intent.putExtra("topicfc",temp.getTopic());
                intent.putExtra("prizefc",temp.getPrize());
                intent.putExtra("referenceimgfc",temp.getReferenceimg());
                intent.putExtra("last_datefc",temp.getLastdate());
                intent.putExtra("descriptionfc",temp.getDescription());
                intent.putExtra("categoryfc",temp.getCategory());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return freecompetetionModelList.size() ;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView date,description, topic,refernceimg,prize,category;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewfreecomp_fc);
            date = itemView.findViewById(R.id.lastDate_fc);
            topic = itemView.findViewById(R.id.topic_fc);
            constraintLayout = itemView.findViewById(R.id.constraintl_fc);
            refernceimg = itemView.findViewById(R.id.referenceimage_fc);
            description = itemView.findViewById(R.id.description_fc);
            prize = itemView.findViewById(R.id.prize_fc);
            category = itemView.findViewById(R.id.category_fc);
        }
    }
}
