package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.models.All_Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import static android.widget.Toast.*;

public class Signup extends AppCompatActivity {

    TextView login,termscondition;
    EditText username,email,password;
    Button register;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    FirebaseFirestore database;


    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.Dark_blue));

        setContentView(R.layout.activity_signup);
        login = findViewById(R.id.s_login_tv);
        termscondition = findViewById(R.id.termscondition);
        termscondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.privacypolicies.com/live/6d48d28e-4264-4b91-ba03-8e5d7810e786";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Signup.this,Login.class);
                startActivity(in);
                finish();
            }
        });


        email = findViewById(R.id.s_email_et);
        password = findViewById(R.id.s_password_et);
        username = findViewById(R.id.s_username_et);
        register = findViewById(R.id.s_register_btn);
        database = FirebaseFirestore.getInstance();


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String Username = username.getText().toString();

                All_Users users = new All_Users( Email, Username, Password);
                if(Email.isEmpty()){
                    email.setError("Please fill this section");
                }else if (Password.isEmpty()) {
                    password.setError("Please fill this section");
                }else if(Password.length()<8){
                    password.setError("Password must be atleast 8 characters");
                }else if(Username.isEmpty()){
                    username.setError("Please fill this section");
                }
                else{
                    progressDialog.setMessage("Please Wait While Registration...");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String uid = task.getResult().getUser().getUid();
                                progressDialog.dismiss();
                                database.collection("All_Users")
                                        .document(uid).set(users)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(Signup.this,MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(Signup.this,"Registered Successfully", LENGTH_SHORT).show();

                                        }else{
                                            Toast.makeText(Signup.this,""+task.getException().getLocalizedMessage(), LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(Signup.this,""+task.getException().getLocalizedMessage(), LENGTH_SHORT).show();
                            }

                        }
                    });

                }

            }
        });





    }



}

//

