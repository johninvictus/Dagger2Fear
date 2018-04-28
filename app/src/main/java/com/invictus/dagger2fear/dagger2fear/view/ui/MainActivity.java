package com.invictus.dagger2fear.dagger2fear.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.invictus.dagger2fear.dagger2fear.R;
import com.invictus.dagger2fear.dagger2fear.view.ui.common.NavigationController;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            navigationController.navigateToMovieListFragment();
        }

        supportPostponeEnterTransition();
    }
}
