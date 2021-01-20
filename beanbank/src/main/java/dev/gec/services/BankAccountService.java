package dev.gec.services;

import java.util.List;

import dev.gec.entities.BankAccount;
import dev.gec.entities.User;

//CRUD outline optional
public interface BankAccountService
{
	//CREATE
	BankAccount makeBankAccount(User user, BankAccount account);
	BankAccount makeBankAccount(User user);
	BankAccount makeBankAccount(int userID);

	//READ
	double getBankAccountBalance(BankAccount account);
	List<BankAccount> getUserAccounts(int userID);
//	List<BankAccount> getAllAccounts();
	BankAccount getAccountByAccountID(int accountID);
	
	//UPDATE
//	boolean depositOrWithdraw(int accountID, double balance);
	boolean depositOrWithdraw(BankAccount account, double amount);
//	boolean depositOrWithdraw(BankAccount account, double amount);
	
	//DELETE
	boolean deleteBankAccount(User user, int accountID);
	boolean deleteBankAccount(BankAccount account);
}