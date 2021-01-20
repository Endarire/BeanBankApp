package dev.gec.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.gec.entities.User;
import dev.gec.util.JDBConnection;

public class UserDAOImp
{
	public static Connection con = JDBConnection.getConnection();
	
	public User createUser(String username, String password)
	{
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		
		try
		{
			//Java doesn't know our Actor sequence (actorSequence)!
			//Better if we use our SQL stored procedure!
			String SQL = "CALL createUser(?, ?, ?, ?)"; //4 input parameters on the SQL procedure
			CallableStatement cs = con.prepareCall(SQL);
			int isSuperuser = 0;
			if(u.isSuperuser())
			{
				isSuperuser = 1;
			}
			
			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());
			cs.setString(3, String.valueOf(isSuperuser));
			cs.setString(4, u.getName());
			
			cs.execute();	//If there's an error, move to Catch block!
			return u;		//If there's no error, return true for good!
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
		return null;
	}
	
	public User createUser(String username, String password, boolean superuser, String name)
	{
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		
		try
		{
			//Java doesn't know our Actor sequence (actorSequence)!
			//Better if we use our SQL stored procedure!
			String SQL = "CALL createUser(?, ?, ?, ?)"; //4 input parameters on the SQL procedure
			CallableStatement cs = con.prepareCall(SQL);
			int isSuperuser = 0;
			if(u.isSuperuser())
			{
				isSuperuser = 1;
			}
			
			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());
			cs.setString(3, String.valueOf(isSuperuser));
			cs.setString(4, u.getName());
			
			cs.execute();	//If there's an error, move to Catch block!
			return u;		//If there's no error, return true for good!
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
		return null;
	}
	
	public User createUser(String username, String password, String superuser, String name)
	{
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		
		try
		{
			//Java doesn't know our Actor sequence (actorSequence)!
			//Better if we use our SQL stored procedure!
			String SQL = "CALL createUser(?, ?, ?, ?)"; //4 input parameters on the SQL procedure
			CallableStatement cs = con.prepareCall(SQL);
			int isSuperuser = 0;
			if(u.isSuperuser())
			{
				isSuperuser = 1;
			}
			
			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());
			cs.setString(3, String.valueOf(isSuperuser));
			cs.setString(4, u.getName());
			
			cs.execute();	//If there's an error, move to Catch block!
			return u;		//If there's no error, return true for good!
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
		return null;
	}
	
	public User createUser(User user)
	{
		User u = user;
		u.setUsername(u.getUsername());
		u.setPassword(u.getPassword());
		
		try
		{
			//Java doesn't know our Actor sequence (actorSequence)!
			//Better if we use our SQL stored procedure!
			String SQL = "CALL createUser(?, ?, ?, ?)"; //4 input parameters on the SQL procedure
			CallableStatement cs = con.prepareCall(SQL);
			int isSuperuser = 0;
			if(u.isSuperuser())
			{
				isSuperuser = 1;
			}
			
			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());
			cs.setString(3, String.valueOf(isSuperuser));
			cs.setString(4, u.getName());
			
			cs.execute();	//If there's an error, move to Catch block!
			return u;		//If there's no error, return true for good!
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
		return null;
	}

	public User getUserByID(int ID)
	{
		try
		{
			String SQL = "SELECT * FROM BeanBankUsers WHERE userID = ?"; //Returns no ID (if not exists) or 1 ID if it exists
			
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, Integer.toString(ID)); //Change the first question mark (?) to the Stringified ID (int)
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				User u = new User();
				u.setUserID(rs.getInt("userID"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setSuperuser(rs.getBoolean("superuser"));
				u.setName(rs.getString("fullName"));		
				return u;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUsernameAndPassword(String username, String password)
	{
		try
		{
			String SQL = "SELECT * FROM BeanBankUsers WHERE username = ?, password = ?"; //Returns no ID (if not exists) or 1 ID if it exists
			
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, username); //Change the first question mark (?) to the username
			ps.setString(2, password); //Change the first question mark (?) to the password
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				User u = new User();
				u.setUserID(rs.getInt("userID"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setSuperuser(rs.getBoolean("superuser"));
				u.setName(rs.getString("fullName"));		
				return u;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllUsers()
	{
		List<User> allUsers = new ArrayList<User>();
		
		try
		{
			String SQL = "SELECT * FROM BeanBankUsers";
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{	
				User u = new User();
				
				u.setUserID(rs.getInt("userID"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setSuperuser(rs.getBoolean("superuser"));
				u.setName(rs.getString("fullName"));
				
				allUsers.add(u);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
//		System.out.println("A superuser wants to see all users in the database!  Here you go!");
		return allUsers;
	}

	public boolean updateUserByID(User u)
	{
		try
		{
			String SQL = "UPDATE users SET username = ?, password = ?, fullName = ? WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(SQL);
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getName());
			
			ps.executeQuery();
			System.out.println("User ID " + u.getUserID() + " now has a username of " + u.getUserID() + ", a password of " + u.getPassword() + ", and a full name of " + u.getName() + ".");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUserByID(int ID)
	{
		try
		{
			String SQL = "DELETE BeanBankUsers WHERE userID = ?";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, ID);
			
			ps.executeQuery();
			System.out.println("The user with ID " + ID + " was deleted!  Bye bye now!");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}