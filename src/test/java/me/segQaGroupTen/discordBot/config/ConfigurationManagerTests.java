package me.segQaGroupTen.discordBot.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationManagerTests {

    @AfterEach
    public void tearDown() {
        Configuration.clearConfiguration();
    }

    @Test
    public void testLoadYamlConfiguration_noError() {
        ConfigurationManager.loadYamlConfiguration();
    }

    @Test
    public void testLoadYamlConfiguration_injection_success() {
        ConfigurationManager.loadYamlConfiguration("testConfig.yml");

        assertEquals(1, Configuration.getInstance().getConfigVersion());
        assertEquals("abc", Configuration.getInstance().getToken());
    }

    @Test
    public void testLoadYamlConfiguration_fileDoesNotExist_IOException() {
        Exception exception = assertThrows(UncheckedIOException.class, () -> {
            ConfigurationManager.loadYamlConfiguration("bad file name");
        });
        String expectedMessage = "Config file does not exist!";

        String actual = exception.getMessage();

        assertTrue(actual.contains(expectedMessage));
    }
}
