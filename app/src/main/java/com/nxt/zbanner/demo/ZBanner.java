package com.nxt.zbanner.demo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by zhangyonglu on 2017/11/14 001417:18
 */

public class ZBanner extends FrameLayout{
    private Context mcontext;
    ViewPager vp;
    OnBannerItemClick monBannerItemClick;
    private LinearLayout dotcontiner;
    private ZPagerAdapter mzpageadapter;
    public ZBanner(Context context) {
        super(context);
        this.mcontext=context;
        init();
    }



    public ZBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mcontext=context;

        init();

    }

    public ZBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontext=context;

        init();

    }
    private void init() {
        vp=new ViewPager(mcontext);
        FrameLayout.LayoutParams layoutparams=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        vp.setLayoutParams(layoutparams);
        addView(vp);

        dotcontiner=new LinearLayout(mcontext);
        FrameLayout.LayoutParams dotlayoutparams=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        dotlayoutparams.setMargins(0,0,0,DensityUtil.dip2px(mcontext,12));
        dotlayoutparams.gravity= Gravity.BOTTOM;
        dotcontiner.setGravity(Gravity.CENTER);
        dotcontiner.setOrientation(LinearLayout.HORIZONTAL);
        dotcontiner.setLayoutParams(dotlayoutparams);
        addView(dotcontiner);

    }
    public void setadapter(ZPagerAdapter adapter){
        vp.setAdapter(adapter);
        adapter.setpagerlistener(vp);
        adapter.setdotview(this);
        this.mzpageadapter=adapter;

    }

    interface OnBannerItemClick{
        void OnItemClick(int position);
    }
    public  void setbanneritemclick(OnBannerItemClick onbannerclick){
        this.monBannerItemClick=onbannerclick;
        if(this.mzpageadapter!=null) mzpageadapter.setbanneritemclick(onbannerclick);

    }
    public void adddotview(ZDotView dotview){
        dotcontiner.addView(dotview);
    }
    public void setautoplay(boolean b){
        if(mzpageadapter!=null) mzpageadapter.setisautoplay(b);
    }
}
