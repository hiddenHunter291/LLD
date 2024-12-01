package com.lld.movieticketingapplication.resources;

import com.lld.movieticketingapplication.place.Theatre;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private final String theatreId;
    private final String screenId;
    private final String showId;
    private final List<String> seatIds;
    private static final int MAX_SEATS_PER_USER = 2;
    public static class Builder {
        public String theatreId;
        public String screenId;
        public String showId;
        public List<String> seatIds = new ArrayList<>();
        public Builder theatre(final String theatreId) {
            this.theatreId = theatreId;
            return this;
        }
        public Builder screenId(final String screenId) {
            this.screenId = screenId;
            return this;
        }
        public Builder showId(final String showId) {
            this.showId = showId;
            return this;
        }
        public Builder addSeat(final String seatId) {
            if(this.seatIds.size() == MAX_SEATS_PER_USER) {
                throw new IllegalStateException("max selectable seats limit reached");
            }
            this.seatIds.add(seatId);
            return this;
        }
        public Request build() {
            return new Request(this);
        }
    }
    private Request(Builder builder) {
        this.theatreId = builder.theatreId;
        this.screenId = builder.screenId;
        this.showId = builder.showId;
        this.seatIds = builder.seatIds;
    }

}
