package com.firstapp.arthub.colorpencil_fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.arthub.R;

public class MyViewCPHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView lastdate,fees,topic;
    LinearLayout linear_casf;
    public MyViewCPHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_colorpencilcpsf);
        lastdate = itemView.findViewById(R.id.Lastdatecolorpencilcpsf);
        fees = itemView.findViewById(R.id.entryfeecolorpencilcpsf);
        topic = itemView.findViewById(R.id.topiccolorpencilcpsf);
        linear_casf = itemView.findViewById(R.id.linear_casf);
    }
}
