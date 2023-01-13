package model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import portfolio.Iportfolio;

/**
 * StockModelImplFlex class implementation.
 */

public class StockModelImplFlex extends StockModelImpl implements StockModelFlex {
  Iportfolio flexible;

  /**
   * StockModelImplFex constructor used to assign linked list objects.
   *
   * @param input Input as.
   */
  public StockModelImplFlex(Iportfolio input) {
    super(input);
    this.flexible = input;
  }

  private boolean totalStockCountHelper(String[] sVal) {
    try {
      int i = 0;
      int sum = 0;
      int size = flexible.getSize();
      while (i < size) {
        String[] s = flexible.getIndex(i).split(",");
        if (s[0].equals(sVal[0])) {

          if (dateCheck(s[1], sVal[1])) {
            sum += Float.parseFloat(s[2]);
          }
        }
        i++;
      }
      return sum >= (Float.parseFloat(sVal[2]));
    } catch (Exception e) {
      throw new IllegalArgumentException("Check date entered");
    }
  }

  @Override
  public void portfolioSell(String username, String filename, String input) {
    String[] sellVal = input.split(",");
    boolean totSv = totalStockCountHelper(sellVal);
    try (FileWriter fileWriter = new FileWriter(username + "/flex/"
            + filename + ".csv", true)) {
      if (totSv) {
        String[] s = input.split(",");
        if (!s[2].equals("0")) {
          flexible.append(s[0] + "," + s[1] + "," + "-" + s[2] + "," + s[3] + "," + s[4]);
          push(username + "/flex/" + filename);
        } else {
          throw new IllegalArgumentException("Quantity Entered is 0 which is not Valid");
        }
      } else {
        throw new IllegalArgumentException("Please enter valid quantity");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Please enter valid quantity");
    }
  }


  private String getLeastDate(String[][] pFall) {
    String lDate = pFall[0][1];
    for (int i = 0; i < pFall.length; i++) {
      try {
        if (!dateCheck(pFall[i][1], pFall[i + 1][1])) {
          lDate = pFall[i + 1][1];
        }
      } catch (Exception e) {
        //continue
      }
    }
    return lDate;
  }

  @Override
  public float flexibleTotalVal(String date) {
    try {
      int size = flexible.getSize();
      int i = 0;
      float sum = 0.00f;
      while (i < size) {
        String[] s = flexible.getIndex(i).split(",");
        if (dateCheck(s[1], date)) {
          Float priF = priceApiFetch(s[0], date);
          sum += Float.parseFloat(s[2]) * priF;
        }
        i++;
      }
      return sum;
    } catch (Exception e) {
      throw new IllegalArgumentException("Check date");
    }
  }

  @Override
  public float costBasis(String date)
          throws ParseException {
    if (!dateCheck(LocalDate.now().toString(), date)) {
      int i = 0;
      float sum = 0.00f;
      int size = flexible.getSize();
      while (i < size) {
        try {
          String[] s = flexible.getIndex(i).split(",");
          if (dateCheck(s[1], date)) {
            Float q = Float.valueOf(s[2]);
            if (q > 0) {
              sum = sum + Float.parseFloat(s[3]) * q + Float.parseFloat(s[4]);
            }
          }
        } catch (Exception e) {
          throw new IllegalArgumentException("Enter correct Date");
        }
        i++;
      }
      return sum;
    } else {
      throw new IllegalArgumentException("Dont Enter the future Date");
    }
  }

  private String plot(String[][] pFall, String dateTo, String[] days,
                      String type) {
    int scale = 1;
    int[] temp = new int[days.length + 1];
    String graph = "";

    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    for (int i = 0; i < days.length; i++) {
      try {
        priceApiFetch("A", days[i]);
        temp[i] = (int) flexibleTotalVal(days[i]);
        while ((temp[i] / scale) > 50) {
          scale = scale + 1;
        }

      } catch (Exception e) {
        LocalDate tmp = LocalDate.parse(days[i], f);
        int j = 0;
        while (true) {

          tmp = tmp.minusDays(1);
          try {
            priceApiFetch("A", tmp.toString());

            temp[i] = (int) flexibleTotalVal(tmp.toString());
            break;
          } catch (Exception ex) {
            j = j + 1;
            if (tmp.isAfter(LocalDate.now())) {
              tmp = LocalDate.now();
            }
            if (j > 5) {
              temp[i] = 0;
              break;
            }

          }
        }
      }
    }

    graph += type + " Bar Chart: " + "\n";

    for (int i = 0; i < days.length; i++) {
      if (type.equals("daily") || type.equals("weekly")) {
        graph += days[i] + " : ";
      }
      if (type.equals("monthly")) {
        LocalDate tmp = LocalDate.parse(days[i], f);
        graph += tmp.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toString()
                + " " + tmp.getYear() + ":";
      }
      if (type.equals("quarterly")) {
        LocalDate tmp = LocalDate.parse(days[i], f);
        graph += tmp.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toString()
                + " " + tmp.getYear() + ":";
      }
      if (type.equals("yearly")) {
        LocalDate tmp = LocalDate.parse(days[i], f);
        graph += tmp.getYear() + ":";
      }
      for (int j = 0; j < temp[i] / scale; j++) {
        graph += "*";
      }
      graph += " \n";
      if (type.equals("quarterly")) {
        i = i + 3;
      }
    }
    graph += "scale: * = $ " + String.valueOf(scale) + "\n";

    return graph;
  }

  @Override
  public String graphBuilder(String[][] pFall, String dateFrom, String dateTo) {
    try {
      String graph = "";
      if (dateCheck(dateFrom, dateTo) && dateCheck(dateTo,
              LocalDate.now().toString()) && dateCheck(getLeastDate(pFall), dateFrom)) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date1 = LocalDate.parse(dateFrom, f);
        LocalDate date2 = LocalDate.parse(dateTo, f);
        String tmp = Arrays.toString(date1.datesUntil(date2).toArray());
        String tmp2 = Arrays.toString(date1.datesUntil(date2).toArray());
        String[] dates = ((tmp.substring(1, tmp.length() - 1))).split(", ");
        String[] dates2 = ((tmp.substring(1, tmp.length() - 1)) + ", " + dateTo).split(", ");

        if (dates2.length <= 30 && dates2.length >= 5) {
          graph = plot(pFall, dateTo, dates2, "daily");

        } else if (dates.length / 7 < 30 && dates.length / 7 >= 4) {
          String pass = "";
          for (int i = 0; i < dates.length; i++) {
            LocalDate tmpO = LocalDate.parse(dates[i], f);
            if (tmpO.getDayOfWeek().toString().equals("FRIDAY")) {
              pass = pass + "," + dates[i];
            }
          }
          pass = pass + "," + dateTo;
          graph = plot(pFall, dateTo, pass.substring(1, pass.length())
                  .split(","), "weekly");
        } else if (dates2.length / 30 <= 30 && dates2.length / 30 >= 5) {
          String pass = "";
          for (int i = 0; i < dates2.length; i++) {
            LocalDate tmpo = LocalDate.parse(dates2[i], f);
            tmpo.withDayOfMonth(tmpo.getMonth().length(tmpo.isLeapYear()));
            if (pass.indexOf(tmpo.withDayOfMonth(tmpo.getMonth().
                    length(tmpo.isLeapYear())).toString()) == -1) {
              pass += ",";
              pass += tmpo.withDayOfMonth(tmpo.getMonth().length(tmpo.isLeapYear())).toString();
            }
          }
          graph = plot(pFall, dateTo, pass.substring(1, pass.length())
                  .split(","), "monthly");
        } else if (dates2.length / 120 <= 30 && dates2.length / 120 >= 5) {
          String pass = "";
          for (int i = 0; i < dates2.length; i++) {
            LocalDate tmpo = LocalDate.parse(dates2[i], f);
            if (pass.indexOf(tmpo.withDayOfMonth(tmpo.getMonth().
                    length(tmpo.isLeapYear())).toString()) == -1) {
              pass += ",";
              pass += tmpo.withDayOfMonth(tmpo.getMonth().length(tmpo.isLeapYear())).
                      toString();
            }
          }
          graph = plot(pFall, dateTo, pass.substring(1, pass.length()).split(","),
                  "quarterly");
        } else if (dates2.length / 365 < 30 && dates2.length / 365 >= 5) {
          String pass = "";
          for (int i = 0; i < dates2.length; i++) {
            LocalDate tmpo = LocalDate.parse(dates2[i], f);
            if (pass.indexOf(tmpo.getYear() + "-12-31") == -1) {
              pass += "," + tmpo.getYear() + "-12-31";
            }
          }
          graph = plot(pFall, dateTo, pass.substring(1, pass.length())
                  .split(","), "yearly");
        } else if (graph.equals("")) {
          throw new IllegalArgumentException("Kindly enter right range\n");
        }

      } else {
        throw new IllegalArgumentException("Enter right date!!! Supported start date for this " +
                "portfolio " +
                "is " + getLeastDate(pFall) + "\n");
      }
      return graph;
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }

  }

  @Override
  public void portfolioBuy(String username, String filename, String buy) {
    flexible.append(buy);

  }

  @Override
  public void seTip(String op, HashMap<String, Float> cost, String price, String comm
  ) throws ParseException, IOException {
    String[] data = new String[4];
    data = op.split(",");
    int days;
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    LocalDate date1 = LocalDate.parse(data[0], f);
    LocalDate date2 = LocalDate.parse(data[1], f);
    if (dateCheck(data[0], data[1])) {
      String tmp = Arrays.toString(date1.datesUntil(date2).toArray());
      String[] dates2 = ((tmp.substring(1, tmp.length() - 1)) + ", " + date2).split(", ");
      days = dates2.length;
      String temps = "";
      String[] fdates = new String[days];
      if (days > Integer.parseInt(data[2])) {
        for (int i = 0; i < days; i++) {
          if (i % Integer.parseInt(data[2]) == 0) {
            temps += dates2[i] + ",";
          }
        }
        fdates = temps.split(",");
        for (int i = 0; i < fdates.length; i++) {
          dca(cost, price, fdates[i], comm);
        }
      } else {
        throw new IllegalArgumentException("Days in Frequency is higher"
                + " than From date and To date");
      }
    } else {
      throw new IllegalArgumentException("From date is Bigger than TO date");
    }

  }

  @Override
  public void dca(HashMap<String, Float> cost, String price, String date, String comm)
          throws ParseException, IOException {
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    LocalDate datep = LocalDate.parse(date, f);
    String[] s = new String[cost.keySet().size()];
    Float p = 0.00f;
    p = Float.parseFloat(String.format("%.2f", Double.parseDouble(price))) - (cost.keySet()
            .size() * Float.parseFloat(comm));
    String a = "";
    s = cost.keySet().toArray(s);
    for (int i = 0; i < s.length; i++) {
      datep = LocalDate.parse(date, f);
      if (dateCheck(LocalDate.now().toString(), date)) {
        flexible.append(s[i] + "," + date + "," + "0" + "," + String.format("%.2f",
                p * cost.get(s[i]) / 100) + "," + comm);
        System.out.println(date);
      } else {
        float k = 0;
        try {
          k = priceApiFetch(s[i], date);
        } catch (Exception e) {
          int j = 1;
          while (j < 6) {
            LocalDate dt = datep;
            try {
              if (dt.plusDays(j).isAfter(LocalDate.now())) {
                flexible.append(s[i] + "," + date + "," + "0" + "," + String.format("%.2f",
                        p * cost.get(s[i]) / 100) + "," + comm);
                break;

              }
              k = priceApiFetch(s[i], dt.plusDays(j).toString());
              datep = datep.plusDays(j);

              break;
            } catch (Exception m) {
              System.out.println(m.getMessage());
            }

            j++;
          }
        }
        flexible.append(s[i] + "," + datep.toString() + "," + String.format("%.2f",
                ((p * cost.get(s[i]) / 100) / k)) +
                "," + k + "," + comm);
      }
    }
  }

}
