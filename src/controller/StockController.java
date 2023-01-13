package controller;

import java.io.IOException;
import java.text.ParseException;

import model.IModel;

/**
 * StockController Interface which gets in the user inputs and
 * performs operation using stock model and displays using stock view.
 */
public interface StockController {

  /**
   * go gets StockModel object. It contains the driver code which scans the input from user and
   * passes required operations to the Model and View.
   *
   * @param portfolio Model object that is to be passed inside the controller.
   * @throws IOException          is thrown when file cannot be accessed.
   * @throws InterruptedException is thrown when a thread is interrupted.
   * @throws ParseException       When the dates cannot be parsed.
   */
  void goo(IModel portfolio) throws IOException, InterruptedException, ParseException;
}
