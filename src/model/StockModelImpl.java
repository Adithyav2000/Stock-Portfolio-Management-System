package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.Locale;
import java.util.Scanner;

import portfolio.Iportfolio;


/**
 * StockModelImpl implements StockModel interface which contains all the functions which performs
 * the actual operation.
 * it gets the input parameters from the controller and performs the desired operation.
 */
public class StockModelImpl implements StockModel {
  /**
   * Constructor for StockModelImpl class.
   */
  private Iportfolio inflexible;

  /**
   * Stock model implementation constructor.
   *
   * @param inflexible takes in inflexible object.
   */
  public StockModelImpl(Iportfolio inflexible) {
    this.inflexible = inflexible;

  }


  @Override
  public void portfolioCreator(String username, String filename) throws IOException {

    File path = new File(username);
    if (!path.exists()) {
      path.mkdirs();
    }
    File file = new File(username + "/" + filename + ".csv");
    if (file.exists()) {
      throw new IllegalArgumentException("File name Already Exists");
    } else {
      file.createNewFile();
    }
  }

  @Override
  public void push(String filename) {
    try (FileWriter fileWriter = new FileWriter(filename + ".csv", false)) {
      fileWriter.append(inflexible.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void portfolioBuilder(String s) {
    inflexible.append(s);

  }


  private String[][] fetch(String filename) throws FileNotFoundException {
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
  public void deletePortfolio(String username, String filename) {
    File file = new File(username + "/" + filename + ".csv");
    file.delete();
  }

  @Override
  public float portfolioTotal(String date) {
    int size = inflexible.getSize();
    float sum = 0.00f;
    int i = 0;
    while (i < size) {

      String ticker = inflexible.getIndex(i).split(",")[0];
      int qty = Integer.parseInt(inflexible.getIndex(i).split(",")[2]);
      try {
        float k = priceApiFetch(ticker, date);
        sum += k * qty;

      } catch (Exception e) {
        throw new IllegalArgumentException(e.getMessage());
      }
      i++;
    }
    return sum;
  }

  private String alphacall(String tickerSymbol) {
    String apiKey = "YBLZFBT01STVSWTF";
    String stockSymbol = tickerSymbol;
    URL url = null;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }

    return output.toString();
  }

  @Override
  public boolean dateCheck(String dataDate, String inputDate) throws ParseException {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    f.setLenient(false);
    try {
      Date datadate1 = f.parse(dataDate);
      Date inputdate1 = f.parse(inputDate);
      if (inputdate1.equals(datadate1)) {
        return true;
      } else {
        return inputdate1.after(datadate1);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Enter valid date");
    }
  }

  private void alphacallcsv(String tickerSymbol, String date) throws IOException, ParseException {
    File path = new File("stockdata");
    boolean flag = false;
    if (!path.exists()) {
      path.mkdirs();
    }
    File file = new File("stockdata" + "/" + tickerSymbol + ".csv");
    if (file.exists()) {
      String[][] csr;
      csr = fetch("stockdata" + "/" + tickerSymbol);
      for (int i = 0; i < csr.length; i++) {
        if (csr[i][0].equals(date)) {
          flag = true;
        }
      }
    } else if (!flag) {
      file.createNewFile();
      String a = alphacall(tickerSymbol);
      if (a.contains("Error Message")) {
        throw new IllegalArgumentException("Enter Valid Ticker");
      }
      FileOutputStream os = new FileOutputStream("stockdata" + "/" + tickerSymbol + ".csv");
      byte[] sb = a.getBytes();
      os.write(sb);
      os.close();
    }
  }

  @Override
  public Float priceApiFetch(String tickerSymbol, String date) throws ParseException, IOException {
    boolean flag = false;
    if (dateCheck(LocalDate.now().toString(), date)) {
      throw new IllegalArgumentException("Do not enter future date");
    }
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    LocalDate tmp = LocalDate.parse(date, f);
    if (tmp.getDayOfWeek().toString().equals("SATURDAY") || tmp.getDayOfWeek().toString().equals(
            "SUNDAY")) {
      throw new IllegalArgumentException("Stock market is closed during the weekend\n Kindly "
              + "enter a new date");
    }

    alphacallcsv(tickerSymbol, date);
    String[][] csr;
    csr = fetch("stockdata" + "/" + tickerSymbol);
    float priceval = 0.000F;
    for (int i = 0; i < csr.length; i++) {
      if (csr[i][0].equals(date)) {
        priceval = Float.parseFloat(csr[i][4]);
        flag = true;
      }
    }
    if (!flag) {
      throw new IllegalArgumentException("The Data for the stock(" + tickerSymbol + ") "
              + "is not available"
              + " for the date" + date + ". Please try "
              + "again with a different"
              + "Date\nReasons for ths issues "
              + "are:\n1.Either there was a holiday on this given"
              + "date\n2. The Database might not contain the data due to Some Technical "
              + "issue");
    }

    return priceval;
  }
}