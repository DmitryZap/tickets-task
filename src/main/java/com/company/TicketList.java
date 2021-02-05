package main.java.com.company;

import org.joda.time.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketList {
    private static List<Ticket> tickets = new ArrayList<Ticket>();

        public TicketList(JSONArray ticketsList) {
        for (Object o : ticketsList) {
            JSONObject ticket = (JSONObject) o;

            tickets.add(new Ticket(ticket));
        }
    }

    public Duration getAverageTime() throws ArithmeticException {
        double sum = 0;
        double countOfElement = 0;
        for (Ticket ticket : tickets) {
            if (("VVO").equals(ticket.getOrigin()) && ("TLV").equals(ticket.getDestination())) {
                Duration dur = getLocalDuration(ticket.getDepartureDateTime(), ticket.getArrivalDateTime());

                sum += dur.getStandardMinutes();

                countOfElement++;
            }
        }

        int resultMinutes = (int) (sum / countOfElement);

        return new Duration(resultMinutes * 60000L);
    }

    public Duration getAverageTime(String originName, String destinationName) throws ArithmeticException {
        double sum = 0;
        double countOfElement = 0;
        for (Ticket ticket : tickets) {
            if ((originName).equals(ticket.getOrigin()) && (destinationName).equals(ticket.getDestinationName())) {
                Duration dur = getLocalDuration(ticket.getDepartureDateTime(), ticket.getArrivalDateTime());

                sum += dur.getStandardMinutes();

                countOfElement++;
            }
        }

        int resultMinutes = (int) (sum / countOfElement);

        return new Duration(resultMinutes * 60000L);
    }

    public Duration getTimePercentile () {
        List<Minutes> ticketsByTime = new ArrayList<Minutes>();
        for (Ticket ticket : tickets) {
            if (("VVO").equals(ticket.getOrigin()) && ("TLV").equals(ticket.getDestination())) {
                Duration dur = getLocalDuration(ticket.getDepartureDateTime(), ticket.getArrivalDateTime());

                ticketsByTime.add(dur.toStandardMinutes());
            }
        }
        Collections.sort(ticketsByTime);


        double coefficient = 0.90 * ticketsByTime.size();
        int index = (int) Math.ceil(coefficient) - 1;

        return new Duration(ticketsByTime.get(index).toStandardDuration());
    }

    public Duration getTimePercentile (String originName, String destinationName, int percentile)  {
        if (percentile < 0 || percentile > 100){
            System.out.println("The percentile should be between 0 and 100");
            System.exit(5);
        }
        List<Minutes> ticketsByTime = new ArrayList<Minutes>();
        for (Ticket ticket : tickets) {
            if (ticket.getOriginName().equals(originName) && ticket.getOriginName().equals(destinationName)) {
                Duration dur = getLocalDuration(ticket.getDepartureDateTime(), ticket.getArrivalDateTime());

                ticketsByTime.add(dur.toStandardMinutes());
            }
        }
        Collections.sort(ticketsByTime);


        double coefficient = ((double) percentile / 100) * ticketsByTime.size();
        int index = (int) Math.ceil(coefficient) - 1;

        return new Duration(ticketsByTime.get(index).toStandardDuration());
    }

    private static Duration getLocalDuration(LocalDateTime start, LocalDateTime end) {
        return new Duration(start.toDateTime(DateTimeZone.UTC), end.toDateTime(DateTimeZone.UTC));
    }
}
