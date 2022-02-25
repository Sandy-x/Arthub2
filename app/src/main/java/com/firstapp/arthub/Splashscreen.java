package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splashscreen extends AppCompatActivity {


    LottieAnimationView lottieAnimationView;
    private static  int SPLASH_TIME_OUT = 3200;
    SharedPreferences mSharedPref;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));

        lottieAnimationView = findViewById(R.id.lottie);

        lottieAnimationView.animate().translationY(-2800).setDuration(1000).setStartDelay(4000);
        getSupportActionBar().hide();






        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Splashscreen.this,Login.class);
                Splashscreen.this.startActivity(mainIntent);
                Splashscreen.this.finish();

            }
        },2000);


    }

}