package com.general_hello.commands.commands.Commands.Objects;

import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.RpgUser.RPGDataUtils;
import com.general_hello.commands.commands.Commands.Types.AttainableBy;
import com.general_hello.commands.commands.Commands.Types.Rarity;

public class Artifact extends Objects {
    private final AttainableBy retrieveBy;

    public Artifact(Rarity rarity, String name, String emoji, String description, int price) {
        super(name, rarity, price, emoji, description);
        this.retrieveBy = AttainableBy.CRAFTING;
        String[] split = name.split("\\s+");
        Initializer.artifactToId.put(RPGDataUtils.filter(split[0]), this);
        Initializer.allItems.put(RPGDataUtils.filter(name), this);
        Initializer.allItems.put(RPGDataUtils.filter(split[0]), this);
        Initializer.artifactToId.put(name.toLowerCase().replace("'", "").replaceAll("\\s+", ""), this);
        Initializer.artifacts.add(this);
        Initializer.allNames.add(name);
    }

    public AttainableBy getRetrieveBy() {
        return retrieveBy;
    }
}
