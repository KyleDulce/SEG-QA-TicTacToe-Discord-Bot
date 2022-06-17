package me.segQaGroupTen.discordBot.config;

public class Configuration {

    //variables
    //format @Value("path.separated.by.dots:defaultValue") OR @Value("path.separated.by.dots")
    @Value("configVersion:1")
    int configVersion;

    @Value("bot.token")
    String token;

    //getters
    public String getToken() {
        return token;
    }

    public int getConfigVersion() {
        return configVersion;
    }

    private static Configuration instance;
    public static Configuration getInstance() { return instance; }
    public Configuration() {
        if(instance == null) {
            instance = this;
        } else {
            System.out.println("(Level|WARNING) Config: Config is being constructed multiple times!");
        }
    }

    //test only
    public static void clearConfiguration() {
        instance = null;
    }
}
