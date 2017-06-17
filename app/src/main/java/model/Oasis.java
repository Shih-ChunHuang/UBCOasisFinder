package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by csam on 2017-05-18.
 */

public class Oasis {

    private Amenity amenity;
    private double lat;
    private double lon;
    private Map<String, String> info;

    public Oasis(Amenity type, double lat, double lon) {
        this.amenity = type;
        this.lat = lat;
        this.lon = lon;
        this.info = new HashMap<>();
    }

    public Amenity getAmenity() {
        return amenity;
    }

    public void setAmenity(Amenity amenity) {
        this.amenity = amenity;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
