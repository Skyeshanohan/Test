package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.Objects.Objects;
import com.general_hello.commands.commands.Commands.Objects.RPGEmojis;
import com.general_hello.commands.commands.Commands.RpgUser.RPGDataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.CooldownScope;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BuyCommand extends SlashCommand {
    public BuyCommand() {
        this.name = "buy";
        this.help = "Buys an item";
        this.cooldown = 10;
        this.cooldownScope = CooldownScope.USER;
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "item", "The item to buy").setRequired(true).setAutoComplete(true));
        options.add(new OptionData(OptionType.INTEGER, "amount", "The amount of that item to buy (Default is 1)").setRequired(false).setMinValue(1));
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
        long authorId = event.getUser().getIdLong();

        int amount = 1;
        if (event.getOption("amount") != null) {
            amount = (int) event.getOption("amount").getAsDouble();
        }

        String itemName = RPGDataUtils.filter(event.getOption("item").getAsString());
        itemName = RPGDataUtils.filter(FuzzySearch.extractOne(itemName, Initializer.allNames).getString());
        Objects item = Initializer.allItems.get(itemName);
        Integer cost = item.getCostToBuy();

        if (cost == null) {
            event.reply("The item you chose is out of stock. Kindly do `/shop " + itemName + "` for more details.").setEphemeral(true).queue();
            return;
        }

        cost = cost * amount;
        int shekels = RPGUser.getMoney(authorId);

        if (shekels < cost) {
            event.reply("You don't have enough money to buy this item. The cost of this item is " + RPGEmojis.currency + " " + RPGDataUtils.formatter.format(cost) + ".").setEphemeral(true).queue();
            return;
        }

        RPGUser.addMoney(authorId, -cost);
        RPGUser.addItem(authorId, amount, itemName);
        event.reply("Successfully bought " + amount + " " + item.getEmojiOfItem() + " " + item.getName() + " for " + RPGEmojis.currency + " " + RPGDataUtils.formatter.format(cost) + ".").queue();
    }
}
