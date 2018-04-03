package application;


import java.util.List;

/**
 * 
 * @author Bartosz Grzegorz Jêdrzejczak
 *
 */

public class Environment {
	private String nameOfEnvironment; 
	private Users users;
	
	/**
	 * 
	 * @param name the name of environment 
	 */
	public Environment(String name) {
		nameOfEnvironment = name;
		users = new Users();
	}
	/**
	 * 
	 * @param usersAndPasses the name of table containing users and passes
	 */
	public void addUsersAndPasses(String... usersAndPasses) {
		users.addUsersWithPasses(usersAndPasses);
	}
	/**
	 * 
	 * @param user the name of user
	 * @param pass password of this user
	 */
	public void addUserAndPass(String user, String pass) {
		users.addUserAndPass(user, pass);
	}
	/**
	 * 
	 * @param user the name of user
	 * @param pass password of user
	 * @return true when password is correct, false when password is incorrect
	 */
	public boolean checkPass(String user, String pass) {
		return users.checkPass(user, pass);
	}
	/**
	 * 
	 * @return String containing name of environment
	 */
	public String getNameOfEnvironment() {
		return nameOfEnvironment;
	}
	/**
	 * 
	 * @return List of Strings containing all users in environment
	 */
	public List<String> getListOfUsers(){
		return users.getListOfUsers();
	}

	@Override
	public String toString() {
		return nameOfEnvironment;
	}
}
