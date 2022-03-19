package com.general_hello.commands.OtherEvents;

import com.general_hello.commands.Bot;
import com.general_hello.commands.Database.SQLiteDataSource;
import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.Objects.Objects;
import com.general_hello.commands.commands.Commands.RpgUser.RPGDataUtils;
import com.general_hello.commands.commands.Commands.SteamChecker;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OnReadyEvent extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnReadyEvent.class);

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        Bot.jda = event.getJDA();
        LOGGER.info("Starting the bot...");
        try {
            final File dbFile = new File("database.db");

            if (!dbFile.exists()) {
                if (dbFile.createNewFile()) {
                    LOGGER.info("Created database file");
                } else {
                    LOGGER.info("Could not create database file");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            SQLiteDataSource.connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("Opened database successfully");

        try (final PreparedStatement preparedStatement = SQLiteDataSource.getConnection()
                .prepareStatement("CREATE TABLE IF NOT EXISTS RPGData (" +
                        "UserId INTEGER," +
                                "Shekels INTEGER," +
                        "SteamId INTEGER DEFAULT -1," + items() +
                                ")")) {
            LOGGER.info("Made a new table (RPGData)");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOGGER.info("Running the steam checker!");
        SteamChecker.runChecker();
    }

    private String items() {
        StringBuilder items = new StringBuilder();

        int x = 0;
        ArrayList<String> names = Initializer.allNames;
        while (x < names.size()) {
            Objects objects = Initializer.allItems.get(RPGDataUtils.filter(names.get(x)));
            items.append(RPGDataUtils.filter(objects.getName())).append(" INTEGER DEFAULT 0");
            if (x + 1 < names.size()) {
                items.append(", ");
            }
            x++;
        }

        return items.toString();
    }
}
