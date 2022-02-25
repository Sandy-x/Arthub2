package com.firstapp.arthub.drawing_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;


public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView LastDate,Topic,Fee,RegisteredID,CompID;
    LinearLayout linear_dsf;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_drawingdsf);
        LastDate = itemView.findViewById(R.id.drawing_Lastdatedsf);
        Topic = itemView.findViewById(R.id.topic_drawingdsf);
        Fee = itemView.findViewById(R.id.entryfeedrawingdsf);
        CompID = itemView.findViewById(R.id.CompID_DSF);
        RegisteredID = itemView.findViewById(R.id.RegisteredID_DSF);
        linear_dsf = itemView.findViewById(R.id.linear_dsf);

    }
}
