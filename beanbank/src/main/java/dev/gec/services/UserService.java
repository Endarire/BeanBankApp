package dev.gec.services;

import java.util.List;

import dev.gec.entities.User;

//void functions or User functions - What does each do and why?  What's better?

public interface UserService
{
	//Methods for a User's bank accounts
	List<User> displayUserAccounts(User user);
	List<User> getAllUserAccounts();
	
	//Make a new User account!
	User makeUser(String username, String password);
	User makeUser(String username, String password, boolean superuser, String name);
	
	//Edit user details!
	boolean updateUserByID(User user);
	
	//Delete or purge stuff!
	boolean deleteUser(User user);
//	boolean deleteUserByID(int userID);
	boolean deleteAllUsers();
	
	//Method to log into a User's BankAccount
	User login(String username, String password);
	
	
												//Adapted from Adam Raneiri's EmployeePTO Code
											//	User enrollUser(String username, String name, boolean superuser, boolean loggedIn);
											//	
											//	User login(String username);
											//	
											//	User deposit(User u, double amount);
											//	
											//	User withdraw(User u, double amount);
}