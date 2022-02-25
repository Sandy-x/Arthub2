package com.firstapp.arthub.painting_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firstapp.arthub.R;
import com.firstapp.arthub.UploadimagePainting;
import com.firstapp.arthub.models.PaintingSecondModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class painting_second extends Fragment {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<PaintingSecondModel> options;
    FirebaseRecyclerAdapter<PaintingSecondModel, MyViewPaintHolder> adapter;
    DatabaseReference databaseReference;
    FirebaseFirestore database;

    TextView TextpopupPAINT;
    ProgressBar progressbarSecondPAINT;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public painting_second() {

    }


    public static painting_second newInstance(String param1, String param2) {
        painting_second fragment = new painting_second();
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
        View root = inflater.inflate(R.layout.fragment_painting_second, container, false);
        recyclerView = root.findViewById(R.id.recycleV_paintingfragsecond);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Particular_parti_lists").child("Painting").child(uid);
        LoadDate();
        database = FirebaseFirestore.getInstance();
        progressbarSecondPAINT = root.findViewById(R.id.progressbarSecondPAINT);
        TextpopupPAINT = root.findViewById(R.id.TextpopupPAINT);
        Sprite doublebounce = new FadingCircle();
        progressbarSecondPAINT.setIndeterminateDrawable(doublebounce);
        return root;
    }

    private void LoadDate() {

        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getActivity());
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        options =  new FirebaseRecyclerOptions.Builder<PaintingSecondModel>()
                .setQuery(databaseReference,PaintingSecondModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<PaintingSecondModel, MyViewPaintHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull MyViewPaintHolder holder, int position, @NonNull PaintingSecondModel model) {
                progressbarSecondPAINT.setVisibility(View.GONE);
                TextpopupPAINT.setVisibility(View.GONE);
                holder.fees.setText(model.getFees());
                holder.lastdate.setText(model.getLastDate());
                holder.topic.setText(model.getTopic());
                Glide.with(holder.imageView.getContext()).load(model.getPoster_url()).into(holder.imageView);

                holder.linear_psf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), UploadimagePainting.class);
                        intent.putExtra("P_OrdId",getRef(position).getKey());
                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public MyViewPaintHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paintinglist_secondfrag,parent,false);
                return new MyViewPaintHolder(view);

            }
            @Override
            public void onDataChanged() {
                if (progressbarSecondPAINT != null){
                    progressbarSecondPAINT.setVisibility(View.GONE);
                    TextpopupPAINT.setVisibility(View.VISIBLE);
                }
            }


        };

        recyclerView.setLayoutManager(mLinearLayout);
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

}