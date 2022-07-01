package me.segQaGroupTen.discordBot;

import me.segQaGroupTen.discordBot.config.ConfigurationManager;

public class Main {
    public static void main(String[] args) {
        ConfigurationManager.loadYamlConfiguration();
        Bot bot = new Bot();
        Bot.api.addListener(bot);
        Bot.api.addListener(new BotBoardInteraction());
    }
}
