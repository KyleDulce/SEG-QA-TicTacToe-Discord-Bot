package me.segQaGroupTen.discordBot.util;

import me.segQaGroupTen.discordBot.*;
import me.segQaGroupTen.discordBot.config.ConfigurationManager;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.interaction.callback.InteractionImmediateResponseBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotBoardInteractionTests {

    public static BotBoardInteraction botBoardInteraction;
    public static Player player;
    public static String[][] gameBoard;
    public static Bot bot;

    @BeforeAll
    public static void setup(){
        ConfigurationManager.loadYamlConfiguration();
        botBoardInteraction = new BotBoardInteraction();
        player = new Player();
        gameBoard = new String[3][3];
        bot = new Bot();
    }

    @Test
    public void test1(){
        MessageComponentCreateEvent mockMessageComponentCreateEvent = mock(MessageComponentCreateEvent.class);
        MessageComponentInteraction mockMessageComponentInteraction = mock(MessageComponentInteraction.class);
        User mockUser = mock(User.class);
        InteractionImmediateResponseBuilder mockInteractionImmediateResponseBuilder = mock(InteractionImmediateResponseBuilder.class);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction()).thenReturn(mockMessageComponentInteraction);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getCustomId()).thenReturn("R1C1");
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getUser()).thenReturn(mockUser);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getUser().getName()).thenReturn("user1");
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().createImmediateResponder()).thenReturn(mockInteractionImmediateResponseBuilder);
        try (MockedStatic<GameLogic> mockGameLogic = Mockito.mockStatic(GameLogic.class)) {
            mockGameLogic.when(() -> GameLogic.makeMove(any(Integer.class),any(Integer.class),any(Player.class), AdditionalMatchers.aryEq(Bot.gameBoard))).thenReturn(GameLogicResponse.WIN);
            botBoardInteraction.onComponentCreate(mockMessageComponentCreateEvent);
            assertEquals(botBoardInteraction.currentresponse, "won. Good game!");
        }

    }
    @Test
    public void test2(){
        MessageComponentCreateEvent mockMessageComponentCreateEvent = mock(MessageComponentCreateEvent.class);
        MessageComponentInteraction mockMessageComponentInteraction = mock(MessageComponentInteraction.class);
        User mockUser = mock(User.class);
        InteractionImmediateResponseBuilder mockInteractionImmediateResponseBuilder = mock(InteractionImmediateResponseBuilder.class);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction()).thenReturn(mockMessageComponentInteraction);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getCustomId()).thenReturn("R1C1");
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getUser()).thenReturn(mockUser);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getUser().getName()).thenReturn("user1");
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().createImmediateResponder()).thenReturn(mockInteractionImmediateResponseBuilder);
        try (MockedStatic<GameLogic> mockGameLogic = Mockito.mockStatic(GameLogic.class)) {
            mockGameLogic.when(() -> GameLogic.makeMove(any(Integer.class),any(Integer.class),any(Player.class), AdditionalMatchers.aryEq(Bot.gameBoard))).thenReturn(GameLogicResponse.TIE);
            botBoardInteraction.onComponentCreate(mockMessageComponentCreateEvent);
            assertEquals(botBoardInteraction.currentresponse, "The game is a tie! Good game");
        }
    }

    @Test
    public void test3(){
        MessageComponentCreateEvent mockMessageComponentCreateEvent = mock(MessageComponentCreateEvent.class);
        MessageComponentInteraction mockMessageComponentInteraction = mock(MessageComponentInteraction.class);
        User mockUser = mock(User.class);
        InteractionImmediateResponseBuilder mockInteractionImmediateResponseBuilder = mock(InteractionImmediateResponseBuilder.class);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction()).thenReturn(mockMessageComponentInteraction);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getCustomId()).thenReturn("R1C1");
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getUser()).thenReturn(mockUser);
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().getUser().getName()).thenReturn("user1");
        when(mockMessageComponentCreateEvent.getMessageComponentInteraction().createImmediateResponder()).thenReturn(mockInteractionImmediateResponseBuilder);
        try (MockedStatic<GameLogic> mockGameLogic = Mockito.mockStatic(GameLogic.class)) {
            mockGameLogic.when(() -> GameLogic.makeMove(any(Integer.class),any(Integer.class),any(Player.class), AdditionalMatchers.aryEq(Bot.gameBoard))).thenReturn(GameLogicResponse.FAILURE);
            botBoardInteraction.onComponentCreate(mockMessageComponentCreateEvent);
            assertEquals(botBoardInteraction.currentresponse, "Sorry someone already played on this square");
        }
    }
    @Test
    public void test4(){
        String buttonID = "R1C1";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 1);
        assertEquals(botBoardInteraction.column, 1);
    }
    @Test
    public void test5(){
        String buttonID = "R1C2";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 1);
        assertEquals(botBoardInteraction.column, 2);
    }
    @Test
    public void test6(){
        String buttonID = "R1C3";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 1);
        assertEquals(botBoardInteraction.column, 3);
    }

    @Test
    public void test7(){
        String buttonID = "R2C1";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 2);
        assertEquals(botBoardInteraction.column, 1);
    }

    @Test
    public void test8(){
        String buttonID = "R2C2";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 2);
        assertEquals(botBoardInteraction.column, 2);
    }

    @Test
    public void test9(){
        String buttonID = "R2C3";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 2);
        assertEquals(botBoardInteraction.column, 3);
    }
    @Test
    public void test10(){

        String buttonID = "R3C1";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 3);
        assertEquals(botBoardInteraction.column, 1);
    }
    @Test
    public void test11(){

        String buttonID = "R3C2";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 3);
        assertEquals(botBoardInteraction.column, 2);
    }
    @Test
    public void test12(){
        String buttonID = "R3C3";
        botBoardInteraction.moveLocation(buttonID);
        assertEquals(botBoardInteraction.row, 3);
        assertEquals(botBoardInteraction.column, 3);
    }
}
