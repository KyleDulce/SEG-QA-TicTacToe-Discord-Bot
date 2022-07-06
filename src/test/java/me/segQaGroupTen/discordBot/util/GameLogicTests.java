package me.segQaGroupTen.discordBot.util;

import me.segQaGroupTen.discordBot.GameLogic;
import me.segQaGroupTen.discordBot.GameLogicResponse;
import me.segQaGroupTen.discordBot.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class GameLogicTests {
    String[][] gameBoard;       //format: board[row][column]
    Player player;

    @BeforeEach
    public void init(){
        gameBoard= new String[3][3];

        GameLogic.resetGame(gameBoard);
        //Make player with player ID player and player token O
        player = new Player("player",1);
    }

    @Test
    public void MakeMove_Success(){
        GameLogicResponse response = GameLogic.makeMove(1,1,player, gameBoard);
        assertEquals(GameLogicResponse.SUCCESS, response);
    }

    @Test
    public void MakeMove_Failure(){
        GameLogicResponse response;

        //Try to play twice in the same square
        GameLogic.makeMove(1,1,player, gameBoard);
        response = GameLogic.makeMove(1,1,player, gameBoard);

        assertEquals(response, GameLogicResponse.FAILURE);
    }

    @Test
    public void MakeMove_WinRow(){
        GameLogicResponse response;

        //Make player 1 play on all row 1 squares
        GameLogic.makeMove(1,1,player, gameBoard);
        GameLogic.makeMove(3,3,player, gameBoard);  //Moves of the second player don't matter
        GameLogic.makeMove(1,2,player, gameBoard);
        GameLogic.makeMove(3,2,player, gameBoard);  //Moves of the second player don't matter

        response = GameLogic.makeMove(1,3,player, gameBoard);

        assertEquals(GameLogicResponse.WIN, response);
    }

    @Test
    public void MakeMove_WinColumn(){
        GameLogicResponse response;

        //Make player 1 play on all column 1 squares
        GameLogic.makeMove(1,1,player, gameBoard);
        GameLogic.makeMove(3,3,player, gameBoard);  //Moves of the second player don't matter
        GameLogic.makeMove(2,1,player, gameBoard);
        GameLogic.makeMove(3,2,player, gameBoard);  //Moves of the second player don't matter
        response = GameLogic.makeMove(3,1,player, gameBoard);

        assertEquals(GameLogicResponse.WIN, response);
    }

    @Test
    public void MakeMove_WinDiagonal(){
        GameLogicResponse response;

        //Make player 1 play on all the squares of the diagonal
        GameLogic.makeMove(1,1,player, gameBoard);
        GameLogic.makeMove(3,2,player, gameBoard);  //Moves of the second player don't matter
        GameLogic.makeMove(2,2,player, gameBoard);
        GameLogic.makeMove(1,2,player, gameBoard);
        response = GameLogic.makeMove(3,3,player, gameBoard);

        assertEquals(GameLogicResponse.WIN, response);
    }

    @Test
    public void MakeMove_WinAntiDiagonal(){
        GameLogicResponse response;

        //Make player 1 play on all the squares of the anti-diagonal
        GameLogic.makeMove(1,3,player, gameBoard);
        GameLogic.makeMove(3,2,player, gameBoard);  //Moves of the second player don't matter
        GameLogic.makeMove(2,2,player, gameBoard);
        GameLogic.makeMove(3,3,player, gameBoard);
        response = GameLogic.makeMove(3,1,player, gameBoard);

        assertEquals(GameLogicResponse.WIN, response);
    }

    @Test
    public void MakeMove_Tie(){
        GameLogicResponse response;

        //Make 9 moves that don't result in a player winning
        GameLogic.makeMove(1,1,player,gameBoard);
        GameLogic.makeMove(1,2,player,gameBoard);
        GameLogic.makeMove(1,3,player,gameBoard);
        GameLogic.makeMove(2,1,player,gameBoard);
        GameLogic.makeMove(2,3,player,gameBoard);
        GameLogic.makeMove(2,2,player,gameBoard);
        GameLogic.makeMove(3,1,player,gameBoard);
        GameLogic.makeMove(3,3,player,gameBoard);
        response = GameLogic.makeMove(3,2,player,gameBoard);

        assertEquals(GameLogicResponse.TIE, response);
    }
}
