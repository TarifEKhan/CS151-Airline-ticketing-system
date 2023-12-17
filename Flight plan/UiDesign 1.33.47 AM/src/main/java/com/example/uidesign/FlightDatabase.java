package com.example.uidesign;

import java.util.ArrayList;
import java.util.List;

public class FlightDatabase {
    private static List<Flight> flights = new ArrayList<>();

    // Initialize sample flights
    static {
        flights.add(new Flight("Lufthansa", "Boeing 777-ER", "New York", "London", "2023-12-01", "2023-12-10", 1200.0, "4 hours"));
        flights.add(new Flight("Emirates", "Airbus A380", "Dubai", "New York", "2023-12-05", "2023-12-15", 1500.0, "2 hours"));
        flights.add(new Flight("British Airways", "Boeing 747", "London", "Tokyo", "2023-12-08", "2023-12-18", 1800.0, "7 hours"));
        flights.add(new Flight("Emirates", "Airbus A380", "London", "Tokyo", "2023-12-08", "2023-12-18", 1500.0, "Overnight"));
        flights.add(new Flight("Lufthansa", "Boeing 747", "London", "Tokyo", "2023-12-08", "2023-12-18", 1800.0, "2 hours"));
        flights.add(new Flight("Lufthansa", "Boeing 747", "London", "Tokyo", "2023-12-08", "2023-12-18", 1800.0, "None"));
    }

    public static List<Flight> getFlights() {
        return flights;
    }

    public static List<Flight> searchFlights(String departure, String destination, String startDate, String endDate) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDeparture().equalsIgnoreCase(departure) &&
                    flight.getDestination().equalsIgnoreCase(destination) &&
                    flight.getStartDate().equals(startDate) &&
                    flight.getEndDate().equals(endDate)) {
                result.add(flight);
            }
        }
        return result;
    }
}

class Flight {
    private String airline;
    private String planeType;
    private String departure;
    private String destination;
    private String startDate;
    private String endDate;
    private double price;
    private String layover;

    public Flight(String airline, String planeType, String departure, String destination, String startDate, String endDate, double price, String layover) {
        this.airline = airline;
        this.planeType = planeType;
        this.departure = departure;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.layover = layover;
    }
    public String getLayover(){
        return layover;
    }

    public double getPrice(){
        return price;
    }
    public String getAirline() {
        return airline;
    }

    public String getPlaneType() {
        return planeType;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}

