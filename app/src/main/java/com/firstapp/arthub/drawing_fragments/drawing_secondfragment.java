package com.firstapp.arthub.drawing_fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firstapp.arthub.DeatailCompetetion;
import com.firstapp.arthub.DetailCancelOrder;
import com.firstapp.arthub.R;
import com.firstapp.arthub.UploadImage;
import com.firstapp.arthub.models.All_Users;
import com.firstapp.arthub.models.DrawingSecondModel;
import com.firstapp.arthub.models.drawinglistModel;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link drawing_secondfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class drawing_secondfragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<DrawingSecondModel> options;
    FirebaseRecyclerAdapter<DrawingSecondModel,MyViewHolder> adapter;
    DatabaseReference databaseReference;
    FirebaseFirestore database;

    TextView TextpopupDR;

    ProgressBar progressbarSecondDR;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public drawing_secondfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment drawing_secondfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static drawing_secondfragment newInstance(String param1, String param2) {
        drawing_secondfragment fragment = new drawing_secondfragment();
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
        View root = inflater.inflate(R.layout.fragment_drawing_secondfragment, container, false);
        recyclerView = root.findViewById(R.id.recycleV_drawingfragsecond);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Particular_parti_lists").child("Drawing").child(uid);
        LoadDate();
        database = FirebaseFirestore.getInstance();
        progressbarSecondDR = root.findViewById(R.id.progressbarSecondDR);
        TextpopupDR = root.findViewById(R.id.TextpopupDR);
        Sprite doublebounce = new FadingCircle();
        progressbarSecondDR.setIndeterminateDrawable(doublebounce);

        return root;
    }


    private void LoadDate() {

        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getActivity());
        mLinearLayout.setReverseLayout(true);
        mLinearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        options =  new FirebaseRecyclerOptions.Builder<DrawingSecondModel>()
                .setQuery(databaseReference,DrawingSecondModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<DrawingSecondModel, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull DrawingSecondModel model) {
                progressbarSecondDR.setVisibility(View.GONE);
                TextpopupDR.setVisibility(View.GONE);
                holder.CompID.setText(model.getComp_id());
                holder.RegisteredID.setText(model.getRegisteredID());
                holder.LastDate.setText(model.getLastDate());
                holder.Fee.setText(model.getFees());
                holder.Topic.setText(model.getTopic());
                Picasso.get().load(model.getPoster_url()).into(holder.imageView);
                holder.linear_dsf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),UploadImage.class);
                        intent.putExtra("OrdId",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawinglist_secondfragment,parent,false);
                return new MyViewHolder(view);

            }

            @Override
            public void onDataChanged() {
                if (progressbarSecondDR != null){
                    progressbarSecondDR.setVisibility(View.GONE);
                    TextpopupDR.setVisibility(View.VISIBLE);
                }
            }



        };
        recyclerView.setLayoutManager(mLinearLayout);
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


}