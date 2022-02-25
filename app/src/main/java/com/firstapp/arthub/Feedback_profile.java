package com.firstapp.arthub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.arthub.models.feedbackModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback_profile extends AppCompatActivity {

    EditText feedbacket;
    Button sendfeedbackbtn;
    private DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_profile);

        feedbacket = findViewById(R.id.edittextfeedback);
        sendfeedbackbtn = findViewById(R.id.sendfeedback);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("FeedBacks");

        sendfeedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Feedback = feedbacket.getText().toString();
                if(Feedback.isEmpty()){
                    Toast.makeText(Feedback_profile.this, "Please write something!", Toast.LENGTH_SHORT).show();
                }else{
                    feedbackModel feedback = new feedbackModel(Feedback);
                    databaseReference1.push().setValue(feedback);
                    Toast.makeText(Feedback_profile.this, "Thank You For Your Feedback", Toast.LENGTH_LONG).show();
                    feedbacket.setText(null);
                }
            }
        });

    }
}