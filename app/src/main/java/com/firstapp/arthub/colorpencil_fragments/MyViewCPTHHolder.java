package com.firstapp.arthub.colorpencil_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewCPTHHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView Regdate,Topic,Fee;
    LinearLayout linear_dtf;
    public MyViewCPTHHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_colorpencilcptf);
        Regdate = itemView.findViewById(R.id.colorpencil_REGdatecptf);
        Topic = itemView.findViewById(R.id.topic_colorpencilcptf);
        Fee = itemView.findViewById(R.id.entryfeecolorpencilcptf);
        linear_dtf = itemView.findViewById(R.id.linear_cptf);
    }
}
