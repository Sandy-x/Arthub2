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
import com.firstapp.arthub.R;
import com.firstapp.arthub.models.drawinglistModel;

import java.util.List;

public class DrawinglistAdapter extends RecyclerView.Adapter<DrawinglistAdapter.ViewHolder> {

    private Context context;
    private List<drawinglistModel> drawinglistModels;

    public DrawinglistAdapter(Context context,List<drawinglistModel> drawinglistModels) {
        this.context = context;
        this.drawinglistModels =  drawinglistModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drawing_fragment_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final drawinglistModel temp = drawinglistModels.get(position);
        Glide.with(context).load(drawinglistModels.get(position).getImage_url()).into(holder.imageView);
        holder.date.setText(drawinglistModels.get(position).getLast_date());
        holder.fee.setText(drawinglistModels.get(position).getEntry_fee());
        holder.compdrawing_ID.setText(drawinglistModels.get(position).getCompid());
        holder.topic.setText(drawinglistModels.get(position).getTopic());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeatailCompetetion.class);
                intent.putExtra("topic",temp.getTopic());
                intent.putExtra("fee",temp.getEntry_fee());
                intent.putExtra("image",temp.getImage_url());
                intent.putExtra("last_date",temp.getLast_date());
                intent.putExtra("comp_id",temp.getCompid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });




    }



    @Override
    public int getItemCount() {
        return drawinglistModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView date, fee, topic;
        LinearLayout linearLayout;
        TextView compdrawing_ID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_drawing);
            fee = itemView.findViewById(R.id.entryfeedrawing);
            date = itemView.findViewById(R.id.drawing_Lastdate);
            topic = itemView.findViewById(R.id.topic_drawing);
            linearLayout = itemView.findViewById(R.id.linear_d);
            compdrawing_ID = itemView.findViewById(R.id.compdrawing_ID);

        }


    }
}
