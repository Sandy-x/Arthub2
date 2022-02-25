package com.firstapp.arthub;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firstapp.arthub.adapters.CompetitionListAdapter;
import com.firstapp.arthub.models.competitionlistModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_home extends Fragment {



    RecyclerView competitionlist;
    FirebaseFirestore db;
    List<competitionlistModel> competitionlistModelList;
    CompetitionListAdapter competitionListAdapter;

    ShimmerFrameLayout sm;


    ShimmerFrameLayout layout ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public main_home() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static main_home newInstance(String param1, String param2) {
        main_home fragment = new main_home();
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
        View root = inflater.inflate(R.layout.fragment_main_home,container,false);
        db = FirebaseFirestore.getInstance();
        sm = root.findViewById(R.id.ShimmerLayout);
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        competitionlist = root.findViewById(R.id.recycleview_comlist);
        competitionlist.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        competitionlistModelList = new ArrayList<>();
        competitionListAdapter = new CompetitionListAdapter(getActivity(),competitionlistModelList);
        competitionlist.setAdapter(competitionListAdapter);

        db.collection("homecompetition").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            sm.stopShimmer();
                            sm.setVisibility(View.GONE);
                            competitionlist.setVisibility(View.VISIBLE);

                            for (QueryDocumentSnapshot document : task.getResult()){
                                competitionlistModel competitionlistModel = document.toObject(competitionlistModel.class);
                                competitionlistModelList.add(competitionlistModel);
                                competitionListAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        return root;
    }


}