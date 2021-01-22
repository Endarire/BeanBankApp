package dev.gec.daos;

import java.util.List;

import dev.gec.entities.BankAccount;
import dev.gec.entities.User;

public interface BankAccountDAO
{
	//CREATE
	public BankAccount makeBankAccount(int userID);
	public BankAccount makeBankAccount(String username, String password);
	public BankAccount makeBankAccount(User user, BankAccount account);
	public boolean makeBankAccount(BankAccount account);
	
	//READ
	public double getBankAccountBalance(BankAccount account);
	
	public List<BankAccount> getAllBankAccounts();
	
//	public List<BankAccount> getAccountsByUserID(int userID);
	public List<BankAccount> getAccountsByUserObject(User user);
	
	//UPDATE
	public boolean depositOrWithdraw(BankAccount account, double amount);	//Coded as a bool!
	
//	public BankAccount updateAccount(); //INCLUDE PROPER PARAMETERS TO UPDATE
	public boolean updateAccount(BankAccount account); //BankAccount object is parameter since we update the passed-in BankAccount object.  We want to access ALL of the object!
	
	//DELETE - Delete current account (any user) AND delete all user accounts (superuser only) 
	public boolean deleteBankAccount(User user, int accountID);
	public boolean deleteBankAccount(int accountID);
	public boolean deleteAllBankAccounts();
}