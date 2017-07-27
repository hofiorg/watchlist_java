package org.hofi.watchlist;

import java.text.DecimalFormat;
import java.text.ParseException;

class Util {

  static String formatNumber(Number number) {
    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
    return df2.format(number);
  }

  static Number parseNumber(String s) throws ParseException {
    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
    return df2.parse(s);
  }

  static String formatEuroNumber(Double number) {
    return formatString(formatNumber(number) + "€");
  }

  static String formatString(String s) {
    return String.format("%17s", s);
  }
}
