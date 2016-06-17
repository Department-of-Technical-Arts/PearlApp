package set;

/**
 * Created by admin on 06-03-2016.
 */
public class ScheduleSet {
    String name,round,venue;
    long time;
    int event_id;

    public ScheduleSet(String name, String round, String venue, long time, int event_id) {
        this.name = name;
        this.round = round;
        this.venue = venue;
        this.time = time;
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public String getRound() {
        return round;
    }

    public String getVenue() {
        return venue;
    }

    public long getTime() {
        return time;
    }

    public int getEvent_id() {
        return event_id;
    }
}
