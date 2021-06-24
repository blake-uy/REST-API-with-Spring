package poi.core;

public class PointOfInterest {

    private int id;
    private String name;
    private String address;
    private String tags;
    private GeoLocation location;

    public PointOfInterest( int id, String n, String a, String t, GeoLocation loc ) {
        this.id = id;
        name = n;
        address = a;
        tags = t;
        location = loc;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getTags() { return tags; }
    public GeoLocation getLocation() { return location; }

    /**
     * Returns whether or not the provided string is a substring of the
     * name or tags of this object.
     * @param search the search string
     * @return true if there is a match
     */
    public boolean match( String search ) {
        String compare = name.toLowerCase() + tags.toLowerCase();
        return compare.contains(search.toLowerCase());
    }

    /**
     * Returns the distance from this point of interest to the provided
     * GeoLocation.
     *
     * @param spot a location
     * @return the distance in miles from this to spot
     */
    public double distanceFrom( GeoLocation spot ) {
        return spot.distanceFrom(location);
    }

    @Override
    public String toString() {
        return String.format("(%d) name = '%s' add = '%s' tags = '%s' location = '%s'", id, name, address, tags, location.toString());
    }
}
