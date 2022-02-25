package com.firstapp.arthub.drawing_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewDTHHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView Regdate,Topic,Fee,CompID;
    LinearLayout linear_dtf;
    public MyViewDTHHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_drawingdtf);
        Regdate = itemView.findViewById(R.id.drawing_REGdatedtf);
        Topic = itemView.findViewById(R.id.topic_drawingdtf);
        Fee = itemView.findViewById(R.id.entryfeedrawingdtf);
        CompID = itemView.findViewById(R.id.CompID_DTF);
        linear_dtf = itemView.findViewById(R.id.linear_dtf);
    }
}
