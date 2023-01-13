package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

import model.commands.IAPI;
import model.commands.IAPIImpl;
import portfolio.Iportfolio;
import portfolio.Portfolio;
import portfolio.PortfolioFlex;

/**
 * Main Model Implementation.
 */
public class MainModel implements IModel {
  HashMap<String, Iportfolio> portfolios = new HashMap<>();
  private IAPI api = new IAPIImpl();
  private StockModel inflex;
  private StockModelFlex flex;

  @Override
  public String[] getFileList(String type, String username) {
    String s = "";
    if (type.equals("1")) {
      File directoryPath = new File(username + "/" + "flex");
      String[] p = directoryPath.list();
      for (int i = 0; i < p.length; i++) {
        File t = new File(username + "/" + "flex" + "/" + p[i]);
        if (!t.isDirectory()) {
          if (t.getName().endsWith(".csv")) {
            if (p.length != 0) {
              s += p[i].substring(0, p[i].indexOf(".csv")) + ",";
            }

          }
        }
      }
    }
    if (s.equals("")) {
      return "empty(Create portfolios first),".split(",");
    }
    return s.substring(0, s.length() - 1).split(",");

  }

  @Override
  public void upload(String[][] s, String filename, String username, String type)
          throws ParseException {
    if (type.equals("1")) {
      StockModelFlex n = new StockModelImplFlex(new PortfolioFlex(s));
      n.push(username + "/flex/" + filename);
    }
    if (type.equals("2")) {
      {
        StockModel n = new StockModelImpl(new Portfolio(s));
        n.push(username + "/" + filename);
      }
    }

  }

  private void gettype(String type, String username) throws FileNotFoundException,
          ParseException {
    if (type.equals("1")) {
      File directoryPath = new File(username + "/" + "flex");
      String[] p = directoryPath.list();
      for (int i = 0; i < p.length; i++) {
        File t = new File(username + "/" + "flex" + "/" + p[i]);
        if (!t.isDirectory()) {
          if (t.getName().endsWith(".csv")) {
            portfolios.put(p[i].substring(0, p[i].indexOf(".csv")),
                    new PortfolioFlex(fetch(username + "/" + "flex" + "/"
                            + p[i].substring(0,
                            p[i].indexOf(".csv")), type)));

          }
        }
      }

    } else {
      File directoryPath = new File(username + "/");
      String[] p = directoryPath.list();
      for (int i = 0; i < p.length; i++) {
        File t = new File(username + "/" + p[i]);
        if (!t.isDirectory()) {
          if (t.getName().endsWith(".csv")) {
            portfolios.put(p[i].substring(0, p[i].indexOf(".csv")),
                    new Portfolio(fetch(username +
                                    "/" + p[i].substring(0, p[i].indexOf(".csv")),
                            type)));
          }
        }
      }

    }
  }

  private String[][] fetcher(String filename) throws FileNotFoundException {
    File file = new File(filename + ".csv");
    Scanner lineval = new Scanner(file);
    int i = 0;
    int count = 0;
    while (lineval.hasNext()) {
      lineval.nextLine();
      count++;
    }
    String[][] portfolioall = new String[count][11];
    lineval = new Scanner(file);
    while (lineval.hasNext()) {
      String pfval = lineval.nextLine();
      String[] alp = pfval.split(",");
      for (int j = 0; j < alp.length; j++) {
        portfolioall[i][j] = alp[j];
      }
      i++;
    }
    return portfolioall;
  }

  @Override
  public boolean login(String username, String password) throws FileNotFoundException {
    File file = new File("userbase.csv");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    String[][] ss = fetcher("userbase");
    for (int i = 0; i < ss.length; i++) {
      if (ss[i][0].equals(username) && ss[i][1].equals(password)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void createUser(String username, String password) throws IOException {
    try (FileWriter fileWriter = new FileWriter("userbase.csv", true)) {
      String[][] ss = fetcher("userbase");
      int flagg = 0;
      for (int i = 0; i < ss.length; i++) {
        if (ss[i][0].equals(username)) {
          flagg = 1;
          break;
        }
      }
      if (flagg == 0) {
        fileWriter.append(username + "," + password + "\n");

      } else {
        throw new IllegalArgumentException("User Profile Already Exists");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void portfolioCreator(String username, String fileName, String type) throws IOException {
    if (type.equals("1")) {
      flex = new StockModelImplFlex(new PortfolioFlex());
      flex.portfolioCreator(username, fileName);
    }
    if (type.equals("2")) {
      inflex = new StockModelImpl(new Portfolio());
      inflex.portfolioCreator(username, fileName);
    }
  }

  @Override
  public void portfolioBuilder(String input, String type) throws ParseException {
    if (type.equals("1")) {
      flex.portfolioBuilder(input.substring(0, input.length() - 1));
    }
    if (type.equals("2")) {
      inflex.portfolioBuilder(input.substring(0, input.length() - 1));

    }
  }

  @Override
  public void push(String username, String type, String filename) {
    if (type.equals("1")) {
      flex.push(username + "/" + filename);

    }
    if (type.equals("2")) {
      inflex.push(username + "/" + filename);

    }

  }

  @Override
  public float portfolioTotal(String username, String date, String type, String filename)
          throws IOException,
          ParseException {

    if (type.equals("2")) {
      gettype(type, username);
      inflex = new StockModelImpl(portfolios.get(filename));

    }
    return inflex.portfolioTotal(date);
  }

  @Override
  public String[][] fetch(String fileName, String type) throws FileNotFoundException {
    return fetcher(fileName);
  }

  @Override
  public void deletePortfolio(String username, String fileName, String type) {
    if (type.equals("1")) {
      flex.deletePortfolio(username, fileName);
    } else {
      inflex.deletePortfolio(username, fileName);
    }
  }

  @Override
  public Float priceApiFetch(String tickerSymbol, String date) {
    Function<String, String[][]> reader = p -> {
      try {
        return fetcher(p);
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    };
    return api.getPrice(tickerSymbol, date, reader);

  }

  @Override
  public boolean buyCheck(String username, String filename) {
    File file = new File(username + "/" + filename + ".csv");
    return file.exists();
  }

  @Override
  public void portfolioSell(String username, String filename, String input)
          throws IOException, ParseException {
    gettype("1", username);
    flex = new StockModelImplFlex(portfolios.get(filename));
    flex.portfolioSell(username, filename, input);
  }

  @Override
  public void portfolioBuy(String username, String filename, String buy)
          throws FileNotFoundException, ParseException {
    gettype("1", username);
    flex = new StockModelImplFlex(portfolios.get(filename));
    flex.portfolioBuy(username, filename, buy.substring(0, buy.length() - 1));

  }

  @Override
  public float flexibleTotalVal(String date, String username, String type, String filename)
          throws IOException,
          ParseException, InterruptedException {
    gettype(type, username);
    flex = new StockModelImplFlex(portfolios.get(filename));
    return flex.flexibleTotalVal(date);
  }

  @Override
  public float costBasis(String type, String username, String date, String filename)
          throws FileNotFoundException,
          ParseException {
    gettype(type, username);
    flex = new StockModelImplFlex(portfolios.get(filename));
    return flex.costBasis(date);
  }

  @Override
  public String graphBuilder(String type, String username, String[][] pFall, String dateFrom,
                             String dateTo, String filename) throws FileNotFoundException,
          ParseException {
    gettype(type, username);
    flex = new StockModelImplFlex(portfolios.get(filename));
    return flex.graphBuilder(pFall, dateFrom, dateTo);
  }

  @Override
  public void seTip(String op, HashMap<String, Float> cost, String price, String comm,
                    String filename, String username
  ) throws ParseException, IOException {
    gettype("1", username);
    flex = new StockModelImplFlex(portfolios.get(filename));
    flex.seTip(op, cost, price, comm);
  }

  @Override
  public void buyDca(String type, String username, String filename, HashMap<String, Float> comp,
                     String amount, String date, String comm) throws IOException, ParseException {
    gettype(type, username);
    flex = new StockModelImplFlex(portfolios.get(filename));
    flex.dca(comp, amount, date, comm);
  }

  @Override
  public String[][] comp(String username, String filename) throws FileNotFoundException {
    String[][] s = fetch(username + "/flex/" + filename, "1");
    HashMap<String, Float> comp = new HashMap<>();
    String output = "Ticker     |     quantity\n";
    for (int i = 0; i < s.length; i++) {
      if (!comp.containsKey(s[i][0])) {
        if (Float.parseFloat(s[i][2]) > 0) {
          comp.put(s[i][0], Float.parseFloat(s[i][2]));
        }
      } else {
        if (comp.containsKey(s[i][0])) {
          if (Float.parseFloat(s[i][2]) > 0) {
            Float tmp = 0.00f;
            tmp = comp.get(s[i][0]);
            tmp = tmp + Float.valueOf(s[i][2]);
            comp.replace(s[i][0], tmp);
          }
        }
      }
    }
    String[] k = new String[comp.size()];
    k = comp.keySet().toArray(k);
    String[][] data = new String[k.length][2];


    for (int i = 0; i < k.length; i++) {
      data[i][0] = k[i];
      data[i][1] = comp.get(k[i]).toString();

    }

    return data;
  }

}

