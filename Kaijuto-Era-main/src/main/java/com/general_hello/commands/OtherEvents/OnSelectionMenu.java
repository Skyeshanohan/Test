package com.general_hello.commands.OtherEvents;

import com.general_hello.commands.Config;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.general_hello.commands.commands.Utils.Util;
import com.general_hello.commands.commands.Utils.UtilString;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.List;

public class OnSelectionMenu extends ListenerAdapter {
    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {
        //here
        List<SelectOption> selectedOptions = event.getSelectedOptions();
        int x = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (x < selectedOptions.size()) {
            long roleId = Long.parseLong(selectedOptions.get(x).getValue());
            Role role = event.getGuild().getRoleById(roleId);
            stringBuilder.append(role.getAsMention()).append(" ");
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
            x++;
        }

        event.reply("Successfully added " + stringBuilder + " to you.").setEphemeral(true).queue();
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("register")) {
            String steamId = event.getValue("steam").getAsString();
            try {
                Long.parseLong(steamId);
            } catch (NumberFormatException e) {
                event.reply("The one you inputted isn't a number!").setEphemeral(true).queue();
                return;
            }
            RPGUser.setSteamId(event.getUser().getIdLong(), Long.parseLong(steamId));
            event.reply("Successfully registered your account!").queue();
        }

        if (event.getModalId().equals("contact")) {
            String message = event.getValue("message").getAsString();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor(event.getUser().getAsTag(), null, event.getUser().getEffectiveAvatarUrl());
            embedBuilder.setTimestamp(OffsetDateTime.now()).setFooter("Message sent at ");
            embedBuilder.setTitle("New contact request received");
            embedBuilder.setColor(Color.YELLOW);
            embedBuilder.setDescription("[**Raw Author Info:**](https://discord.com)\n" +
                    "```java\n" +
                    "Discord Tag: " + event.getUser().getAsTag() + "\n" +
                    "Discord ID: " + event.getUser().getId() + "\n" +
                    "Account Time Created: " + UtilString.formatOffsetDateTime(event.getUser().getTimeCreated()) +
                    "\n```\n\n" +
                    "[**Message**](https://discord.com):\n```ini\n" +
                    message + "\n```");
            event.reply("Sent the message to the developer! Have a great day!").setEphemeral(true).queue();
            Util.sendDM(Long.parseLong(Config.get("owner_id_partner")), embedBuilder.build());
        }
    }
}