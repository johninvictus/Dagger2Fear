package com.invictus.dagger2fear.dagger2fear.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.invictus.dagger2fear.dagger2fear.db.entity.Movie;
import com.invictus.dagger2fear.dagger2fear.repository.Dagger2FearRepository;
import com.invictus.dagger2fear.dagger2fear.utils.AbsentLiveData;
import com.invictus.dagger2fear.dagger2fear.vo.Resource;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by invictus on 4/28/18.
 */

public class MovieListViewModel extends ViewModel {
    private final LiveData<Resource<List<Movie>>> moviesLiveData;
    private final MutableLiveData<String> query = new MutableLiveData<>();
    private final LiveData<Resource<List<Movie>>> searchResults;

    @Inject
    MovieListViewModel(@NonNull Dagger2FearRepository tmdbRepository) {
        moviesLiveData = tmdbRepository.getPopularMovies();
        searchResults = Transformations.switchMap(query, search -> {
            if (search == null || search.trim().length() == 0) {
                return AbsentLiveData.create();
            } else {
                return tmdbRepository.searchMovie(search);
            }
        });
    }

    public LiveData<Resource<List<Movie>>> getSearchResults() {
        return searchResults;
    }

    public void setSearchQuery(@NonNull String originalInput) {
        String input = originalInput.toLowerCase(Locale.getDefault()).trim();
        if (Objects.equals(input, query.getValue())) {
            return;
        }
        query.setValue(input);
    }

    public LiveData<Resource<List<Movie>>> getPopularMovies() {
        return moviesLiveData;
    }

    static class NextPageHandler implements Observer<Resource<Boolean>> {
        @Nullable
        private LiveData<Resource<Boolean>> nextPageLiveData;
        private final MutableLiveData<LoadMoreState> loadMoreState = new MutableLiveData<>();
        private String query;
        private final Dagger2FearRepository repository;
        @VisibleForTesting
        boolean hasMore;

        @VisibleForTesting
        NextPageHandler(Dagger2FearRepository repository) {
            this.repository = repository;
            reset();
        }

        void queryNextPage(String query) {
            if (Objects.equals(this.query, query)) {
                return;
            }
            unregister();
            this.query = query;
            nextPageLiveData = repository.searchNextPage(query);
            loadMoreState.setValue(new LoadMoreState(true, null));
            //noinspection ConstantConditions
            nextPageLiveData.observeForever(this);
        }

        @Override
        public void onChanged(@Nullable Resource<Boolean> result) {
            if (result == null) {
                reset();
            } else {
                switch (result.status) {
                    case SUCCESS:
                        hasMore = Boolean.TRUE.equals(result.data);
                        unregister();
                        loadMoreState.setValue(new LoadMoreState(false, null));
                        break;
                    case ERROR:
                        hasMore = true;
                        unregister();
                        loadMoreState.setValue(new LoadMoreState(false,
                                result.message));
                        break;
                }
            }
        }

        private void unregister() {
            if (nextPageLiveData != null) {
                nextPageLiveData.removeObserver(this);
                nextPageLiveData = null;
                if (hasMore) {
                    query = null;
                }
            }
        }

        private void reset() {
            unregister();
            hasMore = true;
            loadMoreState.setValue(new LoadMoreState(false, null));
        }

        MutableLiveData<LoadMoreState> getLoadMoreState() {
            return loadMoreState;
        }
    }

    static class LoadMoreState {
        private final boolean running;
        private final String errorMessage;
        private boolean handledError = false;

        LoadMoreState(boolean running, String errorMessage) {
            this.running = running;
            this.errorMessage = errorMessage;
        }

        boolean isRunning() {
            return running;
        }

        String getErrorMessage() {
            return errorMessage;
        }

        String getErrorMessageIfNotHandled() {
            if (handledError) {
                return null;
            }
            handledError = true;
            return errorMessage;
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.d("@onCleared called");
    }

}
