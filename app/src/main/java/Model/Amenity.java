package Model;

/**
 * Created by csam on 2017-05-21.
 */

public enum Amenity {
    MICROWAVE("Microwave", 0xFFDC241F),
    VENDINGMACHINE("Vending Machine", 0xFF000000),
    WATERFOUNTAIN("Water Fountain", 0xFF0019A8);

    private String displayName;
    private int colour;

    Amenity(String displayName, int colour) {
        this.displayName = displayName;
        this.colour = colour;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getColour() {
        return colour;
    }
}
