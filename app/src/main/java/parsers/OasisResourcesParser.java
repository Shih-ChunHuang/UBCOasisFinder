package parsers;

/**
 * Created by Shih-Chun on 2017-06-03.
 */


import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.StringReader;

import model.Amenity;
import model.Oasis;
import model.OasisManager;


/**
 * A parser for the data returned by oasisdata.json
 */
public class OasisResourcesParser {

    private String filename;

    public OasisResourcesParser(String filename) {
        this.filename = filename;
    }


    public void parse() throws IOException, JSONException {
        DataProvider dataProvider = new FileDataProvider(filename);
        parseOasis(dataProvider.dataSourceToString());
    }


    public static void parseOasis(String jsonResponse) throws JSONException{

        OasisManager oasisManager = OasisManager.getInstance();

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray waterFountain = jsonObject.getJSONArray(Amenity.WATERFOUNTAIN.getDisplayName());
        JSONArray microwave = jsonObject.getJSONArray(Amenity.MICROWAVE.getDisplayName());
        JSONArray vendingMachine = jsonObject.getJSONArray(Amenity.VENDINGMACHINE.getDisplayName());

        for (int i=0; i < waterFountain.length(); i++ ){

            JSONObject oneWaterFountain = waterFountain.getJSONObject(i);
            double waterFountainLat = oneWaterFountain.getDouble("Lat");
            double waterFountainLon = oneWaterFountain.getDouble("Lon");
            String waterFountainBuilding = oneWaterFountain.getString("Building");
            String waterFountainFloor = oneWaterFountain.getString("Floor");
            String waterFountainNearby = oneWaterFountain.getString("NearBy");


            Oasis newOasis = new Oasis(Amenity.WATERFOUNTAIN, waterFountainLat, waterFountainLon);

            oasisManager.addOasises(newOasis);


        }









    }

}
