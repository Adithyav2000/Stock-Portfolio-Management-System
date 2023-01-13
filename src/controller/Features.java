package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import view.IView;

/**
 * Features is the interface of controller which has all the functions to be performed for view.
 */

public interface Features {

  /**
   * Exits the program.
   */

  void exitProgram();

  /**
   * Sets the view.
   *
   * @param v view object.
   */

  void setView(IView v);

  /**
   * Gets the name.
   *
   * @return string array.
   */
  String[] getNames();

  /**
   * Calls the main panel.
   */

  void mainLsp();

  /**
   * Calls the login page.
   */
  void login();

  /**
   * Calls the sign-up page.
   */
  void signup();

  /**
   * Post login functionality.
   *
   * @param username gets the username.
   * @param password gets the password.
   */
  void pLogin(String username, String password);

  /**
   * Calls the main panel for main menu.
   */

  void mainnn();

  /**
   * Post signup.
   *
   * @param text  gets the username .
   * @param text1 gets the password.
   */

  void pSignup(String text, String text1);

  /**
   * Portfolio creator.
   */

  void createPortfolio();

  /**
   * Post creates portfolio and calls next page.
   *
   * @param portfolioName portfolio name.
   */

  void postCreatePortfolio(String portfolioName);

  /**
   * Gets the composition.
   */

  void getComposition();

  /**
   * Creates flexible stock.
   */

  void createFlexStock();

  /**
   * Portfolio evaluation.
   */

  void getPfEval();

  /**
   * Gets the buy page.
   *
   * @param tick    ticker symbol.
   * @param percent gets percentage.
   */

  void postAddInvBuyNew(String tick, String percent);

  /**
   * Final buy page.
   *
   * @throws ParseException is thrown.
   * @throws IOException    is thrown.
   */
  void postBuyCb2Final() throws ParseException, IOException;

  /**
   * Buy stock page.
   */

  void buyStock();

  /**
   * zSell stock page.
   */
  void sellStock();

  /**
   * Gets cost bias.
   */
  void getCb();

  /**
   * After adding stock page.
   *
   * @param tickerButton    ticker button.
   * @param dateButton      date value.
   * @param quantityButton  quantity value.
   * @param comissionButton comm val.
   */

  void postAddStocks(String tickerButton, String dateButton, String quantityButton,
                     String comissionButton);

  /**
   * investment page.
   */
  void createInvestPf();

  /**
   * composition page.
   *
   * @param portfolioName portfolio name.
   * @throws FileNotFoundException is thrown.
   */

  void postGetComp(String portfolioName) throws FileNotFoundException;

  /**
   * add investment page.
   *
   * @param startDateButton start date.
   * @param endDateButton   end date.
   * @param termButton      term button.
   * @param amountButton    amount button.
   * @param commission      commission fee.
   */

  void postAddInv1(String startDateButton, String endDateButton, String termButton,
                   String amountButton, String commission);

  /**
   * After adding stock page.
   */

  void postAddAllStocks();

  /**
   * After adding investment page.
   *
   * @param tick    ticker symbol.
   * @param percent percentage of stock.
   */

  void postAddInv2(String tick, String percent);

  /**
   * reset page.
   */

  void postAddInv2Reset();

  /**
   * final investment page.
   *
   * @throws ParseException is thrown.
   * @throws IOException    is thrown.
   */

  void postAddInv2Final() throws ParseException, IOException;

  /**
   * gets total value.
   *
   * @param pfVal portfolio  value.
   * @param date  date got.
   */
  void postGetEval(String pfVal, String date);

  /**
   * get the total value.
   *
   * @param pfVal portfolio  value.
   * @param date  date got.
   */

  void postGetCostB(String pfVal, String date);

  /**
   * Buy dca.
   */

  void buyUsingDca();

  /**
   * get portfolio cost bias.
   *
   * @param portfolioName portfolio name.
   */

  void postGetPfb(String portfolioName);

  /**
   * post dca page.
   *
   * @param sDate      start date.
   * @param price      amount quantity.
   * @param commission comm fee.
   */

  void postAllDcaP(String sDate, String price, String commission);

  /**
   * get buy page.
   *
   * @param portfolioName portfolio name.
   */

  void postGetPfBuy(String portfolioName);

  /**
   * post buy stock page.
   *
   * @param ticker     ticker symbol.
   * @param date       date val.
   * @param quantity   amount quantity.
   * @param commission comm val.
   */
  void postAddStocksOfBuy(String ticker, String date, String quantity, String commission);

  /**
   * get portfolio sell page.
   *
   * @param portfolioName portfolio name.
   */

  void postGetPfSell(String portfolioName);

  /**
   * post val.
   *
   * @param ticker     ticker symbol.
   * @param date       date val.
   * @param quantity   amount quantity.
   * @param commission comm val.
   */

  void postAddStocksOfSell(String ticker, String date, String quantity, String commission);

  /**
   * uploads the file.
   *
   * @throws FileNotFoundException is thrown.
   */
  void uploadFile() throws FileNotFoundException;

  /**
   * after uploading page.
   *
   * @param filename file name.
   * @throws IOException is thrown.
   */

  void afterUploadFile(String filename) throws IOException;

  /**
   * create investment page.
   */

  void createInvPfNew();

  /**'
   * post investment page.
   *
   * @param portfolioName portfolio name.
   */

  void postGetPfInv(String portfolioName);

  /**
   * buy reset.
   */

  void postBuyReset();
}
