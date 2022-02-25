package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.models.All_Users;
import com.firstapp.arthub.models.WithdrawAmountModel;
import com.firstapp.arthub.models.WithdrawNewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class WithdrawActivityNew extends AppCompatActivity {

    TextView wallet_money;
    EditText accountno,ifsccode,accountype,accountname,enterammount;
    Button withdrawbutton;
    FirebaseFirestore database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_new);
        getSupportActionBar().hide();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        wallet_money = findViewById(R.id.wallet_moneyWA);
        accountno = findViewById(R.id.accountno_WA);
        ifsccode = findViewById(R.id.ifsccode_WA);
        accountype = findViewById(R.id.accountype_WA);
        accountname = findViewById(R.id.accountname_WA);
        enterammount = findViewById(R.id.enteramount_WA);
        withdrawbutton = findViewById(R.id.withdrawbtn_WA);
        database = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("WithdrawMoney_Request");

        database.collection("All_Users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {

                All_Users user = documentSnapshot.toObject(All_Users.class);
                wallet_money.setText(String.valueOf(user.getWalletsmoney()));
            }
        });


        withdrawbutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cdate = Calendar.getInstance();
                SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
                final String savedate = currentdate.format(cdate.getTime());
                String IssuedDate = savedate;
                String AccountNO = accountno.getText().toString();
                String wallet = wallet_money.getText().toString();
                String IfscCode = ifsccode.getText().toString();
                String AccountType = accountype.getText().toString();
                String wall = getIntent().getStringExtra("wall");
                String AccountName = accountname.getText().toString();
                String RequestAmount = enterammount.getText().toString();
                String Status = "Pending";
                Integer a = Integer.parseInt(wallet);
                Integer b = Integer.parseInt(RequestAmount);
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                WithdrawAmountModel user = new WithdrawAmountModel(AccountNO, IfscCode, AccountType, AccountName, RequestAmount, wallet, IssuedDate, Status);

                if (b>a){
                    Toast.makeText(WithdrawActivityNew.this, "Insufficient Balance!", Toast.LENGTH_SHORT).show();
                }else if (AccountNO.isEmpty()){
                    Toast.makeText(WithdrawActivityNew.this, "Enter Account No", Toast.LENGTH_SHORT).show();
                }else if (IfscCode.isEmpty()){
                    Toast.makeText(WithdrawActivityNew.this, "Enter IFSC Code", Toast.LENGTH_SHORT).show();
                }else if(AccountName.isEmpty()){
                    Toast.makeText(WithdrawActivityNew.this, "Enter Account Name", Toast.LENGTH_SHORT).show();
                }else if(AccountType.isEmpty()){
                    Toast.makeText(WithdrawActivityNew.this, "Enter Account Type", Toast.LENGTH_SHORT).show();
                }else if(RequestAmount.isEmpty()){
                    Toast.makeText(WithdrawActivityNew.this, "Enter Amount you wish to withdraw", Toast.LENGTH_SHORT).show();
                }else{
                    databaseReference.child(uid).push().setValue(user);
                    Toast.makeText(WithdrawActivityNew.this, "Request sent", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(WithdrawActivityNew.this,PastTransaction_profile.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });


    }

}