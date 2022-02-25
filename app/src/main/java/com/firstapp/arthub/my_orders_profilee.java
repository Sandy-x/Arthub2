package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firstapp.arthub.models.colorpencillistModel;
import com.firstapp.arthub.models.my_ordersModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class my_orders_profilee extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<my_ordersModel> options;
    FirebaseRecyclerAdapter<my_ordersModel, MyOrdersViewHolder> adapter;
    DatabaseReference databaseReference;

    ImageView imageViewPROGRESS;
    TextView NOorderstext;
    ProgressBar Progressbar_MOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_profilee);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));

        Progressbar_MOP = findViewById(R.id.Progressbar_MOP);
        NOorderstext = findViewById(R.id.NOorderstext);

        imageViewPROGRESS = findViewById(R.id.imageViewPROGRESS);
        Sprite doublebounce = new FadingCircle();
        Progressbar_MOP.setIndeterminateDrawable(doublebounce);


        recyclerView = findViewById(R.id.recycleV_my_orders);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid);
        LoadDate();

    }

    private void LoadDate() {
        recyclerView.setLayoutManager(new LinearLayoutManager(my_orders_profilee.this));
        options =  new FirebaseRecyclerOptions.Builder<my_ordersModel>()
                .setQuery(databaseReference,my_ordersModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<my_ordersModel, MyOrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyOrdersViewHolder holder, int position, @NonNull my_ordersModel model) {

                NOorderstext.setVisibility(View.GONE);
                Progressbar_MOP.setVisibility(View.GONE);
                imageViewPROGRESS.setVisibility(View.GONE);
                holder.OrderDate.setText(model.getOrderDate());
                holder.Orderid.setText(model.getOrderid());
                holder.totalamount.setText(model.getTotalcharges());
                holder.category.setText(model.getCategory());
                Glide.with(holder.imageView.getContext()).load(model.getReferenceimage()).into(holder.imageView);
                holder.cancel_orderbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(my_orders_profilee.this,DetailCancelOrder.class);
                        intent.putExtra("Orderidkey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_list,parent,false);
                return new MyOrdersViewHolder(view);
            }

            @Override
            public void onDataChanged() {
                if (Progressbar_MOP != null){
                    Progressbar_MOP.setVisibility(View.GONE);
                    NOorderstext.setVisibility(View.VISIBLE);
                    imageViewPROGRESS.setVisibility(View.VISIBLE);
                }
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


}