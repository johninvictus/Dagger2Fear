package com.invictus.dagger2fear.dagger2fear.repository.api;


import com.invictus.dagger2fear.dagger2fear.db.entity.Genre;

import java.util.List;

public class GenreResponse {

    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "ClassPojo [genres = " + genres + "]";
    }
}