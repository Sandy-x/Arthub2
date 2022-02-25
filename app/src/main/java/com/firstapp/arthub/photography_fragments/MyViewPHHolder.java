package com.firstapp.arthub.photography_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewPHHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView lastdate,fees,topic;
    LinearLayout linear_phsf;
    public MyViewPHHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_Photographypsf);
        lastdate = itemView.findViewById(R.id.LastdatePhotographypsf);
        fees = itemView.findViewById(R.id.entryfeePhotographypsf);
        topic = itemView.findViewById(R.id.topic_Photographypsf);
        linear_phsf = itemView.findViewById(R.id.linear_phsf);
    }
}
