package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firstapp.arthub.models.All_user_competetionlists;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class my_competetions_profile extends AppCompatActivity {
    RecyclerView recyclerView;

    FirebaseRecyclerOptions<All_user_competetionlists> options;
    FirebaseRecyclerAdapter<All_user_competetionlists, MyCompViewHolder> adapter;
    DatabaseReference databaseReference;

    TextView NOComptext;
    ProgressBar Progressbar_MCP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_competetions_profile);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        recyclerView = findViewById(R.id.recycleV_my_Compe);
        NOComptext = findViewById(R.id.NOComptext);
        Progressbar_MCP = findViewById(R.id.Progressbar_MCP);
        Sprite doublebounce = new FadingCircle();
        Progressbar_MCP.setIndeterminateDrawable(doublebounce);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("All_participants_lists").child(uid);
        LoadDate();



    }

    private void LoadDate() {
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(my_competetions_profile.this);
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(my_competetions_profile.this));
        options = new FirebaseRecyclerOptions.Builder<All_user_competetionlists>()
                .setQuery(databaseReference, All_user_competetionlists.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<All_user_competetionlists, MyCompViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyCompViewHolder holder, int position, @NonNull All_user_competetionlists model) {

                NOComptext.setVisibility(View.GONE);
                Progressbar_MCP.setVisibility(View.GONE);
                holder.LastDate.setText(model.getLastDate());
                holder.Topic.setText(model.getTopic());
                holder.Entryfee.setText(model.getFees());
                Glide.with(holder.imageView.getContext()).load(model.getPoster_url()).into(holder.imageView);

            }

            @NonNull
            @Override
            public MyCompViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_competetions_list_profile, parent, false);
                return new MyCompViewHolder(view);
            }

            public void onDataChanged() {
                if (Progressbar_MCP != null){
                    Progressbar_MCP.setVisibility(View.GONE);
                    NOComptext.setVisibility(View.VISIBLE);
                }
            }

        };
        recyclerView.setLayoutManager(mLinearLayout);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}