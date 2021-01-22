package dev.gec.entities;

//Entity 
//A class with the main responsibility of just holding data
//Should be a Java Bean

//A new User means auto-adding a new account!

//Add a default constructor 

//Add getters & setters

//Add toString

public class User
{	//Make the initial variables for User.
//	private Map<Integer, BankAccount> accounts = new HashMap<Integer, BankAccount>(); //Doesn't work well for SQL database! 
	
	//For databases, every field in my entity should also be in my table!
	private String username = "", password = "";
	
	
	private int userID = 0; //Unique identifier
	private String name = "";
	private boolean superuser = false;

	public User()
	{
		super();
	}

	public User(String username, String password, int userID, String name, boolean superuser) {
		super();
		this.username = username;
		this.password = password;
		this.userID = userID;
		this.name = name;
		this.superuser = superuser;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the superuser
	 */
	public boolean isSuperuser() {
		return superuser;
	}

	/**
	 * @param superuser the superuser to set
	 */
	public void setSuperuser(boolean superuser) {
		this.superuser = superuser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (superuser ? 1231 : 1237);
		result = prime * result + userID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (superuser != other.superuser)
			return false;
		if (userID != other.userID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nUser [username=" + username + ", password=" + password + ", userID=" + userID + ", name=" + name
				+ ", superuser=" + superuser + "]";
	}
}