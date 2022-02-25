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
import com.firstapp.arthub.DetailActivity_Photography;
import com.firstapp.arthub.R;
import com.firstapp.arthub.models.drawinglistModel;
import com.firstapp.arthub.models.paintinglistModel;
import com.firstapp.arthub.models.photographylistModel;

import java.util.List;

public class PhotographyAdapter  extends RecyclerView.Adapter<PhotographyAdapter.ViewHolder> {
    private Context context;
    private List<photographylistModel> photographylistModels;
    public PhotographyAdapter(Context context,List<photographylistModel> photographylistModels) {
        this.context = context;
        this.photographylistModels =  photographylistModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_fragment_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final photographylistModel temp = photographylistModels.get(position);
        Glide.with(context).load(photographylistModels.get(position).getImage_url()).into(holder.imageView);
        holder.date.setText(photographylistModels.get(position).getLast_date());
        holder.fee.setText(photographylistModels.get(position).getEntry_fee());
        holder.compphotography_ID.setText(photographylistModels.get(position).getCompid());
        holder.topic.setText(photographylistModels.get(position).getTopic());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity_Photography.class);
                intent.putExtra("topicphoto",temp.getTopic());
                intent.putExtra("feephoto",temp.getEntry_fee());
                intent.putExtra("imagephoto",temp.getImage_url());
                intent.putExtra("last_datephoto",temp.getLast_date());
                intent.putExtra("compID",temp.getCompid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return photographylistModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView date, fee, topic,compphotography_ID;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_photography);
            fee = itemView.findViewById(R.id.entryfeephotography);
            date = itemView.findViewById(R.id.photography_Lastdate);
            topic = itemView.findViewById(R.id.topic_photography);
            linearLayout = itemView.findViewById(R.id.linear_p);
            compphotography_ID = itemView.findViewById(R.id.compphotography_ID);
        }
    }
}
