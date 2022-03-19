package com.general_hello.commands.commands.Commands.Items;

import com.general_hello.commands.commands.Commands.Objects.Artifact;
import com.general_hello.commands.commands.Commands.Objects.Objects;
import com.general_hello.commands.commands.Commands.Types.Rarity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Initializer {
    public static HashMap<String, Artifact> artifactToId = new HashMap<>();
    public static HashMap<String, Objects> allItems = new HashMap<>();
    public static ArrayList<Artifact> artifacts = new ArrayList<>();
    public static ArrayList<String> allNames = new ArrayList<>();

    public static void initializer() {
        try (FileReader reader = new FileReader("items.json")) {
            JsonElement obj = JsonParser.parseReader(reader);
            JsonArray itemsList = obj.getAsJsonArray();
            itemsList.forEach(jsonElement -> parseItemsObject(jsonElement.getAsJsonObject()));
        } catch (Exception e) {
            e.printStackTrace();
        }
       }

    private static void parseItemsObject(JsonObject employee)
    {
        //Get items object within list
        JsonObject items = employee.get("item").getAsJsonObject();
        String name = items.get("name").getAsString();
        String emoji = items.get("emoji").getAsString();
        String description = items.get("description").getAsString();
        int price = items.get("price").getAsInt();
        new Artifact(Rarity.UNCOMMON, name, emoji, description, price);
    }
}
