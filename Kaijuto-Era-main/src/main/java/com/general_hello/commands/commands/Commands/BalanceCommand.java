package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.Objects.RPGEmojis;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.Collections;

public class BalanceCommand extends SlashCommand {
    public BalanceCommand() {
        this.name = "balance";
        this.help = "Shows your balance.";
        this.options = Collections.singletonList(new OptionData(OptionType.USER, "user", "The user to show the balance.").setRequired(false));
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        User user = event.getUser();
        if (event.getOption("user") != null) {
            user = event.getOption("user").getAsUser();
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();
        int bones = RPGUser.getMoney(user.getIdLong());
        embedBuilder.setColor(event.getGuild().getSelfMember().getColor());
        embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/948372407108915212/954161667401482320/76-763131_dinosaur-skull.png");
        DecimalFormat formatter = new DecimalFormat("#,###");
        embedBuilder.setTitle(user.getName() + "'s Balance").setTimestamp(OffsetDateTime.now());
        embedBuilder.setDescription(RPGEmojis.currency + " Bones: **" + (bones != -1 ? formatter.format(bones) + " bones" : "You are not in the cache still. Kindly wait for a while.") + "**\n\n" +
                "> Earn bones by playing the game!");
        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
