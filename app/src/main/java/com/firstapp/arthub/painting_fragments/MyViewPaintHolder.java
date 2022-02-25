package com.firstapp.arthub.painting_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewPaintHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView lastdate,fees,topic;
    LinearLayout linear_psf;
    public MyViewPaintHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_paintingpsf);
        lastdate = itemView.findViewById(R.id.Lastdate_paintingpsf);
        fees = itemView.findViewById(R.id.entryfee_paintingpsf);
        topic = itemView.findViewById(R.id.topic_paintingpsf);
        linear_psf = itemView.findViewById(R.id.linear_psf);
    }
}
