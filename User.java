package First;

/**
 * User class that holds the user objects
 * @author Ayşegül SÜmeyye KÜTÜK
 * @version 4.29
 */

public class User {
	public String id, username, name, surname, password, answer;
	public User(String id, String username, String name, String surname, String answer)
	{
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.answer=answer;
	}
	/**
	 * The method for setting the password to a different one
	 * @param password password of the user
	 * @return void
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	/**
	 * toString method returns the information of the user
	 * @return String
	 */
	public String toString()
	{
		String s = id + " " + name + " " + surname;
		return s;
	}
	/**
	 * Accessor method for name of the user
	 * @return String
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Accessor method for the surname of the user
	 * @return String
	 */
	public String getSurname()
	{
		return surname;
	}
	/**
	 * Accessor method for the user name of the user
	 * @return String
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * Accessor method for the secret answer to the secret question of the user
	 * @return String
	 */
	public String getAnswer()
	{
		return answer;
	}
	/**
	 * Accessor method for the password of the user
	 * @return String
	 */
	public String getPassword()
	{
		return password;
	}

}
