package com.lld.movieticketingapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Screen {
    private final String Id;
    private final List<Show> shows;
    public Screen(final String Id) {
        shows = new ArrayList<>();
        this.Id = Id;
    }
    public void addShow(Show show) {
        Optional<Show> conflictingShow = shows.stream()
                .filter(s -> s.conflicts(show))
                .findAny();
        if(conflictingShow.isEmpty()) {
            shows.add(show);
        }
    }
}
