package com.kodxhub.ads.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.kodxhub.ads.R;

public class BannerAdView extends LinearLayout {

    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;

    public BannerAdView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BannerAdView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        initView();
    }

    public BannerAdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }

    public BannerAdView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        this.view = this;
        inflate(mContext, R.layout.view_banner_ad, this);
    }

}
