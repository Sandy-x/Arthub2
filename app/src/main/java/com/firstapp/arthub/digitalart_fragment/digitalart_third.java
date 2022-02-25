package com.firstapp.arthub.digitalart_fragment;

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
import com.firstapp.arthub.ResultsDigitalArt;
import com.firstapp.arthub.ResultsFragment;
import com.firstapp.arthub.drawing_fragments.MyViewDTHHolder;
import com.firstapp.arthub.models.DigitalArtSecondModel;
import com.firstapp.arthub.models.DrawingSecondModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class digitalart_third extends Fragment {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<DigitalArtSecondModel> options;
    FirebaseRecyclerAdapter<DigitalArtSecondModel, MyViewDATHHolder> adapter;
    DatabaseReference databaseReference;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public digitalart_third() {
        // Required empty public constructor
    }


    public static digitalart_third newInstance(String param1, String param2) {
        digitalart_third fragment = new digitalart_third();
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
        View view = inflater.inflate(R.layout.fragment_digitalart_third, container, false);
        recyclerView = view.findViewById(R.id.recycleV_digitalartfragthird);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Results").child("DigitalArt").child(uid);
        LoadDate();
        return view;
    }

    private void LoadDate() {

        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getActivity());
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        options =  new FirebaseRecyclerOptions.Builder<DigitalArtSecondModel>()
                .setQuery(databaseReference,DigitalArtSecondModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<DigitalArtSecondModel, MyViewDATHHolder>(options) {
            @NonNull
            @Override
            public MyViewDATHHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.digitalart_thirdfragment,parent,false);
                return new MyViewDATHHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewDATHHolder holder, int position, @NonNull DigitalArtSecondModel model) {

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
                        ResultsDigitalArt fragment = new ResultsDigitalArt();
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