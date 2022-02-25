package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firstapp.arthub.models.All_Users;
import com.firstapp.arthub.models.WithdrawNewModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PastTransaction_profile extends AppCompatActivity {

    RecyclerView recyclerView;


    FirebaseRecyclerOptions<WithdrawNewModel> options;
    FirebaseRecyclerAdapter<WithdrawNewModel, MyViewPTHolder> adapter;
    DatabaseReference databaseReference;
    FirebaseFirestore database;

    TextView popupPTP;
    ProgressBar progressbar_PT;



    TextView walletbalance_PT;
    LinearLayout INRWithdraw_PT,linearLayoutRedeemT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_transaction_profile);

        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        progressbar_PT = findViewById(R.id.progressbar_PT);
        popupPTP = findViewById(R.id.popupPTP);
        Sprite doublebounce = new FadingCircle();
        progressbar_PT.setIndeterminateDrawable(doublebounce);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("WithdrawMoney_Request").child(uid);

        recyclerView = findViewById(R.id.recycle_PT);
        recyclerView.setLayoutManager(new LinearLayoutManager(PastTransaction_profile.this,RecyclerView.VERTICAL,false));

        linearLayoutRedeemT = findViewById(R.id.linearLayoutRedeemT);
        linearLayoutRedeemT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedeemTrophy bottomsheetFragment = new RedeemTrophy();
                bottomsheetFragment.show(getSupportFragmentManager(),bottomsheetFragment.getTag());
            }
        });

        walletbalance_PT = findViewById(R.id.walletbalance_PT);
        database = FirebaseFirestore.getInstance();
        database.collection("All_Users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {

                All_Users user = documentSnapshot.toObject(All_Users.class);
                walletbalance_PT.setText(String.valueOf(user.getWalletsmoney()));

            }
        });

        INRWithdraw_PT = findViewById(R.id.INRWithdraw_PT);

        INRWithdraw_PT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PastTransaction_profile.this,WithdrawActivityNew.class);
                startActivity(intent);
                intent.putExtra("wall",walletbalance_PT.getText().toString());
            }
        });

        LoadData();

    }

    private void LoadData() {
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(this);
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(PastTransaction_profile.this));
        options =  new FirebaseRecyclerOptions.Builder<WithdrawNewModel>()
                .setQuery(databaseReference,WithdrawNewModel.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<WithdrawNewModel, MyViewPTHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull MyViewPTHolder holder, int position, @NonNull WithdrawNewModel model) {
                progressbar_PT.setVisibility(View.GONE);
                popupPTP.setVisibility(View.GONE);
                holder.Amount.setText(model.getRequestamount());
                holder.date.setText(model.getIssueddate());
                holder.Status.setText(model.getStatus());



            }

            @NonNull
            @Override
            public MyViewPTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pasttransactionlists,parent,false);
                return new MyViewPTHolder(view);

            }

            @Override
            public void onDataChanged() {
                if (progressbar_PT != null){
                    progressbar_PT.setVisibility(View.GONE);
                    popupPTP.setVisibility(View.VISIBLE);
                }
            }



        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLinearLayout);
        adapter.startListening();
    }

}