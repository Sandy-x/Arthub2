package com.firstapp.arthub.painting_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firstapp.arthub.R;
import com.firstapp.arthub.ResultsFragment;
import com.firstapp.arthub.ResultsPainting;
import com.firstapp.arthub.drawing_fragments.MyViewDTHHolder;
import com.firstapp.arthub.models.DrawingSecondModel;
import com.firstapp.arthub.models.PaintingSecondModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link painting_third#newInstance} factory method to
 * create an instance of this fragment.
 */
public class painting_third extends Fragment {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<PaintingSecondModel> options;
    FirebaseRecyclerAdapter<PaintingSecondModel, MyViewPTHHolder> adapter;
    DatabaseReference databaseReference;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public painting_third() {

    }


    public static painting_third newInstance(String param1, String param2) {
        painting_third fragment = new painting_third();
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
        View view = inflater.inflate(R.layout.fragment_painting_third, container, false);
        recyclerView = view.findViewById(R.id.recycleV_paintingfragthird);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Results").child("Painting").child(uid);
        LoadDate();

        return view;
    }

    private void LoadDate() {
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getActivity());
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        options =  new FirebaseRecyclerOptions.Builder<PaintingSecondModel>()
                .setQuery(databaseReference,PaintingSecondModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<PaintingSecondModel, MyViewPTHHolder>(options) {
            @NonNull
            @Override
            public MyViewPTHHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paintinglist_thirdfragment,parent,false);
                return new MyViewPTHHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewPTHHolder holder, int position, @NonNull PaintingSecondModel model) {

                holder.Regdate.setText(model.getRegisteredDate());
                holder.Fee.setText(model.getFees());
                holder.Topic.setText(model.getTopic());
                Picasso.get().load(model.getPoster_url()).into(holder.imageView);
                holder.linear_dtf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        Bundle argsp = new Bundle();
                        argsp.putString("KEY",getRef(position).getKey());
                        ResultsPainting fragment = new ResultsPainting();
                        fragment.setArguments(argsp);
                        fragment.show(fragmentManager, fragment.getTag());
                    }

                });

            }
        };
        recyclerView.setLayoutManager(mLinearLayout);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}