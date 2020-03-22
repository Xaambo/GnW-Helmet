package com.company;

import org.json.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSON {

    final static String FILENAME = "ranking.json";

    public JSONArray readRankingFile() throws IOException {

        FileWriter fw;
        JSONArray ranking;
        String jaja;

        try {

            jaja = new String(Files.readAllBytes(Paths.get(FILENAME)));

        } catch (Exception e) {

            fw = new FileWriter(FILENAME);
            fw.write("[]");
            fw.close();

            jaja = new String(Files.readAllBytes(Paths.get(FILENAME)));
        }

        ranking = new JSONArray(jaja);

        return ranking;
    }

    public void writeRankingFile(JSONObject newTop) throws IOException {

        JSONArray ranking;
        ArrayList<Object> rankingList;
        String jaja;
        File oldRankingJSON = new File(FILENAME);
        File newRankingJSON = new File("newRanking.json");
        FileWriter fw = new FileWriter(newRankingJSON);

        int puntuacioTotal = Integer.parseInt(newTop.get("Rondes").toString()) * 30 + Integer.parseInt(newTop.get("Puntuacio").toString());
        int puntuacioRanking;

        ranking = readRankingFile();

        if (ranking.length() == 0) {

            ranking.put(newTop);

        } else {

            rankingList = (ArrayList<Object>) ranking.toList();

            for (int i = 0; i < ranking.length(); i++) {

                puntuacioRanking = Integer.parseInt(ranking.getJSONObject(i).get("Rondes").toString()) * 30 + Integer.parseInt(ranking.getJSONObject(i).get("Puntuacio").toString());

                if (puntuacioTotal >= puntuacioRanking) {
                    rankingList.add(i, newTop);

                    if (rankingList.size() == 4) {
                        rankingList.remove(3);
                    }

                    break;
                }
            }

            ranking = new JSONArray(rankingList);
        }

        jaja = ranking.toString();

        fw.write(jaja);
        fw.close();

        if (oldRankingJSON.delete()) {
            newRankingJSON.renameTo(new File(FILENAME));
        }
    }
}
