# SlideShow可高度定制的图片轮播器控件

### 使用
引入依赖 compile 'com.lwh.slideshow:slideshow:1.0.1'

1.根据业务继承BasePagerAdapter创建自定义的Adapger并重写getItemView如下：
```
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
```
2.创建布局文件如下：
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.v4.view.ViewPager
            android:id="@+id/m_view_page"
            android:layout_width="match_parent"
            android:layout_height="130dp">

        </android.support.v4.view.ViewPager>

        <com.lvweihao.slideshow.MyCirclePageIndicator
            android:id="@+id/cp_circle"
            android:layout_height="20dp"
            android:layout_width="match_parent"
            android:layout_alignBottom="@id/m_view_page"
            app:radius="3dp"
            app:fillColor="#ffffff"
            app:pageColor="#cccccc"
            app:centered="true"
            app:pageIndicatorStrokeWidth="0dp">
        </com.lvweihao.slideshow.MyCirclePageIndicator>
    </RelativeLayout>
</LinearLayout>
```
3.在java代码中创建关联关系：
```
MyCirclePageIndicator cpCircle = findViewById(R.id.cp_circle);
ViewPager mViewPage = findViewById(R.id.m_view_page);

urls.add("http://seopic.699pic.com/photo/00005/5186.jpg_wh1200.jpg");
urls.add("http://seopic.699pic.com/photo/50010/0719.jpg_wh1200.jpg");
urls.add("http://seopic.699pic.com/photo/50009/9449.jpg_wh1200.jpg");
urls.add("http://seopic.699pic.com/photo/50002/5923.jpg_wh1200.jpg");
urls.add("http://seopic.699pic.com/photo/50001/9330.jpg_wh1200.jpg");
urls.add("http://seopic.699pic.com/photo/50009/9191.jpg_wh1200.jpg");

carouselAdapter = new CarouselAdapter(this, urls, mViewPage);
carouselAdapter.startInterval(5000); //传入的轮询间隔时间单位为毫秒
cpCircle.setViewPager(mViewPage);
```
4.最后记得在界面销毁时停止轮询：
```
@Override
    protected void onDestroy() {
        super.onDestroy();
        carouselAdapter.onDestory();
    }
```
