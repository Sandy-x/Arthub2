package com.firstapp.arthub.drawing_fragments;

import android.content.Intent;
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

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firstapp.arthub.R;
import com.firstapp.arthub.adapters.CompetitionListAdapter;
import com.firstapp.arthub.adapters.DrawinglistAdapter;
import com.firstapp.arthub.models.competitionlistModel;
import com.firstapp.arthub.models.drawinglistModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link drawing_firstfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class drawing_firstfragment extends Fragment {

    RecyclerView drawinglist;
    FirebaseFirestore dbp;
    List<drawinglistModel> drawinglistModelList;
    DrawinglistAdapter drawinglistAdapter;

    ShimmerFrameLayout shimmer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public drawing_firstfragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static drawing_firstfragment newInstance(String param1, String param2) {
        drawing_firstfragment fragment = new drawing_firstfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_drawing_firstfragment,container,false);
        dbp = FirebaseFirestore.getInstance();
        shimmer = root.findViewById(R.id.frameshimmer);
        drawinglist = root.findViewById(R.id.recycleV_drawingfrag);
        drawinglist.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        drawinglistModelList = new ArrayList<>();
        drawinglistAdapter = new DrawinglistAdapter(getActivity(),drawinglistModelList);
        drawinglist.setAdapter(drawinglistAdapter);



        dbp.collection("Drawing_competition_lists").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            shimmer.setVisibility(View.GONE);
                            drawinglist.setVisibility(View.VISIBLE);

                            for (QueryDocumentSnapshot document : task.getResult()){
                                drawinglistModel drawinglistModel = document.toObject(drawinglistModel.class);
                                drawinglistModelList.add(drawinglistModel);
                                drawinglistAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        return root;
    }
}