package com.invictus.dagger2fear.dagger2fear.view.ui.common;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.transition.Fade;
import android.view.View;

import com.invictus.dagger2fear.dagger2fear.R;
import com.invictus.dagger2fear.dagger2fear.view.ui.MainActivity;
import com.invictus.dagger2fear.dagger2fear.view.ui.fragments.DetailsTransition;
import com.invictus.dagger2fear.dagger2fear.view.ui.fragments.MovieDetailFragment;
import com.invictus.dagger2fear.dagger2fear.view.ui.fragments.MovieListFragment;

import javax.inject.Inject;

/**
 * Created by invictus on 4/28/18.
 */

public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        containerId = R.id.container;
        fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToMovieListFragment() {
        MovieListFragment fragment = new MovieListFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss();
    }

    public void navigateToMovieDetailFragment(View sharedImageView, int movieId) {
        MovieDetailFragment fragment = MovieDetailFragment.create(movieId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.setSharedElementEnterTransition(new DetailsTransition());
            fragment.setEnterTransition(new Fade());
            fragment.setExitTransition(new Fade());
            fragment.setSharedElementReturnTransition(new DetailsTransition());
        }
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(sharedImageView, ViewCompat.getTransitionName(sharedImageView))
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
