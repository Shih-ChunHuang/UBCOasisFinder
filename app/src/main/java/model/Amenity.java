package model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by csam on 2017-05-21.
 */

public enum Amenity {
    MICROWAVE("Microwave", BitmapDescriptorFactory.HUE_VIOLET, 0),
    VENDINGMACHINE("Vending Machine", BitmapDescriptorFactory.HUE_GREEN, 1),
    WATERFOUNTAIN("Water Fountain", BitmapDescriptorFactory.HUE_AZURE, 2);

    private String displayName;
    private float colour;
    private int itemNumber;

    Amenity(String displayName, float colour, int itemNumber) {
        this.displayName = displayName;
        this.colour = colour;
        this.itemNumber = itemNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public float getColour() {
        return colour;
    }

    public int getItemNumber() { return itemNumber; }
}
