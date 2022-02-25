package com.firstapp.arthub.Mandala_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewMAHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView lastdate,fees,topic;
    LinearLayout linear_masf;
    public MyViewMAHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_mandalaartmsf);
        lastdate = itemView.findViewById(R.id.Lastdate_mandalaartmsf);
        fees = itemView.findViewById(R.id.entryfee_mandalaartmsf);
        topic = itemView.findViewById(R.id.topic_mandalaartmsf);
        linear_masf = itemView.findViewById(R.id.linear_masf);
    }
}
