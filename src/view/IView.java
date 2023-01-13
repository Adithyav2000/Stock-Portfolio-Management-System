package view;

import controller.Features;

/**
 * The interface for our view class.
 */
public interface IView {

  /**
   * Login features gets and sets username password listener and getter.
   *
   * @param features it is a controller listener object.
   */
  void addLoginFeatures(Features features);

  /**
   * Main feature which assigns the default frames and resolution for the program.
   *
   * @param features it is a controller listener object.
   */
  void addFeatures(Features features);

  /**
   * Echo output sets the string for printing the output.
   *
   * @param s is the output to be printed.
   */

  void setEchoOutput(String s);

  /**
   * Output for composition printing.
   *
   * @param s composition of string value in 2d array.
   */
  void setEchoOutputComp(String[][] s);

  /**
   * Output for cost bias.
   *
   * @param s Value of cost bias.
   */
  void setEchoOutputCb(String s);

  /**
   * Output for total value.
   *
   * @param s Value of total value.
   */
  void setEchoOutputPfVal(String s);

  /**
   * Main panel is the root panel which is created at the start of the program.
   */
  void mainPannel();

  /**
   * Sets the login page for getting input username and password.
   */
  void loginPage();

  /**
   * We use it as an information display dialog box.
   *
   * @param message message to be printed.
   * @param head    title of the box.
   */
  void infoPop(String message, String head);

  /**
   * Use it as an information to display.
   *
   * @param s    message to be printed.
   * @param head title of the box.
   */
  void errorPop(String s, String head);

  /**
   * Signup page is used to create the user account.
   */
  void signUpPage();

  /**
   * It is a listener object which takes in user input and returns to controller.
   *
   * @param features Listener object of controller.
   */
  void addSignUpFeatures(Features features);

  /**
   * Displays login and signup during start of the program.
   */
  void mainMenuPage();

  /**
   * Gets the input, login and signup from the user and sends it to the controller.
   *
   * @param features Listener object of controller.
   */
  void addMainMenu(Features features);

  /**
   * Creates a portfolio for the user.
   */
  void createPortfolioPage();

  /**
   * Gets the portfolio value.
   *
   * @param features Listener object of controller.
   */
  void addCreatePf(Features features);

  /**
   * Creates the stock page for the user.
   */
  void stocksPage();

  /**
   * Gets the stocks and sends it to the controller.
   *
   * @param features Listener object of controller.
   */
  void addStocks(Features features);

  /**
   * Displays the investment page to the user.
   */
  void investmentPage();

  /**
   * Gets the iv2 value from the user page.
   *
   * @param features Listener object of controller.
   */
  void addInvestType(Features features);

  /**
   * Displays the investments requisites to the user.
   */
  void getInvPre();

  /**
   * Passes the user's input to the user.
   *
   * @param features Listener object of controller.
   */
  void addInvPre(Features features);

  /**
   * Gets Composition value of the portfolio name and sends it to the controller.
   *
   * @param features Listener object of controller.
   */
  void compGetPfPage(Features features);

  /**
   * Sends the composition value display page to the controller.
   *
   * @param features Listener object of controller.
   */
  void addCompGet(Features features);

  /**
   * Display composition of the user.
   *
   * @param output zpp.
   */
  void dispComp(String[][] output);

  /**
   * Sends the isp value to the user.
   *
   * @param features Listener object of controller.
   */
  void adddIsp(Features features);

  /**
   * Gets the iv2 value from the user page.
   */
  void getInv2();

  /**
   * Gets the iv2 value from the user page.
   *
   * @param features Listener object of controller.
   */
  void addInv2(Features features);

  /**
   * Gets the iv2 value from the user page.
   *
   * @param features Listener object of controller.
   */
  void toteValGetPf(Features features);

  /**
   * Gets the iv2 value from the user page.
   *
   * @param features Listener object of controller.
   */
  void addTotaleValGet(Features features);

  /**
   * Gets the iv2 value from the user page.
   *
   * @param features Listener object of controller.
   */
  void buyDcaGetPfn(Features features);

  /**
   * Gets the iv2 value from the user page.
   *
   * @param features Listener object of controller.
   */
  void addBuyCapF(Features features);

  /**
   * Gets the dca value and sends it to the controller.
   *
   * @param features Listener object of controller.
   */
  void addGetAllDca(Features features);

  /**
   * Displays the page for getting dca values.
   */
  void getAllDca();

  /**
   * Buying using dca display page.
   */
  void fdCaBuy();

  /**
   * Gets the input and sends it to the controller.
   *
   * @param features Listener object of controller.
   */
  void addFinalDcaBuy(Features features);

  /**
   * Gets the buy value and sends it to the controller.
   *
   * @param features Listener object of controller.
   */
  void getPfBuy(Features features);

  /**
   * Gets the portfolio name and sends it to the controller.
   *
   * @param features Listener object of controller.
   */
  void addBuyPf(Features features);

  /**
   * Gets date to buy and sends it to controller.
   */
  void getsDataForBuy();

  /**
   * Get data for buy and sends it to controller.
   *
   * @param features Listener object of controller.
   */
  void addsDataForBuy(Features features);

  /**
   * Gets portfolio name for selling.
   *
   * @param features Listener object of controller.
   */
  void getPfSell(Features features);

  /**
   * Get data for selling and sends it to controller.
   *
   * @param features Listener object of controller.
   */
  void addPfSell(Features features);

  /**
   * Gets data for selling.
   */
  void getsDataForSell();

  /**
   * Sell multiple add.
   *
   * @param features Listener object of controller.
   */
  void addsDataForSell(Features features);

  /**
   * Gets cost basis portfolio name.
   *
   * @param features Listener object of controller.
   */
  void getCbPf(Features features);

  /**
   * Cost basis gets value and sends it to the controller.
   *
   * @param features Listener object of controller.
   */
  void addCbGet(Features features);

  /**
   * Gets portfolio for investment based portfolio.
   *
   * @param features Listener object of controller.
   */
  void getPfForInvPf(Features features);

  /**
   * Get portfolio and sends it to controller.
   *
   * @param features Listener object of controller.
   */
  void addGetPfForInv(Features features);

  /**
   * For upload get upload display.
   */
  void getPfNameForUp();

  /**
   * Gets the file to be uploaded location to controller.
   *
   * @param features Listener object of controller.
   */
  void addPfForUp(Features features);
}
