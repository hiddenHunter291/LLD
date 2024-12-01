package com.lld.movieticketingapplication.place;

import com.lld.movieticketingapplication.exceptions.ScreenNotFoundException;

import java.util.List;
import java.util.Optional;

public class Screen {
    private final String Id;
    private final List<Show> shows;
    public static class Builder {
        public Builder() {}
        private String Id;
        private List<Show> shows;
        public Builder Id(final String Id) {
            this.Id = Id;
            return this;
        }
        public Builder addShow(final Show show) {
            Optional<Show> conflictingShow = shows.stream()
                    .filter(s -> s.conflicts(show))
                    .findFirst();
            if(conflictingShow.isEmpty()) {
                shows.add(show);
            }
            return this;
        }
        public Screen build() {
            return new Screen(this);
        }
    }
    private Screen(Builder builder) {
        this.Id = builder.Id;
        this.shows = builder.shows;
    }
    public Show getShowById(final String Id) throws ScreenNotFoundException {
        Optional<Show> show = shows.stream()
                .filter(s -> s.getId().equals(Id))
                .findFirst();
        return show.orElseThrow(ScreenNotFoundException::new);
    }
    public String getId() {
        return Id;
    }
}
