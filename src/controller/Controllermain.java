package controller;

import java.io.IOException;
import java.text.ParseException;

import model.IModel;

/**
 * This is the main controller which acts as the facade between two other controllers
 * and delegates the view and the model accordingly depending upon the user choice.
 */
public interface Controllermain {
  /**
   * This is the main controller which acts as the facade between two other controllers
   * and delegates the view and the model accordingly depending upon the user choice.
   *
   * @param portfolio Model object that is to be passed inside the controller.
   * @throws IOException          is thrown when file cannot be accessed.
   * @throws InterruptedException is thrown when a thread is interrupted.
   * @throws ParseException       When the dates cannot be parsed.
   */
  void goo(IModel portfolio) throws IOException, InterruptedException,
          ParseException;
}
