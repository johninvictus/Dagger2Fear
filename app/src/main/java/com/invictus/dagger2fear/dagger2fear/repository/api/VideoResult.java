package com.invictus.dagger2fear.dagger2fear.repository.api;


import com.invictus.dagger2fear.dagger2fear.db.entity.TmdbVideo;

import java.util.List;

public class VideoResult {
    private String id;
    private List<TmdbVideo> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TmdbVideo> getResults() {
        return results;
    }

    public void setResults(List<TmdbVideo> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", results = " + results + "]";
    }
}