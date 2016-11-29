package Model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by andy on 11/25/16.
 */
public class Show {
    private int id, aud_id;
    private String movie;
    private Date date;
    private Time duration;

    public Show(int id, int aud_id, String movie, Date date, Time duration) {
        this.id = id;
        this.aud_id = aud_id;
        this.movie = movie;
        this.date = date;
        this.duration = duration;
    }

    public String getMovie() {
        return movie;
    }
}
