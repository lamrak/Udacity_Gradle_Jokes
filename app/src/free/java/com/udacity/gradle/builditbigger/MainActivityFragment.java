package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import net.validcat.showjokelibrary.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class  MainActivityFragment extends Fragment {
    private InterstitialAd mInterstitialAd;
    private ProgressBar progress;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        progress = (ProgressBar) root.findViewById(R.id.progressBar);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                setupEndpointAsyncTask();
            }
        });

        AdRequest adIntRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adIntRequest);

        return root;
    }

    private void setupEndpointAsyncTask() {
        progress.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(new ICompleteListener() {
            @Override
            public void onComplete(String result) {
                progress.setVisibility(View.GONE);
                startActivity(new Intent(getContext(), JokeActivity.class)
                        .putExtra(JokeActivity.KEY_JOKE, result));
            }
        }).execute();
    }

    public void fetchJoke() {
        if (mInterstitialAd.isLoaded())
            mInterstitialAd.show();
        else setupEndpointAsyncTask();

    }
}
