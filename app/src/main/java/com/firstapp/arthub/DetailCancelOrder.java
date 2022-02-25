package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.models.All_Users;
import com.firstapp.arthub.models.my_ordersModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailCancelOrder extends AppCompatActivity {

    TextView Orderdate,totalcharges,orderid,category;
    Button cancelbtn;
    ImageView imageView;
    DatabaseReference databaseReference;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cancel_order);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));

        dialog = new Dialog(DetailCancelOrder.this);
        dialog.setContentView(R.layout.dialogbox_cancelorder);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_gradient));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        //dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        TextView No = dialog.findViewById(R.id.No_DBCO);
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView YES = dialog.findViewById(R.id.Yes_DBCO);


        orderid = findViewById(R.id.OrderId_DCO);
        Orderdate = findViewById(R.id.OrderDate_DCO);
        totalcharges = findViewById(R.id.TotalCharges_DCO);
        category = findViewById(R.id.Category_DCO);
        cancelbtn = findViewById(R.id.CancelOrderBTN);
        imageView = findViewById(R.id.imageeViewDCO);

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String orderidkey = getIntent().getStringExtra("Orderidkey");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid);
        YES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OrderId = getIntent().getStringExtra("Orderidkey");
                Intent intent = new Intent(DetailCancelOrder.this,CancelOrderWithdrawRefund.class);
                intent.putExtra("OrderIDD",OrderId);
                intent.putExtra("TotalC",totalcharges.getText().toString());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        databaseReference.child(orderidkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String orderD = snapshot.child("orderDate").getValue(String.class);
                    String ImageUrl = snapshot.child("referenceimage").getValue(String.class);
                    String Orderid = snapshot.child("orderid").getValue(String.class);
                    String cat = snapshot.child("category").getValue(String.class);
                    String totalC = snapshot.child("totalcharges").getValue(String.class);
                    orderid.setText(Orderid);
                    Picasso.get().load(ImageUrl).into(imageView);
                    Orderdate.setText(orderD);
                    category.setText(cat);
                    totalcharges.setText(totalC);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DetailCancelOrder.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });


    }



}