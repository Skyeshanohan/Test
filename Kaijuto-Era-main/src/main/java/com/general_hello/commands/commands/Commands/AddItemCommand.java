package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.Objects.Objects;
import com.general_hello.commands.commands.Commands.RpgUser.RPGDataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddItemCommand extends SlashCommand {
    public AddItemCommand() {
        this.name = "additems";
        this.ownerCommand = true;
        this.help = "Adds items to a user";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "item", "The item to give").setRequired(true).setAutoComplete(true));
        options.add(new OptionData(OptionType.USER, "user", "The user to give the item").setRequired(true));
        options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that item to give (Default is 1)").setRequired(false).setMinValue(1));
        this.options = options;
    }

    @Override
    public void onAutoComplete(CommandAutoCompleteInteractionEvent event) {
        if (event.getFocusedOption().getName().equals("item")) {
            List<ExtractedResult> item = FuzzySearch.extractTop(event.getOption("item").getAsString(), Initializer.allNames, 2);
            Collection<Command.Choice> choices = new ArrayList<>();
            int x = 0;
            while (x < item.size()) {
                choices.add(new Command.Choice(item.get(x).getString(), item.get(x).getString()));
                x++;
            }
            event.replyChoices(choices).queue();
        }
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        int count = 1;
        String item;
        User users = event.getOption("user").getAsUser();

        if (event.getOption("amount") != null) {
            count = (int) event.getOption("amount").getAsDouble();
        }

        item = event.getOption("item").getAsString();
        String itemName = RPGDataUtils.filter(item);
        itemName = FuzzySearch.extractOne(itemName, Initializer.allNames).getString();
        Objects object = Initializer.allItems.get(RPGDataUtils.filter(itemName));
        RPGUser.addItem(users.getIdLong(), count, RPGDataUtils.filter(itemName));
        event.reply("Successfully added " + count + " " + (object.getEmojiOfItem() == null ? "" : object.getEmojiOfItem()) + " " + object.getName() + " to " + users.getName()).queue();
    }
}
