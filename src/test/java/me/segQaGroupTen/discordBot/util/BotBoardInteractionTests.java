package me.segQaGroupTen.discordBot.util;

import me.segQaGroupTen.discordBot.Bot;
import me.segQaGroupTen.discordBot.BotBoardInteraction;
import me.segQaGroupTen.discordBot.GameLogicResponse;
import me.segQaGroupTen.discordBot.Player;
import me.segQaGroupTen.discordBot.config.ConfigurationManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotBoardInteractionTests {

    @BeforeAll
    public static void setup(){
        ConfigurationManager.loadYamlConfiguration();}

    @Test
    public void test1(){
        BotBoardInteraction b = new BotBoardInteraction();
        Player p = new Player();
        b.handleResponse(GameLogicResponse.WIN, p);
        assertEquals(b.currentresponse, "won. Good game!");
    }
    @Test
    public void test2(){
        BotBoardInteraction b = new BotBoardInteraction();
        Player p = new Player();
        b.handleResponse(GameLogicResponse.TIE, p);
        assertEquals(b.currentresponse, "The game is a tie! Good game");
    }

    @Test
    public void test3(){
        BotBoardInteraction b = new BotBoardInteraction();
        Player p = new Player();
        b.handleResponse(GameLogicResponse.FAILURE, p);
        assertEquals(b.currentresponse, "Sorry someone already played on this square");
    }
    @Test
    public void test4(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R1C1";
        b.moveLocation(buttonID);
        assertEquals(b.row, 1);
        assertEquals(b.column, 1);
    }
    @Test
    public void test5(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R1C2";
        b.moveLocation(buttonID);
        assertEquals(b.row, 1);
        assertEquals(b.column, 2);
    }
    @Test
    public void test6(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R1C3";
        b.moveLocation(buttonID);
        assertEquals(b.row, 1);
        assertEquals(b.column, 3);
    }

    @Test
    public void test7(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R2C1";
        b.moveLocation(buttonID);
        assertEquals(b.row, 2);
        assertEquals(b.column, 1);
    }

    @Test
    public void test8(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R2C2";
        b.moveLocation(buttonID);
        assertEquals(b.row, 2);
        assertEquals(b.column, 2);
    }

    @Test
    public void test9(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R2C3";
        b.moveLocation(buttonID);
        assertEquals(b.row, 2);
        assertEquals(b.column, 3);
    }
    @Test
    public void test10(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R3C1";
        b.moveLocation(buttonID);
        assertEquals(b.row, 3);
        assertEquals(b.column, 1);
    }
    @Test
    public void test11(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R3C2";
        b.moveLocation(buttonID);
        assertEquals(b.row, 3);
        assertEquals(b.column, 2);
    }
    @Test
    public void test12(){
        BotBoardInteraction b = new BotBoardInteraction();
        String buttonID = "R3C3";
        b.moveLocation(buttonID);
        assertEquals(b.row, 3);
        assertEquals(b.column, 3);
    }
}
