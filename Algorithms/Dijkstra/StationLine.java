import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
/**
 * StationLine.java - Comp2230A1
 * A class for the many lines of a station
 *
 * @author Cody Lewis: c3283349
 * @since 2018-09-30
 */
public class StationLine {
    private String name;
    private Map<String, StationEdge> stationEdges;
    /**
     * Default Constructor
     */
    public StationLine() {
        name = "";
        stationEdges = new HashMap<>();
    }
    /**
     * Input constructor
     * @param name The name of this line
     */
    public StationLine(String name) {
        this();
        this.name = name;
    }
    /**
     * Get the name of this line
     * @return this line's name
     */
    public String getName() { return name; }
    /**
     * Get the stations that are on this line
     * @return Array of the stations include duration to travel to them on this line
     */
    public Collection<StationEdge> getStationEdges() { return stationEdges.values(); }
    /**
     * Add a station to this line
     * @param edgeStation The station to add to this line
     */
    public void add(StationEdge edgeStation) {
        stationEdges.put(edgeStation.getName() + edgeStation.getLine(), edgeStation);
    }
}
