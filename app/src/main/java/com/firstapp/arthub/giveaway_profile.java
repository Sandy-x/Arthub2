package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.adapters.GiveawayAdapter;
import com.firstapp.arthub.adapters.PaintinglistAdapter;
import com.firstapp.arthub.models.GiveawayModel;
import com.firstapp.arthub.models.paintinglistModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class giveaway_profile extends AppCompatActivity {

    RecyclerView giveawaylist;
    FirebaseFirestore db;
    List<GiveawayModel> giveawayModelList;
    GiveawayAdapter giveawayAdapter;
    TextView NOorderstext2;
    ProgressBar Progressbar_MOP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveaway_profile);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));

        NOorderstext2 = findViewById(R.id.NOorderstext2);
        Progressbar_MOP2 = findViewById(R.id.Progressbar_MOP2);
        Sprite doublebounce = new FadingCircle();
        Progressbar_MOP2.setIndeterminateDrawable(doublebounce);

        db = FirebaseFirestore.getInstance();
        giveawaylist = findViewById(R.id.recycleV_giveaway);
        giveawaylist.setLayoutManager(new LinearLayoutManager(giveaway_profile.this,RecyclerView.VERTICAL,false));
        giveawayModelList = new ArrayList<>();
        giveawayAdapter = new GiveawayAdapter(giveaway_profile.this,giveawayModelList);
        giveawaylist.setAdapter(giveawayAdapter);

        db.collection("Giveaway_lists").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            giveawaylist.setVisibility(View.VISIBLE);
                            Progressbar_MOP2.setVisibility(View.GONE);

                            for (QueryDocumentSnapshot document : task.getResult()){
                                GiveawayModel GiveawayModel = document.toObject(GiveawayModel.class);
                                giveawayModelList.add(GiveawayModel);
                                giveawayAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Progressbar_MOP2.setVisibility(View.GONE);
                            Toast.makeText(giveaway_profile.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }

                });

    }
}