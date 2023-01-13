ASSIGNMENT-6 : Stocks (Part 3)

KRISHNAN NARAYANAN - 002752936
ADITHYA VIBAKAR    - 002783656

---------------------------------------------------------------------------------------
The Program is designed using Model View Controller. And we have used 

Design Changes done in this version---
---------------------------------------------------------------------------------------
--->As suggested we have decoupled our API call to a seperate command. SO that price fetch can be extended easily in future. 
--->As suggested we have minimized parameter passing in the function.
----------------------------------------------------------------------
Added new methods to Stockmodelflex class in model. 
----------------------------------------------------------------------
---> Added new function to get investment details and Schedule it for investment.
we take in details from the user such as from date, To Date, term of investment and percentage for all the stocks present.
we then create a hashmap to store each stock with its percentage. date validators for stocks from the existing functions are used and the Scheduleing is done
for the next day if the date is a holiday/Market is closed. This is then calculated for each stocks and then the value is written.
----------------------------------------------------------------------
Controller Change
----------------------------------------------------------------------
We currently have a Main Controller with and 2 sub controler where we have implemented the facade design as main Controller delecate actions with other 2 Controller.
To support both command line and gui design. 
----------------------------------------------------------------------
View Change
----------------------------------------------------------------------
we have new view class Jframeview which has a controller object which listens to the inputs performed by the specified frames. 
As the controller is the first one to notify about the inputs and output. we use Java swing for it. The interface of the new controller features has all the features and it is
implemented by the controller. The view object is passed to the controller and every time an action is done controller listen is triggered.  


Design Changes done in previous version---
----------------------------------------------------------------------
Created a new model flexmodel for the functions of flexible portfolio. 
----------------------------------------------------------------------
We had existing model supporting the inflexible portfolio. Which was implemented in the Stockmodel. As we required features
of the existing model as well as additional features such as adding, removing the portfolio and getting the cost basis. 
We took this approach because all the features in the inflexible portfolio can be used by flexible portfolio and vise versa
should not be possible. This method creates flexibility for the future if we get a new portfolio type or a feature which 
need to be in one or either one of them it can be easily accommodated without any changes or by just creating a new class 
which inherits either of these classes.
----------------------------------------------------------------------
Created a menu option for flexible portfolio. 
----------------------------------------------------------------------
To facilitate the flexible portfolio we have added cases to controler without affecting the existing helper methods with respect
to the type it automatically calls the method of the inflexilbe class.
------------------------------------------------------------------------------------------------------------------------------
We have a main function which creates the Model and view object and passes it to controller object.
Controller is responsible for Input and output of the program. It calls the required public functions of the Model to
perform the operations and calls public function append of the view to display the output stream.

Here Stock main function is the main driver function which assigns the input stream and output stream. 
Controller's go function is called. 

Here main menu is appended to outstream which is displayed using the Stock View. The menu is shown as below.
Welcome to Modern Stock Management Service : 
Press the option to do the operations 
1. Create new portfolio 
2. Get Composition 
3. Upload portfolio 
4. Evaluate portfolio 
5. Exit

scanner in the Controller appends the user input to the input stream and if the value is 1
 
PROGRAM FLOW
1. first the login is created for which a Boolean return type function from the model is called by the controller 
to evaluate the credentials.
It returns the true or false flag based on which user is decided to be given access or not.
Similarly for the create user functionality the controller calls the create user method in the model and 
sends the username and password and depending upon it. Then it creates a entry in the userbase.csv and the 
controller takes the user back to the login page.

2. Once the user is logged in the controller’s program loop prints the main menu and depending upon the 
option received it takes the user to the required feature page.

3. The create portfolio page gets the file name to be created and passes it to the model’s createportfolio function 
which creates a file under the username folder of the user. If the folder does not exist it creates a new one. 
If a existing file is present it throws a exception. Later the controller gets the ticker symbol and evaluates it 
with the beta api function in the model. If the symbol is correct the controller gets the date and checks whether 
the price and date is matched if the date is wrong it throws the exception accordingly. 
Then it gets the quantity if the quantity is of something else than an integer the controller throws an exception.

4. The get composition functionality gets the portfolio name if the portfolio exists else throws an exception 
if the file does not exist. This functionality is fully handled by the fetch function inside the model. 

5. The evaluate portfolio function calls the portfolio total function in the model which uses the beta call function 
to get the price of the portfolio on a specific date given and multiplies it with the quantity this is done in loop 
for all stocks in the portfolio. If the date given is wrong the function throws an exception.

6. The upload function gets the path where the portfolio is present and the file is fetched by the model’s fetch 
function and is written in the users profile using the portfolio creator and builder. 
If there is an issue in the portfolio it throws an exception.

7. When pressed 5 the program exits.

All the Illegal arguments are thrown by the model which is caught by the user and the custom message in the argument 
is displayed directly. 
Whenever there is an exception for a wrong entry the exception is caught and is displayed and the user is given chance either to try again or exit.
The messages displayed by the program to the user is purely given out by the controller through the view.
The controller has only one public function and all other helper functions are private to provide data hiding and abstraction.