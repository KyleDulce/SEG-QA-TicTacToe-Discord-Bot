package me.segQaGroupTen.discordBot;

import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

import java.awt.*;

public class BotBoardInteraction implements MessageComponentCreateListener {

    /**
     * Instance of the current player. Always contains the token
     * (X or O) that is next to move
     */
    Player currentPlayer = new Player();
    @Override
    public void onComponentCreate(MessageComponentCreateEvent messageComponentCreateEvent) {
        MessageComponentInteraction messageComponentInteraction = messageComponentCreateEvent.getMessageComponentInteraction();
        String customId = messageComponentInteraction.getCustomId();
        currentPlayer.setPlayerID(messageComponentInteraction.getUser().getName());

        int row = 0, column = 0;
        switch (customId) {
        //when player click button on board
            case "R1C1":
                row = 1;
                column = 1;
                //make a call to game logic
                //make a call to update board
                break;
            case "R1C2":
                row = 1;
                column = 2;
                break;
            case "R1C3":
                row = 1;
                column = 3;
                break;
            case "R2C1":
                row = 2;
                column = 1;
                break;
            case "R2C2":
                row = 2;
                column = 2;
                break;
            case "R2C3":
                row = 2;
                column = 3;
                break;
            case "R3C1":
                row = 3;
                column = 1;
                break;
            case "R3C2":
                row = 3;
                column = 2;
                break;
            case "R3C3":
                row = 3;
                column = 3;
                break;
        }

        messageComponentInteraction.createImmediateResponder().respond();
        GameLogicResponse response = GameLogic.makeMove(row,column, currentPlayer, Bot.gameBoard);
        Bot.updateBoard();
        handleResponse(response,currentPlayer);
    }

    private void handleResponse(GameLogicResponse response, Player currentPlayer){

        switch (response){
            case FAILURE :
                Bot.sendMessages("Sorry " + currentPlayer.playerID + " someone already played on this square");
                break;
            case TIE:
                GameLogic.resetGame(Bot.gameBoard);
                Bot.sendMessages("The game is a tie! Good game");
                break;
            case WIN:
                GameLogic.resetGame(Bot.gameBoard);
                Bot.sendMessages(currentPlayer.playerID + " won. Good game!");
                break;
        }
    }
}
