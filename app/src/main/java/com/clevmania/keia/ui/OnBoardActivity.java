package com.clevmania.keia.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clevmania.keia.MainActivity;
import com.clevmania.keia.R;
import com.clevmania.keia.adapter.SliderAdapter;

public class OnBoardActivity extends AppCompatActivity {
    private ViewPager onBoardPager;
    private LinearLayout navLayout;
    private SliderAdapter sliderAdapter;
    private TextView navDots[];
    private Button next;
    private int currentPage;
    private ViewPager.OnPageChangeListener slidePageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        onBoardPager = findViewById(R.id.vp_slider);
        navLayout = findViewById(R.id.ll_nav_layout);
        sliderAdapter = new SliderAdapter(this);

        next = findViewById(R.id.btn_next);

        onBoardPager.setAdapter(sliderAdapter);
        displayDots(0);

        slidePageListener = new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                displayDots(position);
                currentPage = position;

                if(currentPage == 0){
//                    next.setEnabled(true);
//                    prev.setEnabled(false);
                    next.setVisibility(View.INVISIBLE);
//

                }else if(currentPage == navDots.length - 1 ){
                    next.setEnabled(true);
                    next.setVisibility(View.VISIBLE);
                }else {
                    next.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        onBoardPager.addOnPageChangeListener(slidePageListener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo intent should fire signin activity
                Intent intent = new Intent(OnBoardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void displayDots(int position){
        navDots = new TextView[3];
        navLayout.removeAllViews();

        for(int i = 0; i<navDots.length; i++){
            navDots[i] = new TextView(this);
            navDots[i].setText(Html.fromHtml("&#8226"));
            navDots[i].setTextSize(35);
            navDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            navLayout.addView(navDots[i]);
        }

        if(navDots.length > 0){
            navDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

    private int getItem(int i){
        return onBoardPager.getCurrentItem() + i;
    }
}
