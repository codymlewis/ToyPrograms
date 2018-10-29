import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
/**
 * assign1.java - COMP2230A1
 * A railway trip optimisation program
 *
 * @author Cody Lewis: c3283349
 * @since 2018-09-29
 */
class assign1 {
    /**
     * The main thread for the program
     * @param args The command line arguments issued
     */
    public static void main(String args[]) {
        if(args.length < 3) {
            System.out.println("You must specify a file, station to go from," +
                    " then a station to as an argument");
            System.exit(-1);
        }
        String filename = args[0];
        String start = args[1];
        String end = args[2];
        assign1 a1 = new assign1();
        System.exit(a1.run(filename, start, end));
    }
    /**
     * Non static driver method
     * @param filename An xml file containing the details of the Railway
     * @param start The name of the station to start from
     * @param end The name of the station to end at
     * @return int representing the end status
     */
	private int run(String filename, String start, String end) {
	    try {
            if(!start.equals(end)) { // check that the start station is not the same as end
                Railway railway = createRailway(filename);
                System.out.println(railway.findOptimalRoute(start, end));
            } else {
                System.out.println("There is nowhere to travel.");
            }
            return 0;
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
	}
	/**
	 * Read an xml file and generate the correspond Railway object based on it
	 * @param filename An xml containing the details of a Railway
	 * @return The Railway object corresponding to the file input
	 */
    private Railway createRailway(String filename) throws FileNotFoundException, ParserConfigurationException,
            SAXException, IOException {
        Map<String, Station> stations = new HashMap<>();
        File file = new File(filename);
        DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFact.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize(); // take in and make sure the xml input is clean
        NodeList stationNodes = doc.getElementsByTagName("Station"); // Look at all the stations
        for(int i = 0; i < stationNodes.getLength(); ++i) { // increment across them
            Node stationNode = stationNodes.item(i);
            if(stationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element stationElement = (Element) stationNode;
                String name = stationElement.getElementsByTagName("Name").item(0).getTextContent();
                Station station;
                if(stations.containsKey(name)) { // check if the data is already there
                    station = stations.get(name);
                } else {
                    station = new Station(name);
                    stations.put(name, station);
                }
                String line = stationElement.getElementsByTagName("Line").item(0).getTextContent();
                StationLine stationLine = new StationLine(line);
                NodeList stationEdges = stationElement.getElementsByTagName("StationEdge");
                for(int j = 0; j < stationEdges.getLength(); ++j) { // iterate of the edges contained in the station
                    Node stationEdgeNode = stationEdges.item(j);
                    if(stationEdgeNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element stationEdgeElement = (Element) stationEdgeNode;
                        String edgeName = stationEdgeElement.getElementsByTagName("Name").item(0).getTextContent();
                        String edgeLine = stationEdgeElement.getElementsByTagName("Line").item(0).getTextContent();
                        int duration = Integer.parseInt(stationEdgeElement.getElementsByTagName("Duration").item(0).getTextContent());
                        Station edgeStation;
                        if(stations.containsKey(edgeName)) { // check whether the edge is already there
                            edgeStation = stations.get(edgeName);
                        } else {
                            edgeStation = new Station(edgeName);
                            stations.put(edgeName, edgeStation);
                        }
                        stationLine.add(new StationEdge(edgeStation, duration, edgeLine));
                    }
                }
                station.addStationLine(stationLine);
            }
        }
        return new Railway(stations.values()); // construct the railway based on the xml input
    }
}
