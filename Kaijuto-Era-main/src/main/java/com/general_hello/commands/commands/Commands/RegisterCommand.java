package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class RegisterCommand extends SlashCommand {
    public RegisterCommand() {
        this.name = "register";
        this.help = "Registers your discord account to your steam account.";
        this.cooldown = 60;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        boolean prevRegistered = false;
        if (RPGUser.getSteamId(event.getUser().getIdLong()) != -1) {
            prevRegistered = true;
        }
        TextInput email = TextInput.create("steam", "Steam ID", TextInputStyle.SHORT)
                .setPlaceholder("Enter your Steam ID" + (prevRegistered ? ". It will reset your current steam id." : ""))
                .setRequired(true)
                .setRequiredRange(17, 17)
                .build();

        Modal modal = Modal.create("register", "Register")
                .addActionRows(ActionRow.of(email))
                .build();

        event.replyModal(modal).queue();
    }
}
