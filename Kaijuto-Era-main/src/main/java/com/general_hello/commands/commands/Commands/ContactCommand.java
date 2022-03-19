package com.general_hello.commands.commands.Commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class ContactCommand extends SlashCommand {
    public ContactCommand() {
        this.name = "contact";
        this.help = "Contacts the discord developer.";
        this.cooldown = 600;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        TextInput body = TextInput.create("message", "Message", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Your concerns goes here.")
                .setRequired(true)
                .setMinLength(30)
                .setMaxLength(3900)
                .build();

        Modal modal = Modal.create("contact", "Contact")
                .addActionRows(ActionRow.of(body))
                .build();

        event.replyModal(modal).queue();
    }
}
