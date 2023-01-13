package view;

import java.io.IOException;

import view.StockView;

/**
 * StockViewImpl class which implements the stock view class.
 */
public class StockViewImpl implements StockView {
  final Appendable out;

  /**
   * Constructor of StockViewImpl which gets the Appendable object and initializes it.
   *
   * @param out gets the appendable object.
   */
  public StockViewImpl(Appendable out) {
    this.out = out;

  }

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    return out.append(csq);
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    return out.append(csq, start, end);
  }

  @Override
  public Appendable append(char c) throws IOException {
    return out.append(c);
  }

}
