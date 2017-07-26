package org.hofi.watchlist;

import java.util.List;

import static org.hofi.watchlist.Util.formatEuroNumber;
import static org.hofi.watchlist.Util.formatString;
import static org.hofi.watchlist.Util.formatNumber;

class Aktie {
  private String name;
  private String isin;
  private String handelsplatz;
  private Double einzelpreis;
  private Integer stueckzahl;
  private Double transaktionsgeb;
  private Double einstandskurs;
  private Double preis;
  Double aktuellerKurs;
  private Double guv;
  private Double einstandswert;
  private Double guvNachGeb;

  void calculate() {
    preis = einzelpreis * stueckzahl;
    guv = aktuellerKurs * stueckzahl - preis;
    einstandswert = einstandskurs * stueckzahl;
    guvNachGeb = aktuellerKurs * stueckzahl - einstandswert;
  }

  static void outSumme(List<Aktie> aktien) {

    Double summePreis = new Double(0);
    Double summeTransaktionsgeb = new Double(0);
    Double summeGuv = new Double(0);
    Double summeEinstandswert = new Double(0);
    Double summeGuvNachGeb = new Double(0);
    for(Aktie aktie : aktien) {
      summePreis += aktie.preis;
      summeTransaktionsgeb += aktie.transaktionsgeb;
      summeGuv += aktie.guv;
      summeEinstandswert += aktie.einstandswert;
      summeGuvNachGeb += aktie.guvNachGeb;
    }

    System.out.println(
      formatString("") + " " +
      formatString("") + " " +
      formatString("") + " " +
      formatString("") + " " +
      formatString("") + " " +
      formatString(formatEuroNumber(summePreis)) + " " +
      formatString(formatEuroNumber(summeTransaktionsgeb)) + " " +
      formatString("") + " " +
      formatString(formatEuroNumber(summeGuv)) + " " +
      formatString("") + " " +
      formatString(formatEuroNumber(summeEinstandswert)) + " " +
      formatString(formatEuroNumber(summeGuvNachGeb))
    );
  }

  static void outTitle() {
    System.out.println(
      formatString("Name") + " " +
      formatString("ISIN") + " " +
      formatString("Handelsplatz") + " " +
      formatString("Einzelpreis") + " " +
      formatString("Stückzahl") + " " +
      formatString("Preis") + " " +
      formatString("Transaktionsgeb.") + " " +
      formatString("aktueller Kurs") + " " +
      formatString("Gewinn/Verlust") + " " +
      formatString("Einstandskurs") + " " +
      formatString("Einstandswert") + " " +
      formatString("G/V nach Gebühr")
    );
  }

  void out() {
    System.out.println(
      formatString(name) + " " +
      formatString(isin) + " " +
      formatString(handelsplatz) + " " +
      formatString(formatEuroNumber(einzelpreis)) + " " +
      formatString(formatNumber(stueckzahl)) + " " +
      formatString(formatEuroNumber(preis)) + " " +
      formatString(formatEuroNumber(transaktionsgeb)) + " " +
      formatString(formatEuroNumber(aktuellerKurs)) + " " +
      formatString(formatEuroNumber(guv)) + " " +
      formatString(formatEuroNumber(einstandskurs)) + " " +
      formatString(formatEuroNumber(einstandswert)) + " " +
      formatString(formatEuroNumber(guvNachGeb))
    );
  }
}
