package com.filmes.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Douglas on 04/01/2017.
 */

public class Filmes implements Serializable {

    private Long id;
    private String title;
    private String year;
    private String poster;
    private String imdbID;

    public Filmes(String title, String year, String poster) {
        this.title = title;
        this.year = year;
        this.poster = poster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
