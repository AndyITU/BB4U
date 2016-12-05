package Model;

import java.time.*;

public class Show {
    private int id, aud_id;
    private String movie;
    private LocalDateTime date;
    private LocalTime duration;

    public Show(int id, int aud_id, String movie, LocalDateTime date, LocalTime duration) {
        this.id = id;
        this.aud_id = aud_id;
        this.movie = movie;
        this.date = date;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }
    public int getAud_id() {
        return aud_id;
    }
    public String getMovie() {
        return movie;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public LocalTime getDuration() {
        return duration;
    }
}
