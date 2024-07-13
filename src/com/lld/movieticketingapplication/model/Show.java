package com.lld.movieticketingapplication.model;


import java.time.*;

public class Show {
    private final String Id;
    private final Movie movie;
    private final Duration startTime;
    private final Duration endTime;
    public static class Builder {
        private String Id;
        private Movie movie;
        private Duration startTime;
        private Duration endTime;
        public Builder Id(final String Id) {
            this.Id = Id;
            return this;
        }
        public Builder movie(final Movie movie) {
            this.movie = movie;
            return this;
        }
        public Builder startTime(final Duration startTime) {
            this.startTime = startTime;
            return this;
        }
        public Builder endTime() {
            if(this.movie == null) throw new IllegalStateException("Movie not set");
            if(this.startTime == null) throw new IllegalStateException("Start time is not set");
            this.endTime = this.startTime.plus(movie.getDuration());
            return this;
        }
        public Show build() {
            return new Show(this);
        }
    }
    private Show(Builder builder) {
        this.Id = builder.Id;
        this.movie = builder.movie;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }
    public boolean conflicts(Show show) {
        Show first = this.startTime.toSeconds() < show.startTime.toSeconds() ? this : show;
        Show second = first.equals(show) ? this : show;
        return first.endTime.toSeconds() > second.startTime.toSeconds();
    }
}
