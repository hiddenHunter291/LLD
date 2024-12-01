package com.lld.movieticketingapplication.place;

import com.lld.movieticketingapplication.exceptions.ScreenNotFoundException;

import java.util.List;
import java.util.Optional;

public class Theatre {
    public final String Id;
    private final List<Screen> screens;
    public static class Builder {
        public Builder() {}
        private String Id;
        private List<Screen> screens;
        public Builder Id(String Id) {
            this.Id = Id;
            return this;
        }
        public Builder addScreen(Screen screen) {
            Optional<Screen> screen1 = screens.stream()
                    .filter(screen2 -> screen2.getId().equals(screen.getId()))
                    .findFirst();
            if(screen1.isEmpty()) {
                screens.add(screen);
            }
            return this;
        }
        public Theatre build() {
            return new Theatre(this);
        }
    }
    private Theatre(Builder builder) {
        this.Id = builder.Id;
        this.screens = builder.screens;
    }
    public Screen getScreeById(final String Id) throws ScreenNotFoundException {
        Optional<Screen> screen = screens.stream()
                .filter(s -> s.getId().equals(Id))
                .findAny();
        return screen.orElseThrow(ScreenNotFoundException::new);
    }
}
