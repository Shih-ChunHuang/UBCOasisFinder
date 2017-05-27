package Model;

import java.util.List;

/**
 * Created by csam on 2017-05-18.
 */

public abstract class Oasis {

    private Amenity amenity;
    private double lat;
    private double lon;
    private List<String> info;

    public Oasis(Amenity type, double lat, double lon) {
        this.amenity = type;
        this.lat = lat;
        this.lon = lon;
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

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }
}
