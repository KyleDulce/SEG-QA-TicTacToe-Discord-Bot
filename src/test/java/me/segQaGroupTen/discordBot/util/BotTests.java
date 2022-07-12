package me.segQaGroupTen.discordBot.util;

import me.segQaGroupTen.discordBot.Bot;
import me.segQaGroupTen.discordBot.config.ConfigurationManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BotTests {

    @BeforeAll
    public static void setup(){
        ConfigurationManager.loadYamlConfiguration();
    }

    @Test
    public void testStartBot_noError(){
        Bot bot = new Bot();
        assertNotEquals(bot.gameBoard, null);
    }

}