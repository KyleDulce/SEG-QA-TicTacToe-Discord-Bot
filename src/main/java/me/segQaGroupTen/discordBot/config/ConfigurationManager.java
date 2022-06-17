package me.segQaGroupTen.discordBot.config;

import me.segQaGroupTen.discordBot.Main;
import me.segQaGroupTen.discordBot.util.Logger;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;

import static me.segQaGroupTen.discordBot.util.AnnotationReaderUtil.*;

public class ConfigurationManager {
    public static final String CONFIG_FILE = "config.yml";

    public static void loadYamlConfiguration() {
        loadYamlConfiguration(CONFIG_FILE);
    }

    public static void loadYamlConfiguration(String filename) {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(filename);

        if(resource == null) {
            Logger.logErrorMessage("ConfigManager", "FETAL: file '" + filename + "' could not be found! Shutting down! Error 110");
            throw new UncheckedIOException(new IOException("Config file does not exist!"));
        }

        File file = new File(resource.getPath());

        YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(file);
        setupConfigurationClass(yamlConfig);
    }

    private static void setupConfigurationClass(YamlConfiguration yamlConfiguration) {
        Logger.logInfoMessage("ConfigManager", "Setting up Configuration Class");

        Configuration configInstance = new Configuration();
        List<Field> annotatedFields = getFieldsFromClassWithAnnotation(Value.class, Configuration.class);
        for(Field field : annotatedFields) {
            System.out.println("test");
            Value valueAnnotationInstance = field.getAnnotationsByType(Value.class)[0];
            injectValueIntoObject(field, configInstance, valueAnnotationInstance.value(), yamlConfiguration);
        }
    }

    private static void injectValueIntoObject(Field field,
                                              Configuration config,
                                              String valueString,
                                              YamlConfiguration yaml) {
        String[] valuesSplit = valueString.split(":");
        Object defaultValue = null;

        if(valuesSplit.length > 1) {
            defaultValue = parseOrCastObject(field.getType(), valuesSplit[1]);
        }

        try {
            Object valueToPut = yaml.get(valuesSplit[0], defaultValue);

            field.setAccessible(true);
            field.set(config, valueToPut);
            field.setAccessible(false);
        } catch(Exception e) {
            Logger.logErrorMessage("ConfigManager",
                    "Failed to inject value into field '" + field.getName() + "' type '" +
                    field.getType().getName() + "' " + "refering to path '" + valueString + "' Skipping!");
        }
    }

    private static Object parseOrCastObject(Class<?> type, String objToParse) throws NumberFormatException {
        try {
            if(type == Long.TYPE) {
                return Long.valueOf(objToParse);
            } else if(type == Integer.TYPE) {
                return Integer.valueOf(objToParse);
            } else if(type == Double.TYPE) {
                return Double.valueOf(objToParse);
            } else if(type == Float.TYPE) {
                return Float.valueOf(objToParse);
            } else if(type == Boolean.TYPE) {
                return Boolean.valueOf(objToParse);
            } else if(type == Byte.TYPE) {
                return Byte.valueOf(objToParse);
            } else if(type == Short.TYPE) {
                return Short.valueOf(objToParse);
            } else if(type == String.class) {
                return objToParse;
            } else {
                throw new IllegalArgumentException("Class Type is not parsable");
            }
        } catch (Exception e) {
            Logger.logErrorMessage("ConfigManager",
                    "Field of type " + type.getName() + " does not support default value parsing, ignoring");
            return null;
        }
    }
}
