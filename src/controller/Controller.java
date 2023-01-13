package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JFileChooser;

import javax.swing.filechooser.FileNameExtensionFilter;

import model.IModel;
import view.IView;

/**
 * This is the controller that contains the listener of the view and also has
 * the object of the model and acts between the view and model.
 */
public class Controller extends JFrame implements Features {
  HashMap<String, Float> comp;
  private IModel model;
  private IView view;
  private String username;
  private String iDataOp;
  private String p;
  private String c;
  private String type = "1";
  private float percentage = 0.0f;
  private String portfolioName;

  /**
   * This is the constructor for the controller.
   *
   * @param m is the model object on whih the model does the operation..
   */
  public Controller(IModel m) {
    model = m;
    comp = new HashMap<>();

  }

  @Override
  public void setView(IView v) {
    view = v;
    //provide view with all the callbacks
    view.addFeatures(this);
  }

  @Override
  public String[] getNames() {
    return model.getFileList(type, username);
  }


  @Override
  public void mainLsp() {
    view.mainPannel();
    view.addFeatures(this);
  }

  @Override
  public void login() {
    view.loginPage();
    view.addLoginFeatures(this);
  }

  @Override
  public void signup() {
    view.signUpPage();
    view.addSignUpFeatures(this);
  }

  @Override
  public void pLogin(String username, String password) {
    try {
      if (model.login(username, password)) {
        mainnn();
        this.username = username;
      } else {
        view.infoPop("Enter valid username and password", "invalid Entry");
      }
    } catch (Exception e) {
      view.infoPop("Enter Valid Input", "Invalid Entry");
    }

  }

  @Override
  public void mainnn() {
    view.mainMenuPage();
    view.addMainMenu(this);
  }

  @Override
  public void pSignup(String username, String password) {
    if (username.equals("")) {
      view.errorPop("Please Enter Valid Username", "Invalid Entry");
    } else if (username.length() != 5) {
      view.errorPop("Please Enter Username with 5 characters", "Invalid Entry");
    } else if (password.equals("")) {
      view.errorPop("Please Enter Valid Password", "Invalid Entry");
    } else if (username.length() != 5) {
      view.errorPop("Please Enter Username with 5 characters", "Invalid Entry");
    } else if (username.equals(password)) {
      view.errorPop("Username cannot be same", "Invalid Entry");
    } else {
      try {
        model.createUser(username, password);
        view.errorPop("User Successfully Created", "User Creation Successful");
        view.loginPage();
        view.addLoginFeatures(this);
      } catch (Exception e) {
        view.errorPop("Username cannot be same", "Invalid Entry");
      }

    }
  }

  @Override
  public void createPortfolio() {
    view.createPortfolioPage();
    view.addCreatePf(this);
  }


  @Override
  public void postCreatePortfolio(String portfolioName) {
    String filename = "flex" + "/" + portfolioName;
    File path = new File(username + "/flex");
    if (!path.exists()) {
      path.mkdirs();
    }
    try {
      model.portfolioCreator(username, filename, type);
      this.portfolioName = portfolioName;
      view.errorPop(portfolioName + " portfolio Successfully created" + " to user "
              + username, "Success");
      view.investmentPage();
      view.addInvestType(this);
    } catch (Exception e) {
      this.view.errorPop(e.getMessage(), "Invalid portfolio name");
    }

  }

  @Override
  public void createFlexStock() {
    view.stocksPage();
    view.addStocks(this);
  }

  @Override
  public void postAddStocks(String ticker, String date, String quantity, String commission) {

    try {
      String price = String.format("%.3f", model.priceApiFetch(ticker, date));
      Integer.parseInt(quantity);
      if (Integer.parseInt(quantity) == 0) {
        throw new IllegalArgumentException("Quantity cannot be 0.\n");
      }
      if (commission.contains("-")) {
        throw new IllegalArgumentException("Commission Fee is negative.\n");
      }
      model.portfolioBuilder(ticker + "," + date + "," + quantity + "," + price + ","
              + commission + "\n", type);
      model.push(username, type, "flex/" + portfolioName);
      view.infoPop("Stock Added Successfully", "Success");
    } catch (Exception e) {
      if (ticker.equals("") || date.equals("") || quantity.equals("") || quantity.equals("")) {
        view.errorPop("Blank fields found!!", "Invalid Entry");
      } else if (e.getMessage() == null) {
        view.errorPop("wrong data", "Invalid Entry");
      } else if (e.getMessage().contains("index 4")) {
        view.errorPop("Enter Comission fee properly", "Invalid Entry");
      } else if (e.getMessage().contains("Index")) {
        view.errorPop(e.getMessage(), "Invalid Entry");
      } else if (e.getMessage().contains("For")) {
        view.errorPop("Enter Quantity Correctly!!", "Invalid Entry");
      } else {
        view.errorPop(e.getMessage(), "Invalid Entry");
      }
    }
    view.stocksPage();
    view.addStocks(this);
  }

  @Override
  public void postAddAllStocks() {
    resetAll();
    mainnn();
  }

  @Override
  public void createInvestPf() {
    view.getInvPre();
    view.addInvPre(this);
  }


  @Override
  public void postAddInv1(String startDateButton, String endDateButton, String term, String amount,
                          String commission) {
    try {
      DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

      LocalDate date1 = LocalDate.parse(startDateButton, f);
      if (endDateButton.equals("") && date1.isBefore(LocalDate.now())) {
        endDateButton = LocalDate.now().plusDays(20 + Integer.parseInt(term)).toString();
      }

      LocalDate date2 = LocalDate.parse(endDateButton, f);
      if (date2.isBefore(date1)) {
        throw new IllegalArgumentException("end date is greater than date 1");
      }
      Integer.parseInt(term);
      String tmp = Arrays.toString(date1.datesUntil(date2).toArray());
      String[] dates2 = ((tmp.substring(1, tmp.length() - 1)) + ", " + date2).split(", ");
      int days = dates2.length;
      if (days < Integer.parseInt(term)) {
        throw new IllegalArgumentException("Terms Cant be greater than"
                + " TO date and From Date Length");
      }
      if (Float.parseFloat(amount) <= 0) {
        throw new IllegalArgumentException("Enter valid amount. "
                + "amount should be a number greater than 0");
      }
      if (Float.parseFloat(commission) < 0) {
        throw new IllegalArgumentException("Commission cannot be negative");
      }
      iDataOp = startDateButton + "," + endDateButton + "," + term;
      p = amount;
      c = commission;
      view.getInv2();
      view.addInv2(this);

    } catch (Exception e) {
      if (e.getMessage() == null) {
        view.errorPop("wrong data", "Invalid Entry");
      } else if (e.getMessage().contains("Index")) {
        view.errorPop("Empty String or invalid input", "Invalid Entry");
      } else if (e.getMessage().contains("For")) {
        view.errorPop("Enter Quantity Correctly!!", "Invalid Entry");
      } else if (e.getMessage().contains("index 0")) {
        view.infoPop("Enter valid Date", "Wrong entry");
      } else if (e.getMessage().contains("for input string")) {
        view.infoPop("Enter valid term", "Wrong entry");
      } else {
        view.infoPop("Invalid input", "Invalid Entry");

      }
      createInvestPf();
    }
  }


  @Override
  public void postAddInv2(String tick, String percent) {
    try {
      model.priceApiFetch(tick, "2022-11-01");
      if (percentage + Float.parseFloat(percent) <= 100) {
        if (Float.parseFloat(percent) > 0) {
          if (!comp.containsKey(tick)) {
            percentage = Float.parseFloat(percent) + percentage;
            comp.put(tick, Float.parseFloat(percent));
            view.errorPop("Stock Added Successfully", "Success");
          } else {
            throw new IllegalArgumentException("Enter a new ticker symbol");
          }

        } else {
          throw new IllegalArgumentException("Enter percent less than 0");
        }
      } else {
        throw new IllegalArgumentException("Total percentage Greater than 100");
      }
    } catch (Exception e) {
      view.infoPop(e.getMessage(), "Invalid Entry");
    }

    view.getInv2();
    view.setEchoOutput("Percent To be added is " + String.format("%.2f", 100 - percentage) + "%");
    view.addInv2(this);

  }

  private void resetAll() {
    portfolioName = "";
    percentage = 0.0f;
    comp = new HashMap<>();
    p = "";
    c = "";
    iDataOp = "";

  }

  @Override
  public void postAddInv2Reset() {
    percentage = 0.0f;
    comp = new HashMap<>();
    view.getInv2();
    view.setEchoOutput("Percent To be added is " + String.format("%.2f", 100 - percentage) + "%");
    view.addInv2(this);
    view.infoPop("Reset done", "reset");

  }

  @Override
  public void postAddInv2Final() throws ParseException, IOException {
    if (percentage < 100) {
      view.infoPop("You Need 100 percent of your Amount to Buy", "Invalid");


    } else {
      model.seTip(iDataOp, comp, p, c, portfolioName, username);
      model.push(username, type, "flex/" + portfolioName);
      view.infoPop("Invest plan Added and scheduled successfully", "success");
      resetAll();
      mainnn();
    }
  }


  @Override
  public void getComposition() {
    view.compGetPfPage(this);
    view.addCompGet(this);
  }

  @Override
  public void postGetComp(String portfolioName) throws FileNotFoundException {
    String[][] s = model.comp(username, portfolioName);
    view.compGetPfPage(this);


    view.setEchoOutputComp(s);
    view.addCompGet(this);

  }

  @Override
  public void getPfEval() {
    view.toteValGetPf(this);
    view.addTotaleValGet(this);
  }

  @Override
  public void postGetEval(String pfVal, String date) {
    try {
      DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
      LocalDate date1 = LocalDate.parse(date, f);

      view.infoPop("Total Value of Portfolio is"
              + "$" + String.format("%.2f",
              model.flexibleTotalVal(date, username, type, pfVal)), "Success");
      getPfEval();
      view.setEchoOutputPfVal("Total Value of Portfolio is "
              + ":$ " + String.format("%.2f", model.flexibleTotalVal(date, username, type, pfVal))
              + " on " + date);

    } catch (Exception e) {
      if (e.getMessage().contains("could not be parsed")) {
        view.infoPop("Invalid date", "Invalid date");
      }

    }


  }

  @Override
  public void getCb() {
    view.getCbPf(this);
    view.addCbGet(this);
  }

  @Override
  public void postGetCostB(String pfVal, String date) {
    try {
      if (!model.buyCheck(username, "flex/" + pfVal)) {
        throw new IllegalArgumentException("\nWrong file name Entered (Try again)\n");
      }
      Float a = 0.00f;
      a = model.costBasis(type, username, date, pfVal);
      view.infoPop("Cost Basis for the portfolio" + pfVal + "is $" +
              String.format("%.2f", a), "" +
              "Success");
      view.getCbPf(this);
      view.setEchoOutputCb("Cost Basis for the portfolio " + pfVal + " is $" +
              String.format("%.2f", a) + " on " + date);
      view.addCbGet(this);
    } catch (Exception e) {
      if (e.getMessage().contains("Wrong file")) {
        view.infoPop("Invalid filename", "Invalid Entry");
      } else {
        view.infoPop("Wrong Date Entered", "Invalid Entry");
      }
    }

  }

  @Override
  public void buyUsingDca() {
    view.buyDcaGetPfn(this);
    view.addBuyCapF(this);
  }

  @Override
  public void postGetPfb(String portfolioName) {
    if (model.buyCheck(username, "flex/" + portfolioName)) {

      view.infoPop("Successfully found portfolio", "Success");
      this.portfolioName = portfolioName;
      view.getAllDca();
      view.addGetAllDca(this);
    } else {
      view.infoPop("Portfolio name not found", "Invalid entry");
      buyUsingDca();

    }
  }

  @Override
  public void postAllDcaP(String sDate, String price, String commission) {
    try {
      DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

      LocalDate date1 = LocalDate.parse(sDate, f);
      if (Float.parseFloat(price) <= 0) {
        throw new IllegalArgumentException("Enter price greater thab zero");
      }
      if (Float.parseFloat(commission) < 0) {
        throw new IllegalArgumentException("Negative Comission fee");
      }
      iDataOp = sDate + "," + price + "," + commission;

      view.fdCaBuy();
      view.addFinalDcaBuy(this);
    } catch (Exception e) {

      if (e.getMessage() == null) {
        view.errorPop("wrong data", "Invalid Entry");
      } else if (e.getMessage().contains("Index")) {
        view.errorPop("Empty String or invalid input", "Invalid Entry");
      } else if (e.getMessage().contains("For")) {
        view.errorPop("Enter Quantity Correctly!!", "Invalid Entry");
      } else if (e.getMessage().contains("index 0")) {
        view.infoPop("Enter valid Date", "Wrong entry");
      } else if (e.getMessage().contains("for input string")) {
        view.infoPop("Enter valid term", "Wrong entry");
      } else {
        view.infoPop("Invalid input", "Invalid Entry");

      }
      view.getAllDca();
      view.addGetAllDca(this);
    }

  }


  @Override
  public void postAddInvBuyNew(String tick, String percent) {
    try {
      model.priceApiFetch(tick, "2022-11-01");
      if (percentage + Float.parseFloat(percent) <= 100) {
        if (Float.parseFloat(percent) > 0) {
          if (!comp.containsKey(tick)) {
            percentage = Float.parseFloat(percent) + percentage;
            comp.put(tick, Float.parseFloat(percent));
            view.infoPop("Stock Added Successfully", "Success");
          } else {
            throw new IllegalArgumentException("Enter a new ticker symbol");
          }

        } else {
          throw new IllegalArgumentException("Enter percent less than 0");
        }
      } else {
        throw new IllegalArgumentException("Total percentage Greater than 100");
      }
    } catch (Exception e) {
      view.infoPop(e.getMessage(), "Invalid Entry");
    }

    view.fdCaBuy();
    view.setEchoOutput("Percent To be added is " + String.format("%.2f", 100 - percentage) + "%");
    view.addFinalDcaBuy(this);

  }

  @Override
  public void postBuyCb2Final() throws ParseException, IOException {
    if (percentage < 100) {
      view.fdCaBuy();
      view.addFinalDcaBuy(this);
      view.infoPop("You Need 100 percent of your Amount to Buy", "Invalid");

    } else {

      String[] s = iDataOp.split(",");
      model.buyDca(type, username, portfolioName, comp, s[1], s[0], s[2]);
      model.push(username, type, "flex/" + portfolioName);
      view.infoPop("Invest plan Added and scheduled successfully", "success");
      resetAll();
      mainnn();
    }

  }

  @Override
  public void buyStock() {
    view.getPfBuy(this);
    view.addBuyPf(this);
  }

  @Override
  public void postGetPfBuy(String portfolioName) {

    if (model.buyCheck(username, "flex/" + portfolioName)) {

      view.errorPop("Successfully found portfolio", "Success");
      this.portfolioName = portfolioName;
      view.getsDataForBuy();
      view.addsDataForBuy(this);
    } else {
      view.infoPop("Portfolio name not found", "Invalid entry");
      buyStock();

    }
  }

  @Override
  public void postAddStocksOfBuy(String ticker, String date, String quantity, String commission) {
    try {
      String price = String.format("%.3f", model.priceApiFetch(ticker, date));
      Integer.parseInt(quantity);
      if (Integer.parseInt(quantity) == 0) {
        throw new IllegalArgumentException("Quantity cannot be 0.\n");
      }
      if (Float.parseFloat(commission) * -1 > 0) {
        throw new IllegalArgumentException("Commission Fee is negative.\n");
      }
      model.portfolioBuy(username, portfolioName, ticker + "," + date + "," + quantity
              + "," + price + "," + commission + "\n");

      model.push(username, type, "flex/" + portfolioName);
      view.errorPop("Stock Bought Successfully", "Success");


    } catch (Exception e) {
      if (ticker.equals("") || date.equals("") || quantity.equals("") || commission.equals("")) {
        view.errorPop("Empty value found", "Invalid Entry");

      } else if (e.getMessage() == null) {
        view.errorPop("wrong data", "Invalid Entry");
      } else if (e.getMessage().contains("Index")) {
        view.errorPop("Wrong Date Entered", "Invalid Entry");
      } else if (e.getMessage().contains("For")) {
        view.errorPop("Enter Quantity Correctly!!", "Invalid Entry");
      } else {
        view.errorPop(e.getMessage(), "Invalid Entry");
      }
    }
    view.getsDataForBuy();
    view.addsDataForBuy(this);
  }

  @Override
  public void sellStock() {
    view.getPfSell(this);
    view.addPfSell(this);
  }

  @Override
  public void postGetPfSell(String portfolioName) {
    if (model.buyCheck(username, "flex/" + portfolioName)) {

      view.errorPop("Successfully found portfolio", "Success");
      this.portfolioName = portfolioName;
      view.getsDataForSell();
      view.addsDataForSell(this);
    } else {
      view.errorPop("Portfolio name not found", "Invalid entry");
      sellStock();
    }
  }

  @Override
  public void postAddStocksOfSell(String ticker, String date, String quantity,
                                  String commission) {

    try {
      String price = String.format("%.3f", model.priceApiFetch(ticker, date));
      Integer.parseInt(quantity);
      if (Integer.parseInt(quantity) == 0) {
        throw new IllegalArgumentException("Quantity cannot be 0.\n");
      }
      if (Float.parseFloat(commission) * -1 > 0) {
        throw new IllegalArgumentException("Commission Fee is negative.\n");
      }
      model.portfolioSell(username, portfolioName, ticker + "," + date + "," + quantity
              + "," + price + "," + commission);
      model.push(username, type, "flex/" + portfolioName);
      view.errorPop("Stock Sold Successfully", "Success");
      postGetPfSell(portfolioName);
    } catch (Exception e) {
      if (ticker.equals("") || date.equals("") || quantity.equals("") || commission.equals("")) {
        view.errorPop("Empty value found", "Invalid Entry");
      } else if (e.getMessage() == null) {
        view.errorPop("wrong data", "Invalid Entry");
      } else if (e.getMessage().contains("Index")) {
        view.errorPop("Wrong Date Entered", "Invalid Entry");
      } else if (e.getMessage().contains("For")) {
        view.errorPop("Enter Quantity Correctly!!", "Invalid Entry");
      } else {
        view.errorPop(e.getMessage(), "Invalid Entry");
      }
    }
  }

  @Override
  public void postBuyReset() {
    resetAll();
    mainnn();
  }

  @Override
  public void uploadFile() throws FileNotFoundException {
    view.getPfNameForUp();
    view.addPfForUp(this);
  }

  @Override
  public void afterUploadFile(String filename) throws IOException {
    final JFileChooser fChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "csv", "csv");
    fChooser.setFileFilter(filter);
    int retValue = fChooser.showOpenDialog(this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      //model.fetch(f.toString(),"1");
      String path = f.getAbsolutePath();
      String k = "";

      try {
        path = path.substring(0, path.indexOf(".csv"));
        String[][] output;
        output = model.fetch(path, type);
        model.portfolioCreator(username, "flex/" + filename, type);
        for (int i = 0; i < output.length; i++) {

          if (output[i][0].equals("") || output[i][1].equals("") || output[i][2].equals("")
                  || output[i][3].equals("") || output[i][4].equals("")) {
            throw new IllegalArgumentException("Empty String Found\n");
          }
          model.priceApiFetch(output[i][0], "2022-11-01");
          Float.parseFloat(output[i][2]);
          Float.parseFloat(output[i][3]);
          Float.parseFloat(output[i][4]);
          k += output[i][0] + "," + output[i][1] + "," + output[i][2] + ","
                  + output[i][3] + "," + output[i][4] + "\n";
        }
        if (!k.equals("")) {
          model.upload(output, filename, username, type);
        }
        view.infoPop("Uploaded Successfully Now You can view it's Composition",
                "Upload Success");
        getComposition();
      } catch (Exception e) {
        k = "";
        if (e.getMessage() == null) {
          view.infoPop("Found missing values", "Invalid option");
          model.deletePortfolio(username, "flex/" + filename, type);
        } else if (e.getMessage().contains("For")) {
          view.infoPop("Enter price/Quantity of the above entry Correctly!!",
                  "invalid entry");
          model.deletePortfolio(username, "flex/" + filename, type);
        } else {
          view.infoPop(e.getMessage(), "Invalid entry");
          uploadFile();
        }

      }
    }
  }

  @Override
  public void createInvPfNew() {
    view.getPfForInvPf(this);
    view.addGetPfForInv(this);
  }

  @Override
  public void postGetPfInv(String portfolioName) {
    this.portfolioName = portfolioName;
    createInvestPf();
  }


  @Override
  public void exitProgram() {
    System.exit(0);
  }


}