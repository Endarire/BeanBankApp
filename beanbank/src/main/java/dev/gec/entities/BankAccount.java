package dev.gec.entities;

//Entity 
//A class with the main responsibility of just holding data
//Should be a Java Bean

public class BankAccount
{	
	private int accountID;		//Unique identifier.  Preferred over (nick)naming accounts.
	private int userID;			//This foreign key tells us what user ID owns this account! 
	private String name;		//Are we naming or nicknaming accounts?
	private double balance;		//User-entered amount.
	
	//Expected typical constructor.
	public BankAccount(int userID)
	{
		this.name = "";				//Included just in case!
		this.userID = userID;		//Should an account be made by account ID.
		this.balance = 0;			//Definitely set default balance to 0!
		this.accountID = 0;			//We haven't used the database's version yet; thus, make it 0!
	}
	
	//Just in case constructor
	public BankAccount()
	{
		this.name = "";			//Included just in case!
		this.accountID = 0;		//Auto-set account ID?
		this.balance = 0;		//Definitely set default balance to 0!
	}
	
	//Expected secondary constructor.
	public BankAccount(int accountID, int userID)
	{
		this.name = "";				//Included just in case!
		this.accountID = accountID;	//Should an account be made by account ID. 
		this.balance = 0;			//Definitely set default balance to 0!
	}

	public BankAccount(int accountID, int userID, String name, double balance)
	{
		super();
		this.accountID = accountID;
		this.userID = userID;
		this.name = name;
		this.balance = balance;
	}
	
	public BankAccount(int accountID, double balance, int userID)
	{
		super();
		this.accountID = accountID;
		this.userID = userID;
		this.balance = balance;
	}
	
	public BankAccount(BankAccount account)
	{
		super();
		this.accountID = account.accountID;
		this.userID = account.userID;
		this.balance = account.balance;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + userID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountID != other.accountID)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "\nBank Account [accountID=" + accountID + ", userID=" + userID + ", name=" + name + ", balance=" + balance
				+ "]";
	}
}