package com.general_hello.commands.commands.Commands.RpgUser;

import com.general_hello.commands.commands.Commands.Objects.RPGEmojis;

import java.text.DecimalFormat;

public class RPGDataUtils {
    public static final DecimalFormat formatter = new DecimalFormat("#,###");

    public static String filter(String filterWord) {
        return filterWord.toLowerCase().replace("'", "").replaceAll("\\s+", "");
    }

    public static String getBarFromPercentage(int percentage) {
        String bar = "";

        if (percentage < 10) {
            bar = RPGEmojis.bar1Empty + RPGEmojis.bar2Empty + RPGEmojis.bar2Empty + RPGEmojis.bar3Empty;
        } else if (percentage < 20) {
            bar = RPGEmojis.bar1Half + RPGEmojis.bar2Empty + RPGEmojis.bar2Empty + RPGEmojis.bar3Empty;
        } else if (percentage < 30) {
            bar = RPGEmojis.bar1Full + RPGEmojis.bar2Empty + RPGEmojis.bar2Empty + RPGEmojis.bar3Empty;
        } else if (percentage < 40) {
            bar = RPGEmojis.bar1Full + RPGEmojis.bar2Half + RPGEmojis.bar2Empty + RPGEmojis.bar3Empty;
        } else if (percentage < 50) {
            bar = RPGEmojis.bar1Full + RPGEmojis.bar2High + RPGEmojis.bar2Empty + RPGEmojis.bar3Empty;
        } else if (percentage < 65) {
            bar = RPGEmojis.bar1Full + RPGEmojis.bar2Full + RPGEmojis.bar2Half + RPGEmojis.bar3Empty;
        } else if (percentage < 70) {
            bar = RPGEmojis.bar1Full + RPGEmojis.bar2Full + RPGEmojis.bar2High + RPGEmojis.bar3Empty;
        } else if (percentage < 85) {
            bar = RPGEmojis.bar1Full + RPGEmojis.bar2Full + RPGEmojis.bar2Full + RPGEmojis.bar3Half;
        } else if (percentage < 101) {
            bar = RPGEmojis.bar1Full + RPGEmojis.bar2Full + RPGEmojis.bar2Full + RPGEmojis.bar3Full;
        }

        return bar;
    }

    public static int getPercentage(int firstNumber, int secondNumber) {
        double solving = (double) firstNumber/secondNumber;
        solving = solving * 100;
        return (int) solving;
    }
}