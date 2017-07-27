package org.hofi.watchlist;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.hofi.watchlist.Util.parseNumber;

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
  }

  private void readKursForAktie(Aktie aktie) throws IOException, ParseException {
    String body = readHTTPResponseToString("http://www.finanzen.net/aktien/" + aktie.name);
    String from = "<div class=\"col-xs-5 col-sm-4 text-sm-right text-nowrap\">";
    String till = "<span>";
    aktie.aktuellerKurs =  parseNumber(readFromBody(body, from, till)).doubleValue();
    from = "<div class=\"quotebox-time\">";
    till = "</div>";
    aktie.kursZeit = readFromBody(body, from, till);



    from = "<div class=\"menuDown color-blue pointer\">";
    till = "<ul class=\"hidden-xs\"";
    aktie.boerseKurs = readFromBody(body, from, till);
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

  private String readHTTPResponseToString(String parseUrl) throws IOException {
    URL url = new URL(parseUrl);
    URLConnection con = url.openConnection();
    InputStream in = con.getInputStream();
    String encoding = con.getContentEncoding();
    encoding = encoding == null ? "UTF-8" : encoding;
    return IOUtils.toString(in, encoding);
  }

  private String readFromBody(String body, String from, String till) {
    if(!body.contains(from))
      throw new IllegalStateException("from not found: " + from);

    if(!body.contains(till))
      throw new IllegalStateException("till not found: " + till);

    int fromPos = body.indexOf(from) + from.length();
    int tillPos = body.indexOf(till, body.indexOf(from));
    return body.substring(fromPos, tillPos);
  }
}