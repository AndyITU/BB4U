package Controller;
import Model.Show;

import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Search {

    public static String[] getMovies(int aud_id, LocalDateTime date) {
        List<String> uniqueMovies = new ArrayList<>();

        for (Show s : Database.getShows(0)) {
            if (aud_id < 1 || s.getAud_id() == aud_id)
                uniqueMovies.add(s.getMovie());
        }
        return uniqueMovies.toArray(new String[0]);
    }
    public static String[] getMovies(int aud_id) {
        return getMovies(aud_id, LocalDateTime.MIN);
    }
    public static String[] getMovies() {
        return getMovies(0);
    }

}
