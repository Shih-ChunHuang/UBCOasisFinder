package parsers;

/**
 * Created by Shih-Chun on 2017-06-03.
 */



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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



        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray waterFountain = jsonObject.getJSONArray(Amenity.WATERFOUNTAIN.getDisplayName());
        JSONArray microwave = jsonObject.getJSONArray(Amenity.MICROWAVE.getDisplayName());
        JSONArray vendingMachine = jsonObject.getJSONArray(Amenity.VENDINGMACHINE.getDisplayName());

        parseOneOasis(Amenity.WATERFOUNTAIN ,waterFountain);
        parseOneOasis(Amenity.MICROWAVE, microwave);
        parseOneOasis(Amenity.VENDINGMACHINE, vendingMachine);


    }


    public static void parseOneOasis(Amenity amenity,JSONArray oasisArray) throws JSONException {

        OasisManager oasisManager = OasisManager.getInstance();

        for (int i=0; i < oasisArray.length(); i++ ){

            JSONObject oneOasisArray = oasisArray.getJSONObject(i);
            double oasisLat = oneOasisArray.getDouble("Lat");
            double oasisLon = oneOasisArray.getDouble("Lon");
            String oasisBuilding = oneOasisArray.getString("Building");
            String oasisFloor = oneOasisArray.getString("Floor");
            String oasisNB = oneOasisArray.getString("NearBy");
            String oasisID = oneOasisArray.getString("ID");

            Oasis newOasis = new Oasis(amenity, oasisLat, oasisLon);
            Map<String, String> temp = null;

            if (newOasis.getAmenity().getDisplayName().equals(Amenity.VENDINGMACHINE)){

                String oneVendingMachineCount = oneOasisArray.getString("Count");
                String oneVendingMachineType= oneOasisArray.getString("Type");

                temp.put("Count", oneVendingMachineCount);
                temp.put("Type", oneVendingMachineType);

            }

            if(newOasis.getAmenity().getDisplayName().equals(Amenity.MICROWAVE)){

                String oneMicrowaveCount = oneOasisArray.getString("Count");
                String oneMicrowaveRoomNum = oneOasisArray.getString("RoomNumber");
                String oneMicrowaveGA = oneOasisArray.getString("GeneralAccess");

                temp.put("Count", oneMicrowaveCount);
                temp.put("RoomNumber", oneMicrowaveRoomNum);
                temp.put("GeneralAccess", oneMicrowaveGA);


            }

            temp.put("Building", oasisBuilding);
            temp.put("ID", oasisID);
            temp.put("Floor", oasisFloor);
            temp.put("NearBy", oasisNB);

            newOasis.setInfo(temp);
            oasisManager.addOasises(newOasis);

        }

    }

}
