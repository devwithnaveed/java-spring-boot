package fr.epita.quiz.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Config config;

    Properties prop = new Properties();

    public Config() throws IOException {
        prop.load(new FileReader("conf.properties"));
    }

    public static String resolveConf(String s) {
        if (config == null) {
            try {
                config = new Config();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return config.prop.getProperty(s);
    }

}
