package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Config;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class AddCurrencyCommand extends SlashCommand {
    public AddCurrencyCommand() {
        this.name = "addbones";
        this.ownerCommand = true;
        this.help = "Adds bones (currency) to a user";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "user", "The user to give the item").setRequired(true));
        options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that bones you'll give (Default is 100)").setRequired(false).setMinValue(1));
        this.options = options;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        if (!event.getUser().getId().equals(Config.get("owner_id")) && !event.getUser().getId().equals(Config.get("owner_id_partner"))) {
            event.getChannel().sendMessage("Only the owner can do this!").queue();
            return;
        }

        int money = 100;

        if (event.getOption("amount") != null) {
            money = (int) event.getOption("amount").getAsDouble();
        }

        RPGUser.addMoney(event.getOption("user").getAsUser().getIdLong(), money);
        event.reply("Successfully added " + money + " bones to " + event.getOption("user").getAsUser().getAsMention()).setEphemeral(true).queue();
    }
}
