package poi.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PoiManager {

    // The list of POI objects
    private Map<Integer, PointOfInterest> points;

    // ID number counter
    private static int lastId = 0;

    /**
     * Creates a PoiManager with initial data from the provided file.
     *
     * @param fileName path to file containing initial data
     * @throws IOException
     */
    public PoiManager(String fileName ) throws IOException {
        Scanner scan = new Scanner(new File(fileName));
        scan.useDelimiter("[\\t\\n]");  // Tab separated fields

        points = new HashMap<>();
        while( scan.hasNext() ) {
            String name = scan.next();
            String address = scan.next();
            String tags = scan.next();
            double lat = scan.nextDouble();
            double longitude = scan.nextDouble();

            this.createPoi(name, address, tags, new GeoLocation(lat, longitude));
        }
    }

    private synchronized int generateId() {
        lastId++;
        return lastId;
    }

    /**
     * Returns the PointOfInterest with the provided id number.
     * @param id the id number
     * @return the PointOfInterest if found, null otherwise
     */
    public synchronized PointOfInterest getPoi( int id ) {
        return points.get(id);
    }

    /**
     * Create a new PointOfInterest and add to the list.
     *
     * @param name name of the new PointOfInterest
     * @param address the address
     * @param tags the tag string
     * @param loc the location of the new PointOfInterest
     * @return the ID number of the new PointOfInterest
     */
    public synchronized int createPoi( String name, String address, String tags, GeoLocation loc ) {
        int id = this.generateId();
        PointOfInterest poi = new PointOfInterest(id, name, address, tags, loc);
        points.put(id, poi);
        return id;
    }

    /**
     * Delete a PointOfInterest
     *
     * @param id the id of the PointOfInterest
     * @return true if the object was removed
     */
    public synchronized boolean deletePoi( int id ) {
        return points.remove(id) != null;
    }

    // TODO: Add other methods as needed.
}
