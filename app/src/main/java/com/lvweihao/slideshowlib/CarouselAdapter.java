package com.lvweihao.slideshowlib;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lvweihao.slideshow.BasePagerAdapter;
import com.lvweihao.slideshowlib.R;

import java.util.ArrayList;

public class CarouselAdapter extends BasePagerAdapter<String> {

    public CarouselAdapter(Context context, ArrayList<String> datas, ViewPager viewPager) {
        super(context, datas, viewPager);
    }

    private ViewGroup.LayoutParams layoutParams;

    @Override
    protected View getItemView(String data) {
//        if (layoutParams == null) {
//            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        }
//        ImageView imageView = new ImageView(mContext);
//        imageView.setLayoutParams(layoutParams);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        Glide.with(mContext)
//                .load(data)
//                .into(imageView);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_page, null);
        CardView cardView = view.findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageView imageView = view.findViewById(R.id.img);
        Glide.with(mContext)
                .load(data)
                .into(imageView);

        return view;
    }
}