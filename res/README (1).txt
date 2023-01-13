ASSIGNMENT-6 : Stocks (Part 3)

KRISHNAN NARAYANAN - 002752936
ADITHYA VIBAKAR    - 002783656

---------------------------------------------------------------------------------------
New Features Of The Program 2.0
---------------------------------------------------------------------------------------
1. |Supporting Graphical as well as Text based Interface|
	----> During the start of the program User will be prompted with an option to choose between text-based and graphical Interface.
	----> Press one for Graphical user interface.
	----> press two for Text Based interface.

2. |Graphical User interface supports only flexibe|
	----> Supports Login and Signup and Logout of user in GUI
	----> It supports creation of portfolio creation by clicking create portfolio button in Main menu Post Login.
	----> It Supports Portfolio Composition Graphically.
	----> It supports portfolio Load and Retrieve Portfolio Graphically.
	----> It supports Buying and selling of Stocks Graphically.
	----> It supports Buying using dollar cost averaging.
	----> It supports getting total portfolio evaluation.
	----> It supports getting cost basis evaluation.
	----> It supports Creating Portfolio using Investment Strategy Start To Finish graphically.
	----> It supports	Applying Investment Strategy on existing portfolio.
	----> It supports Buy Using Dollar Cost Averaging graphically.

3. | Creating Portfolio using Investment Strategy|
	----> Program allows creation of Flexible portfolio after the login.
	----> Once the user presses the Create Portfolio button and enters the new portfolio name
	----> Once the name is validated by checking if the portfolio doesnot have the name of the existing portfolios of the user
	----> The user will be prompted to ask Would you like to add investment plans.
	----> If the user presses yes then It will ask for Starting Date of Investment, Ending Date of Investment(optional), Term frequency, Total Amount and Commission fee.
	----> Once the user presses Create Investment It will ask for Ticker symbol and percentage. You Need to keep adding it untill required percentage is 0.00%.
	----> Once you click Start Investment the investment configuration is schuduled.
	----> You have an option to reset the configuration during the creation.
	----> Once Schuduled you cannot undo the transaction.
	----> You can check the status of the portfolio by going to the composition.

4. | Applying Investment Strategy on existing portfolio|
	----> In the Main menu User needs to click Investment Mode and it will ask the user to select the portfolio.
	----> It will ask for Starting Date of Investment, Ending Date of Investment(optional), Term frequency, Total Amount and Commission fee.
	----> Once the user presses Create Investment It will ask for Ticker symbol and percentage. You Need to keep adding it untill required percentage is 0.00%..
	----> Once you click Start Investment the investment configuration is scheduled.
	----> You can check the status of the portfolio by going to the composition.

5. | Buy Using Dollar Cost Averaging|

	----> In the Main menu User needs to click Buy using Dollar Cost averaging and it will ask the user to select the portfolio.
	----> It asks for date to invest, Total Amount and Commission fee.
	----> Once the user presses Create Investment It will ask for Ticker symbol and percentage. You Need to keep adding it untill required percentage is 0.00%..
	----> Once you click Buy added stocks the investment configuration is scheduled.
	----> You can check the status of the portfolio by going to the composition.

---------------------------------------------------------------------------------------
New Features Of The Program 1.0
---------------------------------------------------------------------------------------
 1. | Menu Flexible/Inflexible Portfolio|
	----> Program allows creation of Flexible portfolio after the login.
	----> Once new Flexible Portfolio is created you will be given access to the Flexible portfolio features below along with
			the features of the inflexible portfolio.

 2. |Buying new Stocks|
	----> Program allows buying of new stocks to the portfolio if there is a Flexible portfolio available.
	----> You need to enter the portfolio name.
	----> If the portfolio name is valid then It will ask for Ticker Symbol, Date, Quantity, Price and Commission Fee.
	----> These data is then added to the Portfolio specified.

 3.	 |Selling new Stocks|
	----> Program allows Selling of new stocks to the portfolio if there is a Flexible portfolio available.
	----> If there is a flexible Portfolio you need to enter the Portfolio name.
	----> If the Portfolio name is valid then It will ask for Ticker Symbol, Date, Quantity, Price and Commission Fee.
	----> If the amount of the Quantity in the portfolio is more than or equal to the Quantity given by the user in the 
			date of selling. Then the selling is allowed.
	---->	Here All the data is saved and the Quantity is saved in negative value to indicate the selling.

 4. |Cost basis|
	----> Program allows to get Cost basis the portfolio if there is a Flexible portfolio available.
	----> Cost basis gets the portfolio name and date as input.
	----> It returns the total amount of money spent in the portfolio before the date specified by the user.
 
 5. |Evaluation of a portfolio|
	---->Program will ask for Portfolio name to be Evaluated and a date.
   		   If filename does not exist then you will be provided with message below.
		   ("username\entered wrongname.csv (The system cannot find the file specified)")
	----> It returns the value of the portfolio on that particular date.
	----> If there is no stocks bought before that date then 0 is returned.
	----> Else the value of the portfolio is created.

 6. |Portfolio Performance Over Time|
	----> Program allows to get Performance Over Time of the portfolio if there is a Flexible portfolio available.
	----> Program will ask for Portfolio name, from date and to date to get the performance through graph.
	----> If filename does not exists then you will be provided with message below.
		   ("username\entered wrongname.csv (The system cannot find the file specified)")
	----> Here the from date should be less than the to date. 
		   From date should be greater than or equal to the first purchase date in that portfolio.
			To date should not be a future day.
	----> Once the correct portfolio and date range is passed Graph is printed according to the range.
			which is daily if it is less than 30 days, else if Weekly if no of weeks less than 30, 
			else if months if no of months is less than 30, else if quarterly if the no of quarters less than 30 or years.
---------------------------------------------------------------------------------------
Existing Features Of The Program 
---------------------------------------------------------------------------------------
 1. |Creating User|
   ---->Program allows creation of new user by inputting username and password.
   ---->The Username and Password must be of length 5. 
   ---->For any other length you will be provided with message below.
   		  ("The entered Username or Password does not Satisfy the requirements!!! Try Again")
	---->If the User already exists then you will be provided with message below.
		  ("User Profile Already Exists Try Again")
	---->Once the user is created you will be prompted with  
		  ("Successfully Created the profile. Now Login!!!")

2. |Login|
	---->Login asks for username and password 
	---->Checks if user exists
		  If user exists you will be provided with message below.
		  ("Logged in")
		  Else you will be provided with message below.
		  ("User Does not exist Please Try again")

3. |Main Menu after Login|
	Once You are successfully Logged in you are now given access to the menu
	Welcome to Modern Stock Management Service : 
	Press the option to do the operations 
	1. Create new portfolio 
	2. Get Composition 
	3. Upload portfolio 
	4. Evaluate portfolio 
	5. Exit
	
4. |Creating New Portfolio|
	---->To create a new portfolio you need to press 1 in the Main Menu after Login
	---->Program will ask for Portfolio Name	

	---->If portfolio name already exits for the same user then you will be provided with message below.
		   ("File name Already Exists")
			Note: Different users can have same portfolio Name but same users cant have portfolios with same name.
			Once correct name is provided. 

	---->You will be asked to input Valid ticker Symbol.
		  If ticker Symbol is not valid you will be provided with message below.
		  Ticker Symbol Wrong!!
		  Once correct ticker Symbol is provided. 

	---->You will be asked to Enter the Date in format(YYYY-MM-DD)
		  If date provided has special characters or wrong format you will be provided with message below.
		  ("Wrong date entered")
		  If date provided is a future date you will be provided with message below.
		  ("Do not enter future date")
		  Once valid date is entered.
 
	---->It asks for quantity.
		  If quantity entered is in fractional value i.e., Float you will be provided with message to enter correct quantity.
		  Once correct quantity is entered 

	---->Price from API is fetched and displayed.

	---->Then you will be provided with sub menu as below
		  Do you wish to add more?
   		  Press '1' -> yes 
   		  Press '2' -> NO
		  If you press 1 then you will be asked for fields and so on.
		  If you press 2 then the values are all added to created portfolio.
		  This creates a portfolio file inside the User folder.

4. |Get Composition|
   	---->To get composition you need to press 2 in the Main Menu after Login
	---->Program will ask for Portfolio Name to be fetched.
		  If portfolio name entered is wrong then you will be provided with message below.
		  ("username\entered wrongname.csv (The system cannot find the file specified)")
		  ---->Then you will be provided with sub menu as below
		  		 Press '1' -> Try Again 
		  	 	 Press 'anyinput' -> go back to main menu
	---->Once correct Portfolio Name is entered. Portfolio is displayed.

5.	|Upload Portfolio|
   	---->To get composition you need to press 3 in the Main Menu after Login.
	---->Program will ask for Portfolio path to be fetched.
	---->If path entered doesn’t exists or file doesn’t exists you will be provided with message below.
		  ("path entered.csv (The system cannot find the file specified))
		  Note: This feature doesn’t support path which contains file name with spaces.
	---->If Missing values is found in the file to be uploaded then you will be provided with message below.
		  ("Found missing values")
	---->Once correct path is fetched then it displays the portfolio in the format below.
			Ticker:GOOG
			Date:2022-11-01
			Quantity:10
			Price:90.5
			---------------------
6.	|Evaluate portfolio|
   	---->To get Evaluation you need to press 4 in the Main Menu after Login.
	---->Program will ask for Portfolio name to be Evaluated.
   		  If filename does not exists then you will be provided with message below.
		  ("username\entered wrongname.csv (The system cannot find the file specified)")		
	---->When the correct date is entered contents of the portfolio on the date of creation along with the 
		  evaluated total value is displayed in the format below.
		  Contents of the Portfolio on the Date of creation:
		  Ticker:GOOG
		  Date:2022-11-01
	  	  Quantity:10
		  Price:90.5
		  ------------------------------------------------------
		  Total:Value of the portfolio on 2022-10-31 is 946.60
		  ------------------------------------------------------
7.	|Exit|
	---->You have the option to exit the code in the Login Menu.
	---->Post Login You have the option to exit the code from the Main Menu.
