package main.java.com.company;


import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;



public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private String carrier;

    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    private Long stops;
    private Long price;

    public Ticket(JSONObject ticket){
        this.origin = (String) ticket.get("origin");
        this.originName = (String) ticket.get("origin_name");
        this.destination = (String) ticket.get("destination");
        this.destinationName = (String) ticket.get("destination_name");
        this.carrier = (String) ticket.get("carrier");

        DateTimeFormatter formatter1 = DateTimeFormat.forPattern("dd.MM.yyyy");
        DateTimeFormatter formatter2 = DateTimeFormat.forPattern("HH:mm");
        DateTimeFormatter formatter3 = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");

        this.departureDate = formatter1.parseLocalDate((String) ticket.get("departure_date"));
        this.departureTime = formatter2.parseLocalTime((String) ticket.get("departure_time"));
        String departureDateTimeString = (String) ticket.get("departure_date") + " " + ticket.get("departure_time");
        this.departureDateTime = formatter3.parseLocalDateTime(departureDateTimeString);

        this.arrivalDate = formatter1.parseLocalDate((String) ticket.get("arrival_date"));
        this.arrivalTime = formatter2.parseLocalTime((String) ticket.get("arrival_time"));
        String arrivalDateTimeString = (String) ticket.get("arrival_date") + " " + ticket.get("arrival_time");
        this.arrivalDateTime = formatter3.parseLocalDateTime(arrivalDateTimeString);


        this.stops = (Long) ticket.get("stops");
        this.price = (Long) ticket.get("price");
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setStops(Long stops) {
        this.stops = stops;
    }

    public Long getPrice() {
        return price;
    }

    public Long getStops() {
        return stops;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getOrigin() {
        return origin;
    }

    public String getOriginName() {
        return originName;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }
}
