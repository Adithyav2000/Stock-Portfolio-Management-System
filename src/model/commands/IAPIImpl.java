package model.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.function.Function;

/**
 * Api command page.
 */
public class IAPIImpl implements IAPI {

  @Override
  public float getPrice(String tickerSymbol, String date, Function<String, String[][]> reader) {
    boolean flag = false;
    try {
      alphaCallCsv(tickerSymbol, date, reader);
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
    String[][] csr;
    csr = reader.apply("stockdata" + "/" + tickerSymbol);
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

  private void alphaCallCsv(String tickerSymbol, String date, Function<String, String[][]> reader)
          throws IOException, ParseException {
    File path = new File("stockdata");
    boolean flag = false;
    if (!path.exists()) {
      path.mkdirs();
    }
    File file = new File("stockdata" + "/" + tickerSymbol + ".csv");
    if (file.exists()) {
      String[][] csr;
      csr = reader.apply("stockdata" + "/" + tickerSymbol);
      for (int i = 0; i < csr.length; i++) {
        if (csr[i][0].equals(date)) {
          flag = true;
        }
      }
    } else if (!flag) {
      file.createNewFile();
      String a = alphaCall(tickerSymbol);
      if (a.contains("Error Message")) {
        throw new IllegalArgumentException("Enter Valid Ticker");
      }
      FileOutputStream os = new FileOutputStream("stockdata" + "/" + tickerSymbol + ".csv");
      byte[] sb = a.getBytes();
      os.write(sb);
      os.close();
    }
  }

  private String alphaCall(String tickerSymbol) {
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

}