package com.kodxhub.ads.banner;

import static com.kodxhub.ads.util.Constant.ADMOB;
import static com.kodxhub.ads.util.Constant.AD_STATUS_ON;
import static com.kodxhub.ads.util.Constant.FAN_BIDDING_ADMOB;
import static com.kodxhub.ads.util.Constant.FAN_BIDDING_AD_MANAGER;
import static com.kodxhub.ads.util.Constant.GOOGLE_AD_MANAGER;
import static com.kodxhub.ads.util.Constant.HUAWEI;
import static com.kodxhub.ads.util.Constant.NONE;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.kodxhub.ads.R;
import com.kodxhub.ads.util.Tools;

@SuppressWarnings("deprecation")
public class BannerAd {

    private static final String TAG = "AdNetwork";
    private final Activity activity;
    private AdView adView;
    private AdManagerAdView adManagerAdView;

    private String adStatus = "";
    private String adNetwork = "";
    private String backupAdNetwork = "";
    private String adMobBannerId = "";
    private String googleAdManagerBannerId = "";
    private String fanBannerId = "";
    private String unityBannerId = "";
    private String appLovinBannerId = "";
    private String appLovinBannerZoneId = "";
    private String ironSourceBannerId = "";
    private String wortiseBannerId = "";
    private String alienAdsBannerId = "";
    private String pangleBannerId = "";
    private String huaweiBannerId = "";
    private String yandexBannerId = "";
    private int placementStatus = 1;
    private boolean darkTheme = false;
    private boolean legacyGDPR = false;
    private boolean collapsibleBanner = false;

    public BannerAd(Activity activity) {
        this.activity = activity;
    }

    public BannerAd build() {
        loadBannerAd();
        return this;
    }

    public BannerAd setAdStatus(String adStatus) {
        this.adStatus = adStatus;
        return this;
    }

    public BannerAd setAdNetwork(String adNetwork) {
        this.adNetwork = adNetwork;
        return this;
    }

    public BannerAd setBackupAdNetwork(String backupAdNetwork) {
        this.backupAdNetwork = backupAdNetwork;
        return this;
    }

    public BannerAd setAdMobBannerId(String adMobBannerId) {
        this.adMobBannerId = adMobBannerId;
        return this;
    }

    public BannerAd setGoogleAdManagerBannerId(String googleAdManagerBannerId) {
        this.googleAdManagerBannerId = googleAdManagerBannerId;
        return this;
    }

    public BannerAd setFanBannerId(String fanBannerId) {
        this.fanBannerId = fanBannerId;
        return this;
    }

    public BannerAd setUnityBannerId(String unityBannerId) {
        this.unityBannerId = unityBannerId;
        return this;
    }

    public BannerAd setAppLovinBannerId(String appLovinBannerId) {
        this.appLovinBannerId = appLovinBannerId;
        return this;
    }

    public BannerAd setAppLovinBannerZoneId(String appLovinBannerZoneId) {
        this.appLovinBannerZoneId = appLovinBannerZoneId;
        return this;
    }

    public BannerAd setIronSourceBannerId(String ironSourceBannerId) {
        this.ironSourceBannerId = ironSourceBannerId;
        return this;
    }

    public BannerAd setWortiseBannerId(String wortiseBannerId) {
        this.wortiseBannerId = wortiseBannerId;
        return this;
    }

    public BannerAd setAlienAdsBannerId(String alienAdsBannerId) {
        this.alienAdsBannerId = alienAdsBannerId;
        return this;
    }

    public BannerAd setPangleBannerId(String pangleBannerId) {
        this.pangleBannerId = pangleBannerId;
        return this;
    }

    public BannerAd setHuaweiBannerId(String huaweiBannerId) {
        this.huaweiBannerId = huaweiBannerId;
        return this;
    }

    public BannerAd setYandexBannerId(String yandexBannerId) {
        this.yandexBannerId = yandexBannerId;
        return this;
    }

    public BannerAd setPlacementStatus(int placementStatus) {
        this.placementStatus = placementStatus;
        return this;
    }

    public BannerAd setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
        return this;
    }

    public BannerAd setLegacyGDPR(boolean legacyGDPR) {
        this.legacyGDPR = legacyGDPR;
        return this;
    }

    public BannerAd setIsCollapsibleBanner(boolean collapsibleBanner) {
        this.collapsibleBanner = collapsibleBanner;
        return this;
    }

    public void loadBannerAd() {
        if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
            switch (adNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                    FrameLayout adContainerView = activity.findViewById(R.id.admob_banner_view_container);
                    adContainerView.post(() -> {
                        adView = new AdView(activity);
                        adView.setAdUnitId(adMobBannerId);
                        adContainerView.removeAllViews();
                        adContainerView.addView(adView);
                        adView.setAdSize(Tools.getAdSize(activity));
                        adView.loadAd(Tools.getAdRequest(collapsibleBanner));
                        adView.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                adContainerView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                adContainerView.setVisibility(View.GONE);
                                loadBackupBannerAd();
                            }
                        });
                    });
                    Log.d(TAG, adNetwork + " Banner Ad unit Id : " + adMobBannerId);
                    break;

                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    FrameLayout googleAdContainerView = activity.findViewById(R.id.google_ad_banner_view_container);
                    googleAdContainerView.post(() -> {
                        adManagerAdView = new AdManagerAdView(activity);
                        adManagerAdView.setAdUnitId(googleAdManagerBannerId);
                        googleAdContainerView.removeAllViews();
                        googleAdContainerView.addView(adManagerAdView);
                        adManagerAdView.setAdSize(Tools.getAdSize(activity));
                        adManagerAdView.loadAd(Tools.getGoogleAdManagerRequest());
                        adManagerAdView.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                super.onAdLoaded();
                                googleAdContainerView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                                googleAdContainerView.setVisibility(View.GONE);
                                loadBackupBannerAd();
                            }
                        });
                    });
                    break;

                case HUAWEI:
                    RelativeLayout huaweiContainerView = activity.findViewById(R.id.huawei_banner_view_container);
                    com.huawei.hms.ads.banner.BannerView bannerView = new com.huawei.hms.ads.banner.BannerView(activity);
                    bannerView.setAdId(huaweiBannerId);
                    bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_320_50);
                    huaweiContainerView.addView(bannerView);
                    AdParam adParam = new AdParam.Builder().build();
                    bannerView.loadAd(adParam);
                    bannerView.setAdListener(new com.huawei.hms.ads.AdListener() {
                        @Override
                        public void onAdLoaded() {
                            huaweiContainerView.setVisibility(View.VISIBLE);
                            Log.d(TAG, adNetwork + " Banner Ad Loaded");
                        }
                        @Override
                        public void onAdFailed(int errorCode) {
                            huaweiContainerView.setVisibility(View.GONE);
                            loadBackupBannerAd();
                            Log.d(TAG, adNetwork + " Failed to Load Banner Ad " + huaweiBannerId + " Error: " + errorCode);
                        }
                    });
                    break;

                case NONE:
                    //do nothing
                    break;
            }
            Log.d(TAG, "Banner Ad is enabled");
        } else {
            Log.d(TAG, "Banner Ad is disabled");
        }
    }

    public void loadBackupBannerAd() {
        if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
            switch (backupAdNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                    FrameLayout adContainerView = activity.findViewById(R.id.admob_banner_view_container);
                    adContainerView.post(() -> {
                        adView = new AdView(activity);
                        adView.setAdUnitId(adMobBannerId);
                        adContainerView.removeAllViews();
                        adContainerView.addView(adView);
                        adView.setAdSize(Tools.getAdSize(activity));
                        adView.loadAd(Tools.getAdRequest(collapsibleBanner));
                        adView.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                adContainerView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                adContainerView.setVisibility(View.GONE);
                            }
                        });
                    });
                    Log.d(TAG, adNetwork + " Banner Ad unit Id : " + adMobBannerId);
                    break;

                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    FrameLayout googleAdContainerView = activity.findViewById(R.id.google_ad_banner_view_container);
                    googleAdContainerView.post(() -> {
                        adManagerAdView = new AdManagerAdView(activity);
                        adManagerAdView.setAdUnitId(googleAdManagerBannerId);
                        googleAdContainerView.removeAllViews();
                        googleAdContainerView.addView(adManagerAdView);
                        adManagerAdView.setAdSize(Tools.getAdSize(activity));
                        adManagerAdView.loadAd(Tools.getGoogleAdManagerRequest());
                        adManagerAdView.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                super.onAdLoaded();
                                googleAdContainerView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                                googleAdContainerView.setVisibility(View.GONE);
                            }
                        });
                    });
                    break;

                case HUAWEI:
                    RelativeLayout huaweiContainerView = activity.findViewById(R.id.huawei_banner_view_container);
                    com.huawei.hms.ads.banner.BannerView bannerView = new com.huawei.hms.ads.banner.BannerView(activity);
                    bannerView.setAdId(huaweiBannerId);
                    bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_320_50);
                    huaweiContainerView.addView(bannerView);
                    AdParam adParam = new AdParam.Builder().build();
                    bannerView.loadAd(adParam);
                    bannerView.setAdListener(new com.huawei.hms.ads.AdListener() {
                        @Override
                        public void onAdLoaded() {
                            huaweiContainerView.setVisibility(View.VISIBLE);
                            Log.d(TAG, backupAdNetwork + " [backup] Banner Ad Loaded");
                        }
                        @Override
                        public void onAdFailed(int errorCode) {
                            huaweiContainerView.setVisibility(View.GONE);
                            Log.d(TAG, backupAdNetwork + " [backup] Failed to Load Banner Ad " + huaweiBannerId + " Error: " + errorCode);
                        }
                    });
                    break;
            }
            Log.d(TAG, "Banner Ad is enabled");
        } else {
            Log.d(TAG, "Banner Ad is disabled");
        }
    }

    public void destroyAndDetachBanner() {

    }

}
