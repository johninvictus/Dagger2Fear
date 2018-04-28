package com.invictus.dagger2fear.dagger2fear.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.invictus.dagger2fear.dagger2fear.db.entity.TmdbVideo;

import java.util.List;

/**
 * Created by invictus on 4/29/18.
 */

@Dao
public abstract class VideoDao {

    @Query("SELECT * FROM TmdbVideo")
    public abstract LiveData<List<TmdbVideo>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(TmdbVideo... tmdbVideos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertVideo(List<TmdbVideo> userList);

    @Query("SELECT * FROM TmdbVideo where movieId = :movieId")
    public abstract LiveData<List<TmdbVideo>> searchVideodByMovieId(int movieId);

    @Query("DELETE FROM TmdbVideo")
    public abstract void deleteAll();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long createVideoIfNotExists(TmdbVideo tmdbVideo);
}
