package com.firstapp.arthub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class detail_helpandsupport extends AppCompatActivity {
    TextView question,answer,answer2,answer3,answer4,answer5,answer6,answer7,answer8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_helpandsupport);

        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));

        question = findViewById(R.id.questionDHS);
        answer = findViewById(R.id.answers1DHS);
        answer2 = findViewById(R.id.answers2DHS);
        answer3 = findViewById(R.id.answers3DHS);
        answer4 = findViewById(R.id.answers4DHS);
        answer5 = findViewById(R.id.answers5DHS);
        answer6 = findViewById(R.id.answers6DHS);
        answer7 = findViewById(R.id.answers7DHS);
        answer8 = findViewById(R.id.answers8DHS);


        question.setText(getIntent().getStringExtra("question"));
        answer.setText(getIntent().getStringExtra("line1"));
        answer2.setText(getIntent().getStringExtra("line2"));
        answer3.setText(getIntent().getStringExtra("line3"));
        answer4.setText(getIntent().getStringExtra("line4"));
        answer5.setText(getIntent().getStringExtra("line5"));
        answer6.setText(getIntent().getStringExtra("line6"));
        answer7.setText(getIntent().getStringExtra("line7"));
        answer8.setText(getIntent().getStringExtra("line8"));

    }
}