package model;

import java.io.IOException;
import java.text.ParseException;

/**
 * StockModel contains all the functions which performs the actual operation.
 * it gets the input parameters from the controller and performs the desired operation.
 */
public interface StockModel {


  /**
   * This creates the portfolio by getting filename and creating into folder of user.
   *
   * @param username It takes in username and navigates to the folder to create the portfolio.
   * @param fileName File is created with the inputted File Name in the username folder.
   * @throws IOException when the input stream is not properly added to the new file created.
   */
  void portfolioCreator(String username, String fileName) throws IOException;

  /**
   * This function is used to push the stock to save it in the file.
   *
   * @param filename zpp.
   */
  void push(String filename);

  /**
   * PortfolioBuilder creates the portfolio.
   *
   * @param s is thrown.
   */
  void portfolioBuilder(String s);

  /**
   * It returns the final summation of all stocks price in that particular date multiplied with
   * the amount of stock the user holds.
   * It accesses each stock present in portfolio and calls private helper Portfoliogetval which
   * returns value of particular stock in that entered date.
   * Each of these values is added and returned as float.
   *
   * @param date gets date.
   * @return float value of the total.
   */
  float portfolioTotal(String date);

  /**
   * Delete portfolio gets the username and filename to generate the path and delete the portfolio.
   *
   * @param username username under which the portfolio is created.
   * @param fileName The name of the portfolio is the file name which is going to be deleted.
   */
  void deletePortfolio(String username, String fileName);

  /**
   * is thrown.
   *
   * @param dataDate  is thrown.
   * @param inputDate is thrown.
   * @return is thrown.
   * @throws ParseException is thrown.
   */

  boolean dateCheck(String dataDate, String inputDate) throws ParseException;


  /**
   * beta call gets Tickersymbol and date and fetches the value of the stock in that particular
   * date by getting it from ALPHAVANTAGE API.
   *
   * @param tickerSymbol used to fetch the stock.
   * @param date         Date for which stock price is required.
   * @return price value as string.
   * @throws IOException If the fetched API data cant be written into the file.
   * @throws ParseException is thrown.
   */

  Float priceApiFetch(String tickerSymbol, String date) throws ParseException, IOException;
}