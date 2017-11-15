package com.nxt.zbanner.demo;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by zhangyonglu on 2017/11/15 001508:13
 */

public class ZPagerAdapter extends PagerAdapter{
    private Context mcontext;
    private List<Integer> mresids;
    private List<String> murllist;
    private List<ImageView> imageViewList=new ArrayList<>();
    private List<ZDotView> zDotViewList=new ArrayList<>();
    private int page;
    private int mcount;
    private ViewPager mvp;
    private MyCountDownTimer mycountdowntimer;
    ZBanner.OnBannerItemClick monbanneritemclick;
    public ZPagerAdapter(Context context,Integer[] resids){
        this.mcontext=context;
        this.mresids= Arrays.asList(resids);
        mycountdowntimer=new MyCountDownTimer(3000,1000);
        initview();

    }

    private void initview() {

        if(mresids!=null){
             mcount=mresids.size();
        }
        if(murllist!=null){
            mcount=murllist.size();
        }
        for(int i=0;i<mcount;i++){
            ZDotView zDotview=new ZDotView(mcontext.getApplicationContext());
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(DensityUtil.dip2px(mcontext,8),DensityUtil.dip2px(mcontext,8));
            layoutParams.setMargins(DensityUtil.dip2px(mcontext,5),0,0,0);
            zDotview.setLayoutParams(layoutParams);
            zDotViewList.add(zDotview);
        }
    }

    public ZPagerAdapter(Context context,String[] urllist){
        this.mcontext=context;
        this.murllist=Arrays.asList(urllist);
        mycountdowntimer=new MyCountDownTimer(3000,1000);
        initview();

    }
    public ZPagerAdapter(Context context,List<String> ulist){
        this.mcontext=context;
        this.murllist=ulist;
        mycountdowntimer=new MyCountDownTimer(3000,1000);
        initview();


    }
    @Override
    public int getCount() {
        if(mresids!=null){
            return mresids.size();
        }else if(murllist!=null){
            return murllist.size();

        }
        return 0;
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

        container.removeView(imageViewList.get(position));

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView scaleView = new ImageView(mcontext.getApplicationContext());
        scaleView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewList.add(scaleView);
        container.addView(scaleView);
        if(mresids!=null){
            scaleView.setImageResource(mresids.get(position));

        }
        if(murllist!=null){
            Log.i("sss","m ulist---------------->"+murllist.get(position));
            Glide.with(mcontext)
                    .load(murllist.get(position))
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(scaleView);
        }
        scaleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               monbanneritemclick.OnItemClick(position);
            }
        });
        return scaleView;
    }

    public void setbanneritemclick(ZBanner.OnBannerItemClick onbanneritemclick){
          monbanneritemclick=onbanneritemclick;
    }

    public void setdotview(ZBanner zbanner){
        for(int i=0;i<zDotViewList.size();i++){
            zbanner.adddotview(zDotViewList.get(i));
        }
    }

    public void setpagerlistener(ViewPager vp){
        this.mvp=vp;
      vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                   setcolor(position);
                   page=position+1;

          }

          @Override
          public void onPageSelected(int position) {

          }

          @Override
          public void onPageScrollStateChanged(int state) {

          }
      });
    }

    private void setcolor(int position) {
        for(int i=0;i<zDotViewList.size();i++){
            if(position==i){
                zDotViewList.get(position).setCircleColor(android.R.color.white);
            }else{
                zDotViewList.get(i).setCircleColor(android.R.color.darker_gray);

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
            if(page>mcount-1) page=0;
           mvp.setCurrentItem(page);
            // page++;
          mycountdowntimer.start();
        }
    }

    public void setisautoplay(boolean b){
        if(b){
            mycountdowntimer.start();
        }else{
            mycountdowntimer.onFinish();
        }
    }
}
