package controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.IModel;
import view.StockView;

/**
 * StockControllerImpl which implements StockController gets in the user inputs and
 * performs operation using stock model and displays using stock view.
 */
public class StockControllerimpl implements StockController {
  final StockView out;
  final Readable in;
  final Scanner scanInput;

  /**
   * Constructor of the class StockControllerimpl it initializes input and output object.
   *
   * @param out contains the output values appended to it.
   * @param in  contains the input values scanned by the user appended to it.
   */
  public StockControllerimpl(StockView out, Readable in) {
    this.out = out;
    this.in = in;
    scanInput = new Scanner(this.in);

  }

  private String scan() {
    return scanInput.next();
  }

  /**
   * go gets StockModelFlex object. It contains the driver code which scans the input from user and
   * passes required operations to the Model and View.
   */
  public void goo(IModel portfolio) throws IOException, InterruptedException, ParseException {
    Objects.requireNonNull(portfolio);
    String username = "";
    String type = "";
    while (true) {
      if (username.equals("")) {
        username = login(portfolio);
      }
      if (username.equals("exit")) {
        return;
      }

      while (true) {
        if (type.equals("")) {
          this.out.append("Enter type of portfolio you want 1. Flexible 2. Inflexible: ");
          type = scan();
        }
        if (!type.equals("1") && !type.equals("2")) {
          this.out.append("Wrong type\n");
          this.out.append("Enter type of portfolio you want 1. Flexible 2. Inflexible: ");
          type = scan();

        } else {
          break;
        }
      }
      if (type.equals("1")) {
        menu();
      }
      if (type.equals("2")) {
        menuInFlex();
      }
      this.out.append("your Choice:");
      switch (scan()) {
        case "1":

          String filename = "";
          String s = "";
          filename = portfolioCreationHelper(username, portfolio, type);
          if (filename.equals("")) {
            break;
          }
          s = portfolioCreation(portfolio, type);
          portfolio.portfolioBuilder(s, type);
          while (true) {
            this.out.append("\nSuccessfully Saved Entry\nDo "
                    + "you wish to add more?\nPress '1' -> yes"
                    + " "
                    + "\nPress '2' -> NO\n");
            this.out.append("your Choice:");
            String op = scan();
            if (op.equals("2")) {
              break;
            } else if (op.equals("1")) {
              s = portfolioCreation(portfolio, type);
              portfolio.portfolioBuilder(s, type);

            } else {
              this.out.append("Enter a valid Choice!!!:");
            }
          }
          portfolio.push(username, type, filename);
          break;
        case "2":
          String[][] output;
          while (true) {
            try {
              this.out.append("\nEnter file name to be fetched:");
              if (type.equals("1")) {
                output = portfolio.fetch(username + "/" + "flex/" + scan(), type);
              } else {
                output = portfolio.fetch(username + "/" + scan(), type);
              }

              for (int i = 0; i < output.length; i++) {
                this.out.append("Ticker:" + output[i][0]
                        + "\n" + "Date:" + output[i][1]
                        + "\n" + "Quantity:" + output[i][2] + "\n"
                        + "Price:" + output[i][3]);
                if (type.equals("1")) {
                  this.out.append("\nCommission:" + output[i][4]
                          + "\n" + "---------------------" + "\n");
                } else {
                  this.out.append("\n" + "---------------------" + "\n");
                }
              }
              break;
            } catch (Exception e) {
              this.out.append(e.getMessage() + "\n");
              this.out.append("\nPress '1' -> Try Again"
                      + " " + "\nPress 'anyinput' "
                      + "-> go back to main menu\n");
              String c = scan();
              if (!c.equals("1")) {
                break;
              }
            }
          }
          break;
        case "3":
          if (type.equals("2")) {
            uploadHelper(username, portfolio, type);
          }
          if (type.equals("1")) {
            uploadHelperFlex(username, portfolio, type);
          }
          break;
        case "4":
          if (type.equals("2")) {
            String k = "";
            k = totalValHelper(username, portfolio, type);
            if (!k.equals("")) {
              this.out.append("Total:" + k + "\n");
              this.out.append("--------------------------------------------------------\n");
            }
            break;
          }
          if (type.equals("1")) {
            fTotalValHelper(username, portfolio, type);
            break;
          }
          break;
        case "5":
          if (type.equals("2")) {
            this.out.append("Exiting.....");
            TimeUnit.SECONDS.sleep(2);
            return;
          }
          buyHelper(username, portfolio, type);
          break;
        case "6":
          if (type.equals("2")) {
            this.out.append("Please Enter a Valid option. Try Again\n");
            break;
          }
          sellHelper(username, portfolio, type);
          break;
        case "7":
          if (type.equals("2")) {
            this.out.append("Please Enter a Valid option. Try Again\n");
            break;
          }
          costBasisHelper(username, portfolio, type);
          break;
        case "8":
          if (type.equals("2")) {
            this.out.append("Please Enter a Valid option. Try Again\n");
            break;
          }
          graphHelper(username, portfolio, type);
          break;
        case "9":
          if (type.equals("2")) {
            this.out.append("Please Enter a Valid option. Try Again\n");
            break;
          }
          this.out.append("Exiting.....");
          TimeUnit.SECONDS.sleep(2);
          return;
        default:
          this.out.append("Please Enter a Valid option. Try Again\n");
      }

    }
  }

  private String login(IModel portfolio) throws IOException {
    String username;
    String password;
    String choice;
    String output;
    output = "";
    while (true) {

      this.out.append("Hi There Press 1 -> Login\nPress 2 -> Create new user\nPress 3 -> Exit"
              + "\nyour choice:");
      choice = scan();
      if (choice.equals("1")) {
        this.out.append("Enter Username:");
        username = scan();
        this.out.append("Enter Password:");
        password = scan();
        if (portfolio.login(username, password)) {
          this.out.append("Logged in\n");
          break;
        } else {
          this.out.append("User Does not exist Please Try again\n");
        }

      } else if (choice.equals("2")) {
        while (true) {
          this.out.append("Enter Username(exactly 5 charcters Required!!):");
          username = scan();
          this.out.append("Enter pin(exactly 5 charcters Required!! Not same as Username):");
          password = scan();
          if (username.length() == 5 && password.length() == 5 && !password.equals(username)) {
            try {
              portfolio.createUser(username, password);
              this.out.append("Successfully Created the profile. Now Login!!!\n");
              break;
            } catch (Exception e) {
              this.out.append(e.getMessage() + " Try Again\n");
              this.out.append("\nPress->1 to go back or press -> any input to try again ");
              String c;
              c = this.scan();
              if (c.equals("1")) {
                break;
              }
            }
          } else {
            this.out.append("The entered Username or Password does not "
                    + "Satisfy the requirements!!!"
                    + " Try Again\n");
          }
        }
      } else if (choice.equals("3")) {
        return "exit";
      } else {
        this.out.append("Enter correct Choice\n");
      }
    }
    return username;
  }

  private void menu() throws IOException {

    String menuoptions;
    menuoptions = "Welcome to Modern Stock Management Service : \n"
            + "Press the option to do the "
            + "operations \n" + "1. Create new portfolio \n"
            + "2. Get Composition \n"
            + "3. Upload portfolio \n"
            + "4. Evaluate portfolio \n" + "5. Buy Stocks \n"
            + "6. Sell Stocks \n"
            + "7. Get Cost Basis of portfolio \n"
            + "8. Get Portfolio Performance Over Time (Graph) \n"
            + "9. Exit \n";
    this.out.append(menuoptions);

  }

  private void menuInFlex() throws IOException {

    String menuoptions;
    menuoptions = "Welcome to Modern Stock Management Service : \n"
            + "Press the option to do the "
            + "operations \n" + "1. Create new portfolio \n"
            + "2. Get Composition \n"
            + "3. Upload portfolio \n"
            + "4. Evaluate portfolio \n"
            + "5. Exit \n";
    this.out.append(menuoptions);

  }

  private String portfolioCreationHelper(String username, IModel portfolio, String type)
          throws IOException {
    int flag = 0;
    String filename = "";
    String s = "";
    while (true) {
      try {
        if (flag == 1) {
          this.out.append("Enter Correct Choice\n");
          throw new IllegalArgumentException("");
        }
        this.out.append("Portfolio Creation: \n");
        this.out.append("Enter Portfolio Name:");
        filename = scan();
        if (type.equals("1")) {
          filename = "flex" + "/" + filename;
          File path = new File(username + "/flex");
          if (!path.exists()) {
            path.mkdirs();
          }
        }
        portfolio.portfolioCreator(username, filename, type);
        this.out.append("portfolio Created Successfully");
        flag = 0;
        break;
      } catch (Exception e) {
        this.out.append(e.getMessage() + "Press '1' -> "
                + "Try AGAIN\nPress '2' -> main menu\nyour" +
                " Choice:");
        String choice = scan();
        if (choice.equals("1")) {
          flag = 0;
        } else if (choice.equals("2")) {
          flag = 3;
          break;
        } else {
          flag = 1;
        }
      }
    }
    if (flag == 3) {
      return "";
    }
    return filename;
  }

  private String portfolioCreation(IModel portfolio, String type) throws IOException {
    while (true) {
      String output = "";
      try {
        String ticker = "";
        String date = "";
        String price = "";
        String qty = "";
        String com = "";

        this.out.append("\nEnter Valid ticker Symbol:");
        ticker = scan();
        output += ticker + ",";
        this.out.append("Enter the Date in format(YYYY-MM-DD):");
        date = scan();
        price = String.format("%.3f", portfolio.priceApiFetch(ticker, date));
        output += date + ",";
        this.out.append("Enter Quantity:");
        qty = scan();
        Integer.parseInt(qty);
        output += qty + ",";
        if (type.equals("1")) {
          this.out.append("Enter broker fee:");
          com = scan();
          if (Float.parseFloat(com) * -1 >= 0) {
            throw new IllegalArgumentException("Commission Fee is negative.\n");
          }
        }
        this.out.append("price from API is $" + price + " !!");
        output += price + ",";
        output += com + "\n";
        return output;
      } catch (Exception e) {
        if (e.getMessage() == null) {
          this.out.append("wrong data");
        } else if (e.getMessage().contains("Index")) {
          this.out.append("Wrong Date Entered");
        } else if (e.getMessage().contains("For")) {
          this.out.append("Enter Quantity Correctly!!");
        } else {
          this.out.append(e.getMessage());
        }
      }
    }
  }

  private String totalValHelper(String username, IModel portfolio, String type) throws IOException {
    String ret = "";

    String[][] output;
    while (true) {
      try {
        String s;
        String input;
        this.out.append("\nEnter file name to be fetched: ");
        s = scan();
        this.out.append("\n Enter Date in (yyyy-MM-DD): ");
        input = scan();
        output = portfolio.fetch(username + "/" + s, type);
        ret = String.format("Value of the portfolio on " + input + " is %.2f",
                portfolio.portfolioTotal(username, input, type, s));

        this.out.append("Contents of the Portfolio on the Date of creation:\n");

        break;
      } catch (Exception e) {
        this.out.append(e.getMessage() + "\n");
        this.out.append("\nPress '1' -> Try Again"
                + " " + "\nPress 'anyinput' -> go back to main menu\n");
        String c = scan();
        if (!c.equals("1")) {
          break;
        }
      }
    }
    return ret;
  }

  private void uploadHelperFlex(String username, IModel portfolio, String type) throws IOException {
    String[][] output;
    String k = "";
    String filename = "";
    while (true) {
      try {
        String s = "";
        this.out.append("\nEnter portfolio "
                + "with path to be fetched(to not include extension of "
                + "the portfolio:");
        s = scan();
        output = portfolio.fetch(s, type);
        this.out.append("Enter portfolio name");
        filename = scan();
        portfolio.portfolioCreator(username, "flex/" + filename, type);
        for (int i = 0; i < output.length; i++) {

          if (output[i][0].equals("") || output[i][1].equals("") || output[i][2].equals("")
                  || output[i][3].equals("") || output[i][4].equals("")) {
            throw new IllegalArgumentException("Empty String Found\n");
          }
          this.out.append("Ticker:" + output[i][0] + "\n"
                  + "Date:" + output[i][1] + "\n"
                  + "Quantity:" + output[i][2] + "\n"
                  + "Price: $" + output[i][3] + "\n"
                  + "\nCommission: $" + output[i][4] + "\n"
                  + "---------------------" + "\n");
          portfolio.priceApiFetch(output[i][0], "2022-11-01");
          Float.parseFloat(output[i][2]);
          Float.parseFloat(output[i][3]);
          Float.parseFloat(output[i][4]);
          k += output[i][0] + "," + output[i][1] + "," + output[i][2] + ","
                  + output[i][3] + "," + output[i][4] + "\n";
        }
        if (!k.equals("")) {
          portfolio.upload(output, filename, username, type);
          break;
        }
      } catch (Exception e) {
        k = "";
        if (!filename.equals("")) {
          portfolio.deletePortfolio(username, "flex/" + filename, type);
        }
        if (e.getMessage() == null) {
          this.out.append("Found missing values");
        } else if (e.getMessage().contains("For")) {
          this.out.append("Enter price/Quantity of the above entry Correctly!!");
        } else {
          this.out.append(e.getMessage());
        }
        this.out.append("\nPress '1' -> Try Again"
                + " " + "\nPress 'anyinput' -> go back to main menu\n");
        String c = scan();
        if (!c.equals("1")) {
          break;
        }
      }
    }
  }

  private void uploadHelper(String username, IModel portfolio, String type) throws IOException {
    String[][] output;
    String k = "";
    String filename = "";
    while (true) {
      try {
        String s = "";
        this.out.append("\nEnter portfolio "
                + "with path to be fetched(to not include extension of "
                + "the portfolio:");
        s = scan();
        output = portfolio.fetch(s, type);
        this.out.append("Enter portfolio name");
        filename = scan();
        portfolio.portfolioCreator(username, filename, type);
        for (int i = 0; i < output.length; i++) {

          if (output[i][0].equals("") || output[i][1].equals("") || output[i][2].equals("")
                  || output[i][3].equals("")) {
            throw new IllegalArgumentException("Empty String Found\n");
          }
          this.out.append("Ticker:" + output[i][0] + "\n"
                  + "Date:" + output[i][1] + "\n"
                  + "Quantity:" + output[i][2] + "\n"
                  + "Price:" + output[i][3] + "\n"
                  + "---------------------" + "\n");
          portfolio.priceApiFetch(output[i][0], output[i][1]);
          Integer.parseInt(output[i][2]);
          Float.parseFloat(output[i][3]);
          k += output[i][0] + "," + output[i][1] + "," + output[i][2] + ","
                  + output[i][3] + "\n";
        }
        if (!k.equals("")) {
          //portfolio.portfolioBuilder(username + "/" + filename, k,type);
          break;
        }
      } catch (Exception e) {
        k = "";
        if (!filename.equals("")) {
          portfolio.deletePortfolio(username, filename, type);
        }
        if (e.getMessage() == null) {
          this.out.append("Found missing values");
        } else if (e.getMessage().contains("For")) {
          this.out.append("Enter price/Quantity of the above entry Correctly!!");
        } else {
          this.out.append(e.getMessage());
        }
        this.out.append("\nPress '1' -> Try Again"
                + " " + "\nPress 'anyinput' -> go back to main menu\n");
        String c = scan();
        if (!c.equals("1")) {
          break;
        }
      }
    }
  }

  private void buyHelper(String username, IModel portfolio, String type)
          throws IOException, ParseException {
    String buy = "";
    String f = "";
    int i = 0;
    while (true) {
      if (i > 0) {
        this.out.append("Wrong entry");
        this.out.append("Press 1 -> go back\n");
        this.out.append("Press any input ->continue");
        f = scan();
        i = 0;
        if (f.equals("1")) {
          break;
        }
      }
      this.out.append("Enter filename: ");
      f = scan();
      if (!portfolio.buyCheck(username, "flex/" + f)) {
        i++;
        continue;
      }
      buy = portfolioCreation(portfolio, "1");
      portfolio.portfolioBuy(username, f, buy);
      portfolio.push(username, type, "flex/" + f);
      String temp = "";
      while (true) {
        this.out.append("\npress 1-> add more\n");
        this.out.append("press any input-> exit: ");
        temp = scan();
        if (!temp.equals("1")) {
          break;
        } else {
          buy = "";
          buy = portfolioCreation(portfolio, "1");
          portfolio.portfolioBuy(username, f, buy);
          portfolio.push(username, type, "flex/" + f);
        }

      }
      if (!temp.equals("1")) {
        break;
      }


    }
  }

  private void sellHelper(String username, IModel portfolio, String type) throws IOException {
    String sell = "";
    String f = "";
    int i = 0;
    while (true) {
      if (i > 0) {
        this.out.append("\nPress 1 -> go back\n");
        this.out.append("Press any input ->continue");
        f = scan();
        i = 0;
        if (f.equals("1")) {
          break;
        }
      }
      this.out.append("Enter filename: ");
      f = scan();
      if (!portfolio.buyCheck(username, "flex/" + f)) {
        i++;
        continue;
      }
      sell = portfolioCreation(portfolio, "1");
      try {
        portfolio.portfolioSell(username, f, sell);
        this.out.append("Successfully Sold\n");
      } catch (Exception e) {
        this.out.append(e.getMessage());
      }
      String temp = "";
      while (true) {
        this.out.append("\nTo sell more / try again stocks press 1-> sell more\n");
        this.out.append("press any input-> exit: ");
        temp = scan();
        if (!temp.equals("1")) {
          break;
        } else {
          sell = "";
          sell = portfolioCreation(portfolio, "1");
          try {
            portfolio.portfolioSell(username, f, sell);
          } catch (Exception e) {
            this.out.append(e.getMessage());
          }
        }

      }
      if (!temp.equals("1")) {
        break;
      }


    }
  }

  private void costBasisHelper(String username, IModel portfolio, String type) throws IOException {
    while (true) {
      try {
        String f = "";
        this.out.append("Enter portfolio name: ");
        f = scan();
        if (!portfolio.buyCheck(username, "flex/" + f)) {
          throw new IllegalArgumentException("\nWrong file name Entered (Try again)\n");
        }
        String date = "";
        this.out.append("Enter date to get the Cost basis : ");
        date = scan();
        this.out.append("Cost bias is: $");
        this.out.append(String.format("%.3f\n", portfolio.costBasis(type, username,
                date, f)));
        break;
      } catch (Exception e) {
        String k = "";
        this.out.append("\nPress -> 1 to try again or Press any input to ->go back ");
        k = scan();
        if (!k.equals("1")) {
          break;
        }
      }
    }
  }

  private void graphHelper(String username, IModel portfolio, String type) throws IOException {
    while (true) {
      try {
        String file = "";
        this.out.append("Enter portfolio Name: ");
        file = scan();
        if (!portfolio.buyCheck(username, "flex/" + file)) {
          throw new IllegalArgumentException("portfolio does not exist Try again");
        }
        String from = "";
        String to = "";
        this.out.append("\nEnter from date: ");
        from = scan();
        this.out.append("\nEnter to date: ");
        to = scan();
        String graph = "";
        this.out.append("Your Graph is Being Plotted......\n");
        graph = portfolio.graphBuilder(type, username, portfolio.fetch(username + "/"
                        + "flex/" + file, type),
                from, to, file);
        this.out.append("Performance of portfolio " + "flex/" + file + " from " + from
                + " to " + to + "\n");
        this.out.append(graph + "\n");
        break;
      } catch (Exception e) {
        this.out.append(e.getMessage());
        String k = "";
        this.out.append("\nPress 1->try again or any input->go back");
        k = scan();
        if (!k.equals("1")) {
          break;
        }
      }
    }
  }

  private void fTotalValHelper(String username, IModel portfolio, String type) throws IOException {
    while (true) {
      try {
        String f = "";
        this.out.append("Enter portfolio name: ");
        f = scan();
        if (!portfolio.buyCheck(username, "flex/" + f)) {
          throw new IllegalArgumentException("\nWrong file name Entered (Try again)\n");
        }
        String date = "";
        this.out.append("Enter date to get the Total Value : ");
        date = scan();
        this.out.append("Total Value is $" + String.format("%.2f\n",
                portfolio.flexibleTotalVal(date, username, type, f)));
        break;
      } catch (Exception e) {
        String k = "";
        this.out.append("\nPress -> 1 to try again or Press any input to ->go back ");
        k = scan();
        if (!k.equals("1")) {
          break;
        }
      }
    }
  }

}