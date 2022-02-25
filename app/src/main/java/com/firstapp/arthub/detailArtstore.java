package com.firstapp.arthub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firstapp.arthub.adapters.slidepageradapter;

import com.firstapp.arthub.imageslider.Image1;
import com.firstapp.arthub.imageslider.Image2;
import com.firstapp.arthub.imageslider.Image3;

import java.util.ArrayList;
import java.util.List;

public class detailArtstore extends AppCompatActivity {

    private ViewPager pager;
    LinearLayout sliderDotspanel;
    private int dotscount;

    private ImageView[] dots;
    private PagerAdapter pagerAdapter;

    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artstore);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        List<Fragment> list = new ArrayList<>();
        list.add(new Image1());
        list.add(new Image2());
        list.add(new Image3());

        pager = findViewById(R.id.viewpagerDA);
        pagerAdapter = new slidepageradapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDotsDA);
        dotscount = pagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dots));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            sliderDotspanel.addView(dots[i],params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.artstoredots));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            public void  onPageScrolled(int position, float positionOffset , int positionOffsetPixels){

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i<dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dots));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.artstoredots));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        title = findViewById(R.id.titleDAS);
        title.setText(getIntent().getStringExtra("title_MC"));



    }
}