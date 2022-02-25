package com.firstapp.arthub.photography_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firstapp.arthub.R;
import com.firstapp.arthub.adapters.DrawinglistAdapter;
import com.firstapp.arthub.adapters.PhotographyAdapter;
import com.firstapp.arthub.models.drawinglistModel;
import com.firstapp.arthub.models.photographylistModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link photography_first#newInstance} factory method to
 * create an instance of this fragment.
 */
public class photography_first extends Fragment {
    RecyclerView photolist;
    FirebaseFirestore dbp;
    List<photographylistModel> photographylistModelList;
    PhotographyAdapter photographyAdapter;

    ShimmerFrameLayout shimmer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public photography_first() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment photography_first.
     */
    // TODO: Rename and change types and number of parameters
    public static photography_first newInstance(String param1, String param2) {
        photography_first fragment = new photography_first();
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

        View root = inflater.inflate(R.layout.fragment_photography_first, container, false);
        dbp = FirebaseFirestore.getInstance();
        shimmer = root.findViewById(R.id.frameshimmer);
        photolist = root.findViewById(R.id.recycleV_photofrag);
        photolist.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        photographylistModelList = new ArrayList<>();
        photographyAdapter = new PhotographyAdapter(getActivity(),photographylistModelList);
        photolist.setAdapter(photographyAdapter);
        dbp.collection("Photography_lists").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            shimmer.setVisibility(View.GONE);
                            photolist.setVisibility(View.VISIBLE);

                            for (QueryDocumentSnapshot document : task.getResult()){
                                photographylistModel photographylistModel = document.toObject(photographylistModel.class);
                                photographylistModelList.add(photographylistModel);
                                photographyAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        return root;
    }
}