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
import android.widget.Toast;

import com.firstapp.arthub.adapters.HelpandSupportAdapter;
import com.firstapp.arthub.adapters.PaintinglistAdapter;
import com.firstapp.arthub.models.HelpandSupportModel;
import com.firstapp.arthub.models.paintinglistModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class helpandsupport_profile extends AppCompatActivity {
    RecyclerView paintinglist;
    FirebaseFirestore db;
    List<HelpandSupportModel> helpandSupportModelList;
    HelpandSupportAdapter helpandSupportAdapter;
    ProgressBar Progressbar_HAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        setContentView(R.layout.activity_helpandsupport_profile);
        db = FirebaseFirestore.getInstance();
        paintinglist = findViewById(R.id.recycle_HS);
        Progressbar_HAS = findViewById(R.id.Progressbar_HAS);
        paintinglist.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        helpandSupportModelList = new ArrayList<>();
        helpandSupportAdapter = new HelpandSupportAdapter(helpandsupport_profile.this,helpandSupportModelList);
        paintinglist.setAdapter(helpandSupportAdapter);
        db.collection("Help and Support").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            paintinglist.setVisibility(View.VISIBLE);
                            Progressbar_HAS.setVisibility(View.GONE);

                            for (QueryDocumentSnapshot document : task.getResult()){
                                HelpandSupportModel HelpandSupportModel = document.toObject(HelpandSupportModel.class);
                                helpandSupportModelList.add(HelpandSupportModel);
                                helpandSupportAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(helpandsupport_profile.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}