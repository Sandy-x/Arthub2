package com.firstapp.arthub;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.adapters.FreeCompAdapter;
import com.firstapp.arthub.adapters.PaintinglistAdapter;
import com.firstapp.arthub.models.freecompetetionModel;
import com.firstapp.arthub.models.paintinglistModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class FreeCompetetions extends Fragment {
    RecyclerView freecomplist;
    FirebaseFirestore db;
    List<freecompetetionModel> freecompetetionModelList;
    FreeCompAdapter freeCompAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_free_competetions, container, false);
        db = FirebaseFirestore.getInstance();
        freecomplist = view.findViewById(R.id.recycleV_freeCompetetion);
        freecomplist.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        freecompetetionModelList = new ArrayList<>();
        freeCompAdapter = new FreeCompAdapter(getActivity(),freecompetetionModelList);
        freecomplist.setAdapter(freeCompAdapter);
        db.collection("free_competetion").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            freecomplist.setVisibility(View.VISIBLE);

                            for (QueryDocumentSnapshot document : task.getResult()){
                                freecompetetionModel freecompetetionModel = document.toObject(freecompetetionModel.class);
                                freecompetetionModelList.add(freecompetetionModel);
                                freeCompAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        return view;
    }
}