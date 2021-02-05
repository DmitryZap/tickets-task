package main.java.com.company;

import com.google.gdata.util.io.base.UnicodeReader;
import org.joda.time.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String file;

        if (args.length == 0) {
            file = "tickets.json";
        } else {
            file = args[0];
        }

        JSONParser parser = new JSONParser();

        try (Reader reader = new UnicodeReader(new FileInputStream(file), "UTF-8")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray tickets = (JSONArray) jsonObject.get("tickets");

            TicketList ticketList = new TicketList(tickets);

            try {
                Duration averageTime;
                Duration timePercentile;
                String originCity = "Владивосток";
                String destinationCity = "Тель-Авив";
                Integer percentile = 90;
                if (args.length >= 3) {
                    averageTime = ticketList.getAverageTime(args[1], args[2]);
                    originCity = args[1];
                    destinationCity = args[2];
                } else {
                    averageTime = ticketList.getAverageTime();
                }

                if (args.length == 4) {
                    timePercentile = ticketList.getTimePercentile(args[1], args[2], Integer.parseInt(args[3]));
                    percentile = Integer.parseInt(args[3]);
                } else if (args.length == 3) {
                    timePercentile = ticketList.getTimePercentile(args[1], args[2], percentile);
                } else {
                    timePercentile = ticketList.getTimePercentile();
                }

                System.out.println("Average time (" + originCity + " -> " + destinationCity + "): " + averageTime.getStandardHours() + " hours " + averageTime.getStandardMinutes() % 60 + " minutes.");
                System.out.println(percentile +" percentile ("  + originCity + " -> " + destinationCity + "): " + timePercentile.getStandardHours() + " hours " + timePercentile.getStandardMinutes() % 60 + " minutes.");
            } catch (ArithmeticException e) {
                System.out.println("There is no information about these tickets in this file.");
                System.exit(3);
            }
        } catch (ParseException e) {
            System.err.println("Couldn't get data from the file.");
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            System.exit(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
