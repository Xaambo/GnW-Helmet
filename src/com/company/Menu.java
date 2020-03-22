package com.company;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.*;

public class Menu {

    private Game game;
    private JSON json = new JSON();

    private JSONArray ranking = new JSONArray();
    private JSONArray newRanking;

    private JSONObject top1 = new JSONObject("{\"Rondes\":null,\"Puntuacio\":\"N/D\"}");
    private JSONObject top2 = new JSONObject("{\"Rondes\":\"N/D\",\"Puntuacio\":\"N/D\"}");
    private JSONObject top3 = new JSONObject("{\"Rondes\":\"N/D\",\"Puntuacio\":\"N/D\"}");

    public Menu(Game game) {
        this.game = game;

        ranking.put(top1);
        ranking.put(top2);
        ranking.put(top3);
    }

    public void paint(Graphics2D g) throws IOException {

        Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
        Font topsFont = new Font("Times New Roman", Font.PLAIN, 30);

        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("PLAY", 520, 300);

        g.setFont(topsFont);

        newRanking = json.readRankingFile();

        if (newRanking.length() != 0) {

            for (int i = 0; i < newRanking.length(); i++) {

                ranking.put(i, newRanking.getJSONObject(i));

            }

        }

        g.drawString("TOP 1: Rondes: " + ranking.getJSONObject(0).get("Rondes") + " | Punts: " + ranking.getJSONObject(0).get("Puntuacio"), 820, 480);
        g.drawString("TOP 2: Rondes: " + ranking.getJSONObject(1).get("Rondes") + " | Punts: " + ranking.getJSONObject(1).get("Puntuacio"), 820, 560);
        g.drawString("TOP 3: Rondes: " + ranking.getJSONObject(2).get("Rondes") + " | Punts: " + ranking.getJSONObject(2).get("Puntuacio"), 820, 640);

    }
}
