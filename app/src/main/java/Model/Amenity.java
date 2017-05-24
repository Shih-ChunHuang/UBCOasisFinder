package Model;

/**
 * Created by csam on 2017-05-21.
 */

public enum Amenity {
    MICROWAVE("Microwave"),
    VENDINGMACHINE("Vending Machine"),
    WATERFOUNTAIN("Water Fountain");

    private String displayName;

    Amenity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
