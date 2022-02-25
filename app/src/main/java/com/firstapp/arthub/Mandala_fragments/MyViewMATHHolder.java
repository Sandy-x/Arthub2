package com.firstapp.arthub.Mandala_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewMATHHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView Regdate,Topic,Fee;
    LinearLayout linear_dtf;
    public MyViewMATHHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_mandalaartMAtf);
        Regdate = itemView.findViewById(R.id.painting_REGdateMAtf);
        Topic = itemView.findViewById(R.id.topic_paintingMAtf);
        Fee = itemView.findViewById(R.id.entryfeepaintingMAtf);
        linear_dtf = itemView.findViewById(R.id.linear_MAtf);
    }
}
