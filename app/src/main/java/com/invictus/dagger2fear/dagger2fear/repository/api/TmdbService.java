package com.invictus.dagger2fear.dagger2fear.repository.api;

import android.arch.lifecycle.LiveData;

import com.invictus.dagger2fear.dagger2fear.db.entity.Movie;
import com.invictus.dagger2fear.dagger2fear.repository.model.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by invictus on 4/29/18.
 */

public interface TmdbService {

    @GET("movie/top_rated?")
    LiveData<ApiResponse<MovieResult>> getTopRatedMovies();

    @GET("movie/popular?")
    LiveData<ApiResponse<MovieResult>> getPopularMovies();

    @GET("movie/latest")
    LiveData<ApiResponse<MovieResult>> getLatestMovies();

    @GET("discover/movie?sort_by=popularity.desc")
    LiveData<ApiResponse<MovieResult>> discoverPopularMovies();

    @GET("search/movie?")
    LiveData<ApiResponse<MovieResult>> searchMovies(@Query("query") String query);

    @GET("movie/{movie_id}")
    LiveData<ApiResponse<Movie>> getMovieById(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/similar")
    LiveData<ApiResponse<Movie>> getSimilarMovies(@Path("movie_id") int movieId);

    @GET("genre/movie/list")
    LiveData<ApiResponse<GenreResponse>> getGenres();

    @GET("movie/{movie_id}/videos")
    LiveData<ApiResponse<VideoResult>> getMovieVideos(@Path("movie_id") int movieId);
}