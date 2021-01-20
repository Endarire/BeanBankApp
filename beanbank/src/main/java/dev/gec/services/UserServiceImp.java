package dev.gec.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dev.gec.daos.BankAccountDAOImp;
import dev.gec.daos.UserDAOImp;
import dev.gec.entities.BankAccount;
import dev.gec.entities.User;
import dev.gec.util.JDBConnection;

//  Bean Bank's Business Logic
public class UserServiceImp implements UserService
{
	public static Connection con = JDBConnection.getConnection();
	public UserDAOImp usdao = new UserDAOImp();
	public BankAccountDAOImp badao = new BankAccountDAOImp();
	
	//Show what users are registered on the system.
	public List<User> displayUserAccounts(User user)
	{
		return usdao.getAllUsers();
	}
	public List<User> getAllUserAccounts()
	{
		return usdao.getAllUsers();
	}

	public User makeUser(String username, String password)
	{
		
		return usdao.createUser(username, password);
	}
	
	public User makeUser(String username, String password, boolean superuser, String name)
	{
		
		return usdao.createUser(username, password, superuser, name);
	}
	
	public boolean deleteUser(User user)
	{
		List<BankAccount> usersBankAccounts = badao.getAccountsByUserID(user.getUserID());
		if (!usersBankAccounts.isEmpty())
		{
			System.out.println("ERROR!  Can't delete User since this user still has at least 1 bank account with a nonzero balance!"
							 + "\nUsername: " + user.getUsername() + "\nUser ID: " + user.getUserID());
			return false;
		}
		usdao.deleteUserByID(user.getUserID());
		return deleteUser(user);
	}
	
//	public boolean deleteUser(int userID)
//	{
//		List<BankAccount> usersBankAccounts = badao.getAccountsByUserID(userID);
//		if (!usersBankAccounts.isEmpty())
//		{
//			System.out.println("Deleted account with User ID " + userID + "!  I hope you're happy!"
//			return false;
//		}
//		usdao.deleteUserByID(user.getUserID());
//		return deleteUser(user);
//	}
	
	public boolean deleteAllUsers()
	{
		boolean nukedEmAll = false;
		List<User> allUsers = new ArrayList<User>();
		allUsers = usdao.getAllUsers();
		Iterator<User> itsy = allUsers.iterator();
		while(itsy.hasNext())
		{
			User tempUserObject = itsy.next();
			if (tempUserObject.getUserID() != 0)
			{
				nukedEmAll = usdao.deleteUserByID(tempUserObject.getUserID());
				if(!nukedEmAll)
				{
					System.out.println("ERROR!  For some reason, all users couldn't be deleted at this time.\nThis might be due to at least 1 user having an account with a nonzero balance.\nThat's all we know on this matter at present.");
					return nukedEmAll;
				}
			}
		}
		return nukedEmAll;
	}

	public User login(String username, String password)
	{
		for(User u : usdao.getAllUsers())
		{
			if(u.getUsername().equals(username) && u.getPassword().equals(password))
			{
				System.out.println("Welcome, " + username + "!  Your username and password were in our system!");
				return u;
			}
		}
		System.out.println("Apologies, " + username + "!  Your username and password didn't match any record in our system!");
		return null;
	}

}