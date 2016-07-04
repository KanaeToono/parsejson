package com.example.conga.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ConGa on 7/03/2016.
 */
public class Movie {
    @SerializedName("movie")
    private String movie;
    @SerializedName("year")
    private int year;
    @SerializedName("rating")
    private float rating;
    @SerializedName("duration")
    private String duration;
    @SerializedName("director")
    private String director;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("image")
    private String image;
    @SerializedName("story")
    private String story;
    @SerializedName("cast")
    private List<Cast>castList;
   public static  class Cast {
        private String name;

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

}
