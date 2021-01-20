/**
 *  @author Greg Campbell (CampbellGregE [at] gmail.com)
 */

package dev.gec.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dev.gec.entities.BankAccount;
import dev.gec.entities.User;
import dev.gec.services.BankAccountService;
import dev.gec.services.BankAccountServiceImp;
import dev.gec.services.UserService;
import dev.gec.services.UserServiceImp;

public class BeanBankApp
{
	public static void main(String[] args)
	{
		Scanner scan 							= new Scanner(System.in);
//		String username							= "";
		String hold								= "";
		UserService userv 						= new UserServiceImp();
		BankAccountService bserv 				= new BankAccountServiceImp();
		User userLoggedIn 						= null; //Object of the user logged into the system right now!
		User u									= new User();
		BankAccount bankAccount					= new BankAccount();
		boolean mainMenuLoop 					= true;
		boolean isValid 						= false;
		List<User> allUsers						= new ArrayList<User>();
		List<BankAccount> allAccounts			= new ArrayList<BankAccount>();
		Iterator<BankAccount> accountIterator	= allAccounts.iterator();
		Iterator<User> userIterator				= allUsers.iterator();
		
			while (mainMenuLoop == true)
			{
				System.out.println("Welcome to Bean Bank!\nYour source of money, code, and coffee since 2021!");
				System.out.println("Input a number for one of these options (1 or 2), then push Enter.\n1: Enroll a new user.\n2: Log into an existing account.\n");
				int choice = Integer.parseInt(scan.nextLine());

				switch (choice)
				{
					case 1:
					{
						System.out.println("Please enter your username, such as Yardothrin.  Spaces aren't allowed!");
						String username = scan.nextLine().trim();
						System.out.println("Please enter your password, such as Charismata.  Spaces aren't allowed!");
						String password = scan.nextLine().trim();
						System.out.println("Please enter your name, such as AllyLouia.  Spaces aren't allowed!");
						String name = scan.nextLine().trim();
//						System.out.println("Are you a superuser?  If so, input true.  Otherwise, input false.  (Case insensitive.)  Spaces aren't allowed!");
						boolean boolSuperuser = false;
						
						//||\\ HOW TO CHECK DATABASE HERE FOR EXISTING USER AND LOG IN IF NOT EXISTS? //||\\ 
						u = userv.makeUser(username, password, boolSuperuser, name);
						userLoggedIn = userv.login(username, password);
						System.out.println("Welcome, " + u.getName() + " to Bean Bank!  We've counted your beans and they're safe with us!");
						bserv.makeBankAccount(u);
						System.out.println("We also made a starting account for you!  Cool beans!");
						mainMenuLoop = false;
					}
					break;
		
					case 2:
					{
						System.out.println("Please enter your username, such as Yardothrin.  Spaces aren't allowed!");
						String username = scan.nextLine().trim();
						System.out.println("Please enter your password, such as Charismata.  Spaces aren't allowed!");
						String password = scan.nextLine().trim();
						
//						u = userv.makeUser(username, password);
						userLoggedIn = userv.login(username, password);
						System.out.println("Welcome to Bean Bank!  We've counted your beans and they're safe with us!");
						mainMenuLoop = false;
					}
					break;
					
					default:
					{
						System.out.println("Invalid input!  Follow the following instructions.");
						System.out.println("Welcome to Bean Bank!\nYour source of money, code, and coffee since 2021!");
						System.out.println("Input a number for one of these options (1 or 2), then push Enter.\n1: Enrollment a new user.\n2: Log into an existing account.\n");
						choice = Integer.parseInt(scan.nextLine());
					}
				}
				
				//Default User Abilities
				while(userLoggedIn != null)
				{
					System.out.println("Input a number for one of these options (1, 2, 3, etc.), then push Enter."
							+ "\n1: Make a new bank account.  (It's free!)"
							+ "\n2: Change the balance of a bank account you own!  (Some call it deposit or withdrawal!)"
							+ "\n3: Delete a bank account you own if the balance is 0.  (It's permanent!  Bye!)"
							+ "\n4: View your accounts' current balances.  (Be financially prepared!)"
							+ "\n5: Log out of the system.  (We'll miss you!)"
							+ "\n6: View all users.  (Superuser only!)"
							+ "\n7: Delete 1 user. (Superuser only!)"
							+ "\n8: Delete all users. (Superuser only!)");
					
					int decision = Integer.parseInt(scan.nextLine());
					switch(decision)
					{
						case 1: //Make a new bank account for the logged in user.
						{
							//Should each bank account have a user-input name?  Nae!  Use ID only
							bserv.makeBankAccount(u);
							
							System.out.println("Now you can spill your beans into your new account!");		
						}
						break;
						
						case 2: //Change the balance of a bank account the logged in user has.
								//Requires the user have a bank account.
								//If user has no accounts, one is made automatically!
						{
							double amount = 0;
							System.out.println("Enter an account ID from this list to give (deposit) or eat (withdraw) your beans!");
							System.out.println(bserv.getUserAccounts(userLoggedIn.getUserID()));
							int pickAnAccount = Integer.parseInt(scan.nextLine());
							
							///FIX IT!\\\
							//Use a foreach loop:  For all account IDs in logged in user's bank accounts\\
//							for(BankAccount baloop : bserv.getUserAccounts(userLoggedIn.getUserID())) 
//							{
//								if(bserv.getUserAccounts(userLoggedIn.getUserID() == pickAnAccount))
//								{
//									System.out.println("SOMETHING SOMETHING!  THIS SHOULD PROCEED TO THE DEPOSIT/WITHDRAWAL MENU!");
//								}
//							}
							System.out.println("Apologies, " + userLoggedIn.getUsername() + "!  Your input account ID didn't match any record in our system!");
							
//							if(pickAnAccount == bserv.getUserAccounts(userLoggedIn.getUserID()))
//							{
//								;
//							}
							
							//INSERT LOGIC HERE TO CHANGE A BANK ACCOUNT'S BALANCE
							System.out.println("For this account, enter a negative number to withdraw money or a positive number to deposit money!\nYour account can have a negative balance, meaning it's overdrawn!");
							amount = Double.parseDouble(scan.nextLine());
							bserv.depositOrWithdraw(bankAccount, amount); //Probably doesn't work yet!  Needs proper bankAccount object parameter!\\
						};
						
						case 3: //Delete a bank account the logged in user has if it's 0 balance.
							//Requires the user have a bank account.
							//If user has no accounts, show a console message saying the user has no accounts, then return to the logged in menu! 
						{
						//STUFF!\\
						
						
						//INSERT LOGIC HERE TO CHOOSE A BANK ACCOUNT FOR THE LOGGED IN USER
						System.out.println("Choose an account of yours at Bean Bank!");

						// IF THE ACCOUNT HAS A NON-ZERO BALANCE, WARN THE USER AND TELL THEM WHAT TO DO
						
						
						//IF THE ACCOUNT HAS A ZERO BALANCE, ASK THEM IF THEY'RE SURE THEY WANT TO DELETE THIS ACCOUNT.  IF NOT, REPROMPT FOR INPUT.  IF SO, DELETE THE ACCOUNT AND SHOW A CONFIRMATION MESSAGE WITH WHAT ACCOUNT WAS DELETED.
						
						};
						break;
						
						case 4: //Shows all accounts the logged in user has and the balance of each.  If null (no accounts exist for this user), make a new account and display a message, "Since you had no accounts, we made a free account for you!" 
							//Regardless, show the balance and account name of each account.
						{
							System.out.println("What have we here?  Oh,  yes!  Your account list!");
							System.out.println(bserv.getUserAccounts(userLoggedIn.getUserID()));
						
					    };
						break;
						
						case 5: //Log the current user out of the system.
							//The only prerequisite for use is to be logged in. 
						{
							userLoggedIn = null;
							System.out.println("You are now logged out of our system.\nThank you for be-aning our customer!");
						};
						break;
						
						case 6: //View all users.  (Superuser only!)
							//Requires the initiating user be a superuser. 
						{
							if(userLoggedIn.isSuperuser() == true)
							{
								userv.getAllUserAccounts();
							}
							else
							{
								System.out.println("Only superusers can access this!  Nice try, though!");
							}
						};
						break;
						
						case 7: //Delete a user.  (Superuser only!)
							//Requires the initiating user be a superuser. 
						{
							if(userLoggedIn.isSuperuser() == true)
							{
//								userv.delet
							}
							else
							{
								System.out.println("Only superusers can access this!  Nice try, though!");
							}
						};
						break;
						
						case 8: //Delete all user.  (Superuser only!)
							//Requires the initiating user be a superuser. 
						{
							if(userLoggedIn.isSuperuser() == true)
							{
								userv.deleteAllUsers();
							}
							else
							{
								System.out.println("Only superusers can access this!  Nice try, though!");
							}
						};
						break;
						
						default:
						{
							System.out.println("ERROR!  Invalid input!\n"
									+ "Input a number for one of these options (1, 2, 3, etc.), then push Enter."
									+ "\n1: Make a new bank account.  (It's free!)"
									+ "\n2: Change the balance of a bank account you own!  (Some call it deposit or withdrawal!)"
									+ "\n3: Delete a bank account you own if the balance is 0.  (It's permanent!  Bye!)"
									+ "\n4: View your accounts' current balances.  (Be financially prepared!)"
									+ "\n5: Log out of the system.  (We'll miss you!)"
									+ "\n6: View all users.  (Superuser only!)"
									+ "\n7: Delete 1 user. (Superuser only!)"
									+ "\n8: Delete all users. (Superuser only!)");
							
							decision = Integer.parseInt(scan.nextLine());
						}
					}
				}
			}
	}	
}