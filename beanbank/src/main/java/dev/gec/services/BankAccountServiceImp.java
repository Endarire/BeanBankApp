package dev.gec.services;

import java.util.ArrayList;
import java.util.List;

import dev.gec.daos.BankAccountDAOImp;
import dev.gec.entities.BankAccount;
import dev.gec.entities.User;

public class BankAccountServiceImp implements BankAccountService
{
//	public BankAccountService badao = new BankAccountService();
	public static BankAccountDAOImp badao = new BankAccountDAOImp();
	private static List<BankAccount> BankAccounts = new ArrayList<BankAccount>();
	
	//Makes a new bank account and attaches it to a User object.
	public BankAccount makeBankAccount(User user, BankAccount account)
	{
		return badao.makeBankAccount(user, account);
	}
	
	public BankAccount makeBankAccount(User user)
	{
		return badao.makeBankAccount(user);
	}
	
	public BankAccount makeBankAccount(int userID)
	{
		return badao.makeBankAccount(userID);
	}
	
	public BankAccount makeBankAccount(String username, String password)
	{
		return badao.makeBankAccount(username, password);
	}

	//Reads/retrieves an account's balance and returns a BankAccount object.
	public double getBankAccountBalance(BankAccount account)
	{
		return badao.getBankAccountBalance(account);
	}
	
	public BankAccount getAccountByAccountID(int accountID)
	{
		return badao.getAccountByAccountID(accountID);
	}

	public List<BankAccount> getUserAccounts(int userID)
	{
		return badao.getAccountsByUserID(userID);
	}

	//Changes balance server-side.
	public boolean depositOrWithdraw(BankAccount account, double amount)
	{
		return badao.depositOrWithdraw(account, amount);
	}

	public boolean deleteBankAccount(User user, int accountID)
	{		
		return badao.deleteBankAccount(user, accountID);
	}

	public boolean deleteBankAccount(BankAccount account)
	{
		return badao.deleteBankAccount(account);
	}

//	public List<BankAccount> getUserAccounts(int userID)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public List<BankAccount> getAllAccounts()
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
}