package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firstapp.arthub.adapters.ArtoftheweekAdapter;
import com.firstapp.arthub.adapters.PaintinglistAdapter;
import com.firstapp.arthub.models.artoftheweekModel;
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

public class artoftheweek extends AppCompatActivity {
    RecyclerView paintinglist;
    FirebaseFirestore db;
    List<artoftheweekModel> artoftheweekModelList;
    ArtoftheweekAdapter artoftheweekAdapter;
    ProgressBar Progressbar_AOTW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artoftheweek);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        db = FirebaseFirestore.getInstance();
        paintinglist = findViewById(R.id.recycleviewAOTW);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        paintinglist.setLayoutManager(gridLayoutManager);
        artoftheweekModelList = new ArrayList<>();
        artoftheweekAdapter = new ArtoftheweekAdapter(this,artoftheweekModelList);
        paintinglist.setAdapter(artoftheweekAdapter);
        Progressbar_AOTW = findViewById(R.id.Progressbar_AOTW);
        Sprite doublebounce = new FadingCircle();
        Progressbar_AOTW.setIndeterminateDrawable(doublebounce);

        db.collection("ArtoftheWeek").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Progressbar_AOTW.setVisibility(View.GONE);
                            paintinglist.setVisibility(View.VISIBLE);

                            for (QueryDocumentSnapshot document : task.getResult()){
                                artoftheweekModel artoftheweekModel = document.toObject(artoftheweekModel.class);
                                artoftheweekModelList.add(artoftheweekModel);
                                artoftheweekAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(artoftheweek.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}