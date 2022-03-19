package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Bot;
import com.general_hello.commands.commands.ButtonPaginator;
import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.Objects.Artifact;
import com.general_hello.commands.commands.Commands.Objects.RPGEmojis;
import com.general_hello.commands.commands.Commands.RpgUser.RPGDataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.CooldownScope;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShopCommand extends SlashCommand {
    public static final DecimalFormat formatter = new DecimalFormat("#,###.00");
    public ShopCommand() {
        this.name = "shop";
        this.cooldown = 5;
        this.cooldownScope = CooldownScope.USER;
        this.help = "Shows the shop";
        this.options = Collections.singletonList(new OptionData(OptionType.STRING, "item", "The item to show the info.").setRequired(false).setAutoComplete(true));
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
        if (event.getOption("item") == null) {
            ButtonPaginator.Builder builder = new ButtonPaginator.Builder(event.getJDA())
                    .setEventWaiter(Bot.getBot().getEventWaiter())
                    .setItemsPerPage(5)
                    .setTimeout(1, TimeUnit.MINUTES)
                    .useNumberedItems(false);
            builder.setTitle("Shop")
                    .setItems(buildDescription(event.getUser().getIdLong()))
                    .addAllowedUsers(event.getUser().getIdLong())
                    .setColor(Color.decode("#452350"));
            builder.build().paginate(event.getTextChannel(), 1);
            event.reply("Loading shop...").queue((interactionHook -> {
                interactionHook.deleteOriginal().queueAfter(3, TimeUnit.SECONDS);
            }));
            return;
        }
        String name = event.getOption("item").getAsString().toLowerCase();
        name = RPGDataUtils.filter(name);
        name = RPGDataUtils.filter(FuzzySearch.extractOne(name, Initializer.allNames).getString());

        boolean isErrorOutputed = false;
        try {
            if (Initializer.artifactToId.containsKey(name)) {
                Artifact artifact = Initializer.artifactToId.get(name);
                EmbedBuilder embedBuilder = new EmbedBuilder().setTitle(artifact.getName() + " (" + RPGUser.getItemCount(event.getUser().getIdLong(), RPGDataUtils.filter(name)) + " owned)");
                embedBuilder.setColor(Color.YELLOW);
                embedBuilder.setDescription("> " + artifact.getDescription() + "\n\n" +
                        "**BUY** - " + RPGEmojis.currency + " [" + artifact.getFormattedPrice() + "](https://discord.com)" + "\n" +
                        "**SELL** - " + RPGEmojis.currency + " [" + artifact.getFormattedSellPrice() + "](https://discord.com)\n" +
                        "**TRADE** - Unknown");
                embedBuilder.addField("Rarity", "`" + artifact.getRarity().getName() + "`", true);
                embedBuilder.addField("Type", "`Collectible`", true);
                embedBuilder.addField("ID", "`" + name + "`", true);
                try {
                    embedBuilder.setThumbnail(artifact.getEmojiUrl());
                } catch (IllegalArgumentException ignored) {}
                event.replyEmbeds(embedBuilder.build()).queue();
                return;
            }
        } catch (Exception e) {
            event.reply("The item you searched for is not there!").queue();
            e.printStackTrace();
            isErrorOutputed = true;
        }

        if (!isErrorOutputed) {
            event.reply("The item you searched for is not there!").queue();
        }
    }

    private static ArrayList<String> buildDescription(long userId) {
        ArrayList<String> content = new ArrayList<>();
        ArrayList<Artifact> artifacts = Initializer.artifacts;

        int x = 0;
        while (x < artifacts.size()) {
            Artifact artifact = artifacts.get(x);
            content.add(artifact.getEmojiOfItem() + " **" + artifact.getName() + "** " +
                    "(" + RPGUser.getItemCount(userId, RPGDataUtils.filter(artifact.getName())) + ") — " + "<:4Currency:949050677441749023> [" + formatter.format(artifact.getCostToBuy()) + "](https://discord.com)\n" +
                    artifact.getDescription() + "\n");
            x++;
        }
        return content;
    }
}