package me.segQaGroupTen.discordBot;

import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

public class BotBoardInteraction implements MessageComponentCreateListener {
    @Override
    public void onComponentCreate(MessageComponentCreateEvent messageComponentCreateEvent) {
        MessageComponentInteraction messageComponentInteraction = messageComponentCreateEvent.getMessageComponentInteraction();
        String customId = messageComponentInteraction.getCustomId();
        switch (customId) {
        //when player click button on board
            case "R1C1":
                messageComponentInteraction.createImmediateResponder()
                        .setContent("You clicked a button!")
                        .respond();
                //make a call to game logic
                //make a call to update board
                break;
            case "R1C2":
                break;
            case "R1C3":
                break;
            case "R2C1":
                break;
            case "R2C2":
                break;
            case "R2C3":
                break;
            case "R3C1":
                break;
            case "R3C2":
                break;
            case "R3C3":
                break;
        }
    }
}
