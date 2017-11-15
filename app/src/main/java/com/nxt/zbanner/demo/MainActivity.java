package com.nxt.zbanner.demo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nxt.zbanner.demo.widget.ZDotView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> viewlIst=new ArrayList<>();
    private List<ZDotView> dotviewlIst=new ArrayList<>();
    private int resIds[] = new int[] { R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private ImageView[] mScaleViews=new ImageView[6];
    private MyCountDownTimer myCountDownTimer;
    private int page=0;
    private LinearLayout dotcontiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotcontiner= (LinearLayout) findViewById(R.id.layout_dot_continer);
        View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_img_item,null);
        viewlIst.add(view);
        for(int i=0;i<mScaleViews.length;i++){
            ZDotView dotview=new ZDotView(getApplicationContext());
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,8),DensityUtil.dip2px(this,8));
            layoutParams.setMargins(DensityUtil.dip2px(this,5),0,0,0);
            dotview.setLayoutParams(layoutParams);
            dotviewlIst.add(dotview);
            dotcontiner.addView(dotview);

        }
         myCountDownTimer=new MyCountDownTimer(3000,1000);
        viewPager= (ViewPager) findViewById(R.id.vp_demo);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(new ScalePagerAdapter());
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setcolor(position);
                page=position+1;
            }

            @Override
            public void onPageSelected(int position) {
                //page++;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        myCountDownTimer.start();

    }
    private class ScalePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return resIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//          if (object instanceof ScaleView) {
//              ScaleView scaleView = (ScaleView) object;
//              container.removeView(scaleView);
//          }
            Log.i("sss","destory   position--------------->"+position+"");

            container.removeView(mScaleViews[position]);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            ImageView scaleView = new ImageView(getApplicationContext());
            scaleView.setImageResource(resIds[position]);
            scaleView.setScaleType(ImageView.ScaleType.FIT_XY);
            mScaleViews[position] = scaleView;
            container.addView(scaleView);
            scaleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
                }
            });

        return scaleView;
        }

    }

    private void setcolor(int position) {
        for(int i=0;i<dotviewlIst.size();i++){
            if(position==i){
                dotviewlIst.get(position).setCircleColor(android.R.color.white);
            }else{
                dotviewlIst.get(i).setCircleColor(android.R.color.darker_gray);

            }
        }
    }

    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击

        }
        //计时完毕的方法
        @Override
        public void onFinish() {
            //  ZToastUtils.showShort(getActivity(),"start get data----------");
            if(page>resIds.length-1) page=0;
            viewPager.setCurrentItem(page);
           // page++;
            myCountDownTimer.start();
        }
    }
}
