package com.firstapp.arthub;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.models.All_Users;
import com.firstapp.arthub.models.WithdrawAmountModel;
import com.firstapp.arthub.models.WithdrawTrophyModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RedeemTrophy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RedeemTrophy extends BottomSheetDialogFragment {

    TextView trophybalance_RT,Error_RT;
    FirebaseFirestore database;
    EditText enteramount_RT;
    Button withdrawbtn_RT,withdrawbtn2_RT;
    private DatabaseReference databaseReference1;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RedeemTrophy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RedeemTrophy.
     */
    // TODO: Rename and change types and number of parameters
    public static RedeemTrophy newInstance(String param1, String param2) {
        RedeemTrophy fragment = new RedeemTrophy();
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
        View view =  inflater.inflate(R.layout.fragment_redeem_trophy, container, false);
        trophybalance_RT = view.findViewById(R.id.trophybalance_RT);
        withdrawbtn_RT = view.findViewById(R.id.withdrawbtn_RT);
        enteramount_RT = view.findViewById(R.id.enteramount_RT);
        Error_RT = view.findViewById(R.id.Error_RT);
        database = FirebaseFirestore.getInstance();
        database.collection("All_Users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {

                All_Users user = documentSnapshot.toObject(All_Users.class);
                trophybalance_RT.setText(String.valueOf(user.getTrophy()));
            }
        });

        database = FirebaseFirestore.getInstance();
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("TrophyWithdraw_Request");

        withdrawbtn_RT.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cdate =Calendar.getInstance();
                SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
                final String savedate = currentdate.format(cdate.getTime());

                String IssuedDate = savedate;
                String Status = "Pending";
                String AvailableTrophy = trophybalance_RT.getText().toString();
                String RequestedTrophy = enteramount_RT.getText().toString();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                int a = Integer.parseInt(RequestedTrophy.toString());
                int b = Integer.parseInt(AvailableTrophy);

                if (RequestedTrophy == null){
                    enteramount_RT.setError("Enter no of trophy");
                }else if(a == 0){
                    Error_RT.setText("Invalid input!");
                    Error_RT.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Error_RT.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                }else if (a>b){
                    Error_RT.setText("Invalid input!");
                    Error_RT.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Error_RT.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                }else{
                    WithdrawTrophyModel user = new WithdrawTrophyModel(Status,IssuedDate,RequestedTrophy,AvailableTrophy);
                    databaseReference1.child(uid).push().setValue(user);
                    Toast.makeText(getActivity(), "Request sent", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(),PastTransaction_profile.class);
                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });
        return view;
    }
}