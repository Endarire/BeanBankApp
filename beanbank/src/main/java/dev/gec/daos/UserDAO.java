package dev.gec.daos;

import java.util.List;

import dev.gec.entities.BankAccount;
import dev.gec.entities.User;

// CRUD:
// Create, Read, Update, Delete.
// Every entity gets its own DAO.

public interface UserDAO
{
	//CREATE
	User createUser(String username, String password);
	User createUser(String username, String password, boolean superuser, String name);
	User createUser(String username, String password, String superuser, String name);
	User createUser(User user);
	
	//READ
	User getUserByID(int ID);
	User getUsernameAndPassword(String username, String password);
	List<User> getAllUsers();
	
	//UPDATE
	boolean updateUserByID(User u);

	//DELETE
	boolean deleteUserByID(int ID);
}