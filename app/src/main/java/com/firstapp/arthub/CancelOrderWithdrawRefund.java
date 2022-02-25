package com.firstapp.arthub;

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
import android.widget.Toast;

import com.firstapp.arthub.models.All_user_competetionlists;
import com.firstapp.arthub.models.CancelOrderRefundModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CancelOrderWithdrawRefund extends AppCompatActivity {

    EditText Accountname,ifscCode,AccountType,Accountnumber;
    Button submit;
    DatabaseReference delete,savedata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order_withdraw_refund);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));


        String orderIDD = getIntent().getStringExtra("OrderIDD");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        delete = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid).child(orderIDD);
        savedata = FirebaseDatabase.getInstance().getReference().child("CancelOrders").child(uid);


        AccountType = findViewById(R.id.accountype_RCO);
        Accountname = findViewById(R.id.accountname_RCO);
        ifscCode = findViewById(R.id.ifsccode_RCO);
        Accountnumber = findViewById(R.id.accountno_RCO);
        submit = findViewById(R.id.submitBTN);

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String accounttype = AccountType.getText().toString();
                String accountname = Accountname.getText().toString();
                String IFScCode = ifscCode.getText().toString();
                String accountnumber = Accountnumber.getText().toString();
                String OrDerIDD = getIntent().getStringExtra("OrderIDD");
                Calendar cdate =Calendar.getInstance();
                String TotC = getIntent().getStringExtra("TotalC");
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
                final String savedate = currentdate.format(cdate.getTime());
                String CancelDate = savedate;


                if (accountnumber.isEmpty()){
                    Accountnumber.setError("Please enter Account No");
                }else if(IFScCode.isEmpty()){
                    ifscCode.setError("Please enter IFSC Code");
                }else if (accounttype.isEmpty()){
                    AccountType.setError("Please enter Account type");
                }else if (accountname.isEmpty()){
                    Accountname.setError("Please enter Account Name");
                }else{
                    CancelOrderRefundModel user = new CancelOrderRefundModel(userid,accountname,accountnumber,IFScCode,accounttype,TotC,CancelDate);
                    savedata.push().setValue(user);
                    delete.removeValue();
                    Toast.makeText(CancelOrderWithdrawRefund.this, "Order Cancelled!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CancelOrderWithdrawRefund.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}