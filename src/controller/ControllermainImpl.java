package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.IModel;
import view.IView;
import view.JFrameView;
import view.StockView;

/**
 * This is the main controller which acts as the facade between two other controllers
 * and delegates the view and the model accordingly depending upon the user choice.
 */
public class ControllermainImpl implements Controllermain {
  final StockView out;
  final Readable in;
  final Scanner scanInput;
  StockController c;
  Features f;

  /**
   * This is the main controller constructor which acts as the facade between two other controllers
   * and delegates the view and the model accordingly depending upon the user choice.
   *
   * @param out output object.
   * @param in  input object.
   */

  public ControllermainImpl(StockView out, Readable in) {
    this.out = out;
    this.in = in;
    scanInput = new Scanner(this.in);
  }

  private String scan() {
    return scanInput.next();
  }

  @Override
  public void goo(IModel portfolio) throws IOException, InterruptedException,
          ParseException {
    while (true) {
      this.out.append("Welcome to portfolio Managment\nPress 1->graphical User interface\nPress "
              + "2->Text Based Interface");
      String s = scan();
      if (s.equals("2")) {
        c = new StockControllerimpl(out, in);
        c.goo(portfolio);
        break;
      } else if (s.equals("1")) {
        IView view1 = new JFrameView("");
        f = new Controller(portfolio);
        f.setView(view1);
        break;

      } else {
        this.out.append("Wrong Input");
      }

    }

  }
}
