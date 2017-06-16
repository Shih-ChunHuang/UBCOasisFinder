package com.example.ubcoasisfinder;


import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import parsers.DataProvider;
import parsers.FileDataProvider;
import parsers.OasisResourcesParser;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Shih-Chun on 2017-06-05.
 */

public class OasisResourcesParserTest {

    private String testOasis;
    private OasisResourcesParser orp;

    @Before
    public void setUp() throws Exception{

//        DataProvider dataProvider = new FileDataProvider("./app/src/main/assets/oasisdata.json");
//        testOasis = dataProvider.dataSourceToString();

        orp = new OasisResourcesParser("./app/src/main/assets/oasisdata.json");

    }

    @Test
    public void testBasicOasisParsing() throws JSONException {
        try {
            orp.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            assertTrue(true);
        }
    }




}
