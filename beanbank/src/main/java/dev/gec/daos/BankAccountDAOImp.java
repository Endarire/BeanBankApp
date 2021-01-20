package dev.gec.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.gec.entities.BankAccount;
import dev.gec.entities.User;
import dev.gec.util.JDBConnection;

// implementation of the user dao that only saves objects into memory
public class BankAccountDAOImp implements BankAccountDAO
{
	public static Connection con = JDBConnection.getConnection();
	
	public BankAccount makeBankAccount(int userID)
	{
		BankAccount ba = new BankAccount(userID);
		try
		{
			//Java doesn't know our Actor sequence (actorSequence)!
			//Better if we use our SQL stored procedure!
			String SQL = "CALL createAccount(?, ?)"; //2 input parameters on the SQL procedure
			CallableStatement cs = con.prepareCall(SQL);
			
			cs.setString(1, String.valueOf(0));
			cs.setString(2, String.valueOf(userID));
			
			cs.execute();	//If there's an error, move to Catch block!
			return ba;		//If there's no error, return true for good!
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public BankAccount makeBankAccount(User user)
	{
		BankAccount ba = new BankAccount(user.getUserID());
		try
		{
			//Java doesn't know our SQL sequence!
			//Better if we use our SQL stored procedure!
			String SQL = "CALL createAccount(?, ?)"; //2 input parameters on the SQL procedure
			CallableStatement cs = con.prepareCall(SQL);
			
			cs.setString(1, String.valueOf(0));
			cs.setString(2, String.valueOf(user.getUserID()));
			
			cs.execute();	//If there's an error, move to Catch block!
			return ba;		//If there's no error, return true for good!
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public BankAccount makeBankAccount(String username, String password)
	{
//		BankAccount ba.set(username, password); //?
//		try
//		{
//			//Java doesn't know our Actor sequence (actorSequence)!
//			//Better if we use our SQL stored procedure!
//			String SQL = "CALL createAccount(?, ?)"; //2 input parameters on the SQL procedure
//			CallableStatement cs = con.prepareCall(SQL);
//			
//			cs.setString(1, username);
//			cs.setString(2, password);
//			
//			cs.execute();	//If there's an error, move to Catch block!
//			return ba;		//If there's no error, return true for good!
//		}
//		
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
		
		return null;
	}

	public BankAccount makeBankAccount(User user, BankAccount account)
	{
//		BankAccount ba = new BankAccount(user, account);
//		try
//		{
//			//Java doesn't know our Actor sequence (actorSequence)!
//			//Better if we use our SQL stored procedure!
//			String SQL = "CALL createAccount(?, ?)"; //2 input parameters on the SQL procedure
//			CallableStatement cs = con.prepareCall(SQL);
//			
//			cs.setString(1, String.valueOf(0));
//			cs.setString(2, String.valueOf(userID));
//			
//			cs.execute();	//If there's an error, move to Catch block!
//			return ba;		//If there's no error, return true for good!
//		}
//		
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
		
		return null;
	}
	
	public boolean makeBankAccount(BankAccount account)
	{
//		BankAccount ba = new BankAccount(account);
		try
		{
			//Java doesn't know our sequence!
			//Better if we use our SQL stored procedure!
			String SQL = "CALL createAccount(?, ?)"; //2 input parameters on the SQL procedure
			CallableStatement cs = con.prepareCall(SQL);
			
			cs.setString(1, String.valueOf(0));
			cs.setString(2, String.valueOf(account.getUserID()));
			
			cs.execute();	//If there's an error, move to Catch block!
			return true;	//If there's no error, return true for good!
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	public double getBankAccountBalance(BankAccount account)
	{
		BankAccount ba = account;
		
		try
		{
			String SQL = "SELECT * FROM account WHERE accountID = ?";
			
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, Integer.toString(ba.getAccountID()));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				//Make a new account on this line?
				ba.setAccountID(rs.getInt("accountID"));
				ba.setBalance(rs.getDouble("balance"));
				ba.setUserID(rs.getInt("userID"));
//				System.out.println("Your balance for the account with ID " + account.getAccountID() + " is " + account.getBalance() + " .");
				System.out.println("Account ID: " + account.getAccountID() + ", Balance: " + account.getBalance() + " .");
				return ba.getBalance();
			}
		}
		catch (SQLException e)
		{
			e.getStackTrace();
		}
		
		return -1; //Returned bad result!
	}
	
	public List<BankAccount> getAccountsByUserID(int userID)
	{
		List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
		try
		{
			String SQL = "SELECT * FROM account WHERE userID = ?";
			
			PreparedStatement ps = con.prepareStatement(SQL);
			
			ps.setString(1, Integer.toString(userID));
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				allBankAccounts.add(new BankAccount(rs.getInt("userID"), rs.getDouble("balance"), rs.getInt("accountID")));
			}
			return allBankAccounts;
			
		}
		catch (SQLException e)
		{
			e.getStackTrace();	
		}
		return null;
	}
	
	public BankAccount getAccountByAccountID(int accountID)
	{
		BankAccount ba = new BankAccount();
		try
		{
			String SQL = "SELECT * FROM account WHERE accountID = ?";
			
			PreparedStatement ps = con.prepareStatement(SQL);
			
			ps.setString(1, Integer.toString(accountID));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				ba.setAccountID(rs.getInt("accountID"));
				ba.setBalance(rs.getDouble("balance"));
				ba.setUserID(rs.getInt("userID"));
				return ba;
			}
			
		}
		catch (SQLException e)
		{
			e.getStackTrace();	
		}
		return null;
	}

	public List<BankAccount> getAllBankAccounts()
	{
		List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
		
		try
		{
			String SQL = "SELECT * FROM BeanBankAccounts";
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				BankAccount b = new BankAccount();
				b.setAccountID(rs.getInt("accountID"));
				b.setBalance(rs.getDouble("balance"));
				b.setUserID(rs.getInt("userID"));
				
				allBankAccounts.add(b);
				
				System.out.println("A superuser wanted to view ALL the bank accounts!");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return allBankAccounts;
	}
	
	public List<BankAccount> getAllAccounts()
	{
		
		return null;
	}

	public boolean depositOrWithdraw(BankAccount account, double amount)
	{
		try
		{
//			String SQL = "UPDATE BeanBankAccounts SET balance = ?, worth = ? WHERE id = ?";
			String SQL = "UPDATE BeanBankAccounts SET balance = ?, WHERE accountID = ?";
			PreparedStatement ps = con.prepareStatement(SQL);
			
			BankAccount b = new BankAccount();
			//			ps.setString(1, change.getName());
			ps.setString(1, Double.toString(b.getBalance() + amount));
			ps.setString(2, Integer.toString(b.getAccountID()));
			
			ps.executeQuery();
			System.out.println("Account with ID " + b.getAccountID() + " now has a balance of " + b.getBalance() + " after that transaction of $" + amount + ".");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateAccount(BankAccount account)
	{
		try
		{	
			String SQL = "UPDATE account SET userID = ?, balance = ?, WHERE accountID = ?";
			PreparedStatement ps = con.prepareStatement(SQL);
			
			ps.setString(1, Integer.toString(account.getAccountID()));
			ps.setString(2, Double.toString(account.getBalance()));
			ps.setString(3, Integer.toString(account.getUserID()));
			ps.executeQuery();
			System.out.println("Account with ID " + account.getAccountID() + " now has a new User ID of " + account.getUserID() + " and a balance of " + account.getBalance() + ".");
			return true;	
		}
		catch (SQLException e)
		{
			e.getStackTrace();	
		}
		return false;
	}

	public boolean deleteBankAccount(User user, int accountID)
	{
		try
		{
			String SQL = "DELETE BeanBankAccounts WHERE accountID = ?";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, accountID);
			
			ps.executeQuery();
			System.out.println("Account with ID " + accountID + " from User ID " + user.getUserID() + " initiated its deletion sequence.");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteBankAccount(int accountID)
	{
		try
		{
			String SQL = "DELETE BeanBankAccounts WHERE accountID = ?";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, accountID);
			
			ps.executeQuery();
			System.out.println("Account with ID " + accountID + " initiated its deletion sequence.");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteBankAccount(BankAccount account)
	{
		try
		{
			String SQL = "DELETE BeanBankAccounts WHERE accountID = ?";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, account.getAccountID());
			
			if(account.getBalance() == 0)
			{
				ps.executeQuery();
				System.out.println("Account with ID " + account.getAccountID() + " was deleted due to having a 0 balance.");
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		System.out.println("Account with ID " + account.getAccountID() + " WASN'T deleted due to NOT having a 0 balance!");
		return false;
	}

	//Nuke all the accounts on the table and start over!
	public boolean deleteAllBankAccounts()
	{
		try
		{
			String SQL = "TRUNCATE TABLE BeanBankAccounts";
			
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.execute();
			System.out.println("A superuser demanded all bank accounts be deleted!  Did someone spill the beans?");
			return true;
			
		}
		catch (SQLException e)
		{
			e.getStackTrace();	
		}
		return false;
	}
}