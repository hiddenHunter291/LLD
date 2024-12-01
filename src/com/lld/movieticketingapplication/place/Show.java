package com.lld.movieticketingapplication.place;

import com.lld.movieticketingapplication.exceptions.SeatNotFoundException;

import java.time.*;
import java.util.List;
import java.util.Optional;

public class Show {
    private final String Id;
    private final Movie movie;
    private final Duration startTime;
    private final Duration endTime;
    private final List<Seat> seats;
    public static class Builder {
        public Builder() {}
        private String Id;
        private Movie movie;
        private Duration startTime;
        private Duration endTime;
        private List<Seat> seats;
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
        public Builder addSeat(final Seat seat) {
            Optional<Seat> seat1 = seats.stream()
                    .filter(s -> s.getId().equals(seat.getId()))
                    .findFirst();
            if(seat1.isEmpty()) {
                seats.add(seat);
            }
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
        this.seats = builder.seats;
    }
    public boolean conflicts(Show show) {
        Show first = this.startTime.toSeconds() < show.startTime.toSeconds() ? this : show;
        Show second = first.equals(show) ? this : show;
        return first.endTime.toSeconds() > second.startTime.toSeconds();
    }
    public String getId() {
        return Id;
    }
    public Seat getSeatById(final String Id) throws SeatNotFoundException {
        Optional<Seat> seat = seats.stream()
                .filter(s -> s.getId().equals(Id))
                .findFirst();
        return seat.orElseThrow(SeatNotFoundException::new);
    }
}
