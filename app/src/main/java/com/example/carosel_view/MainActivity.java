package com.example.carosel_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.carosel_view.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    public ViewPager viewPager;

    public final static int LOOPs = 1000;
    private CarouselPagerAdapter adapter;
    public static int count = 10; // View Pager Item Size

    /**
     * You shouldn't define first page = 0.
     * Let define firstPage = 'Number viewpager size' to make endless carousel layout
     */
     public static final int FIRST_PAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        viewPager = mBinding.viewPager;

        // Set page margin between pages for view pager
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int pageMargin = ((displayMetrics.widthPixels / 4) * 2);
        viewPager.setPageMargin(-pageMargin);

        // Initial View Pager Adapter
        adapter = new CarouselPagerAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        viewPager.addOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // Directions left and right
        viewPager.setCurrentItem(FIRST_PAGE);
        viewPager.setOffscreenPageLimit(3);
    }
}