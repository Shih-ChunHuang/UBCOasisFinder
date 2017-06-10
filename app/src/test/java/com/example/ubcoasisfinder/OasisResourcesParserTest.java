package com.example.ubcoasisfinder;


import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import parsers.DataProvider;
import parsers.FileDataProvider;
import parsers.OasisResourcesParser;

/**
 * Created by Shih-Chun on 2017-06-05.
 */

public class OasisResourcesParserTest {

    private String testOasis;

    @Before
    public void setUp() throws Exception{

        DataProvider dataProvider = new FileDataProvider("./app/src/main/assets/oasisdata.json");
        testOasis = dataProvider.dataSourceToString();


    }

    @Test
    public void testBasicOasisParsing() throws JSONException {

    }


}
