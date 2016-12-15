package Model;

import java.time.*;
/**
 * This is the show model class. It includes all necessary information about a show for the "Arrival of Bookings" software.
 *
 * @author Mikkel Kaj Andersen, Andreas Clausen, Mads Brodt.
 * @version Grundlæggende Programmering, Biograf Projekt, 2016.
 */
public class Show {
    private final int id, aud_id;
    private final String movie;
    private final LocalDateTime date;
    private final LocalTime duration;
    /**
     * The constructor gives show all parameters as private values. It checks some of these values for valid input.
     * @param id the id of the show.
     * @param aud_id the id of the auditorium connected to the show.
     * @param movie name of the movie in show.
     * @param date date of when the show is displayed.
     * @param duration how long time the show will last.
     */

    public Show(int id, int aud_id, String movie, LocalDateTime date, LocalTime duration) throws IllegalArgumentException {
        if (id >= 0) this.id = id;
        else{
            throw new IllegalArgumentException();
        }
        if (aud_id >= 0) this.aud_id = aud_id;
        else {
            throw new IllegalArgumentException();
        }
        this.movie = movie;
        this.date = date;
        this.duration = duration;
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return id that is currently in Show.
     */
    public int getId() {
        return id;
    }
    /**
     * This method returns an object of the type integer whenever it is called.
     * @return aud_id that is currently in Show.
     */
    public int getAud_id() {
        return aud_id;
    }
    /**
     * This method returns an object of the type String whenever it is called.
     * @return movie that is currently in Show.
     */
    public String getMovie() {
        return movie;
    }
    /**
     * This method returns an object of the type LocalDateTime whenever it is called.
     * @return date that is currently in Show.
     */
    public LocalDateTime getDate() {
        return date;
    }
    /**
     * This method returns an object of the type LocalTime whenever it is called.
     * @return duration that is currently in Show.
     */
    public LocalTime getDuration() {
        return duration;
    }
}
