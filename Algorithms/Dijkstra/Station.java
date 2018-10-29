import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * Station.java - Comp2230A1
 * A railway station class
 *
 * @author Cody Lewis: c3283349
 * @since 2018-09-29
 */
public class Station {
    private String name;
    private Map<String, StationLine> lines;
    /**
     * Default constructor
     */
    public Station() {
        name = "";
        lines = new HashMap<>();
    }
    /**
     * Name input constructor
     * @param name The name of this Station
     */
    public Station(String name) {
        this();
        this.name = name;
    }
    /**
     * Input Constructor
     * @param name The name of the is Station
     * @param line The line this Station is on
     */
    public Station(String name, StationLine line) {
        this();
        this.name = name;
        addStationLine(line);
    }
    // Mutators
    /**
     * Change the name of the station
     * @param name The name of the station
     */
    public void setName(String name) { this.name = name; }
    /**
     * Change the line of the station
     * @param line The line of the station
     */
    public void addStationLine(StationLine line) {
        lines.put(line.getName(), line);
    }
    // Queries
    /**
     * Get the name of this Station
     * @return This Station's name
     */
    public String getName() { return name; }
    /**
     * Get the lines of this Station i.e.\ get the adjacency list of the graph
     * @return This Station's lines
     */
    public Collection<StationEdge> getStationEdges() {
        Collection<StationEdge> edges = new ArrayList<>();
        for(StationLine line : lines.values() ) {
            edges.addAll(line.getStationEdges());
        }
        return edges;
    }
    /**
     * Get the names of the lines this is connected to
     * @return A collection of the names of lines this is connected to
     */
    public Collection<String> getLineNames() {
        return lines.keySet();
    }
    /**
     * Get a String representing this
     * @return The name of the this
     */
    public String toString() { return name; }
}
