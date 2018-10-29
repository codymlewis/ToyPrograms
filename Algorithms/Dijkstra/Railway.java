import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
/**
 * Railway.java - Comp2230A1
 * Container and operator on a collection of stationEdges
 *
 * @author Cody Lewis: c3283349
 * @since 2018-09-29
 */
public class Railway {
        private Map<String, StationEdge> stationEdges;
    private Map<String, Station> stations;
    /**
     * Default Constructor
     */
    public Railway() {
        stationEdges = new HashMap<>();
        stations = new HashMap<>();
    }
    /**
     * Input constructor
     * @param stationEdges The collection of stationEdges to add to this
     */
    public Railway(Collection<Station> stationEdges) {
        this();
        HashMap<String, StationEdge> stationEdgesMap = new HashMap<>();
        for(Station station : stationEdges) {
            stations.put(station.toString(), station);
            for(StationEdge stationEdge : station.getStationEdges()) {
                stationEdgesMap.put(stationEdge.toString(), stationEdge);
            }
        }
        addstationEdges(stationEdgesMap.values());
    }
    /**
     * Add a station to this Railway
     * @param station The station to add
     */
    public void addStation(StationEdge station) {
        String id = station.toString();
        if(!stationEdges.containsKey(id)) {
            stationEdges.put(id, station);
        }
    }
    /**
     * Add a collection of stationEdges to this
     * @param stationEdges The collection of stationEdges to add to this
     */
    public void addstationEdges(Collection<StationEdge> stationEdges) {
        for(StationEdge station : stationEdges) {
            addStation(station);
        }
    }
    /**
     * Perform Dijkstra's algorithm to find the optimal route between two stationEdges
     * @param start The name of the starting station
     * @param end The name of the ending station
     */
    public String findOptimalRoute(String start, String end) {
        if(!stations.containsKey(start) || !stations.containsKey(end)) {
            return "Station " + (!stations.containsKey(start) ? start : end) + " does not exist";
        }
        Map<String, Integer> dist = new HashMap<>();
        Map<String, StationEdge> prev = new HashMap<>();
        PriorityQueue<StationEdge> q = new PriorityQueue<>();
        for(Map.Entry<String, StationEdge> station : stationEdges.entrySet()) {
            if(station.getKey().indexOf(start) == -1) {
                dist.put(station.getKey(), Integer.MAX_VALUE); // MAX_VALUE is used like infinity in this case
                q.add(new StationEdge(station.getValue(), Integer.MAX_VALUE));
            } else {
                dist.put(station.getKey(), 0);
                q.add(new StationEdge(station.getValue(), 0));
            }
            prev.put(station.getKey(), null);
        }
        while(!q.isEmpty()) {
            StationEdge min = q.poll(); // dequeue method
            if(min == null) {
                continue;
            }
            for(StationEdge neighbour : min.getAdjacents()) {
                int alt = dist.get(min.toString()) + neighbour.getDuration();
                if(alt < dist.get(neighbour.toString())) {
                    dist.put(neighbour.toString(), alt);
                    prev.put(neighbour.toString(), min);
                    q.updatePriority(neighbour, new StationEdge(neighbour.getStation(), alt, neighbour.getLine()));
                }
            }
        }
        return outputRoute(dist, prev, start, end);
    }
    /**
     * Get the route in string format
     * @param dist The map of the distances to all points in the railway
     * @param prev The map of station previous to the specified station
     * @param start The name of the starting station
     * @param end The name of the ending station
     * @return A string stating the path to take to go from start to end
     */
    public String outputRoute(Map<String, Integer> dist, Map<String, StationEdge> prev, String start, String end) {
        try {
            String optimalRoute = "";
            String line = "";
            for(String possibleLine : stations.get(end).getLineNames()) {
                Integer currentDistance = dist.get(end + line);
                if(currentDistance == null || dist.get(end + possibleLine) < currentDistance) {
                    line = possibleLine;
                }
            }
            final String endLine = line;
            StationEdge current = prev.get(end + line);
            String station = end;
            String temp = "";
            while(!current.getName().equals(start)) {
                if(!line.equals(current.getLine())) {
                    temp = "then change to line " + line + " and continue to " + station + ";\n";
                    optimalRoute = temp + optimalRoute;
                    line = current.getLine();
                    station = current.getName();
                }
                current = prev.get(current.toString());
            }
            optimalRoute = "From " + start + ", take line " + line + " to station " + station + ";\n" + optimalRoute;
            optimalRoute = optimalRoute.substring(0, optimalRoute.length() - 2) + ".\n"; // end line by line output with full stop
            optimalRoute += "The total trip will take approximately " + dist.get(end + endLine) + " minutes.";
            return optimalRoute;
        } catch(NullPointerException npe) {
            return "No such route exists";
        }
    }
}
