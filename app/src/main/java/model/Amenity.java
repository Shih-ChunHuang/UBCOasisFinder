package model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by csam on 2017-05-21.
 */

public enum Amenity {
    MICROWAVE("Microwave", BitmapDescriptorFactory.HUE_VIOLET),
    VENDINGMACHINE("Vending Machine", BitmapDescriptorFactory.HUE_GREEN),
    WATERFOUNTAIN("Water Fountain", BitmapDescriptorFactory.HUE_AZURE);

    private String displayName;
    private float colour;

    Amenity(String displayName, float colour) {
        this.displayName = displayName;
        this.colour = colour;
    }

    public String getDisplayName() {
        return displayName;
    }

    public float getColour() {
        return colour;
    }
}
