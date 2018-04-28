package com.invictus.dagger2fear.dagger2fear.dagger.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.invictus.dagger2fear.dagger2fear.dagger.qualifires.ViewModelKey;
import com.invictus.dagger2fear.dagger2fear.viewmodel.MovieListViewModel;
import com.invictus.dagger2fear.dagger2fear.viewmodel.ProjectViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    abstract ViewModel bindMovieListViewModel(MovieListViewModel movieListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ProjectViewModelFactory projectViewModelFactory);
}