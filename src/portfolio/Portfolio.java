package portfolio;

import java.text.ParseException;
import java.util.LinkedList;

/**
 * portfolio class.
 */
public class Portfolio implements Iportfolio {
  private LinkedList<String> ticker;
  private LinkedList<String> date;
  private LinkedList<Float> qty;
  private LinkedList<Float> price;

  /**
   * portfolio class.
   *
   * @param s portfolio class.
   * @throws ParseException portfolio class.
   */
  public Portfolio(String[][] s) throws ParseException {
    ticker = new LinkedList<String>();
    date = new LinkedList<String>();
    qty = new LinkedList<Float>();
    price = new LinkedList<Float>();

    for (int i = 0; i < s.length; i++) {
      ticker.add(s[i][0]);
      date.add(s[i][1]);
      qty.add(Float.valueOf(s[i][2]));
      price.add(Float.valueOf(s[i][3]));
    }

  }

  /**
   * portfolio class implementation clkass and its constructor.
   */

  public Portfolio() {
    this.ticker = new LinkedList<String>();
    this.date = new LinkedList<String>();
    this.qty = new LinkedList<Float>();
    this.price = new LinkedList<Float>();

  }

  @Override
  public float append(String input) {
    String[] s = input.split(",");
    ticker.add(s[0]);
    date.add(s[1]);
    qty.add(Float.valueOf(s[2]));
    price.add(Float.valueOf(s[3]));

    return 0;
  }

  @Override
  public String getIndex(int i) {
    return ticker.get(i) + "," + date.get(i) + "," + qty.get(i) + "," + price.get(i);
  }

  @Override
  public String toString() {
    String s = "";
    int i = 0;
    while (i < ticker.size()) {
      s += ticker.get(i) + "," + date.get(i) + "," + qty.get(i) + "," + price.get(i) + "\n";
      i++;
    }
    return s;
  }

  @Override
  public int getSize() {
    return ticker.size();
  }

}
