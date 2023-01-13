package portfolio;


import java.text.ParseException;
import java.util.LinkedList;

/**
 * portfolio class..
 */
public class PortfolioFlex extends Portfolio implements Iportfolio {
  private LinkedList<Float> commission;

  /**
   * portfolio class flex constructor.
   *
   * @param s portfolio class..
   * @throws ParseException portfolio class..
   */
  public PortfolioFlex(String[][] s) throws ParseException {
    super(s);
    commission = new LinkedList<Float>();
    for (int i = 0; i < s.length; i++) {
      commission.add(Float.valueOf(s[i][4]));

    }

  }

  /**
   * portfolio class..
   */
  public PortfolioFlex() {
    super();
    commission = new LinkedList<Float>();

  }

  @Override
  public float append(String input) {
    String[] s = input.split(",");
    super.append(input);
    commission.add(Float.valueOf(s[4]));

    return 0;
  }

  @Override
  public String getIndex(int i) {
    return super.getIndex(i) + "," + commission.get(i);
  }

  @Override
  public String toString() {
    String s = "";
    int i = 0;
    while (i < commission.size()) {
      s = s + super.getIndex(i) + "," + commission.get(i) + "\n";
      i++;
    }
    return s;
  }


}
