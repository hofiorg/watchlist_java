package org.hofi.watchlist;

import java.util.List;

import static org.hofi.watchlist.Util.formatEuroNumber;
import static org.hofi.watchlist.Util.formatString;
import static org.hofi.watchlist.Util.formatNumber;

class Aktie {

  private static final String EMPTY_HEADER = formatString("") + " ";

  String name;
  private String isin;
  private String handelsplatz;
  private Double einzelpreis;
  private Integer stueckzahl;
  private Double transaktionsgeb;
  private Double einstandskurs;
  private Double preis;
  Double aktuellerKurs;
  String kursZeit;
  private Double guv;
  private Double einstandswert;
  private Double guvNachGeb;
  String boerseKurs;

  void calculate() {
    preis = einzelpreis * stueckzahl;
    guv = aktuellerKurs * stueckzahl - preis;
    einstandswert = einstandskurs * stueckzahl;
    guvNachGeb = aktuellerKurs * stueckzahl - einstandswert;
  }

  static void outSumme(List<Aktie> aktien) {

    Double summePreis = 0d;
    Double summeTransaktionsgeb = 0d;
    Double summeGuv = 0d;
    Double summeEinstandswert = 0d;
    Double summeGuvNachGeb = 0d;
    for(Aktie aktie : aktien) {
      summePreis += aktie.preis;
      summeTransaktionsgeb += aktie.transaktionsgeb;
      summeGuv += aktie.guv;
      summeEinstandswert += aktie.einstandswert;
      summeGuvNachGeb += aktie.guvNachGeb;
    }

    System.out.println(
      EMPTY_HEADER +
      EMPTY_HEADER +
      EMPTY_HEADER +
      EMPTY_HEADER +
      EMPTY_HEADER +
      formatString(formatEuroNumber(summePreis)) + " " +
      formatString(formatEuroNumber(summeTransaktionsgeb)) + " " +
      EMPTY_HEADER +
      EMPTY_HEADER +
      EMPTY_HEADER +
      formatString(formatEuroNumber(summeGuv)) + " " +
      EMPTY_HEADER +
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
      formatString("Transgeb.") + " " +
      formatString("aktueller Kurs") + " " +
      formatString("Kurszeit") + " " +
      formatString("Börse Kurs") + " " +
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
      formatString(kursZeit) + " " +
      formatString(boerseKurs) + " " +
      formatString(formatEuroNumber(guv)) + " " +
      formatString(formatEuroNumber(einstandskurs)) + " " +
      formatString(formatEuroNumber(einstandswert)) + " " +
      formatString(formatEuroNumber(guvNachGeb))
    );
  }
}
