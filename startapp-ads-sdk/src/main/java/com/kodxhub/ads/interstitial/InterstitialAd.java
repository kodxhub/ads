package com.kodxhub.ads.interstitial;

import static com.kodxhub.ads.util.Constant.ADMOB;
import static com.kodxhub.ads.util.Constant.AD_STATUS_ON;
import static com.kodxhub.ads.util.Constant.FAN_BIDDING_ADMOB;
import static com.kodxhub.ads.util.Constant.FAN_BIDDING_AD_MANAGER;
import static com.kodxhub.ads.util.Constant.GOOGLE_AD_MANAGER;
import static com.kodxhub.ads.util.Constant.NONE;
import static com.kodxhub.ads.util.Constant.STARTAPP;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kodxhub.ads.listener.OnInterstitialAdDismissedListener;
import com.kodxhub.ads.util.Tools;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

@SuppressWarnings("deprecation")
public class InterstitialAd {

    private static final String TAG = "AdNetwork";
    private final Activity activity;
    private com.google.android.gms.ads.interstitial.InterstitialAd adMobInterstitialAd;
    private AdManagerInterstitialAd adManagerInterstitialAd;
    private StartAppAd startAppAd;
    private int retryAttempt;
    private int counter = 1;

    private String adStatus = "";
    private String adNetwork = "";
    private String backupAdNetwork = "";
    private String adMobInterstitialId = "";
    private String googleAdManagerInterstitialId = "";
    private String fanInterstitialId = "";
    private String unityInterstitialId = "";
    private String appLovinInterstitialId = "";
    private String appLovinInterstitialZoneId = "";
    private String mopubInterstitialId = "";
    private String ironSourceInterstitialId = "";
    private String wortiseInterstitialId = "";
    private String alienAdsInterstitialId = "";
    private String pangleInterstitialId = "";
    private String huaweiInterstitialId = "";
    private String yandexInterstitialId = "";
    private int placementStatus = 1;
    private int interval = 3;
    private boolean legacyGDPR = false;
    private boolean withListener = false;
    private OnInterstitialAdDismissedListener onInterstitialAdDismissedListener;

    public InterstitialAd(Activity activity) {
        this.activity = activity;
    }

    public InterstitialAd build() {
        loadInterstitialAd();
        return this;
    }

    public void show() {
        showInterstitialAd();
    }

    public InterstitialAd setAdStatus(String adStatus) {
        this.adStatus = adStatus;
        return this;
    }

    public InterstitialAd setAdNetwork(String adNetwork) {
        this.adNetwork = adNetwork;
        return this;
    }

    public InterstitialAd setBackupAdNetwork(String backupAdNetwork) {
        this.backupAdNetwork = backupAdNetwork;
        return this;
    }

    public InterstitialAd setAdMobInterstitialId(String adMobInterstitialId) {
        this.adMobInterstitialId = adMobInterstitialId;
        return this;
    }

    public InterstitialAd setGoogleAdManagerInterstitialId(String googleAdManagerInterstitialId) {
        this.googleAdManagerInterstitialId = googleAdManagerInterstitialId;
        return this;
    }

    public InterstitialAd setFanInterstitialId(String fanInterstitialId) {
        this.fanInterstitialId = fanInterstitialId;
        return this;
    }

    public InterstitialAd setUnityInterstitialId(String unityInterstitialId) {
        this.unityInterstitialId = unityInterstitialId;
        return this;
    }

    public InterstitialAd setAppLovinInterstitialId(String appLovinInterstitialId) {
        this.appLovinInterstitialId = appLovinInterstitialId;
        return this;
    }

    public InterstitialAd setAppLovinInterstitialZoneId(String appLovinInterstitialZoneId) {
        this.appLovinInterstitialZoneId = appLovinInterstitialZoneId;
        return this;
    }

    public InterstitialAd setMopubInterstitialId(String mopubInterstitialId) {
        this.mopubInterstitialId = mopubInterstitialId;
        return this;
    }

    public InterstitialAd setIronSourceInterstitialId(String ironSourceInterstitialId) {
        this.ironSourceInterstitialId = ironSourceInterstitialId;
        return this;
    }

    public InterstitialAd setWortiseInterstitialId(String wortiseInterstitialId) {
        this.wortiseInterstitialId = wortiseInterstitialId;
        return this;
    }

    public InterstitialAd setAlienAdsInterstitialId(String alienAdsInterstitialId) {
        this.alienAdsInterstitialId = alienAdsInterstitialId;
        return this;
    }

    public InterstitialAd setPangleInterstitialId(String pangleInterstitialId) {
        this.pangleInterstitialId = pangleInterstitialId;
        return this;
    }

    public InterstitialAd setHuaweiInterstitialId(String huaweiInterstitialId) {
        this.huaweiInterstitialId = huaweiInterstitialId;
        return this;
    }

    public InterstitialAd setYandexInterstitialId(String yandexInterstitialId) {
        this.yandexInterstitialId = yandexInterstitialId;
        return this;
    }

    public InterstitialAd setPlacementStatus(int placementStatus) {
        this.placementStatus = placementStatus;
        return this;
    }

    public InterstitialAd setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    public InterstitialAd setLegacyGDPR(boolean legacyGDPR) {
        this.legacyGDPR = legacyGDPR;
        return this;
    }

    public InterstitialAd setWithListener(boolean withListener, OnInterstitialAdDismissedListener onInterstitialAdDismissedListener) {
        this.withListener = withListener;
        this.onInterstitialAdDismissedListener = onInterstitialAdDismissedListener;
        return this;
    }

    private void loadInterstitialAd() {
        if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
            switch (adNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                    com.google.android.gms.ads.interstitial.InterstitialAd.load(activity, adMobInterstitialId, Tools.getAdRequest(activity, legacyGDPR), new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                            adMobInterstitialAd = interstitialAd;
                            adMobInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    loadInterstitialAd();
                                    if (withListener) {
                                        onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                    }
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                    Log.d(TAG, "The ad failed to show.");
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    adMobInterstitialAd = null;
                                    Log.d(TAG, "The ad was shown.");
                                }
                            });
                            Log.i(TAG, "onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            Log.i(TAG, loadAdError.getMessage());
                            adMobInterstitialAd = null;
                            loadBackupInterstitialAd();
                            Log.d(TAG, "Failed load AdMob Interstitial Ad");
                        }
                    });
                    break;

                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    AdManagerInterstitialAd.load(activity, googleAdManagerInterstitialId, Tools.getGoogleAdManagerRequest(), new AdManagerInterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                            super.onAdLoaded(adManagerInterstitialAd);
                            adManagerInterstitialAd = interstitialAd;
                            adManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent();
                                    loadInterstitialAd();
                                    if (withListener) {
                                        onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                    }
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                    super.onAdFailedToShowFullScreenContent(adError);
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    super.onAdShowedFullScreenContent();
                                    adManagerInterstitialAd = null;
                                    Log.d(TAG, "The ad was shown.");
                                }
                            });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            adManagerInterstitialAd = null;
                            loadBackupInterstitialAd();
                            Log.d(TAG, "Failed load Ad Manager Interstitial Ad");
                        }
                    });
                    break;

                case STARTAPP:
                    startAppAd = new StartAppAd(activity);
                    startAppAd.loadAd(new AdEventListener() {
                        @Override
                        public void onReceiveAd(@NonNull Ad ad) {
                            Log.d(TAG, "Startapp Interstitial Ad loaded");
                        }

                        @Override
                        public void onFailedToReceiveAd(@Nullable Ad ad) {
                            Log.d(TAG, "Failed to load Startapp Interstitial Ad");
                            loadBackupInterstitialAd();
                        }
                    });
                    break;

                default:
                    break;
            }
        }
    }

    private void loadBackupInterstitialAd() {
        if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
            switch (backupAdNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                    com.google.android.gms.ads.interstitial.InterstitialAd.load(activity, adMobInterstitialId, Tools.getAdRequest(activity, legacyGDPR), new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                            adMobInterstitialAd = interstitialAd;
                            adMobInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    loadInterstitialAd();
                                    if (withListener) {
                                        onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                    }
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                    Log.d(TAG, "The ad failed to show.");
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    adMobInterstitialAd = null;
                                    Log.d(TAG, "The ad was shown.");
                                }
                            });
                            Log.i(TAG, "onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            Log.i(TAG, loadAdError.getMessage());
                            adMobInterstitialAd = null;
                            Log.d(TAG, "Failed load AdMob Interstitial Ad");
                        }
                    });
                    break;

                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    AdManagerInterstitialAd.load(activity, googleAdManagerInterstitialId, Tools.getGoogleAdManagerRequest(), new AdManagerInterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                            super.onAdLoaded(adManagerInterstitialAd);
                            adManagerInterstitialAd = interstitialAd;
                            adManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent();
                                    loadInterstitialAd();
                                    if (withListener) {
                                        onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                    }
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                    super.onAdFailedToShowFullScreenContent(adError);
                                }

                                @Override
                                public void onAdImpression() {
                                    super.onAdImpression();
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    super.onAdShowedFullScreenContent();
                                    adManagerInterstitialAd = null;
                                    Log.d(TAG, "The ad was shown.");
                                }
                            });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            adManagerInterstitialAd = null;
                            Log.d(TAG, "Failed load Ad Manager Interstitial Ad");
                        }
                    });
                    break;

                case STARTAPP:
                    startAppAd = new StartAppAd(activity);
                    startAppAd.loadAd(new AdEventListener() {
                        @Override
                        public void onReceiveAd(@NonNull Ad ad) {
                            Log.d(TAG, "Startapp Interstitial Ad loaded");
                        }

                        @Override
                        public void onFailedToReceiveAd(@Nullable Ad ad) {
                            Log.d(TAG, "Failed to load Startapp Interstitial Ad");
                        }
                    });
                    Log.d(TAG, "load StartApp as backup Ad");
                    break;

                case NONE:
                    //do nothing
                    break;
            }
        }
    }

    private void showInterstitialAd() {
        if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
            if (counter == interval) {
                switch (adNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                        if (adMobInterstitialAd != null) {
                            adMobInterstitialAd.show(activity);
                            Log.d(TAG, "admob interstitial not null");
                        } else {
                            showBackupInterstitialAd();
                            Log.d(TAG, "admob interstitial null");
                        }
                        break;

                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        if (adManagerInterstitialAd != null) {
                            adManagerInterstitialAd.show(activity);
                            Log.d(TAG, "ad manager interstitial not null");
                        } else {
                            showBackupInterstitialAd();
                            Log.d(TAG, "ad manager interstitial null");
                        }
                        break;

                    case STARTAPP:
                        if (startAppAd != null) {
                            //startAppAd.showAd();
                            startAppAd.showAd(new AdDisplayListener() {
                                @Override
                                public void adHidden(Ad ad) {
                                    loadInterstitialAd();
                                    if (withListener) {
                                        onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                    }
                                }

                                @Override
                                public void adDisplayed(Ad ad) {

                                }

                                @Override
                                public void adClicked(Ad ad) {

                                }

                                @Override
                                public void adNotDisplayed(Ad ad) {
                                    showBackupInterstitialAd();
                                }
                            });
                            Log.d(TAG, "startapp interstitial not null [counter] : " + counter);
                        } else {
                            showBackupInterstitialAd();
                            Log.d(TAG, "startapp interstitial null");
                        }
                        break;

                    default:
                        if (withListener) {
                            onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                        }
                        break;
                }
                counter = 1;
            } else {
                counter++;
            }
            Log.d(TAG, "Current counter : " + counter);
        }
    }

    private void showBackupInterstitialAd() {
        if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
            Log.d(TAG, "Show Backup Interstitial Ad [" + backupAdNetwork.toUpperCase() + "]");
            switch (backupAdNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                    if (adMobInterstitialAd != null) {
                        adMobInterstitialAd.show(activity);
                    } else {
                        if (withListener) {
                            onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                        }
                    }
                    break;

                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    if (adManagerInterstitialAd != null) {
                        adManagerInterstitialAd.show(activity);
                    } else {
                        if (withListener) {
                            onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                        }
                    }
                    break;

                case STARTAPP:
                    if (startAppAd != null) {
                        //startAppAd.showAd();
                        startAppAd.showAd(new AdDisplayListener() {
                            @Override
                            public void adHidden(Ad ad) {
                                loadBackupInterstitialAd();
                                if (withListener) {
                                    onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                }
                            }

                            @Override
                            public void adDisplayed(Ad ad) {

                            }

                            @Override
                            public void adClicked(Ad ad) {

                            }

                            @Override
                            public void adNotDisplayed(Ad ad) {
                                if (withListener) {
                                    onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                }
                            }
                        });
                    } else {
                        if (withListener) {
                            onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                        }
                    }
                    break;

                case NONE:
                    if (withListener) {
                        onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                    }
                    break;
            }
        }
    }

}
