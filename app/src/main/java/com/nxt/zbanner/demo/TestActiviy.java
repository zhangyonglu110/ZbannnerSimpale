package com.nxt.zbanner.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.nxt.zbanner.demo.adapter.ZPagerAdapter;
import com.nxt.zbanner.demo.widget.ZBanner;


/**
 * Created by zhangyonglu on 2017/11/15 001508:58
 */

public class TestActiviy extends Activity{
    private ZBanner zBanner;
    private ImageView testimg;
    private Integer[] imgids=new Integer[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private String[] urllist=new String[]{"http://www.bitcoin86.com/uploads/allimg/171107/1-1G10F91256239-lp.jpg","http://www.bitcoin86.com/uploads/allimg/171107/1-1G10F91256239-lp.jpg","http://www.bitcoin86.com/uploads/allimg/171107/1-1G10F91256239-lp.jpg","http://www.bitcoin86.com/uploads/allimg/171107/1-1G10F91256239-lp.jpg"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        zBanner=findViewById(R.id.zbanner);


        zBanner.setadapter(new ZPagerAdapter(this,urllist));
        zBanner.setautoplay(true);
        zBanner.setbanneritemclick(new ZBanner.OnBannerItemClick() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(TestActiviy.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
