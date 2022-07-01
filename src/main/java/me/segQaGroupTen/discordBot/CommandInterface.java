package me.segQaGroupTen.discordBot;

import me.segQaGroupTen.discordBot.config.Configuration;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

import java.util.concurrent.CompletableFuture;

public class CommandInterface {

    public static DiscordApi api = new DiscordApiBuilder().setToken(Configuration.getInstance().getToken()).login().join();
    public static TextChannel channel = api.getTextChannels().iterator().next();

    public static void sendMessages(String message){
        CompletableFuture<Message> messageBuilder =  new MessageBuilder()
                .append(message)
                .send(channel);
    }

}
