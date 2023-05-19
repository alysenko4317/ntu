package com.khpi.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileHelper
{
    public Properties loadProperties(final String configFile) throws IOException
    {
        Properties p = new Properties();
        try (InputStream inputStream =
                 getClass().getClassLoader()
                           .getResourceAsStream(configFile)) {
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return p;
    }
}
