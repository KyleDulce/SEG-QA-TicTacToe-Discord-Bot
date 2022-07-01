package me.segQaGroupTen.discordBot;

import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

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
            case "R1C1" -> {
                row = 1;
                column = 1;
            }
            //make a call to game logic
            //make a call to update board
            case "R1C2" -> {
                row = 1;
                column = 2;
            }
            case "R1C3" -> {
                row = 1;
                column = 3;
            }
            case "R2C1" -> {
                row = 2;
                column = 1;
            }
            case "R2C2" -> {
                row = 2;
                column = 2;
            }
            case "R2C3" -> {
                row = 2;
                column = 3;
            }
            case "R3C1" -> {
                row = 3;
                column = 1;
            }
            case "R3C2" -> {
                row = 3;
                column = 2;
            }
            case "R3C3" -> {
                row = 3;
                column = 3;
            }
        }

        messageComponentInteraction.createImmediateResponder().respond();
        GameLogicResponse response = GameLogic.makeMove(row,column, currentPlayer, Bot.gameBoard);
        Bot.updateBoard();
        handleResponse(response,currentPlayer);
    }

    private void handleResponse(GameLogicResponse response, Player currentPlayer){

        switch (response) {
            case FAILURE ->
                    CommandInterface.sendMessages("Sorry " + currentPlayer.playerID + " someone already played on this square");
            case TIE -> {
                GameLogic.resetGame(Bot.gameBoard);
                CommandInterface.sendMessages("The game is a tie! Good game");
            }
            case WIN -> {
                GameLogic.resetGame(Bot.gameBoard);
                CommandInterface.sendMessages(currentPlayer.playerID + " won. Good game!");
            }
        }
    }
}
