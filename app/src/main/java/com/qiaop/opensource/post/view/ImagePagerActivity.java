package com.qiaop.opensource.post.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.qiaop.opensource.post.ImageDetailFragment;
import com.qiaop.opensource.post.R;


/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class ImagePagerActivity extends FragmentActivity {

    private HackyViewPager viewPager;
    private ImagePagerAdapter adapter;

    private String[] urls;
    private String url;

    private int pagerPosition = 0;
    private static final String STATE_POSITION = "STATE_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepager);
        Intent intent = getIntent();
        urls = intent.getStringArrayExtra("urls");
        int position = intent.getIntExtra("position", 0);
        initView();

        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }
        viewPager.setCurrentItem(position);
    }

    @SuppressWarnings("deprecation")
    private void initView(){
        viewPager = (HackyViewPager) findViewById(R.id.imagepager_hackyViewPager);
        adapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int index) {
                url = urls[index];
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, viewPager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public String[] urls;

        public ImagePagerAdapter(FragmentManager fm, String[] urls) {
            super(fm);
            this.urls = urls;
        }

        @Override
        public int getCount() {
            return urls == null ? 0 : urls.length;

        }

        @Override
        public Fragment getItem(int position) {
            String url = urls[position];
            return ImageDetailFragment.newInstance(url);
        }

    }
}
