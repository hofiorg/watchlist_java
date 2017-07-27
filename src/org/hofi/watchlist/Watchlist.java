package org.hofi.watchlist;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.hofi.utils.HttpUtils;
import static org.hofi.utils.NumberUtils.*;
import org.hofi.utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

class Watchlist {

  public static void main(String[] args) throws IOException, ParseException {
    new Watchlist();
  }

  private Watchlist() throws IOException, ParseException {
    List<Aktie> aktien = readAktienFromJSON();
    Aktie.outTitle();
    for(Aktie aktie: aktien) {
      readKursForAktie(aktie);
      aktie.calculate();
      aktie.out();
    }
    Aktie.outSumme(aktien);
    Utils.printNewlines(52);
  }

  private void readKursForAktie(Aktie aktie) throws IOException, ParseException {
    String body = HttpUtils.readHTTPResponseToString("http://www.finanzen.net/aktien/" + aktie.name);
    String from = "<div class=\"col-xs-5 col-sm-4 text-sm-right text-nowrap\">";
    String till = "<span>";
    aktie.aktuellerKurs = parseNumber(HttpUtils.parseResponse(body, from, till)).doubleValue();
    from = "<div class=\"quotebox-time\">";
    till = "</div>";
    aktie.kursZeit = HttpUtils.parseResponse(body, from, till);
    from = "<div class=\"menuDown color-blue pointer\">";
    till = "<ul class=\"hidden-xs\"";
    aktie.boerseKurs = HttpUtils.parseResponse(body, from, till);
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