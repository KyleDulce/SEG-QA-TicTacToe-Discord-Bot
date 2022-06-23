package me.segQaGroupTen.discordBot;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class Bot implements MessageCreateListener{

    public static DiscordApi api = new DiscordApiBuilder().setToken("INSERT BOT TOKEN HERE").login().join();
    public static TextChannel channel = api.getTextChannels().iterator().next();
    public static String[][] gameBoard = new String[3][3];

    public Bot(){
        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard.length; y++) {
                gameBoard[x][y] = "__";
            }
        }
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }

    public static void sendMessages(String message){
            CompletableFuture<Message> messageBuilder =  new MessageBuilder()
                    .append(message)
                    .send(channel);

    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
            if (messageCreateEvent.getMessageContent().equalsIgnoreCase("/tictactoe")) {
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
                        .send(channel);
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
                    .send(channel);
    }
}
