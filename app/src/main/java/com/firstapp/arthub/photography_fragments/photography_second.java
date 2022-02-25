package com.firstapp.arthub.photography_fragments;

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
import com.firstapp.arthub.UploadimagePH;
import com.firstapp.arthub.UploadimagePainting;
import com.firstapp.arthub.models.PhotographySecondModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link photography_second#newInstance} factory method to
 * create an instance of this fragment.
 */
public class photography_second extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<PhotographySecondModel> options;
    FirebaseRecyclerAdapter<PhotographySecondModel, MyViewPHHolder> adapter;
    DatabaseReference databaseReference;
    FirebaseFirestore database;

    TextView TextpopupPH;
    ProgressBar progressbarSecondPH;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public photography_second() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment photography_second.
     */
    // TODO: Rename and change types and number of parameters
    public static photography_second newInstance(String param1, String param2) {
        photography_second fragment = new photography_second();
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
        View root = inflater.inflate(R.layout.fragment_photography_second, container, false);
        recyclerView = root.findViewById(R.id.recycleV_photographyfragsecond);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Particular_parti_lists").child("Postermaking").child(uid);
        LoadDate();
        database = FirebaseFirestore.getInstance();
        progressbarSecondPH = root.findViewById(R.id.progressbarSecondPH);
        TextpopupPH = root.findViewById(R.id.TextpopupPH);
        Sprite doublebounce = new FadingCircle();
        progressbarSecondPH.setIndeterminateDrawable(doublebounce);
        return root;


    }

    private void LoadDate() {

        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getActivity());
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new LinearLayoutManager(getActivity()).setReverseLayout(true);

        new LinearLayoutManager(getActivity()).setStackFromEnd(true);
        options =  new FirebaseRecyclerOptions.Builder<PhotographySecondModel>()
                .setQuery(databaseReference,PhotographySecondModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<PhotographySecondModel, MyViewPHHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull MyViewPHHolder holder, int position, @NonNull PhotographySecondModel model) {
                progressbarSecondPH.setVisibility(View.GONE);
                TextpopupPH.setVisibility(View.GONE);
                holder.fees.setText(model.getFees());
                holder.lastdate.setText(model.getLastDate());
                holder.topic.setText(model.getTopic());
                Glide.with(holder.imageView.getContext()).load(model.getPoster_url()).into(holder.imageView);
                holder.linear_phsf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), UploadimagePH.class);
                        intent.putExtra("PH_OrdId",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewPHHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_secondfrag,parent,false);
                return new MyViewPHHolder(view);

            }

            @Override
            public void onDataChanged() {
                if (progressbarSecondPH != null){
                    progressbarSecondPH.setVisibility(View.GONE);
                    TextpopupPH.setVisibility(View.VISIBLE);
                }
            }



        };
        recyclerView.setLayoutManager(mLinearLayout);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}