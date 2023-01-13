package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * Interface of Main model.
 */

public interface IModel {
  /**
   * Returns all the file name in the folder in string array format.
   *
   * @param type     type either flexible or Inflexible.
   * @param username username of the logged in user.
   * @return String array .
   */
  String[] getFileList(String type, String username);

  /**
   * It takes in the fetched file, the filename in which the target data to be written
   * and type of the file. In case of type 2 it redirects it to stockmodelimpl In case of type 2
   * It sends it to StockModelImplFlex.
   *
   * @param s        is Fetched File.
   * @param filename File Name.
   * @param username User Name.
   * @param type     Type of portfolio.
   * @throws ParseException is thrown when the date in the uploaded files are invalid.
   */

  void upload(String[][] s, String filename, String username, String type) throws ParseException;

  /**
   * Takes in username and password and checks if the username is in the userbase and the password
   * matching is also the same. If its true then it returns the value true else it returns false.
   *
   * @param username is username.
   * @param password is password.
   * @return True if login is approved False if the login credentials are false.
   * @throws FileNotFoundException is thrown when the user-base cant be accessed or found.
   */
  boolean login(String username, String password) throws FileNotFoundException;

  /**
   * Creates the user for the program by saving username and password in the user-base.
   *
   * @param username is inputted and checked if already exists.
   * @param password is inputted and stored in the user-base.
   * @throws IOException when the inputted parameter cant be written into the user-base.
   */
  void createUser(String username, String password) throws IOException;

  /**
   * This creates the portfolio by getting filename and creating into folder of user.
   *
   * @param username It takes in username and navigates to the folder to create the portfolio.
   * @param fileName File is created with the inputted File Name in the username folder.
   * @throws IOException when the input stream is not properly added to the new file created.
   */
  void portfolioCreator(String username, String fileName, String type) throws IOException;

  /**
   * /**
   * PortfolioBuilder creates the portfolio.
   *
   * @param input it contains the value to be written in the csv file.
   * @param type  Type of portfolio.
   */
  void portfolioBuilder(String input, String type) throws ParseException;

  /**
   * Updates all the object data stored in linked list to the file.
   *
   * @param username is username.
   * @param type     is type.
   * @param filename is filename.
   */
  void push(String username, String type, String filename);

  /**
   * It returns the final summation of all stocks price in that particular date multiplied with
   * the amount of stock the user holds.
   * It accesses each stock present in portfolio and calls private helper Portfoliogetval which
   * returns value of particular stock in that entered date.
   * Each of these values is added and returned as float.
   *
   * @param username is username.
   * @param date     is date.
   * @param type     is type.
   * @param filename is filename.
   * @return Total value of portfolio.
   * @throws IOException          is thrown.
   * @throws ParseException       is thrown when the date format cannot be parsed.
   * @throws InterruptedException is thrown.
   */
  float portfolioTotal(String username, String date, String type, String filename)
          throws IOException, ParseException, InterruptedException;

  /**
   * Fetch fetches the value as 2 dimension array where
   * the first index holds the row of the csv file fetched.
   * It takes in the csv file.
   *
   * @param fileName is filename.
   * @param type     is type.
   * @return 2D array with all the file data.
   * @throws FileNotFoundException is thrown when the file to be found doesn't exist.
   */
  String[][] fetch(String fileName, String type) throws FileNotFoundException;

  /**
   * Delete the portfolio by deleting the file.
   *
   * @param username is username.
   * @param fileName is filename.
   * @param type     is type.
   */
  void deletePortfolio(String username, String fileName, String type);

  /**
   * beta call gets Tickersymbol and date and fetches the value of the stock in that particular
   * date by getting it from ALPHAVANTAGE API.
   *
   * @param tickerSymbol is tickerSymbol.
   * @param date         is date.
   * @return is value on that particular Date.
   * @throws ParseException is thrown when the date format cannot be parsed.
   * @throws IOException    is thrown.
   */
  Float priceApiFetch(String tickerSymbol, String date) throws ParseException, IOException;

  /**
   * Buy check checks existence of its portfolio and its type.
   * It allows the buying of portfolio when it returns the true value.
   *
   * @param username Username of the user to add the stock to.
   * @param filename File name contains the portfolio in which this stock is added to.
   * @return true if buying is possible else returns false.
   */
  boolean buyCheck(String username, String filename);

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
   * @throws FileNotFoundException is thrown when the file to be bought doesn't exists.
   * @throws ParseException        When the date cannot be parsed into format.
   */
  void portfolioBuy(String username, String filename, String buy) throws FileNotFoundException,
          ParseException;

  /**
   * It calculates the total value by adding all the stocks purchased and subtracts the value sold.
   * Before the date queried by the user.
   *
   * @param date     is date in which total value is to be calculated.
   * @param username is username.
   * @param type     is type.
   * @param filename is filename.
   * @return Total Flexible value on that particular date.
   * @throws IOException          When the value cannot be written in the portfolio.
   * @throws ParseException       When the date cannot be parsed into format.
   * @throws InterruptedException is thrown.
   */
  float flexibleTotalVal(String date, String username, String type, String filename)
          throws IOException,
          ParseException, InterruptedException;

  /**
   * Gives the total Amount Invested in the portfolio along with the commission fees.
   *
   * @param type     is type.
   * @param username is username.
   * @param date     is date.
   * @param filename is filename.
   * @return the cost basis value.
   * @throws FileNotFoundException If the file cannot be found.
   * @throws ParseException        when the date cannot be parsed into the format.
   */
  float costBasis(String type, String username, String date, String filename)
          throws FileNotFoundException, ParseException;

  /**
   * Gets the from date and checks it with the least date from the portfolio. If date is less than
   * 7 days it throws exceptions. If not it creates graph for daily weekly or monthly.
   *
   * @param type     is type.
   * @param username is username.
   * @param pFall    is the fetched value of the portfolio.
   * @param dateFrom is from date.
   * @param dateTo   is to date.
   * @param file     is filename.
   * @return Plotted graph with in string format.
   * @throws FileNotFoundException If the file cannot be found.
   * @throws ParseException        when the date cannot be parsed into the format.
   */
  String graphBuilder(String type, String username, String[][] pFall, String dateFrom,
                      String dateTo, String file)
          throws FileNotFoundException, ParseException;

  /**
   * Setup function for portfolio creation along with Investment strategy.
   * here we get all the data and calculates the days between and check for other validations.
   * Finally sends to the dca function for actual value assigning.
   *
   * @param op       is From Date, To date and Term for computation .
   * @param cost     is cost.
   * @param price    is price.
   * @param comm     is comm.
   * @param filename is filename.
   * @param username is username.
   * @throws IOException    If the file cannot be found.
   * @throws ParseException when the date cannot be parsed into the format.
   */

  void seTip(String op, HashMap<String, Float> cost, String price, String comm, String filename,
             String username
  ) throws ParseException, IOException;

  /**
   * Function for Dollar Cost Averaging.
   *
   * @param type     is type.
   * @param username is username.
   * @param filename is filename.
   * @param comp     is comp.
   * @param amount   is amount.
   * @param date     is date.
   * @param comm     is comm.
   * @throws ParseException If the file cannot be found.
   * @throws ParseException when the date cannot be parsed into the format.
   */

  void buyDca(String type, String username, String filename, HashMap<String, Float> comp,
              String amount, String date, String comm) throws IOException, ParseException;

  /**
   * Creates Composition format for the gui and sends it to the controller.
   *
   * @param username is username.
   * @param filename is filename.
   * @return 2D array for composition.
   * @throws FileNotFoundException is thrown when file is not found.
   */
  String[][] comp(String username, String filename) throws FileNotFoundException;
}
