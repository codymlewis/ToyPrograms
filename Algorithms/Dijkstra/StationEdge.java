import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
/**
 * StationEdge.java - Comp2230A1
 * A wrapper class for a station including travel duration and line name
 *
 * @author Cody Lewis: c3283349
 * @since 2018-09-29
 */
public class StationEdge implements Comparable<StationEdge> {
    private Station station; // station composition
    private int duration; // travel duration
    private String lineName; // Name of the line this is on (There is always only 1 line that is fastest to travel to)
    private ArrayList<StationEdge> adjacents;
    /**
     * Default constructor
     */
    public StationEdge() {
        station = null;
        duration = 0;
        lineName = "";
    }
    /**
     * Input constructor with blank lineName
     * @param station Station for this to be composed of
     * @param duration Time to travel to this station
     */
    public StationEdge(Station station, int duration) {
        this.station = station;
        this.duration = duration;
    }
    /**
     * Input constructor
     * @param station Station for this to be composed of
     * @param duration Time to travel to this station
     * @param lineName The name of the line this is on
     */
    public StationEdge(Station station, int duration, String lineName) {
        this.station = station;
        this.duration = duration;
        this.lineName = lineName;
    }
    /**
     * Copy constructor copy data from specified edge but with a different duration
     * @param copy The StationEdge to copy from
     * @param duration The new duration to set to
     */
    public StationEdge(StationEdge copy, int duration) {
        this.station = copy.getStation();
        this.lineName = copy.getLine();
        this.duration = duration;
    }
    // Mutators
    /**
     * Set a new station for this to be composed of
     * @param station The new station
     */
    public void setStation(Station station) { this.station = station; }
    /**
     * Set a new time to travel to this station
     * @param duration Time to travel to this station
     */
    public void setDuration(int duration) { this.duration = duration; }
    /**
     * Set a new line for this station
     * @param lineName The name of the line this will be on
     */
    public void setLine(String lineName) { this.lineName = lineName; }
    // Queries
    /**
     * Get the Station this is composed of
     * @return The Station in this object
     */
    public Station getStation() { return station; }
    /**
     * Get the name of the station held in this
     * @return The station's name
     */
    public String getName() { return station.getName(); }
    /**
     * Get the time to travel to this Station
     * @return The time to travel to this Station
     */
    public int getDuration() { return duration; }
    /**
     * Get the name of the line this is on
     * @return The line's name
     */
    public String getLine() { return lineName; }
    /**
     * Get the StationEdges adjacent to this
     * @return A Collection of the StationEdges adjacent to this
     */
    public Collection<StationEdge> getAdjacents() {
        Collection<StationEdge> adjacents = new LinkedList<>(); // Things are only added to the end here
        for(StationEdge stationEdge : station.getStationEdges()) {
            adjacents.add(new StationEdge(stationEdge, stationEdge.getDuration() +
                        (stationEdge == null || stationEdge.getLine().equals(lineName) ? 0 : 15))); // change over calculation
        }
        return adjacents;
    }
    /**
     * Find out whether this station is the same as another station
     * @param that The station edge to compare this to
     * @return true if they are the same, otherwise false
     */
    public boolean equals(StationEdge that) { return getName().equals(that.getName()); }
    /**
     * Get a string representing this
     * @return The toString of the station this is composed of
     */
    public String toString() { return station.toString() + lineName; }
    /**
     * Compare this duration of travel of this station to another
     * @param that The StationEdge to Compare this to
     * @return -1 if this duration is smaller than that, 0  if they are the same, otherwise 1
     */
    @Override
    public int compareTo(StationEdge that) {
        return new Integer(duration).compareTo(that.getDuration());
    }
}
