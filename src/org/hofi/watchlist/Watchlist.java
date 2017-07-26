package org.hofi.watchlist;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Watchlist {

  public static void main(String[] args) throws IOException {
    new Watchlist();
  }

  private Watchlist() throws IOException {
    List<Aktie> aktien = readAktienFromJSON();
    Aktie.outTitle();
    for(Aktie aktie: aktien) {
      readKursForAktie(aktie);
      aktie.calculate();
      aktie.out();
    }
    Aktie.outSumme(aktien);
  }

  private void readKursForAktie(Aktie aktie) {
    aktie.aktuellerKurs = 123.00; // TODO: read kurs
  }

  private List<Aktie> readAktienFromJSON() throws IOException {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new FileReader("./resources/watchlist.json"));
    JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();
    JsonArray jsonArray = jsonObject.get("aktien").getAsJsonArray();
    List<Aktie> aktien = new ArrayList<Aktie>();
    for(int i = 0; i < jsonArray.size(); i++) {
      Aktie aktie = gson.fromJson(jsonArray.get(i), Aktie.class);
      aktien.add(aktie);
    }
    return aktien;
  }
}