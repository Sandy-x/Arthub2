package com.firstapp.arthub.photography_fragments;

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
import com.firstapp.arthub.ResultsPhotography;
import com.firstapp.arthub.drawing_fragments.MyViewDTHHolder;
import com.firstapp.arthub.models.DrawingSecondModel;
import com.firstapp.arthub.models.PhotographySecondModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link photography_third#newInstance} factory method to
 * create an instance of this fragment.
 */
public class photography_third extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<PhotographySecondModel> options;
    FirebaseRecyclerAdapter<PhotographySecondModel, MyViewPHTHHolder> adapter;
    DatabaseReference databaseReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public photography_third() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment photography_third.
     */
    // TODO: Rename and change types and number of parameters
    public static photography_third newInstance(String param1, String param2) {
        photography_third fragment = new photography_third();
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
        View view = inflater.inflate(R.layout.fragment_photography_third, container, false);
        recyclerView = view.findViewById(R.id.recycleV_photographyfragthird);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Results").child("Postermaking").child(uid);
        LoadDate();
        return view;
    }

    private void LoadDate() {
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getActivity());
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        options =  new FirebaseRecyclerOptions.Builder<PhotographySecondModel>()
                .setQuery(databaseReference,PhotographySecondModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<PhotographySecondModel, MyViewPHTHHolder>(options) {
            @NonNull
            @Override
            public MyViewPHTHHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_thirdfragment,parent,false);
                return new MyViewPHTHHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewPHTHHolder holder, int position, @NonNull PhotographySecondModel model) {

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
                        ResultsPhotography fragment = new ResultsPhotography();
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