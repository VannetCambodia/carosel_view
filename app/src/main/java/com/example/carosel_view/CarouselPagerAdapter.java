package com.example.carosel_view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public static final float BIG_SCALE = 1.0f;
    public static final float SMALL_SCALE = 0.7f;
    public static final float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private MainActivity context;
    private FragmentManager fragmentManager;
    private float scale;

    public CarouselPagerAdapter(MainActivity context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        // Make the first pager bigger that others
        try {
            scale = position == MainActivity.FIRST_PAGE?BIG_SCALE:SMALL_SCALE;
            position = position % MainActivity.count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ItemFragment.newInstance(context, position, scale);
    }

    @Override
    public int getCount() {
        int count = 0;
        try{
            count = MainActivity.count * MainActivity.LOOPs;
        }catch (Exception e){
            // TODO: Handle Exceptions
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try{
            if (positionOffset >= 0f && positionOffset <= 1f){
                CarouselLinearLayout current = getRootView(position);
                CarouselLinearLayout next = getRootView(position+1);

                current.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
                next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private CarouselLinearLayout getRootView(int position){
        return fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);
    }

    private String getFragmentTag(int position){
        return "android:switcher:" + context.viewPager.getId() + ":" + position;
    }
}
