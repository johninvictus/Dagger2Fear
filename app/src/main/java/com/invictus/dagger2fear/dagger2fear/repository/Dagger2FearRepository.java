package com.invictus.dagger2fear.dagger2fear.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.invictus.dagger2fear.dagger2fear.db.Dagger2FearDb;
import com.invictus.dagger2fear.dagger2fear.db.dao.GenreDao;
import com.invictus.dagger2fear.dagger2fear.db.dao.MovieDao;
import com.invictus.dagger2fear.dagger2fear.db.dao.VideoDao;
import com.invictus.dagger2fear.dagger2fear.db.entity.Genre;
import com.invictus.dagger2fear.dagger2fear.db.entity.Movie;
import com.invictus.dagger2fear.dagger2fear.db.entity.TmdbVideo;
import com.invictus.dagger2fear.dagger2fear.repository.api.GenreResponse;
import com.invictus.dagger2fear.dagger2fear.repository.api.MovieResult;
import com.invictus.dagger2fear.dagger2fear.repository.api.TmdbService;
import com.invictus.dagger2fear.dagger2fear.repository.api.VideoResult;
import com.invictus.dagger2fear.dagger2fear.repository.model.ApiResponse;
import com.invictus.dagger2fear.dagger2fear.repository.utils.AppExecutors;
import com.invictus.dagger2fear.dagger2fear.repository.utils.NetworkBoundResource;
import com.invictus.dagger2fear.dagger2fear.vo.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by invictus on 4/28/18.
 */

@Singleton
public class Dagger2FearRepository {
    private TmdbService mTmdbService;
    private Dagger2FearDb mTmdbDb;
    private MovieDao mMovieDao;
    private GenreDao mGenreDao;
    private VideoDao mVideoDao;
    private final AppExecutors mAppExecutors;

    @Inject
    Dagger2FearRepository(AppExecutors appExecutors, TmdbService tmdbService, Dagger2FearDb tmdbDb, MovieDao movieDao,
                          GenreDao genreDao, VideoDao videoDao) {
        mTmdbService = tmdbService;
        mAppExecutors = appExecutors;
        mTmdbDb = tmdbDb;
        mMovieDao = movieDao;
        mGenreDao = genreDao;
        mVideoDao = videoDao;
    }

    public LiveData<Resource<List<Movie>>> getPopularMovies() {
        return new NetworkBoundResource<List<Movie>, MovieResult>(mAppExecutors) {
            @Override
            protected void saveCallResult(@NonNull MovieResult item) {
                mMovieDao.insertMovies(item.getResults());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Movie> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return mMovieDao.findAll();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResult>> createCall() {
                return mTmdbService.discoverPopularMovies();
            }

        }.asLiveData();
    }

    public LiveData<Resource<List<Movie>>> searchMovie(String query) {
        return new NetworkBoundResource<List<Movie>, MovieResult>(mAppExecutors) {
            @Override
            protected void saveCallResult(@NonNull MovieResult item) {
                mMovieDao.insertMovies(item.getResults());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Movie> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                //Fetch from the db
                return mMovieDao.searchMovieByTitle(query);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MovieResult>> createCall() {
                return mTmdbService.searchMovies(query);
            }
        }.asLiveData();
    }

    public LiveData<Resource<Movie>> getMovieById(int movieId) {
        return new NetworkBoundResource<Movie, Movie>(mAppExecutors) {

            @Override
            protected void saveCallResult(@NonNull Movie item) {
                mMovieDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Movie data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<Movie> loadFromDb() {
                return mMovieDao.searchMovieById(movieId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Movie>> createCall() {
                return mTmdbService.getMovieById(movieId);
            }
        }.asLiveData();
    }


    public LiveData<Resource<Genre>> getGenresById(int genreId) {
        return new NetworkBoundResource<Genre, GenreResponse>(mAppExecutors) {
            @Override
            protected void saveCallResult(@NonNull GenreResponse item) {
                mGenreDao.insertGenres(item.getGenres());
            }

            @Override
            protected boolean shouldFetch(@Nullable Genre data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<Genre> loadFromDb() {

                return mGenreDao.searchGenresById(genreId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenreResponse>> createCall() {
                return mTmdbService.getGenres();
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<TmdbVideo>>> getMovieVideo(int movieId) {
        return new NetworkBoundResource<List<TmdbVideo>, VideoResult>(mAppExecutors) {
            @Override
            protected void saveCallResult(@NonNull VideoResult item) {
                List<TmdbVideo> tmdbVideoList = new ArrayList<>();
                for (TmdbVideo tmdbVideo : item.getResults()) {

                    TmdbVideo movieTmdbVideo = new TmdbVideo(tmdbVideo.id, tmdbVideo.name, tmdbVideo.type, tmdbVideo.key,
                            tmdbVideo.size, tmdbVideo.site, tmdbVideo.iso_639_1, tmdbVideo.iso_3166_1, movieId);
                    tmdbVideoList.add(movieTmdbVideo);
                }
                mVideoDao.insertVideo(tmdbVideoList);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<TmdbVideo> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<TmdbVideo>> loadFromDb() {
                return mVideoDao.searchVideodByMovieId(movieId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<VideoResult>> createCall() {
                return mTmdbService.getMovieVideos(movieId);
            }
        }.asLiveData();
    }


    public LiveData<Resource<Boolean>> searchNextPage(String query) {
        return null;
    }
}
