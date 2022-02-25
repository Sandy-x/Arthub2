package com.firstapp.arthub.photography_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewPHTHHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView Regdate,Topic,Fee;
    LinearLayout linear_dtf;
    public MyViewPHTHHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_mandalaartPHtf);
        Regdate = itemView.findViewById(R.id.painting_REGdatePHtf);
        Topic = itemView.findViewById(R.id.topic_paintingPHtf);
        Fee = itemView.findViewById(R.id.entryfeepaintingPHtf);
        linear_dtf = itemView.findViewById(R.id.linear_PHtf);
    }
}
