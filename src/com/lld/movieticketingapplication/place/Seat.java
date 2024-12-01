package com.lld.movieticketingapplication.place;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class Seat {
    private final String Id;
    private SeatAvailabilityStatus availabilityStatus = SeatAvailabilityStatus.AVAILABLE;
    private Seat(final String Id) {
        this.Id = Id;
    }
    public static Seat createSeat(final String Id) {
        return new Seat(Id);
    }
    public SeatAvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }
    public void setAvailabilityStatus(SeatAvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
    public String getId() {
        return Id;
    }
}

class Test {
    public static List<Seat> createSeats() {
        List<Seat> seats = new ArrayList<>();
        seats.add(Seat.createSeat("A"));
        seats.add(Seat.createSeat("B"));
        seats.add(Seat.createSeat("C"));
        seats.add(Seat.createSeat("D"));
        return seats;
    }

    public void testBooking(int totalRequests, BookingService bookingService, List<Seat> seatsToBook, CountDownLatch latch) {
        for(int request = 1; request <= totalRequests; request++) {
            new Thread(new Request(latch, bookingService, seatsToBook)).start();
        }
        latch.countDown();
    }
    public static void main(String[] args) throws InterruptedException {
        Test t = new Test();
        List<Seat> seatsToBook = Test.createSeats();
        BookingService bookingService = new BookingService();
        CountDownLatch latch1 = new CountDownLatch(1);
        t.testBooking(3, bookingService, seatsToBook, latch1);
        Thread.sleep(1000);
        CountDownLatch latch2 = new CountDownLatch(1);
        t.testBooking(2, bookingService, seatsToBook, latch2);
    }
}

class Request implements Runnable {
    CountDownLatch latch;
    BookingService bookingService;
    List<Seat> seatsToBook;
    public Request(CountDownLatch latch, BookingService bookingService, List<Seat> seatsToBook) {
        this.latch = latch;
        this.bookingService = bookingService;
        this.seatsToBook = seatsToBook;
    }
    @Override
    public void run() {
        logger.info(Thread.currentThread().getName() + " is waiting....");
        try {
            latch.await();
            List<Seat> bookedSeats = bookingService.book(seatsToBook);
            for(Seat seat : bookedSeats) {
                logger.info("Seat - " + seat.getId() + " booked by " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    Logger logger = Logger.getLogger("request");
}

class BookingService {
    public List<Seat> book(List<Seat> seatsToSelect)  {
        List<Seat> seatsSelected = new ArrayList<>();
        List<Seat> booked = new ArrayList<>();

        for(Seat seat : seatsToSelect) {
            logger.info("status of seat : " + seat.getId() + " seen by : " + Thread.currentThread().getName() + " is : " + seat.getAvailabilityStatus());
            if(seat.getAvailabilityStatus() == SeatAvailabilityStatus.AVAILABLE) {
                synchronized (seat) {
                    if(seat.getAvailabilityStatus() != SeatAvailabilityStatus.TEMPORARILY_UNAVAILABLE) {
                        seat.setAvailabilityStatus(SeatAvailabilityStatus.TEMPORARILY_UNAVAILABLE);
                        seatsSelected.add(seat);
                    }
                }
            }
        }

        for(Seat seat : seatsSelected) {
            boolean isBooked = (int) (Math.random() * 100) % 2 == 0;
            synchronized (seat) {
                if(isBooked) {
                    seat.setAvailabilityStatus(SeatAvailabilityStatus.NOT_AVAILABLE);
                    booked.add(seat);
                } else {
                    seat.setAvailabilityStatus(SeatAvailabilityStatus.AVAILABLE);
                }
            }
        }

        return booked;
    }

    Logger logger = Logger.getLogger("booking");

}