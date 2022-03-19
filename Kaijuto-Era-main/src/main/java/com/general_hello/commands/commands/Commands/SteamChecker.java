package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Bot;
import com.general_hello.commands.Config;
import com.general_hello.commands.commands.Commands.RpgUser.RPGUser;
import com.general_hello.commands.commands.Utils.UtilString;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.dv8tion.jda.api.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SteamChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(SteamChecker.class);
    public static void runChecker() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(SteamChecker::check, 0, 5, TimeUnit.MINUTES);
    }

    private static void check() {
        OffsetDateTime start = OffsetDateTime.now();
        LOGGER.info("Doing the steam check.");
        List<User> users = Bot.jda.getUsers();
        int x = 0;
        while (x < users.size()) {
            long steamId = RPGUser.getSteamId(users.get(x).getIdLong());
            if (steamId != -1) {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=F36134A3BEAAACB93E58549C8ACF1D75&steamids=" + steamId))
                            .method("GET", HttpRequest.BodyPublishers.noBody())
                            .build();
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    String data = response.body();
                    Gson gson = new GsonBuilder().create();

                    JsonObject job = gson.fromJson(data, JsonObject.class);
                    int gameId = job.getAsJsonObject("response").getAsJsonArray("players").get(0).getAsJsonObject().get("gameid").getAsInt();
                    if (gameId == Integer.parseInt(Config.get("game_id"))) {
                        RPGUser.addMoney(users.get(x).getIdLong(), 3);
                        LOGGER.info("Added 10 bones to " + users.get(x).getAsTag());
                    }
                } catch (Exception ignored) {}
            }
            x++;
        }

        LOGGER.info("Finished doing the steam check. It took " + UtilString.formatDurationToString(Duration.between(start, OffsetDateTime.now()).toMillis()));
    }
}
