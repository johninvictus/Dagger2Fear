package com.invictus.dagger2fear.dagger2fear.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.invictus.dagger2fear.dagger2fear.db.entity.Genre;

import java.util.List;

/**
 * Created by invictus on 4/29/18.
 */

@Dao
public abstract class GenreDao {

    @Query("SELECT * FROM Genre")
    public abstract LiveData<List<Genre>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Genre... genres);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertGenres(List<Genre> genreList);

    @Query("DELETE FROM Genre")
    public abstract void deleteAll();

    @Query("SELECT * FROM Genre where id = :id")
    public abstract LiveData<Genre> searchGenresById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long createGenreIfNotExists(Genre genre);
}