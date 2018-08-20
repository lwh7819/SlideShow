package com.lvweihao.slideshow;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> urls = new ArrayList<>();
    private CarouselAdapter carouselAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyCirclePageIndicator cpCircle = findViewById(R.id.cp_circle);
        ViewPager mViewPage = findViewById(R.id.m_view_page);

        urls.add("http://seopic.699pic.com/photo/00005/5186.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50010/0719.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50009/9449.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50002/5923.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50001/9330.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50009/9191.jpg_wh1200.jpg");

        carouselAdapter = new CarouselAdapter(this, urls, mViewPage);
        carouselAdapter.startInterval(5000);
        cpCircle.setViewPager(mViewPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        carouselAdapter.onDestory();
    }
}
