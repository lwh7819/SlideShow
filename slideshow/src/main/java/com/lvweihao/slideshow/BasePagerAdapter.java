package com.lvweihao.slideshow;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class BasePagerAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener {
    public static final int TIMER = 0xabc0001;

    //当前页面
    private int currentPosition = 0;

    protected Context mContext;
    protected ArrayList<View> views;
    protected ViewPager mViewPager;

    private int delayTime;

    public BasePagerAdapter(Context context, ArrayList<T> datas, ViewPager viewPager) {
        mContext = context;
        views = new ArrayList<>();
        //如果数据大于一条
        if (datas.size() > 1) {
            //添加最后一页到第一页
            datas.add(0, datas.get(datas.size() - 1));
            //添加第一页(经过上行的添加已经是第二页了)到最后一页
            datas.add(datas.get(1));
        }

        for (T data : datas) {
            views.add(getItemView(data));
        }

        mViewPager = viewPager;
        viewPager.setAdapter(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(1, false);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    protected abstract View getItemView(T data);

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //若viewpager滑动未停止，直接返回
        if (state != ViewPager.SCROLL_STATE_IDLE) return;
        //若当前为第一张，设置页面为倒数第二张
        if (currentPosition == 0) {
            mViewPager.setCurrentItem(views.size() - 2, false);
        } else if (currentPosition == views.size() - 1) {
            //若当前为倒数第一张，设置页面为第二张
            mViewPager.setCurrentItem(1, false);
        }
    }

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIMER:
                    int currentIndex = mViewPager.getCurrentItem();
                    if (currentIndex == views.size() - 1) {
                        mViewPager.setCurrentItem(views.size() - 2, false);
                    }
                    mViewPager.setCurrentItem(++currentIndex, true);
                    startInterval(delayTime);
                    Log.e("lwh", "run");
                    break;
                default:
                    break;
            }
        }
    };

    public Handler startInterval(int delayedMillisSecond) {
        this.delayTime = delayedMillisSecond;
        handler.sendEmptyMessageDelayed(TIMER, delayedMillisSecond);
        return handler;
    }

    public void onDestory() {
        handler.removeMessages(BasePagerAdapter.TIMER);
        handler = null;
    }

}