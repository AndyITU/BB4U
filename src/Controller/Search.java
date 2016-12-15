package Controller;

import Model.Show;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * Small, static controller for searching for shows.
 * Only methods here are getters that connect to the database via Database-controller.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
class Search {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd. MMMM - yyyy HH:mm", new Locale("da", "DK"));

    /**
     * Retrieves all movie titles from shows in the database.
     *
     * @return An array of all movie titles
     */
    static String[] getMovies() {
        List<String> uniqueMovies = new ArrayList<>();
        uniqueMovies.add("");

        for (Show s : Database.getShows(0)) {

                uniqueMovies.add(s.getMovie());
        }
        return uniqueMovies.toArray(new String[0]);
    }

    /**
     * Retrieves all dates that the selected movie title is playing from shows in the database.
     *
     * @param movie The selected movie title
     * @return An array of all dates that the movie title is playing
     */
    static String[] getDates(String movie) {
        List<String> uniqueDates = new ArrayList<>();
        uniqueDates.add("");

        for (Show s: Database.getShows(0)) {
            if ((movie.equals("") || movie.equals(s.getMovie())))
                uniqueDates.add(s.getDate().format(format));
        }
        return uniqueDates.toArray(new String[0]);
    }

    /**
     * Retrieves all auditoriums in which the selected movie title is playing on the selected date.
     *
     * @param movie The selected movie title
     * @param date The selected date
     * @return An array of all auditoriums where the movie title is playing on the selected date.
     */
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
