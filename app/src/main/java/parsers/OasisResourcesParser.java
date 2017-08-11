package parsers;

/**
 * Created by Shih-Chun on 2017-06-03.
 */



import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        parseOasises(dataProvider.dataSourceToString());
    }


    public static void parseOasises(String jsonResponse) throws JSONException{
        Log.i("This is what I want:", jsonResponse);
        JSONObject jsonObject = new JSONObject(jsonResponse);

        for (Amenity am : Amenity.values()) {
            JSONArray jsArray = jsonObject.getJSONArray(am.getDisplayName());
            parseOneOasis(am, jsArray);

        }
    }


    public static void parseOneOasis(Amenity amenity,JSONArray oasisArray) throws JSONException {

        OasisManager oasisManager = OasisManager.getInstance();

        for (int i=0; i < oasisArray.length(); i++ ){

            JSONObject oneOasisArray = oasisArray.getJSONObject(i);
            double oasisLat = oneOasisArray.getDouble("Lat");
            double oasisLon = oneOasisArray.getDouble("Lon");
            Oasis newOasis = new Oasis(amenity, oasisLat, oasisLon);

            Map<String, String> temp = new HashMap<>();

            Iterator<String> keys = oneOasisArray.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                if (!key.equals("Lat") && !key.equals("Lon")) {
                    String value = oneOasisArray.getString(key);
                    temp.put(key, value);
                }
            }
            newOasis.setInfo(temp);
            oasisManager.addOasises(newOasis);

        }

    }

}
