package com.firstapp.arthub;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewPTHolder extends RecyclerView.ViewHolder {
    TextView Amount,date,Status;
    public MyViewPTHolder(@NonNull View itemView) {
        super(itemView);
        Amount = (TextView)itemView.findViewById(R.id.Amount_PT);
        date = (TextView)itemView.findViewById(R.id.DATE_PT);
        Status = (TextView)itemView.findViewById(R.id.status_PT);
    }
}
