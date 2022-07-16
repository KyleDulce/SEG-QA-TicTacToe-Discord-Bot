package me.segQaGroupTen.discordBot;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.CompletableFuture;

public class Bot implements MessageCreateListener{

    public static String[][] gameBoard;

    public Bot(){
        gameBoard = new String[3][3];
        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard.length; y++) {
                gameBoard[x][y] = "__";
            }
        }
        System.out.println("You can invite the bot by using the following url: " + CommandInterface.api.createBotInvite());
    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
            if (messageCreateEvent.getMessageContent().equalsIgnoreCase("/tictactoe")) {
                updateBoard();
        }
    }
    public static void updateBoard(){
            CompletableFuture<Message> message = new MessageBuilder()
                    .setContent("Play Tic Tac Toe!")
                    .addComponents(
                            ActionRow.of(Button.secondary("R1C1", gameBoard[0][0]),
                                    Button.secondary("R1C2", gameBoard[0][1]),
                                    Button.secondary("R1C3", gameBoard[0][2])),
                            ActionRow.of(Button.secondary("R2C1", gameBoard[1][0]),
                                    Button.secondary("R2C2", gameBoard[1][1]),
                                    Button.secondary("R2C3", gameBoard[1][2])),
                            ActionRow.of(Button.secondary("R3C1", gameBoard[2][0]),
                                    Button.secondary("R3C2", gameBoard[2][1]),
                                    Button.secondary("R3C3", gameBoard[2][2])))
                    .send(CommandInterface.channel);
    }
}
