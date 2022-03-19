package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Bot;
import com.general_hello.commands.commands.ButtonPaginator;
import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.Objects.Objects;
import com.general_hello.commands.commands.Commands.RpgUser.RPGDataUtils;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class InventoryCommand extends SlashCommand {
    public static HashMap<Long, ArrayList<String>> embedPaginatorMessage = new HashMap<>();
    public InventoryCommand() {
        this.name = "inventory";
        this.help = "Displays your inventory";
        this.cooldown = 10;
        this.options = Collections.singletonList(new OptionData(OptionType.USER, "user", "The user to show the inventory.").setRequired(false));
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        User author = event.getUser();
        if (event.getOption("user") != null) {
            author = event.getOption("user").getAsUser();
        }
        ArrayList<String> allNames = Initializer.allNames;
        long authorId = author.getIdLong();
        int x = 0;
        int y = 0;

        ArrayList<String> inventories = new ArrayList<>();
        StringBuilder inventory = new StringBuilder();

        while (x < allNames.size()) {
            int itemCount = RPGUser.getItemCount(authorId, allNames.get(x));
            if (itemCount > 0) {
                Objects objects = Initializer.allItems.get(RPGDataUtils.filter(allNames.get(x)));
                inventory.append(objects.getEmojiOfItem()).append(" **")
                        .append(objects.getName()).append("** ─ ")
                        .append(itemCount).append("\n")
                        .append("*ID* `").append(RPGDataUtils.filter(objects.getName())).append("`")
                        .append(" ─ ").append(objects.getRarity().getName()).append("\n\n");
                y++;
            }

            if (y%5==0) {
                if (!inventory.toString().equals("")) {
                    inventories.add(inventory.toString());
                }
                inventory = new StringBuilder();
            }
            x++;
        }

        if (!inventory.toString().equals("")) {
            inventories.add(inventory.toString());
        }

        if (inventories.isEmpty()) {
            event.reply("You do not have anything in your inventory").queue();
            return;
        }

        event.reply("Loading inventory...").queue((interactionHook -> {
            interactionHook.deleteOriginal().queueAfter(3, TimeUnit.SECONDS);
        }));
        ButtonPaginator.Builder builder = new ButtonPaginator.Builder(event.getJDA())
                .setEventWaiter(Bot.getBot().getEventWaiter())
                .setItemsPerPage(1)
                .setTimeout(1, TimeUnit.MINUTES)
                .useNumberedItems(false);

        builder.setTitle("**" + author.getName() + "**'s inventory")
                .setItems(inventories)
                .addAllowedUsers(event.getUser().getIdLong())
                .setColor(Color.decode("#452350"));

        builder.build().paginate(event.getTextChannel(), 1);
    }
}
