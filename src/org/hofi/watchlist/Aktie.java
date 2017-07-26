package org.hofi.watchlist;

import static org.hofi.watchlist.Util.formatEuroNumber;

class Aktie {
  String name;
  String isin;
  String handelsplatz;
  Double einzelpreis;
  Integer stueckzahl;
  Double transaktionsgeb;
  Double einstandskurs;
  Double preis;

  void calculate() {
    preis = einzelpreis * stueckzahl;
  }

  void out() {
    System.out.println(
      name + " " +
      isin + " " +
      handelsplatz + " " +
      formatEuroNumber(einzelpreis) + " " +
      stueckzahl + " " +
      formatEuroNumber(preis) + " ");
  }
}
