package com.firstapp.arthub;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCompViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView LastDate,Topic,Entryfee;
    public MyCompViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.list_imaGe);
        LastDate = (TextView)itemView.findViewById(R.id.LastDate);
        Topic = (TextView)itemView.findViewById(R.id.toPic);
        Entryfee = (TextView)itemView.findViewById(R.id.entryFee);
    }
}
