package com.clevmania.keia.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.clevmania.keia.MainActivity;
import com.clevmania.keia.R;
import com.clevmania.keia.ui.OnBoardActivity;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ImageView slideLayout, nextView;
    private TextView header;
    private TextView body;

    //Background Image Array
    private int[] sliderBackground = {R.drawable.splash1,
            R.drawable.splash2, R.drawable.splash3,
    };

    private String[] sliderHeader = {"Symptom Assessment","Track Symptom",
            "Consult a Doctor"
    };

    private String[] sliderBody = {"Assess yourself and know more about you",
            "Keep track of symptoms you have experienced overtime",
            "Connect with a Doctor easily and faster"
    };

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderBackground.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.slider_board, container, false);

        slideLayout = view.findViewById(R.id.iv_slide_bg);
        header = view.findViewById(R.id.tv_slide_header);
        body = view.findViewById(R.id.tv_slide_body);
        nextView = view.findViewById(R.id.iv_next_slide_bg);

        slideLayout.setBackgroundResource(sliderBackground[position]);
        Typeface headFace = Typeface.createFromAsset(context.getAssets(),"Nunito-SemiBold.ttf");
        Typeface bodyFace = Typeface.createFromAsset(context.getAssets(),"Nunito-Regular.ttf");
        header.setText(sliderHeader[position]);
        header.setTypeface(headFace);
        body.setText(sliderBody[position]);
        body.setTypeface(bodyFace);

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((FrameLayout)object);
    }
}
