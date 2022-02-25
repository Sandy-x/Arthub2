package com.firstapp.arthub;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrdersViewHolder extends RecyclerView.ViewHolder {
    TextView OrderDate,Orderid,category,totalamount;
    ImageView imageView;
    Button cancel_orderbtn;
    public MyOrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        OrderDate = (TextView)itemView.findViewById(R.id.OrderedDateOL);
        Orderid = (TextView)itemView.findViewById(R.id.orderidOL);
        category = (TextView)itemView.findViewById(R.id.caTegoryOL);
        totalamount = (TextView)itemView.findViewById(R.id.TotalChargesOL);
        imageView = (ImageView)itemView.findViewById(R.id.imageeViewOL);
        cancel_orderbtn = (Button)itemView.findViewById(R.id.cancel_orderbtnOL);
    }
}
