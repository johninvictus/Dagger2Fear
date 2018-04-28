package com.invictus.dagger2fear.dagger2fear.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.invictus.dagger2fear.dagger2fear.db.dao.GenreDao;
import com.invictus.dagger2fear.dagger2fear.db.dao.MovieDao;
import com.invictus.dagger2fear.dagger2fear.db.dao.VideoDao;
import com.invictus.dagger2fear.dagger2fear.db.entity.Genre;
import com.invictus.dagger2fear.dagger2fear.db.entity.Movie;
import com.invictus.dagger2fear.dagger2fear.db.entity.TmdbVideo;

/**
 * Created by invictus on 4/28/18.
 */
@Database(entities = {Movie.class, Genre.class, TmdbVideo.class}, version = 1)
public abstract class Dagger2FearDb extends RoomDatabase {
    abstract public MovieDao movieDao();
    abstract public GenreDao genreDao();

    abstract public VideoDao videoDao();
}
