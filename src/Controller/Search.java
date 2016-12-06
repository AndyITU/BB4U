package Controller;

import Model.Show;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Search {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));
    // Movies
    static String[] getMovies(String aud_id, String date) {
        List<String> uniqueMovies = new ArrayList<>();
        uniqueMovies.add("");

        for (Show s : Database.getShows(0)) {
            if ((aud_id.equals("") || aud_id.equals(Integer.toString(s.getAud_id()))) && (date.equals("") || date.equals(s.getDate().format(format))))
                uniqueMovies.add(s.getMovie());
        }
        return uniqueMovies.toArray(new String[0]);
    }
    static String[] getMoviesByAuditorium(String aud_id) {
        return getMovies(aud_id, "");
    }
    static String[] getMoviesByDate(String date) {
        return getMovies("", date);
    }
    static String[] getMovies() {
        return getMoviesByAuditorium("");
    }

    // Dates
    static String[] getDates(String movie, String aud_id) {
        List<String> uniqueDates = new ArrayList<>();
        uniqueDates.add("");

        for (Show s: Database.getShows(0)) {
            if ((movie.equals("") || movie.equals(s.getMovie())) && (aud_id.equals("") || aud_id.equals(Integer.toString(s.getAud_id()))))
                uniqueDates.add(s.getDate().format(format));
        }
        return uniqueDates.toArray(new String[0]);
    }
    static String[] getDatesByMovie(String movie) {
        return getDates(movie, "");
    }
    static String[] getDatesByAuditorium(String aud_id) {
        return getDates("", aud_id);
    }
    static String[] getDates() {
        return getDatesByMovie("");
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
    static String[] getAuditoriumsByMovie(String movie) {
        return getAuditoriums(movie, "");
    }
    static String[] getAuditoriumsByDate(String date) {
        return getAuditoriums("", date);
    }
    static String[] getAuditoriums() {
        return getAuditoriumsByMovie("");
    }

}
