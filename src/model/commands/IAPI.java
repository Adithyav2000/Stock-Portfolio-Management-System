package model.commands;

import java.util.function.Function;

/**
 * Api command page.
 */
public interface IAPI {
  /**
   * gets the price.
   * @param tickerSymbol ticker symbol.
   * @param date date of stock.
   * @param reader gets the reader function.
   * @return returns the price.
   */
  float getPrice(String tickerSymbol, String date, Function<String, String[][]> reader);

}
