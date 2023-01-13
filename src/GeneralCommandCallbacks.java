import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controllermain;
import controller.ControllermainImpl;
import model.IModel;
import model.MainModel;
import view.StockView;
import view.StockViewImpl;

/**
 * This example shows how to specify the (Key,Runnable) keyboard map using shorter syntax
 * using the ability of java 8 to support lambda expressions.
 */
public class GeneralCommandCallbacks {
  /**
   * Main driver function for the whole project.
   *
   * @param args argument of the function.
   * @throws IOException          is thrown.
   * @throws ParseException       is thrown.
   * @throws InterruptedException is thrown.
   */
  public static void main(String[] args) throws IOException, ParseException, InterruptedException {
    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }

    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
      //handle exception
    }
    IModel model = new MainModel();
    StockView view = new StockViewImpl(System.out);
    Controllermain c = new ControllermainImpl(view, new InputStreamReader(System.in));

    c.goo(model);
  }
}
