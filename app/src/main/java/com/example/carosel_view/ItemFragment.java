package com.example.carosel_view;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;

public class ItemFragment extends Fragment {

    private static final String POSITION = "position";
    private static final String SCALE = "scale";
    private static final String DRAWABLE_RESOURCE = "resource";

    private int screenWidth;
    private int screenHeight;

    private int[] imageArray = new int[]{
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image_8,
            R.drawable.image9,
            R.drawable.image10
    };

    public static Fragment newInstance(MainActivity context, int pos, float scale){
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, pos);
        bundle.putFloat(SCALE, scale);

        return Fragment.instantiate(context, ItemFragment.class.getName(), bundle);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        final int position = this.getArguments().getInt(POSITION);
        float scale = this.getArguments().getFloat(SCALE);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth/2, screenHeight/2);
        CarouselLinearLayout cardView = (CarouselLinearLayout) inflater.inflate(R.layout.fragment_image, container, false);

        TextView textView = (TextView) cardView.findViewById(R.id.text);
        CarouselLinearLayout root = (CarouselLinearLayout) cardView.findViewById(R.id.root_container);
        ImageView imageView = (ImageView) cardView.findViewById(R.id.pagerImg);

        textView.setText("Carousel Item: "+position);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(imageArray[position]);

        root.setScaleBoth(scale);

        return cardView;
    }

    /**
     * Get Device Screen width and height
     */
    private void getWidthAndHeight(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

}
