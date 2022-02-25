package com.firstapp.arthub.digitalart_fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewDATHHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView Regdate,Topic,Fee;
    LinearLayout linear_dtf;
    public MyViewDATHHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_DAtf);
        Regdate = itemView.findViewById(R.id.digitalart_REGdatedtf);
        Topic = itemView.findViewById(R.id.topic_DAtf);
        Fee = itemView.findViewById(R.id.entryfeeDAtf);
        linear_dtf = itemView.findViewById(R.id.linear_DAtf);
    }
}
