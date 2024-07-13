package com.lld.movieticketingapplication.model;

import java.time.Duration;

public class Movie {
    private final String Id;
    private final Duration duration;
    public Movie(final String Id, final Duration duration) {
        this.Id = Id;
        this.duration = duration;
    }
    public Duration getDuration() {
        return duration;
    }
    public String getId() {
        return Id;
    }
    @Override
    public String toString() {
        return "MOVIE[" + this.Id + " : " + duration.toString() + "]";
    }
}
