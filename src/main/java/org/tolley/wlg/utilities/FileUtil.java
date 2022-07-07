package org.tolley.wlg.utilities;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtil {

    /**
     * @param fileName the name of the file
     * @return a File object
     * @throws URISyntaxException
     */
    public static File getFileFromResourceDirectory(String fileName) throws URISyntaxException {
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}
