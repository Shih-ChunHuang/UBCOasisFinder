package parsers;

import android.content.res.AssetManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Shih-Chun on 2017-06-05.
 */

public class FileDataProvider extends AbstractFileDataProvider{

    private String fileName;

    public FileDataProvider(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String dataSourceToString() throws IOException {
        InputStream is = new FileInputStream(fileName);
        return readSource(is);
    }


}
