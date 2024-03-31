package config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("local.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUsername() {
        return properties.getProperty("USERNAME");
    }

    public static String getPassword() {
        return properties.getProperty("PASSWORD");
    }

    public static String getUser() {
        return properties.getProperty("USER");
    }

    public static String getAccessKey() {
        return properties.getProperty("ACCESSKEY");
    }
}
