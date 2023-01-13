package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * Interface of StockModelFlex.
 */

public interface StockModelFlex extends StockModel {

  /**
   * It takes in the input from the user for selling and compares the stock count before
   * that particular date and successfully adds it to portfolio if possible.
   * Here it will save quantity as negative to represent selling.
   *
   * @param username Username of the user to deduct the stock from.
   * @param filename File name contains the portfolio in which this stock is deducted from.
   * @param input    input from the user for selling and compares the stock count
   * @throws IOException    When the value cannot be written in the portfolio.
   * @throws ParseException When the date cannot be parsed into format.
   */

  void portfolioSell(String username, String filename, String input)
          throws IOException, ParseException;

  /**
   * Takes in input from the user for buying and sends it to buy check for verification.
   *
   * @param username Username of the user to add the stock to.
   * @param filename File name contains the portfolio in which this stock is added to.
   * @param buy      input from the user for adding the stock value.
   */
  void portfolioBuy(String username, String filename, String buy);

  /**
   * It calculates the total value by adding all the stocks purchased and subtracts the value sold.
   * Before the date queried by the user.
   * .
   *
   * @param date date for which total value to be calculated.
   * @return returns the total value of the portfolio in float value.
   * @throws ParseException       is thrown.
   * @throws IOException          is thrown.
   * @throws InterruptedException is thrown.
   */
  float flexibleTotalVal(String date) throws IOException,
          ParseException, InterruptedException;

  /**
   * Gives the total Amount Invested in the portfolio along with the commission fees.
   *
   * @param date date for which cost basis to be calculated.
   * @return the cost basis value.
   * @throws FileNotFoundException If the file cannot be found.
   * @throws ParseException        when the date cannot be parsed into the format.
   */
  float costBasis(String date) throws FileNotFoundException, ParseException;

  /**
   * Gets the from date and checks it with the least date from the portfolio. If date is less than
   * 7 days it throws exceptions. If not it creates graph for daily weekly or monthly.
   *
   * @param pFall    the fetched value of the portfolio.
   * @param dateFrom Start date for the graph plot.
   * @param dateTo   last date for the graph plot.
   * @return the Plotted graph with in string format.
   * @throws FileNotFoundException If the file cannot be found.
   * @throws ParseException        when the date cannot be parsed into the format.
   */
  String graphBuilder(String[][] pFall, String dateFrom, String dateTo)
          throws FileNotFoundException, ParseException;

  /**
   * Setup function for portfolio creation along with Investment strategy.
   * here we get all the data and calculates the days between and check for other validations.
   * Finally sends to the dca function for actual value assigning.
   *
   * @param op    Does as directed.
   * @param cost  Does as directed.
   * @param price Does as directed.
   * @param comm  Does as directed.
   * @throws ParseException Does as directed.
   * @throws IOException    Does as directed.
   */
  void seTip(String op, HashMap<String, Float> cost, String price, String comm)
          throws ParseException, IOException;

  /**
   * Function for Dollar Cost Averaging.
   *
   * @param cost  Does as directed.
   * @param price Does as directed.
   * @param date  Does as directed.
   * @param comm  Does as directed.
   * @throws ParseException Does as directed.
   * @throws IOException    Does as directed.
   */
  void dca(HashMap<String, Float> cost, String price, String date, String comm)
          throws ParseException, IOException;
}
