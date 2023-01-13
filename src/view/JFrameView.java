package view;

import java.awt.GridLayout;
import java.io.IOException;
import java.text.ParseException;
import java.io.FileNotFoundException;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;

import controller.Features;

/**
 * JFrameView Class which implements IView interface.
 */
public class JFrameView extends JFrame implements IView {
  JComboBox cmBox;
  JLabel cmbLabel;
  JFrame mainFrame;

  private int width;
  private int height;
  private JLabel display;
  private JLabel display2;
  private JLabel display3;
  private JLabel percentL;
  private JLabel pfVal;
  private JLabel pfCb;
  private JLabel compLab;
  private JScrollPane compLabel;
  private JPanel stockBuyPage;
  private JPanel getPfInVpPage;
  private JPanel mainPanel;
  private JPanel loginPanel;
  private JPanel signupPanel;
  private JPanel mainMenuPanel;
  private JPanel portfolioPage;
  private JPanel stockPage;
  private JPanel investmentsPanel;
  private JPanel compGetPfPage;
  private JPanel displComp;
  private JPanel getInvPrePannel;
  private JPanel getInvPosPannel;
  private JPanel totEvalGetPfPage;
  private JPanel getAllDcaPannel;
  private JPanel stockSellPage;
  private JButton goBackButtonNew;
  private JButton getPfInvPfButton;
  private JButton newInvmButton;
  private JButton buyStock1Button;
  private JButton getPfGbButton;
  private JButton getPfFetchButton;
  private JButton sellButton;
  private JButton exitButton;
  private JButton goBackButton;
  private JButton loginButton;
  private JButton signupButton;
  private JButton getPfButton;
  private JButton adsFinishButton;
  private JButton buyUsingDcaButton;
  private JButton createInvButton;
  private JButton createPfButton;
  private JButton getCompButton;
  private JButton uploadButton;
  private JButton evalPfButton;
  private JButton buyStockButton;
  private JButton sellStockButton;
  private JButton getCbButton;
  private JButton logoutButton;
  private JButton adsButton;
  private JButton addTickButton;
  private JButton resetTickButton;
  private JButton startInvButton;
  private JTextField usernameArea;
  private JTextField passwwordArea;
  private JTextField portfolioName;
  private JTextField tickerButton;
  private JTextField dateButton;
  private JTextField quantityButton;
  private JTextField comissionButton;
  private JTextField startDateButton;
  private JTextField endDateButton;
  private JTextField termButton;
  private JTextField amountButton;
  private JTextField percentButton;
  private JTextField portfolioDate;

  /**
   * Constructor for the JFrameView which creates the panel initially .
   *
   * @param caption We can pass any string value here for tittle for future use.
   */
  public JFrameView(String caption) {
    super(caption);
    width = 500;
    height = 600;
    loginPanel = new JPanel();
    signupPanel = new JPanel();
    mainMenuPanel = new JPanel();
    portfolioPage = new JPanel();
    stockPage = new JPanel();
    investmentsPanel = new JPanel();
    compGetPfPage = new JPanel();
    displComp = new JPanel();
    getInvPrePannel = new JPanel();
    getPfInVpPage = new JPanel();
    getInvPosPannel = new JPanel();
    totEvalGetPfPage = new JPanel();
    getAllDcaPannel = new JPanel();

    totEvalGetPfPage = new JPanel();

    stockBuyPage = new JPanel();
    stockSellPage = new JPanel();
    this.setSize(10, 10);
    mainPannel();
  }

  @Override
  public void setEchoOutput(String s) {
    percentL.setText(s);
  }

  @Override
  public void setEchoOutputComp(String[][] s) {
    String[] columnNames = {"Ticker", "quantity"};
    compLabel = new JScrollPane(new JTable(s, columnNames));
    compGetPfPage.remove(1);
    compGetPfPage.add(compLabel);

  }

  @Override
  public void setEchoOutputCb(String s) {
    pfCb.setText(s);
  }

  @Override
  public void setEchoOutputPfVal(String s) {
    pfVal.setText(s);
  }

  @Override
  public void mainPannel() {
    if (loginPanel.isVisible()) {
      loginPanel.setVisible(false);
    }
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (signupPanel.isVisible()) {
      signupPanel.setVisible(false);
    }
    setTitle("Modern Stock Management");
    setSize(width, height);


    mainPanel = new JPanel(new GridLayout(10, 50, 5, 5));
    mainPanel.setSize(width, height);
    mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));


    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    display = new JLabel("Welcome to Modern Stock Management.");
    display2 = new JLabel("Login or Signup to Continue");

    mainPanel.add(display);
    mainPanel.add(display2);

    loginButton = new JButton("Login");
    loginButton.setActionCommand("Login Button");
    mainPanel.add(loginButton);

    signupButton = new JButton("Signup");
    signupButton.setActionCommand("Signup Button");
    mainPanel.add(signupButton);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    mainPanel.add(exitButton);
    setLocationRelativeTo(null);
    setVisible(true);

  }

  @Override
  public void addFeatures(Features features) {
    loginButton.addActionListener(evt -> features.login());
    signupButton.addActionListener(evt -> features.signup());
    exitButton.addActionListener(evt -> features.exitProgram());
  }

  @Override
  public void loginPage() {
    if (mainPanel.isVisible()) {
      mainPanel.setVisible(false);
    }
    if (signupPanel.isVisible()) {
      signupPanel.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    setTitle("Login Page");
    setSize(width, height);
    loginPanel = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(loginPanel);
    display3 = new JLabel("Login To Continue.");
    loginPanel.add(display3);

    usernameArea = new JTextField(10);
    usernameArea.setBorder(BorderFactory.createTitledBorder("Enter username"));
    loginPanel.add(usernameArea);

    passwwordArea = new JPasswordField(10);
    passwwordArea.setBorder(BorderFactory.createTitledBorder("Enter Password"));
    loginPanel.add(passwwordArea);


    loginButton = new JButton("Login");
    loginButton.setActionCommand("Login Button");
    loginPanel.add(loginButton);

    signupButton = new JButton("Signup");
    signupButton.setActionCommand("Signup Button");
    loginPanel.add(signupButton);

    goBackButton = new JButton("Go Back");
    goBackButton.setActionCommand("Goback Button");
    loginPanel.add(goBackButton);

    setLocationRelativeTo(null);
    loginPanel.setVisible(true);
  }


  @Override
  public void addLoginFeatures(Features features) {
    loginButton.addActionListener(evt -> features.pLogin(usernameArea.getText(),
            passwwordArea.getText()));
    signupButton.addActionListener(evt -> features.signup());
    goBackButton.addActionListener(evt -> features.mainLsp());
  }

  @Override
  public void infoPop(String message, String head) {
    JOptionPane.showMessageDialog(JFrameView.this, message, head,
            JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void errorPop(String message, String head) {
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }

    if (loginPanel.isVisible()) {
      loginPanel.setVisible(false);
    }

    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    if (getInvPrePannel.isVisible()) {
      getInvPrePannel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    JOptionPane.showMessageDialog(JFrameView.this, message, head, JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void signUpPage() {
    if (mainPanel.isVisible()) {
      mainPanel.setVisible(false);
    }
    if (loginPanel.isVisible()) {
      loginPanel.setVisible(false);
    }
    setTitle("Signup");
    setSize(width, height);

    signupPanel = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(signupPanel);
    display3 = new JLabel("Enter Username and Password With 5 Characters to Signup.");
    signupPanel.add(display3);

    usernameArea = new JTextField(10);
    usernameArea.setBorder(BorderFactory.createTitledBorder("Enter username"));
    signupPanel.add(usernameArea);

    passwwordArea = new JPasswordField(10);
    passwwordArea.setBorder(BorderFactory.createTitledBorder("Enter Password"));
    signupPanel.add(passwwordArea);

    loginButton = new JButton("Create User");
    loginButton.setActionCommand("Create User Button");
    signupPanel.add(loginButton);

    goBackButton = new JButton("Go Back");
    goBackButton.setActionCommand("Goback Button");
    signupPanel.add(goBackButton);

    setLocationRelativeTo(null);
    signupPanel.setVisible(true);
  }

  @Override
  public void addSignUpFeatures(Features features) {
    loginButton.addActionListener(evt -> features.pSignup(usernameArea.getText(),
            passwwordArea.getText()));
    goBackButton.addActionListener(evt -> features.mainLsp());
  }

  @Override
  public void mainMenuPage() {
    if (getInvPrePannel.isVisible()) {
      getInvPrePannel.setVisible(false);
    }
    if (portfolioPage.isVisible()) {
      portfolioPage.setVisible(false);
    }
    if (getPfInVpPage.isVisible()) {
      getPfInVpPage.setVisible(false);
    }
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    if (stockBuyPage.isVisible()) {
      stockBuyPage.setVisible(false);
    }
    if (loginPanel.isVisible()) {
      loginPanel.setVisible(false);
    }
    if (stockPage.isVisible()) {
      stockPage.setVisible(false);
    }
    if (stockSellPage.isVisible()) {
      stockSellPage.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    setTitle("Modern Stock Management");
    setSize(width, height);

    mainMenuPanel = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(mainMenuPanel);
    display3 = new JLabel("Welcome to Modern Stock Management");
    display3.setLocation(300, 10);
    mainMenuPanel.add(display3);

    createPfButton = new JButton("Create Portfolio");
    createPfButton.setLocation(300, 30);
    createPfButton.setActionCommand("Create Portfolio");
    mainMenuPanel.add(createPfButton);

    newInvmButton = new JButton("investment Mode");
    newInvmButton.setActionCommand("investment Mode");
    mainMenuPanel.add(newInvmButton);

    getCompButton = new JButton("Get Composition");
    getCompButton.setActionCommand("Get Composition");
    mainMenuPanel.add(getCompButton);

    evalPfButton = new JButton("Evaluate Portfolio");
    evalPfButton.setActionCommand("Evaluate Portfolio");
    mainMenuPanel.add(evalPfButton);

    uploadButton = new JButton("Load Portfolio");
    uploadButton.setActionCommand("Load Portfolio");
    mainMenuPanel.add(uploadButton);

    buyStockButton = new JButton("Buy Stock");
    buyStockButton.setActionCommand("Buy Stock");
    mainMenuPanel.add(buyStockButton);

    buyUsingDcaButton = new JButton("Buy Using Dollar Cost Average");
    buyUsingDcaButton.setActionCommand("Buy dca");
    mainMenuPanel.add(buyUsingDcaButton);

    sellStockButton = new JButton("Sell Stock");
    sellStockButton.setActionCommand("Sell Stock");
    mainMenuPanel.add(sellStockButton);

    getCbButton = new JButton("Get Cost Basis");
    getCbButton.setActionCommand("Get Cost Basis");
    mainMenuPanel.add(getCbButton);

    logoutButton = new JButton("Logout");
    logoutButton.setActionCommand("Logout");
    mainMenuPanel.add(logoutButton);

    setLocationRelativeTo(null);
    mainMenuPanel.setVisible(true);
  }

  @Override
  public void addMainMenu(Features features) {
    createPfButton.addActionListener(evt -> features.createPortfolio());
    getCompButton.addActionListener(evt -> features.getComposition());
    newInvmButton.addActionListener(evt -> features.createInvPfNew());
    evalPfButton.addActionListener(evt -> features.getPfEval());
    uploadButton.addActionListener(evt -> {
      try {
        features.uploadFile();
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });
    buyStockButton.addActionListener(evt -> features.buyStock());
    buyUsingDcaButton.addActionListener(evt -> features.buyUsingDca());
    sellStockButton.addActionListener(evt -> features.sellStock());
    getCbButton.addActionListener(evt -> features.getCb());
    logoutButton.addActionListener(evt -> features.mainLsp());
  }

  @Override
  public void createPortfolioPage() {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }

    setTitle("Portfolio Creation");
    setSize(width, height);
    portfolioPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(portfolioPage);
    display3 = new JLabel("Create Portfolio");
    display3.setLocation(300, 10);
    portfolioPage.add(display3);

    portfolioName = new JTextField(10);
    portfolioName.setBorder(BorderFactory.createTitledBorder("Enter Portfolio Name"));
    portfolioPage.add(portfolioName);


    createPfButton = new JButton("Create Portfolio");
    createPfButton.setLocation(300, 30);
    createPfButton.setActionCommand("Create Portfolio");
    portfolioPage.add(createPfButton);


    goBackButtonNew = new JButton("Go back");
    goBackButtonNew.setLocation(300, 30);
    goBackButtonNew.setActionCommand("Go back");
    portfolioPage.add(goBackButtonNew);

    portfolioPage.setVisible(true);
  }

  @Override
  public void addCreatePf(Features features) {
    createPfButton.addActionListener(evt -> features.postCreatePortfolio(portfolioName.getText()));
    goBackButtonNew.addActionListener(evt -> features.mainnn());
  }

  @Override
  public void stocksPage() {
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (stockPage.isVisible()) {
      stockPage.setVisible(false);
    }
    if (loginPanel.isVisible()) {
      loginPanel.setVisible(false);
    }
    if (stockPage.isVisible()) {
      stockPage.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    if (getInvPrePannel.isVisible()) {
      getInvPrePannel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    setTitle("Portfolio Creation");
    setSize(width, height);
    stockPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(stockPage);
    display3 = new JLabel("Add stocks");

    display3.setLocation(300, 10);
    stockPage.add(display3);

    tickerButton = new JTextField(10);
    tickerButton.setBorder(BorderFactory.createTitledBorder("Enter Ticker Symbol"));
    stockPage.add(tickerButton);

    dateButton = new JTextField(10);
    dateButton.setBorder(BorderFactory.createTitledBorder("Enter Date (YYYY-DD-MM)"));
    stockPage.add(dateButton);

    quantityButton = new JTextField(10);
    quantityButton.setBorder(BorderFactory.createTitledBorder("Enter Quantity"));
    stockPage.add(quantityButton);

    comissionButton = new JTextField(10);
    comissionButton.setBorder(BorderFactory.createTitledBorder("Enter commission"));
    stockPage.add(comissionButton);

    adsButton = new JButton("Add Stocks");
    adsButton.setLocation(300, 30);
    adsButton.setActionCommand("Create Portfolio");
    stockPage.add(adsButton);

    adsFinishButton = new JButton("Go To Main Menu");
    adsFinishButton.setLocation(300, 30);
    adsFinishButton.setActionCommand("Add Finish");
    stockPage.add(adsFinishButton);
    setLocationRelativeTo(null);
    stockPage.setVisible(true);
  }

  @Override
  public void addStocks(Features features) {
    adsButton.addActionListener(evt -> features.postAddStocks(tickerButton.getText(),
            dateButton.getText(), quantityButton.getText(), comissionButton.getText()));
    adsFinishButton.addActionListener(evt -> features.mainnn());
  }

  @Override
  public void investmentPage() {
    if (portfolioPage.isVisible()) {
      portfolioPage.setVisible(false);
    }
    setTitle("Investment Plans");
    setSize(width, height);
    investmentsPanel = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(investmentsPanel);
    display = new JLabel("Would You Like To add any Investment Plans ?");
    investmentsPanel.add(display);

    loginButton = new JButton("Yes");
    loginButton.setActionCommand("Yes");
    investmentsPanel.add(loginButton);


    signupButton = new JButton("NO");
    signupButton.setActionCommand("No");
    investmentsPanel.add(signupButton);

    goBackButton = new JButton("Go back");
    goBackButton.setActionCommand("No");
    investmentsPanel.add(goBackButton);
    setLocationRelativeTo(null);
    investmentsPanel.setVisible(true);
  }

  @Override
  public void addInvestType(Features features) {
    loginButton.addActionListener(evt -> features.createInvestPf());
    signupButton.addActionListener(evt -> features.createFlexStock());
    goBackButton.addActionListener(evt -> features.createPortfolio());
  }

  @Override
  public void getInvPre() {
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (getInvPrePannel.isVisible()) {
      getInvPrePannel.setVisible(false);
    }
    if (getPfInVpPage.isVisible()) {
      getPfInVpPage.setVisible(false);
    }
    setTitle("Investment");
    setSize(width, height);
    getInvPrePannel = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(getInvPrePannel);

    display3 = new JLabel("Add Investment Configuration");
    display3.setLocation(300, 10);
    getInvPrePannel.add(display3);


    startDateButton = new JTextField(10);
    startDateButton.setBorder(BorderFactory.createTitledBorder("Enter Starting Date"
            + " To Invest (YYYY-MM-DD)"));
    getInvPrePannel.add(startDateButton);

    endDateButton = new JTextField(10);
    endDateButton.setBorder(BorderFactory.createTitledBorder("Enter To Date (YYYY-MM-DD) *"));
    getInvPrePannel.add(endDateButton);

    termButton = new JTextField(10);
    termButton.setBorder(BorderFactory.createTitledBorder("Enter Term Frequency"
            + " to Invest (in days)"));
    getInvPrePannel.add(termButton);

    amountButton = new JTextField(10);
    amountButton.setBorder(BorderFactory.createTitledBorder("Enter Total Amount to"
            + " Invest (in Dollars)"));
    getInvPrePannel.add(amountButton);

    comissionButton = new JTextField(10);
    comissionButton.setBorder(BorderFactory.createTitledBorder("Enter commission "
            + "to Invest (in Dollars)"));
    getInvPrePannel.add(comissionButton);


    createInvButton = new JButton("Create Investment");
    createInvButton.setLocation(300, 30);
    createInvButton.setActionCommand("Create Investment");
    getInvPrePannel.add(createInvButton);

    goBackButtonNew = new JButton("Go To Main Menu");
    goBackButtonNew.setLocation(300, 30);
    goBackButtonNew.setActionCommand("Go back");
    getInvPrePannel.add(goBackButtonNew);


    display = new JLabel("* Non Mandatory Field");
    display.setLocation(300, 10);
    getInvPrePannel.add(display);
    setLocationRelativeTo(null);
    getInvPrePannel.setVisible(true);
  }

  @Override
  public void addInvPre(Features features) {
    createInvButton.addActionListener(evt -> features.postAddInv1(startDateButton.getText(),
            endDateButton.getText(), termButton.getText(), amountButton.getText(),
            comissionButton.getText()));
    goBackButtonNew.addActionListener(evt -> features.postAddAllStocks());
  }

  @Override
  public void getInv2() {
    if (getInvPrePannel.isVisible()) {
      getInvPrePannel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    setTitle("Investment");
    setSize(width, height);
    getInvPosPannel = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(getInvPosPannel);

    display3 = new JLabel("Add Investment Configuration");
    display3.setLocation(300, 10);
    getInvPosPannel.add(display3);


    tickerButton = new JTextField(10);
    tickerButton.setBorder(BorderFactory.createTitledBorder("Enter Ticker Symbol"));
    getInvPosPannel.add(tickerButton);

    percentButton = new JTextField(10);
    percentButton.setBorder(BorderFactory.createTitledBorder("Enter Percentage"));
    getInvPosPannel.add(percentButton);

    percentL = new JLabel("Percent To be added is 100.00%");
    percentL.setLocation(300, 10);
    getInvPosPannel.add(percentL);

    addTickButton = new JButton("Add ticker");
    addTickButton.setLocation(300, 30);
    addTickButton.setActionCommand("Add ticker");
    getInvPosPannel.add(addTickButton);

    resetTickButton = new JButton("Reset Ticker Data");
    resetTickButton.setLocation(300, 30);
    resetTickButton.setActionCommand("Create Investment");
    getInvPosPannel.add(resetTickButton);

    startInvButton = new JButton("Start Investment");
    startInvButton.setLocation(300, 30);
    startInvButton.setActionCommand("Create Investment");
    getInvPosPannel.add(startInvButton);

    goBackButtonNew = new JButton("Go To Main Menu");
    goBackButtonNew.setLocation(300, 30);
    goBackButtonNew.setActionCommand("Go To Main Menu");
    getInvPosPannel.add(goBackButtonNew);

    setLocationRelativeTo(null);
    getInvPosPannel.setVisible(true);
  }

  @Override
  public void addInv2(Features features) {
    addTickButton.addActionListener(evt -> features.postAddInv2(tickerButton.getText(),
            percentButton.getText()));
    resetTickButton.addActionListener(evt -> features.postAddInv2Reset());
    startInvButton.addActionListener(evt -> {
      try {
        features.postAddInv2Final();
      } catch (ParseException e) {
        throw new RuntimeException(e);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    goBackButtonNew.addActionListener(evt -> features.postAddAllStocks());

  }

  @Override
  public void compGetPfPage(Features features) {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    if (getPfInVpPage.isVisible()) {
      getPfInVpPage.setVisible(false);
    }
    setTitle("Portfolio Composition");
    setSize(width, height);
    compGetPfPage = new JPanel(new GridLayout(2, 1, 5, 5));
    JPanel compPanel = new JPanel(new GridLayout(6, 1, 5, 5));
    compPanel.setSize(width, height);
    this.add(compGetPfPage);
    display3 = new JLabel("Click Next to see the Portfolio Composition");
    display3.setLocation(300, 10);
    compPanel.add(display3);

    String[] pfNames = features.getNames();
    cmBox = new JComboBox(pfNames);
    compPanel.add(cmBox);

    getPfButton = new JButton("Next");
    getPfButton.setLocation(300, 30);
    getPfButton.setActionCommand("Next");
    compPanel.add(getPfButton);

    goBackButton = new JButton("Go to main menu");
    goBackButton.setLocation(300, 30);
    goBackButton.setActionCommand("Next");
    compPanel.add(goBackButton);

    compLabel = new JScrollPane(new JLabel("Composition"));
    compGetPfPage.add(compPanel);
    compGetPfPage.add(compLabel);

    compGetPfPage.setVisible(true);
  }

  @Override
  public void addCompGet(Features features) {
    getPfButton.addActionListener(evt -> {
      try {
        features.postGetComp((String) cmBox.getSelectedItem());
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    });
    goBackButton.addActionListener(evt -> features.mainnn());

  }

  @Override
  public void dispComp(String[][] output) {
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    setTitle("Portfolio Composition");
    setSize(width, height);
    display = new JLabel("Fetched Portfolio");
    display.setLocation(300, 10);
    displComp.add(display);
    displComp = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(displComp);
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < 5; j++) {
        display3 = new JLabel(output[i][j]);
        display3.setText(output[i][j]);
        display3.setLocation(300, j);
        displComp.add(display3);
      }
    }

    goBackButton = new JButton("Go Back");
    goBackButton.setActionCommand("Goback Button");
    displComp.add(goBackButton);
    displComp.setVisible(true);
  }

  @Override
  public void adddIsp(Features features) {
    goBackButton.addActionListener(evt -> features.mainnn());
  }

  @Override
  public void toteValGetPf(Features features) {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }

    setTitle("Portfolio Total Value");

    setSize(width, height);
    totEvalGetPfPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(totEvalGetPfPage);
    display3 = new JLabel("Choose Portfolio Name and Enter the Date");
    display3.setLocation(300, 10);
    totEvalGetPfPage.add(display3);
    cmbLabel = new JLabel();
    cmbLabel.setText("List of portfolios:");
    totEvalGetPfPage.add(cmbLabel);
    display2 = new JLabel("");
    display2.setText("");
    display.setLocation(300, 10);
    totEvalGetPfPage.add(display2);
    String[] pfnames = features.getNames();
    cmBox = new JComboBox(pfnames);
    totEvalGetPfPage.add(cmBox);

    portfolioDate = new JTextField(10);
    portfolioDate.setBorder(BorderFactory.createTitledBorder("Enter Date (YYYY-MM-DD)"));
    totEvalGetPfPage.add(portfolioDate);

    pfVal = new JLabel();
    pfVal.setText("");
    pfVal.setLocation(300, 10);
    totEvalGetPfPage.add(pfVal);

    getPfButton = new JButton("Get Total Value");
    getPfButton.setLocation(300, 30);
    getPfButton.setActionCommand("Next");
    totEvalGetPfPage.add(getPfButton);

    adsFinishButton = new JButton("Go To Main Menu");
    adsFinishButton.setLocation(300, 30);
    adsFinishButton.setActionCommand("Add Finish");
    totEvalGetPfPage.add(adsFinishButton);

    totEvalGetPfPage.setVisible(true);
  }

  @Override
  public void addTotaleValGet(Features features) {
    getPfButton.addActionListener(evt -> features.postGetEval((String) cmBox.getSelectedItem(),
            (portfolioDate.getText())));
    adsFinishButton.addActionListener(evt -> features.mainnn());
  }

  @Override
  public void getCbPf(Features features) {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }

    setTitle("Cost Basis");
    setSize(width, height);
    totEvalGetPfPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(totEvalGetPfPage);
    display3 = new JLabel("Cost basis of Portfolio is the total amount of money invested"
            + " by the user on a");
    display3.setLocation(300, 10);
    totEvalGetPfPage.add(display3);
    display = new JLabel("specific portfolio until that date.");
    display.setLocation(300, 10);
    totEvalGetPfPage.add(display);
    cmbLabel = new JLabel();
    cmbLabel.setText("List of portfolios:");
    totEvalGetPfPage.add(cmbLabel);
    display2 = new JLabel("");
    display2.setText("");
    display.setLocation(300, 10);
    totEvalGetPfPage.add(display2);
    String[] pfnames = features.getNames();
    cmBox = new JComboBox(pfnames);
    totEvalGetPfPage.add(cmBox);

    portfolioDate = new JTextField(10);
    portfolioDate.setBorder(BorderFactory.createTitledBorder("Enter Date(YYYY-MM-DD)"));
    totEvalGetPfPage.add(portfolioDate);

    pfCb = new JLabel("");
    pfCb.setLocation(300, 10);
    totEvalGetPfPage.add(pfCb);

    getPfButton = new JButton("Next");
    getPfButton.setLocation(300, 30);
    getPfButton.setActionCommand("Next");
    totEvalGetPfPage.add(getPfButton);

    adsFinishButton = new JButton("Go To Main Menu");
    adsFinishButton.setLocation(300, 30);
    adsFinishButton.setActionCommand("Add Finish");
    totEvalGetPfPage.add(adsFinishButton);

    totEvalGetPfPage.setVisible(true);
  }

  @Override
  public void addCbGet(Features features) {
    getPfButton.addActionListener(evt -> features.postGetCostB(((String) cmBox.getSelectedItem()),
            (portfolioDate.getText())));

    adsFinishButton.addActionListener(evt -> features.mainnn());
  }

  @Override
  public void buyDcaGetPfn(Features features) {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    setTitle("Buy On Portfolio Select");
    setSize(width, height);
    compGetPfPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(compGetPfPage);
    display3 = new JLabel("Portfolio");
    display3.setLocation(300, 10);
    compGetPfPage.add(display3);

    cmbLabel = new JLabel();
    cmbLabel.setText("List of portfolios:");
    compGetPfPage.add(cmbLabel);
    String[] pfNames = features.getNames();
    cmBox = new JComboBox(pfNames);
    compGetPfPage.add(cmBox);


    getPfButton = new JButton("Next");
    getPfButton.setLocation(300, 30);
    getPfButton.setActionCommand("Next");
    compGetPfPage.add(getPfButton);

    compGetPfPage.setVisible(true);
  }

  @Override
  public void addBuyCapF(Features features) {
    getPfButton.addActionListener(evt -> features.postGetPfb((String) cmBox.getSelectedItem()));
  }

  @Override
  public void getAllDca() {
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    if (getAllDcaPannel.isVisible()) {
      getAllDcaPannel.setVisible(false);
    }
    setTitle("Investment");
    setSize(width, height);
    getAllDcaPannel = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(getAllDcaPannel);

    display3 = new JLabel("Add Investment Configuration");
    display3.setLocation(300, 10);
    getAllDcaPannel.add(display3);

    startDateButton = new JTextField(10);
    startDateButton.setBorder(BorderFactory.createTitledBorder("Enter Date To Invest"));
    getAllDcaPannel.add(startDateButton);

    amountButton = new JTextField(10);
    amountButton.setBorder(BorderFactory.createTitledBorder("Enter Total Amount to Invest"));
    getAllDcaPannel.add(amountButton);

    comissionButton = new JTextField(10);
    comissionButton.setBorder(BorderFactory.createTitledBorder("Enter commission to Invest"));
    getAllDcaPannel.add(comissionButton);


    adsButton = new JButton("Create Investment");
    adsButton.setLocation(300, 30);
    adsButton.setActionCommand("Create Investment");
    getAllDcaPannel.add(adsButton);
    setLocationRelativeTo(null);
    getAllDcaPannel.setVisible(true);
  }

  @Override
  public void addGetAllDca(Features features) {
    adsButton.addActionListener(evt -> features.postAllDcaP(startDateButton.getText(),
            amountButton.getText(), comissionButton.getText()));
  }

  @Override
  public void fdCaBuy() {
    if (getInvPrePannel.isVisible()) {
      getInvPrePannel.setVisible(false);
    }
    if (getAllDcaPannel.isVisible()) {
      getAllDcaPannel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    setTitle("Investment BUY");
    setSize(width, height);
    getInvPosPannel = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(getInvPosPannel);

    display3 = new JLabel("Buy Investment");
    display3.setLocation(300, 10);
    getInvPosPannel.add(display3);

    tickerButton = new JTextField(10);
    tickerButton.setBorder(BorderFactory.createTitledBorder("Enter Ticker Symbol"));
    getInvPosPannel.add(tickerButton);

    percentButton = new JTextField(10);
    percentButton.setBorder(BorderFactory.createTitledBorder("Enter Percentage"));
    getInvPosPannel.add(percentButton);

    percentL = new JLabel("Percent To be added is 100.00%");
    percentL.setLocation(300, 10);
    getInvPosPannel.add(percentL);

    addTickButton = new JButton("Add ticker");
    addTickButton.setLocation(300, 30);
    addTickButton.setActionCommand("Add ticker");
    getInvPosPannel.add(addTickButton);

    resetTickButton = new JButton("Reset Ticker Data");
    resetTickButton.setLocation(300, 30);
    resetTickButton.setActionCommand("Create Investment");
    getInvPosPannel.add(resetTickButton);

    startInvButton = new JButton("Buy Added Stocks");
    startInvButton.setLocation(300, 30);
    startInvButton.setActionCommand("Create Investment");
    getInvPosPannel.add(startInvButton);
    setLocationRelativeTo(null);
    getInvPosPannel.setVisible(true);
  }

  @Override
  public void addFinalDcaBuy(Features features) {
    addTickButton.addActionListener(evt -> features.postAddInvBuyNew(tickerButton.getText(),
            percentButton.getText()));
    resetTickButton.addActionListener(evt -> features.postAddInv2Reset());
    startInvButton.addActionListener(evt -> {
      try {
        features.postBuyCb2Final();
      } catch (ParseException e) {
        throw new RuntimeException(e);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void getPfBuy(Features features) {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (stockPage.isVisible()) {
      stockPage.setVisible(false);
    }
    if (loginPanel.isVisible()) {
      loginPanel.setVisible(false);
    }
    if (stockPage.isVisible()) {
      stockPage.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    if (getInvPrePannel.isVisible()) {
      getInvPrePannel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }

    setTitle("Portfolio Buy");
    setSize(width, height);
    compGetPfPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(compGetPfPage);
    display3 = new JLabel("Portfolio");
    display3.setLocation(300, 10);
    compGetPfPage.add(display3);

    cmbLabel = new JLabel();
    cmbLabel.setText("List of portfolios:");
    compGetPfPage.add(cmbLabel);
    String[] pfNames = features.getNames();
    cmBox = new JComboBox(pfNames);
    compGetPfPage.add(cmBox);

    getPfFetchButton = new JButton("Next");
    getPfFetchButton.setLocation(300, 30);
    getPfFetchButton.setActionCommand("Next");
    compGetPfPage.add(getPfFetchButton);

    getPfGbButton = new JButton("Go to main menu");
    getPfGbButton.setLocation(300, 30);
    getPfGbButton.setActionCommand("Go to main menu");
    compGetPfPage.add(getPfGbButton);
    compGetPfPage.setVisible(true);
  }

  @Override
  public void addBuyPf(Features features) {
    getPfFetchButton.addActionListener(evt ->
            features.postGetPfBuy((String) cmBox.getSelectedItem()));
    getPfGbButton.addActionListener(evt -> features.mainnn());
  }

  @Override
  public void getsDataForBuy() {
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (stockBuyPage.isVisible()) {
      stockBuyPage.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (stockPage.isVisible()) {
      stockPage.setVisible(false);
    }
    if (loginPanel.isVisible()) {
      loginPanel.setVisible(false);
    }
    if (stockPage.isVisible()) {
      stockPage.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    if (totEvalGetPfPage.isVisible()) {
      totEvalGetPfPage.setVisible(false);
    }
    if (getInvPrePannel.isVisible()) {
      getInvPrePannel.setVisible(false);
    }
    if (getInvPosPannel.isVisible()) {
      getInvPosPannel.setVisible(false);
    }
    setTitle("Buy Stocks");
    setSize(width, height);
    stockBuyPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(stockBuyPage);
    display3 = new JLabel("Buy Stocks");

    display3.setLocation(300, 10);
    stockBuyPage.add(display3);

    tickerButton = new JTextField(10);
    tickerButton.setBorder(BorderFactory.createTitledBorder("Enter Ticker Symbol"));
    stockBuyPage.add(tickerButton);

    dateButton = new JTextField(10);
    dateButton.setBorder(BorderFactory.createTitledBorder("Enter Date"));
    stockBuyPage.add(dateButton);

    quantityButton = new JTextField(10);
    quantityButton.setBorder(BorderFactory.createTitledBorder("Enter Quantity"));
    stockBuyPage.add(quantityButton);

    comissionButton = new JTextField(10);
    comissionButton.setBorder(BorderFactory.createTitledBorder("Enter commission"));
    stockBuyPage.add(comissionButton);

    buyStock1Button = new JButton("Buy Stocks");
    buyStock1Button.setLocation(300, 30);
    buyStock1Button.setActionCommand("Create Portfolio");
    stockBuyPage.add(buyStock1Button);

    adsFinishButton = new JButton("Go To Main Menu");
    adsFinishButton.setLocation(300, 30);
    adsFinishButton.setActionCommand("Add Finish");
    stockBuyPage.add(adsFinishButton);
    setLocationRelativeTo(null);
    stockBuyPage.setVisible(true);
  }

  @Override
  public void addsDataForBuy(Features features) {
    buyStock1Button.addActionListener(evt -> features.postAddStocksOfBuy(tickerButton.getText(),
            dateButton.getText(), quantityButton.getText(), comissionButton.getText()));
    adsFinishButton.addActionListener(evt -> features.postBuyReset());
  }

  @Override
  public void getPfSell(Features features) {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    setTitle("Buy On Portfolio Select");
    setSize(width, height);
    compGetPfPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(compGetPfPage);
    display3 = new JLabel("Portfolio Sell");
    display3.setLocation(300, 10);
    compGetPfPage.add(display3);
    cmbLabel = new JLabel();
    cmbLabel.setText("List of portfolios:");
    compGetPfPage.add(cmbLabel);
    String[] pfNames = features.getNames();
    cmBox = new JComboBox(pfNames);
    compGetPfPage.add(cmBox);

    getPfButton = new JButton("Next");
    getPfButton.setLocation(300, 30);
    getPfButton.setActionCommand("Next");
    compGetPfPage.add(getPfButton);

    compGetPfPage.setVisible(true);
  }

  @Override
  public void addPfSell(Features features) {
    getPfButton.addActionListener(evt -> features.postGetPfSell((String) cmBox.getSelectedItem()));
  }

  @Override
  public void getsDataForSell() {
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    if (investmentsPanel.isVisible()) {
      investmentsPanel.setVisible(false);
    }
    if (stockSellPage.isVisible()) {
      stockSellPage.setVisible(false);
    }
    setTitle("Sell Stocks");
    setSize(width, height);
    stockSellPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(stockSellPage);
    display3 = new JLabel("Sell stocks");

    display3.setLocation(300, 10);
    stockSellPage.add(display3);

    tickerButton = new JTextField(10);
    tickerButton.setBorder(BorderFactory.createTitledBorder("Enter Ticker Symbol"));
    stockSellPage.add(tickerButton);

    dateButton = new JTextField(10);
    dateButton.setBorder(BorderFactory.createTitledBorder("Enter Date"));
    stockSellPage.add(dateButton);

    quantityButton = new JTextField(10);
    quantityButton.setBorder(BorderFactory.createTitledBorder("Enter Quantity"));
    stockSellPage.add(quantityButton);

    comissionButton = new JTextField(10);
    comissionButton.setBorder(BorderFactory.createTitledBorder("Enter commission"));
    stockSellPage.add(comissionButton);

    sellButton = new JButton("Sell Stocks");
    sellButton.setLocation(300, 30);
    sellButton.setActionCommand("Create Portfolio");
    stockSellPage.add(sellButton);

    adsFinishButton = new JButton("Go To Main Menu");
    adsFinishButton.setLocation(300, 30);
    adsFinishButton.setActionCommand("Add Finish");
    stockSellPage.add(adsFinishButton);
    setLocationRelativeTo(null);
    stockSellPage.setVisible(true);
  }

  @Override
  public void addsDataForSell(Features features) {
    sellButton.addActionListener(evt -> features.postAddStocksOfSell(tickerButton.getText(),
            dateButton.getText(), quantityButton.getText(), comissionButton.getText()));
    adsFinishButton.addActionListener(evt -> features.mainnn());
  }


  @Override
  public void getPfForInvPf(Features features) {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (compGetPfPage.isVisible()) {
      compGetPfPage.setVisible(false);
    }
    setTitle("Create Investment Portfolio");
    setSize(width, height);
    getPfInVpPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(getPfInVpPage);

    display3 = new JLabel("Portfolio");
    display3.setLocation(300, 10);
    getPfInVpPage.add(display3);

    cmbLabel = new JLabel();
    cmbLabel.setText("List of portfolios:");
    getPfInVpPage.add(cmbLabel);
    String[] pfNames = features.getNames();
    cmBox = new JComboBox(pfNames);
    getPfInVpPage.add(cmBox);

    getPfInvPfButton = new JButton("Next");
    getPfInvPfButton.setLocation(300, 30);
    getPfInvPfButton.setActionCommand("Next");
    getPfInVpPage.add(getPfInvPfButton);

    goBackButtonNew = new JButton("Go back");
    goBackButtonNew.setLocation(300, 30);
    goBackButtonNew.setActionCommand("Go back");
    getPfInVpPage.add(goBackButtonNew);

    getPfInvPfButton.setVisible(true);
  }

  @Override
  public void addGetPfForInv(Features features) {
    getPfInvPfButton.addActionListener(evt ->
            features.postGetPfInv((String) cmBox.getSelectedItem()));
    goBackButtonNew.addActionListener(evt -> features.mainnn());
  }

  @Override
  public void getPfNameForUp() {
    if (mainMenuPanel.isVisible()) {
      mainMenuPanel.setVisible(false);
    }
    if (portfolioPage.isVisible()) {
      portfolioPage.setVisible(false);
    }
    if (getPfInVpPage.isVisible()) {
      getPfInVpPage.setVisible(false);
    }
    setTitle("File Upload");
    setSize(width, height);
    getPfInVpPage = new JPanel(new GridLayout(10, 50, 5, 5));
    this.add(getPfInVpPage);

    display3 = new JLabel("Portfolio");
    display3.setLocation(300, 10);
    getPfInVpPage.add(display3);

    portfolioName = new JTextField(10);
    portfolioName.setBorder(BorderFactory.createTitledBorder("Enter Portfolio Name"));
    getPfInVpPage.add(portfolioName);

    getPfInvPfButton = new JButton("Next");
    getPfInvPfButton.setLocation(300, 30);
    getPfInvPfButton.setActionCommand("Next");
    getPfInVpPage.add(getPfInvPfButton);

    goBackButtonNew = new JButton("Go back");
    goBackButtonNew.setLocation(300, 30);
    goBackButtonNew.setActionCommand("Go back");
    getPfInVpPage.add(goBackButtonNew);

    getPfInVpPage.setVisible(true);
  }

  @Override
  public void addPfForUp(Features features) {
    getPfInvPfButton.addActionListener(evt -> {
      try {
        features.afterUploadFile(portfolioName.getText());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    goBackButtonNew.addActionListener(evt -> features.mainnn());
  }
}