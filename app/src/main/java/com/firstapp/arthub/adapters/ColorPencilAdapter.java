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
import com.firstapp.arthub.DetailActivity_PencilColor;
import com.firstapp.arthub.R;
import com.firstapp.arthub.models.colorpencillistModel;
import com.firstapp.arthub.models.drawinglistModel;

import java.util.List;

public class ColorPencilAdapter  extends RecyclerView.Adapter<ColorPencilAdapter.ViewHolder>{

    private Context context;
    private List<colorpencillistModel> colorpencillistModelList;

    public ColorPencilAdapter(Context context,List<colorpencillistModel> colorpencillistModelList) {
        this.context = context;
        this.colorpencillistModelList =  colorpencillistModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.colorpencil_fragment_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final colorpencillistModel  temp = colorpencillistModelList.get(position);
        Glide.with(context).load(colorpencillistModelList.get(position).getImage_url()).into(holder.imageView);
        holder.date.setText(colorpencillistModelList.get(position).getLast_date());
        holder.fee.setText(colorpencillistModelList.get(position).getEntry_fee());
        holder.comppencil_ID.setText(colorpencillistModelList.get(position).getCompid());
        holder.topic.setText(colorpencillistModelList.get(position).getTopic());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity_PencilColor.class);
                intent.putExtra("topiccp",temp.getTopic());
                intent.putExtra("feecp",temp.getEntry_fee());
                intent.putExtra("imagecp",temp.getImage_url());
                intent.putExtra("compID",temp.getCompid());
                intent.putExtra("last_datecp",temp.getLast_date());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return colorpencillistModelList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView date, fee, topic,comppencil_ID;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_cp);
            fee = itemView.findViewById(R.id.entryfeecp);
            date = itemView.findViewById(R.id.cp_Lastdate);
            topic = itemView.findViewById(R.id.topic_cp);
            comppencil_ID = itemView.findViewById(R.id.comppencil_ID);
            linearLayout = itemView.findViewById(R.id.linear_cp);
        }
    }
}
