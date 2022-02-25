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
import com.firstapp.arthub.DetailActivity_painting;
import com.firstapp.arthub.R;
import com.firstapp.arthub.models.drawinglistModel;
import com.firstapp.arthub.models.mandalaartlistModel;
import com.firstapp.arthub.models.paintinglistModel;

import java.util.List;

public class PaintinglistAdapter  extends RecyclerView.Adapter<PaintinglistAdapter.ViewHolder> {
    private Context context;
    private List<paintinglistModel> paintinglistModels ;

   public PaintinglistAdapter(Context context,List<paintinglistModel> paintinglistModels){
       this.context = context;
       this.paintinglistModels = paintinglistModels;

   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.painting_fragment_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final paintinglistModel temp = paintinglistModels.get(position);
        Glide.with(context).load(paintinglistModels.get(position).getImage_url()).into(holder.imageView);
        holder.date.setText(paintinglistModels.get(position).getLast_date());
        holder.fee.setText(paintinglistModels.get(position).getEntry_fee());
        holder.topic.setText(paintinglistModels.get(position).getTopic());
        holder.Compid_SPF.setText(paintinglistModels.get(position).getCompid());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity_painting.class);
                intent.putExtra("topicp",temp.getTopic());
                intent.putExtra("feep",temp.getEntry_fee());
                intent.putExtra("imagep",temp.getImage_url());
                intent.putExtra("last_datep",temp.getLast_date());
                intent.putExtra("CompID",temp.getCompid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return paintinglistModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView date, fee, topic,Compid_SPF;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_painting);
            fee = itemView.findViewById(R.id.entryfeepainting);
            date = itemView.findViewById(R.id.painting_Lastdate);
            topic = itemView.findViewById(R.id.topic_painting);
            linearLayout = itemView.findViewById(R.id.linear_p);
            Compid_SPF = itemView.findViewById(R.id.Compid_SPF);
        }
    }
}
