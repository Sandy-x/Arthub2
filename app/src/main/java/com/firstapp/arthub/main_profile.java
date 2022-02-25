package com.firstapp.arthub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.firstapp.arthub.models.All_Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_profile extends Fragment {

    ConstraintLayout myOrders,mycompetetion_cl,Security_cl,feedback_prof,AOFT,helpandsupportlayout,giveaway_profile,walletWithdraw;

    TextView instagram_profile,youtube_profile,withdraw_FMP;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView wallet,username,logout,trophybalance;
    FirebaseFirestore database;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public main_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment main_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static main_profile newInstance(String param1, String param2) {
        main_profile fragment = new main_profile();
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

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_profile,container,false);

        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        wallet = view.findViewById(R.id.wallet_balance);
        username = view.findViewById(R.id.profile_username);
        trophybalance=view.findViewById(R.id.trophybalance);
        logout = view.findViewById(R.id.logout);
        withdraw_FMP = view.findViewById(R.id.withdraw_FMP);
        AOFT = view.findViewById(R.id.AOFT);
        youtube_profile = view.findViewById(R.id.youtube_profile);
        giveaway_profile = view.findViewById(R.id.giveaway_profile);
        instagram_profile = view.findViewById(R.id.instagram_profile);
        walletWithdraw = view.findViewById(R.id.walletWithdraw);
        helpandsupportlayout = view.findViewById(R.id.helpandsupportlayout);
        Security_cl = view.findViewById(R.id.Security_cl);
        feedback_prof = view.findViewById(R.id.feedback_prof);
        withdraw_FMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PastTransaction_profile.class);
                intent.putExtra("wallet",wallet.getText());
                startActivity(intent);
            }
        });
        feedback_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Feedback_profile.class);
                startActivity(intent);
            }
        });
        Security_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Security_profile.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent(getActivity(),Login.class);
                startActivity(in);
                getActivity().finish();
            }
        });
        walletWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PastTransaction_profile.class);
                intent.putExtra("wallet",wallet.getText());
                startActivity(intent);
            }
        });
        giveaway_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), giveaway_profile.class);
                startActivity(intent);
            }
        });

        AOFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),artoftheweek.class);
                startActivity(in);
            }
        });
        database = FirebaseFirestore.getInstance();
        database.collection("All_Users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {

                All_Users user = documentSnapshot.toObject(All_Users.class);
                wallet.setText(String.valueOf(user.getWalletsmoney()));
                username.setText(String.valueOf(user.getUsername()));
                trophybalance.setText(String.valueOf(user.getTrophy()));

            }
        });
        helpandsupportlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),helpandsupport_profile.class);
                getActivity().startActivity(intent);
            }
        });

        myOrders = view.findViewById(R.id.myorders_cl);
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),my_orders_profilee.class);
                getActivity().startActivity(intent);
            }
        });
        mycompetetion_cl = view.findViewById(R.id.mycompetetion_cl);
        mycompetetion_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),my_competetions_profile.class);
                getActivity().startActivity(intent);
            }
        });
        instagram_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://instagram.com/art_.__hub?utm_medium=copy_link";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        youtube_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://youtube.com/channel/UCT7ZVABhk6QTUL2TuPuu09w";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

}