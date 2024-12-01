package com.lld.movieticketingapplication.place;

import java.time.Duration;

public class App {
    private Theatre createTheatre() {
        Show.Builder showBuilder = new Show.Builder();
        Show show = showBuilder
                .Id("SHW-1")
                .movie(new Movie("MOVIE-1", Duration.ofHours(2)))
                .startTime(Duration.ofHours(10))
                .endTime()
                .addSeat(Seat.createSeat("A-1"))
                .addSeat(Seat.createSeat("A-2"))
                .addSeat(Seat.createSeat("A-3"))
                .build();

        Screen.Builder screenBuilder = new Screen.Builder();
        Screen screen = screenBuilder
                .Id("SC-1")
                .addShow(show)
                .build();

        Theatre.Builder theatreBuilder = new Theatre.Builder();
        return theatreBuilder
                .Id("TH-1")
                .addScreen(screen)
                .build();
    }
    public Theatre INSTANCE = createTheatre();
}
