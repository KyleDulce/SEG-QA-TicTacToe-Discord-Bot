package me.segQaGroupTen.discordBot;

public class Player {
    String playerID;
    String playerToken;
    private static final String player1Token = "O";
    private static final String player2Token = "X";

    public Player (){
        playerID = "";
        playerToken = player1Token;
    }

    public Player(String playerID, int playerNum){
        this.playerID = playerID;

        if (playerNum == 1){
            playerToken = player1Token;
        }
        else {
            playerToken = player2Token;
        }
    }

    public void setPlayerID(String playerID){
        this.playerID = playerID;
    }

    public void swapToken(){
        if (playerToken.equals(player1Token))
            playerToken = player2Token;
        else
            playerToken = player1Token;
    }
}
