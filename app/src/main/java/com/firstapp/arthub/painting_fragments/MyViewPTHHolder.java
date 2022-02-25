package com.firstapp.arthub.painting_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewPTHHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView Regdate,Topic,Fee;
    LinearLayout linear_dtf;
    public MyViewPTHHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_paintingPtf);
        Regdate = itemView.findViewById(R.id.painting_REGdateptf);
        Topic = itemView.findViewById(R.id.topic_paintingptf);
        Fee = itemView.findViewById(R.id.entryfeepaintingptf);
        linear_dtf = itemView.findViewById(R.id.linear_Ptf);
    }
}
