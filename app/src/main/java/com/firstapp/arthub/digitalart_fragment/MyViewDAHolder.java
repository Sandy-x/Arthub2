package com.firstapp.arthub.digitalart_fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewDAHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    LinearLayout linear_dasf;
    TextView lastdate,fees,topic;
    public MyViewDAHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_digitalartdsf);
        lastdate = itemView.findViewById(R.id.Lastdatedigitalartdsf);
        fees = itemView.findViewById(R.id.entryfeedigitalartdsf);
        topic = itemView.findViewById(R.id.topicdigitalartdsf);
        linear_dasf = itemView.findViewById(R.id.linear_dasf);
    }
}
