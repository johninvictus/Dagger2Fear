package com.invictus.dagger2fear.dagger2fear.dagger.modules.fragments_module;

import com.invictus.dagger2fear.dagger2fear.view.ui.fragments.MovieDetailFragment;
import com.invictus.dagger2fear.dagger2fear.view.ui.fragments.MovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by invictus on 4/28/18.
 */
@Module
public abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract MovieListFragment contributeMovieListFragment();

    @ContributesAndroidInjector
    abstract MovieDetailFragment contributeMovieDetailFragment();
}
