package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import net.validcat.showjokelibrary.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class  MainActivityFragment extends Fragment {
    private ProgressBar progress;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progress = (ProgressBar) root.findViewById(R.id.progressBar);

        return root;
    }

    public void fetchJoke() {
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
}
