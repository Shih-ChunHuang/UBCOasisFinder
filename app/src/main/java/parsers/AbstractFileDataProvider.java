package parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Shih-Chun on 2017-06-05.
 */


/**
 * Common functionality for file data providers
 */
public abstract class AbstractFileDataProvider implements DataProvider{


    /**
     * Read source data from input stream as string
     *
     * @param is  input stream connected to source data
     * @return  source data as string
     * @throws IOException  when error occurs reading data from file
     */
    public String readSource(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        br.close();

        return sb.toString();
    }
}
