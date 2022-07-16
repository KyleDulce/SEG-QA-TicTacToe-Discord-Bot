package me.segQaGroupTen.discordBot.util;

import me.segQaGroupTen.discordBot.Bot;
import me.segQaGroupTen.discordBot.config.ConfigurationManager;
import org.javacord.api.event.message.MessageCreateEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BotTests {

    @BeforeAll
    public static void setup(){
        ConfigurationManager.loadYamlConfiguration();
    }

    @Test
    public void test1(){
        Bot bot = new Bot();
        assertEquals(bot.gameBoard.length, 3);
    }

    @Test
    public void test2(){
        Bot bot = new Bot();
        MessageCreateEvent mockMessageCreateEvent = mock(MessageCreateEvent.class);
        when(mockMessageCreateEvent.getMessageContent()).thenReturn("/tictactoe");
        bot.onMessageCreate(mockMessageCreateEvent);
        verify(mockMessageCreateEvent).getMessageContent();
    }

}