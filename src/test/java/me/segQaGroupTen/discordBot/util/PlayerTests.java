package me.segQaGroupTen.discordBot.util;

import me.segQaGroupTen.discordBot.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {
    private static final String player1Token = "O";
    private static final String player2Token = "X";

    @Test
    public void TestEmptyConstructor(){
        Player player =  new Player();

        assertEquals("", player.getPlayerID());
        assertEquals(player1Token, player.getPlayerToken());
    }

    @Test
    public void TestSetPlayerID(){
        Player player = new Player();

        String test ="testID";
        player.setPlayerID(test);

        assertEquals(test, player.getPlayerID());
    }

    @Test
    public void TestSwapToken(){
        Player player = new Player();

        player.swapToken();

        assertEquals(player2Token, player.getPlayerToken());
    }

    @Test
    public void TestSwapTokenTwice(){
        Player player = new Player();

        player.swapToken();
        player.swapToken();

        assertEquals(player1Token, player.getPlayerToken());
    }
}
