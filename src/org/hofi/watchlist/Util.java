package org.hofi.watchlist;

import java.text.DecimalFormat;

class Util {
  static String formatNumber(Double number) {
    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
    return df2.format(number);
  }
  static String formatEuroNumber(Double number) {
    return formatNumber(number) + "€";
  }
}
