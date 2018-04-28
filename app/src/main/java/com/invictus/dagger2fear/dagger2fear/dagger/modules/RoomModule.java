package com.invictus.dagger2fear.dagger2fear.dagger.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.invictus.dagger2fear.dagger2fear.db.Dagger2FearDb;
import com.invictus.dagger2fear.dagger2fear.db.dao.GenreDao;
import com.invictus.dagger2fear.dagger2fear.db.dao.MovieDao;
import com.invictus.dagger2fear.dagger2fear.db.dao.VideoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class RoomModule {

    @Singleton
    @Provides
    Dagger2FearDb providesRoomDatabase(Application app) {
        return Room.databaseBuilder(app, Dagger2FearDb.class, "tmdb_db").build();
    }

    @Singleton
    @Provides
    MovieDao provideMovieDao(Dagger2FearDb db) {
        return db.movieDao();
    }

    @Singleton
    @Provides
    GenreDao provideGenreDao(Dagger2FearDb db) {
        return db.genreDao();
    }


    @Singleton
    @Provides
    VideoDao VideoDao(Dagger2FearDb db) {
        return db.videoDao();
    }
}