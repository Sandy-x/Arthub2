package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.models.All_Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Security_profile extends AppCompatActivity {
    EditText oldpass,newpass,confirmnewpass;
    Button changepassbtn;
    FirebaseAuth fauth;
    FirebaseUser user;
    String userid;
    FirebaseFirestore database;
    String OldPaass;
    TextView errortext;
    ProgressDialog progressDialog;
    String email,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_profile);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));

        progressDialog = new ProgressDialog(this);
        
        oldpass = findViewById(R.id.old_pass);
        newpass = findViewById(R.id.new_pass);
        confirmnewpass = findViewById(R.id.new_passConfirm);
        changepassbtn = findViewById(R.id.changepass_btn);
        fauth = FirebaseAuth.getInstance();
        userid = fauth.getCurrentUser().getUid();
        user = FirebaseAuth.getInstance().getCurrentUser();
        errortext = findViewById(R.id.old_passerrortxt);


        database = FirebaseFirestore.getInstance();
        database.collection("All_Users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {

                All_Users user = documentSnapshot.toObject(All_Users.class);
                OldPaass = user.getPassword().toString();
                email = user.getEmail();
                username = user.getUsername();

            }
        });

        changepassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please Wait...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                String oldpassword = oldpass.getText().toString();
                String newpassword = newpass.getText().toString();
                String confirmnewpassword = confirmnewpass.getText().toString();
                String Email = email;
                String Username = username;
                AuthCredential credential = EmailAuthProvider.getCredential(Email, String.valueOf(oldpassword));

                All_Users users = new All_Users( Email, Username, confirmnewpassword);
                if(oldpassword.isEmpty()){
                    progressDialog.dismiss();
                    oldpass.setError("Enter OldPassword");
                }else if(newpassword.isEmpty()){
                    progressDialog.dismiss();
                    newpass.setError("Enter NewPassword");
                }else if(confirmnewpassword.isEmpty()){
                    progressDialog.dismiss();
                    confirmnewpass.setError("fill this section");
                }else if(!confirmnewpassword.equals(newpassword)){
                    progressDialog.dismiss();
                    errortext.setText("Confirm password do not match wih New Password!");
                    errortext.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errortext.setVisibility(View.GONE);
                        }
                    },1700);
                }else if(!oldpassword.equals(OldPaass)){
                    errortext.setText("Old Passwword is incorrect!");
                    errortext.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errortext.setVisibility(View.GONE);
                        }
                    },1700);
                }else{
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(newpassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (!task.isSuccessful()) {
                                            Toast.makeText(Security_profile.this, "Something went wrong. Please try again later", Toast.LENGTH_SHORT).show();
                                        } else {

                                            Toast.makeText(Security_profile.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                                            database.collection("All_Users")
                                                    .document(userid)
                                                    .set(users);
                                            Intent intent = new Intent(Security_profile.this, MainActivity.class);
                                            startActivity(intent);
                                            progressDialog.dismiss();
                                            finish();
                                        }
                                    }
                                });
                            }else {
                                Toast.makeText(Security_profile.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        
        
    }
}