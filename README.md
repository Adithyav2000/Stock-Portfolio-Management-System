# Stock-Portfolio-Management-System
ASSIGNMENT-6 : Stocks (Part 3)

KRISHNAN NARAYANAN - 002752936
ADITHYA VIBAKAR    - 002783656

---------------------------------------------------------------------------------------

To run the Jar file program open the res directory in the terminal and give  java -jar stock.jar

First Press 1 to start the GUI 
To execute the application in GUI:
---->Upon running the GUI, the user will see a screen that says Main Panel at the top. The welcome pge allows the user to Login, Signup or Exit.
---->Initially a new user will have to SignUp by clicking the button. The signup window asks the user for a Username and Password, each of 5 characters length. 
---->The username and password will have to be exacty 5 characters long. After clicking the Create button, the application shows a confirmation message saying the account has been created.
CREATION OF USER ACCOUNT:
---->Upon creation of account, the user will be taken to the Login page, where the user has to login with the same credentials.
----> After Logging in, the user will be redirected to the home page which presents several options to the user.
---->The Main Menu options ae namely, Create portfolio, Get Composition, Loading and retrieving portfolio, Buy using Dollar cost average, 
     Investment Mode, Cost Basis, Evaluate portfolio, Buying and Selling of stocks and Logout.
CREATION OF NEW PORTFOLIO WITH INVESTMENT MODE:
---->Create Portfolio option will ask the user for portfolio name in order to create. 
---->Upon clicking the create portfolio button, the user will be asked whether the user wants to add any investment plan to the portfolio.
----> Selecting the Yes option, the user will be asked for a start date, end date, frequency, total amount to invest and a commission fee pertaining to the entire investment plan. 
---->Once the user gives all the input and clicks the create investment button, the user will be asked for a Ticker Symbol and percentage of amount to be invested for that particular stock.
---->Clicking on the Add Ticker button below allows the user to buy stocks with their percentage repetitively, until the percentage adds up to a total of 100% and 
     shows up a message, "percentage to be added is 0.00%".
---->The user also has an option to reset Ticker symbol and percentage.
---->Once all the stocks are added, the user will have to press "Start Investment". Upon clicking the button, a confirmation message pops up saying that all stocks are scheduled accordingly to be invested.
NOTE: User will not be able to modify or cancel the investment after scheduling it through the button click.
INVESTMENT MODE FOR AN EXISTING PORTFOLIO:
---->The menu option for Investment Mode, asks the user to choose the portfolio in which the user wants to invest.
---->Upon selecting the portfolio, the user is shown a window where the Start date, end date, frequency to invest (in days), total amount to invest the commission fee are expected as the user input.
---->Clicking the Create button redirects the user to a page where it asks for the ticker symbol and percentage.
---->Clicking on the Add Ticker button below allows the user to buy stocks with their percentage repetitively, until the percentage adds up to a total of 100% and 
     shows up a message, "percentage to be added is 0.00%".
---->The user also has an option to reset Ticker symbol and percentage.
---->Once all the stocks are added, the user will have to press "Start Investment". Upon clicking the button, a confirmation message pops up saying that all stocks are scheduled accordingly to be invested.
BUY USING DOLLAR COST AVERAGING ON AN EXISTING PORTFOLIO:
---->Initially the user has to select an existing portfolio from the dropdown options. After selecting portfolio, the user is asked for date to invest, total amount to invest and commission fee.
---->Clicking "Create Investment" will redirect the user to a frame that asks for ticker symbol and percentage.
---->Clicking on the Add Ticker button below allows the user to buy stocks with their percentage repetitively, until the percentage adds up to a total of 100% and 
     shows up a message, "percentage to be added is 0.00%".
---->The user also has an option to reset Ticker symbol and percentage.
---->Once all the stocks are added, the user will have to press "Start Investment". Upon clicking the button, a confirmation message pops up saying that all stocks are scheduled accordingly to be invested.
DISPLAYING COMPOSITION OF PORTFOLIO:
---->Upon creation of investment plan, the user will be redirected to the main menu.
---->CLicking on the "Get Composition" menu option allows the user to see the contents of the portfolio by asking the user for the portfolio name in the form of a Dropdown menu,
     where the user will have to choose the portfolio.
---->The portolio contents are displayed right below in a scrollable format.
EVALUATE PORTFOLIO:
---->In the main menu, Evaluate portfolio button lets the user find the value of a portolio on a certain date.
---->Upon choosing the portfolio, the user will be asked a date to evaluate.
---->Clicking "Get Total Value" button will calculate and print the value of the portfolio on that date,  through a message.
COST BASIS OF A PORTFOLIO:
---->In the main menu, Get Cost Basis option lets the user find the cost basis of a portolio on a certain date.
---->Upon choosing the portfolio, the user will be asked a date to determine cost basis
---->Clicking "Get Cost Basis" button will calculate and print the cost basis of the portfolio on that date, through a message.
LOAD PORTFOLIO:
---->Load Portfolio option in the main menu allows the user to Upload a portfolio in the application.
---->User will be asked for a portfolio name under what the user wants to upload their portfolio to the application.
---->After giving the name, the user will be presented a window where the portfolio can be browsed upon and selected.
---->Upon selection of file, a success message pops up saying that the portfolio has been uploaded and that its composition can be viewed.
---->Next window lets the user select the portfolio by name and displays its composition below.
BUY STOCK:
---->This menu option allows the user to select the portfolio at the starting of the option.
---->Then, the user is asked for Ticker Symbol, Date, Quantity and Commission fee to buy that stock.
---->Upon clicking the "Buy Stocks" button, a confirmation message is printed on the screen.
---->The user can continue to buy more stocks by following the same procedure repeatedly.
SELL STOCK:
---->This menu option allows the user to select the portfolio at the starting of the option.
---->Then, the user is asked for Ticker Symbol, Date, Quantity and Commission fee to buy that stock.
---->Upon clicking the "Sell Stocks" button, a confirmation message is printed on the screen.
---->The user can continue to sell more stocks by following the same procedure repeatedly.
NOTE: The user would see a message asking to enter valid quantity if the quantity is more than what is present for that stock in that portfolio.
LOGOUT:
---->The current user will be logged out of the application upon clicking the Logout option on Main Menu.


For Commandline first press 2 
---->First to login you need to create a new user to login
---->Press 2 and create your user by giving username and password with exactly 5 characters.
---->Make sure your username and password is not same.
---->Now login by pressing 1 and providing username and password.
---->Enter type of portfolio you want as 1 for flexible portfolio.
---->Now to Create new portfolio press 1
---->Give portfolio name
---->Provide first stock Ticker symbol, date, and quantity and broker fee.
---->Press 1 to add new stock
---->Do it for desired number of times and then press 2 to save this portfolio with desired number of stocks
---->Now in the main menu again press 1 
---->Give second portfolio name
---->provide first stock Ticker symbol, date, and quantity and broker fee. 
---->Press 1 to add new stock
---->Do it for desired number of times and then press 2 to save this portfolio with desired number of stocks.
---->Now in the Main menu press 5 for buying the stock.
---->Enter portfolio name to buy the stock for.
---->Provide first stock Ticker symbol, date, and quantity and broker fee.
---->press 1 to add more and keep doing for desired number times and then press any key to exit.
---->You can press 4 and give portfolio name and date to get the total value of portfolio in that particular date.
---->You can press 1 to try again and give portfolio name and date to get the total value of portfolio in that particular date.
---->You can press 7 and give portfolio name and date to get the cost basis of portfolio in that particular date.
---->You can press 1 to try again and give portfolio name and date to get the cost basis of portfolio in that particular date.
---->Press 9 to exit.
To run our program in intellij make sure you have java JDK 11. And Junit Test 4.
---->Go to src and open stock main to run the program.
---->For running the test go to test folder and run each of the files.
---->Note: These test runs only once so if you want to run again delete all the files and folder other than the src and test
and then run.
List of Libraries
File;
FileWriter;
URL;
SimpleDateFormat;
LocalDate;
format.DateTimeFormatter;
Date;
Locale;
Scanner;
