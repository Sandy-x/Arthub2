package com.firstapp.arthub.colorpencil_fragments;

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
import com.firstapp.arthub.ResultsColorPencil;
import com.firstapp.arthub.ResultsFragment;
import com.firstapp.arthub.drawing_fragments.MyViewDTHHolder;
import com.firstapp.arthub.models.ColorpencilSecondModel;
import com.firstapp.arthub.models.DrawingSecondModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class colorpencil_third extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<ColorpencilSecondModel> options;
    FirebaseRecyclerAdapter<ColorpencilSecondModel, MyViewCPTHHolder> adapter;
    DatabaseReference databaseReference;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public colorpencil_third() {

    }

   public static colorpencil_third newInstance(String param1, String param2) {
        colorpencil_third fragment = new colorpencil_third();
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
        View view = inflater.inflate(R.layout.fragment_colorpencil_third, container, false);
        recyclerView = view.findViewById(R.id.recycleV_colorpencilfragthird);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Results").child("colorPencil").child(uid);
        LoadDate();
        return view;
    }

    private void LoadDate() {

        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getActivity());
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        options =  new FirebaseRecyclerOptions.Builder<ColorpencilSecondModel>()
                .setQuery(databaseReference,ColorpencilSecondModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<ColorpencilSecondModel, MyViewCPTHHolder>(options) {
            @NonNull
            @Override
            public MyViewCPTHHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.colorpencil_thirdfrag,parent,false);
                return new MyViewCPTHHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewCPTHHolder holder, int position, @NonNull ColorpencilSecondModel model) {
                holder.Regdate.setText(model.getRegisteredDate());
                holder.Fee.setText(model.getFees());
                holder.Topic.setText(model.getTopic());
                Picasso.get().load(model.getPoster_url()).into(holder.imageView);
                holder.linear_dtf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        Bundle args = new Bundle();
                        args.putString("KEY",getRef(position).getKey());
                        ResultsColorPencil fragment = new ResultsColorPencil();
                        fragment.setArguments(args);
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