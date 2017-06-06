package parsers;

/**
 * Created by Shih-Chun on 2017-06-03.
 */


import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Set;

import model.Amenity;
import model.Oasis;

/**
 * A parser for the data returned by oasisdata.json
 */
public class OasisResourcesParser {


    public static Set<Oasis> parseOasis(Amenity amenity, String jsonResponse) throws JSONException{

        try{

            JSONObject json = (JSONObject) new JSONTokener(jsonResponse).nextValue();



        }catch (JSONException e){

            e.printStackTrace();

        }


        return null;
    }

}
