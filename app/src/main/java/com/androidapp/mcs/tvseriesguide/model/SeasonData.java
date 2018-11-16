package com.androidapp.mcs.tvseriesguide.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeasonData {
    public static List<Seasons> seasonsList;
    public static Map<String, Seasons> seasonsMap;

    static {
        seasonsList = new ArrayList<>();
        seasonsMap = new HashMap<>();
        addItem(new Seasons("1"));
        addItem(new Seasons("2"));
        addItem(new Seasons("3"));
        addItem(new Seasons("4"));
        addItem(new Seasons("5"));
        addItem(new Seasons("6"));
        addItem(new Seasons("7"));
        addItem(new Seasons("8"));
        addItem(new Seasons("9"));
        addItem(new Seasons("10"));

    }

    private static void addItem(Seasons item) {
        seasonsList.add(item);
        seasonsMap.put(item.getItemId(), item);
    }
}
