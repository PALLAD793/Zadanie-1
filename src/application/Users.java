package application;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bartosz Grzegorz Jêdrzejczak
 *
 */

public class Users {
	private List<String> usersInEnv;
	private List<String> passes;
	/**
	 * Constructor with no arguments
	 */
	public Users() {
		usersInEnv = new ArrayList<>();
		passes = new ArrayList<>();
	}
	/**
	 * 
	 * @param usersAndPasses the table of Strings containing users and passes in sequence user, pass, user, pass...
	 */
	public void addUsersWithPasses(String[] usersAndPasses) {

		for(int i=0; i<usersAndPasses.length; ++i) {
			usersInEnv.add(usersAndPasses[i++]);
			passes.add(usersAndPasses[i]);
		}
	}
	/**
	 * 
	 * @param user the String containing name of user
	 * @param pass the String containing password of this user
	 */
	public void addUserAndPass(String user, String pass) {
		usersInEnv.add(user);
		passes.add(pass);
	}
	/**
	 * 
	 * @return List of Strings containing all users
	 */
	public List<String> getListOfUsers() {
		return usersInEnv;
	}
	/**
	 * 
	 * @param user the String containing name of user
	 * @param pass the String containing pass to check
	 * @return true when password is correct, false when password is incorrect
	 */
	public boolean checkPass(String user, String pass) {
		int nr = usersInEnv.indexOf(user);
		if(nr >= 0) return passes.get(nr).equals(pass);
		else return false;
	}
}
