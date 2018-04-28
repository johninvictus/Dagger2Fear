package com.invictus.dagger2fear.dagger2fear.view.ui.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invictus.dagger2fear.dagger2fear.R;
import com.invictus.dagger2fear.dagger2fear.db.entity.Movie;
import com.invictus.dagger2fear.dagger2fear.view.ui.common.NavigationController;
import com.invictus.dagger2fear.dagger2fear.viewmodel.MovieListViewModel;
import com.invictus.dagger2fear.dagger2fear.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

import static com.invictus.dagger2fear.dagger2fear.vo.Status.ERROR;
import static com.invictus.dagger2fear.dagger2fear.vo.Status.LOADING;
import static com.invictus.dagger2fear.dagger2fear.vo.Status.SUCCESS;

/**
 * Created by invictus on 4/28/18.
 */

public class MovieListFragment extends Fragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Inject
    public NavigationController navigationController;

    private MovieListViewModel mMovieListViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_list_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMovieListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MovieListViewModel.class);



        mMovieListViewModel.getPopularMovies()
                .observe(this, this::handleResponse);
    }

    private void handleResponse(Resource<List<Movie>> listResource) {
        if (listResource != null) {
            switch (listResource.status) {
                case ERROR:
                    Timber.e(listResource.toString());
                    break;
                case LOADING:

                    break;
                case SUCCESS:
                    Timber.e("Success all the way");
                    break;
                default:
                    break;
            }
        }
    }
}
