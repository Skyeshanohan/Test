package com.general_hello.commands.commands.Commands.Objects;

import com.general_hello.commands.commands.Commands.ShopCommand;
import com.general_hello.commands.commands.Commands.Types.Rarity;
import com.general_hello.commands.commands.Utils.UtilNum;
import net.dv8tion.jda.api.entities.Emoji;

public class Objects {
    private final String name;
    private final Integer costToBuy;
    private final Integer sellPrice;
    private final String emojiOfItem;
    private final String description;
    private final Rarity rarity;

    public Objects(String name, Rarity rarity, Integer costToBuy, String emojiOfItem, String description) {
        this.name = name;
        this.costToBuy = costToBuy;
        this.rarity = rarity;
        int multi = UtilNum.randomNum(500, 2000);
        this.sellPrice = rarity.getWorth() * multi;
        this.emojiOfItem = emojiOfItem;
        this.description = description;
    }

    public String getEmojiOfItem() {
        return emojiOfItem == null ? "" : emojiOfItem;
    }

    public String getDescription() {
        return description;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }

    public Integer getCostToBuy() {
        return costToBuy;
    }

    public Long getEmojiId() {
        Emoji emoji = Emoji.fromMarkdown(this.emojiOfItem);
        return emoji.getIdLong();
    }

    public String getEmojiUrl() {
        if (getEmojiOfItem().equals("")) {
            return "";
        }
        Emoji emoji = Emoji.fromMarkdown(getEmojiOfItem());
        if (emoji.isAnimated()) {
            return "https://cdn.discordapp.com/emojis/" + emoji.getId() + ".gif";
        }
        return "https://cdn.discordapp.com/emojis/" + emoji.getId() + ".png";

    }

    public String getFormattedPrice() {
        return ShopCommand.formatter.format(this.costToBuy);
    }

    public String getFormattedSellPrice() {
        return ShopCommand.formatter.format(this.sellPrice);
    }
}
