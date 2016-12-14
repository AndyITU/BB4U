package Controller;

import Model.Show;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * Write a description of class BookingFrame here.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
class Search {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));

    static String[] getMovies() {
        List<String> uniqueMovies = new ArrayList<>();
        uniqueMovies.add("");

        for (Show s : Database.getShows(0)) {

                uniqueMovies.add(s.getMovie());
        }
        return uniqueMovies.toArray(new String[0]);
    }

    // Dates
    static String[] getDates(String movie) {
        List<String> uniqueDates = new ArrayList<>();
        uniqueDates.add("");

        for (Show s: Database.getShows(0)) {
            if ((movie.equals("") || movie.equals(s.getMovie())))
                uniqueDates.add(s.getDate().format(format));
        }
        return uniqueDates.toArray(new String[0]);
    }

    // Auditoriums
    static String[] getAuditoriums(String movie, String date) {
        List<String> uniqueAuds = new ArrayList<>();
        uniqueAuds.add("");

        for (Show s: Database.getShows(0)) {
            if ((movie.equals("") || movie.equals(s.getMovie())) && (date.equals("") || date.equals(s.getDate().format(format))))
                uniqueAuds.add(Integer.toString(s.getAud_id()));
        }
        return uniqueAuds.toArray(new String[0]);
    }

}
