package First;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Library class for all database operations
 * @author Tugba DOGAN
 * @version 4.49
 */
public class Database {
	private static Statement stmt;
	private static PreparedStatement prepared;
	private static Connection con;
	
	/**
	 * Open new database connection
	 */
	public static void connect()
	{
		 try {
			Class.forName("com.mysql.jdbc.Driver");
	        con = (Connection) DriverManager.getConnection("jdbc:mysql://178.63.25.70:3306/darbetim_movie","darbetim_movie","movie");
	        stmt = (Statement) con.createStatement();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}
	
	/**
	 * User login method
	 * @param username username
	 * @param password password of user
	 * @return logged in user object
	 */
	public static User login(String username, String password)
	{
		User u = null;
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'");
	        while (rs.next()) {
	        	u = new User(rs.getString("id"),rs.getString("username"),rs.getString("name"),rs.getString("surname"),rs.getString("answer"));
	        	u.setPassword(rs.getString("password"));
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	/**
	 * Register user
	 * @param u user object that will be registered
	 * @return registered logged in user object
	 */
	public static User register(User u)
	{
		connect();
		try {
			stmt.executeUpdate("INSERT INTO users (name,username,surname,password,answer) VALUES('"+u.name+"','"+u.username+"','"+u.surname+"','"+u.password+"','"+u.answer+"')");
			return login(u.username,u.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Update password
	 * @param u user object that has new password
	 * @return whether operation is successful or not
	 */
	public static boolean update(User u)
	{
		connect();
		try {
			stmt.executeUpdate("UPDATE `users` SET  `password` =  '"+u.getPassword()+"' WHERE  `users`.`id` ="+u.id);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Get all users
	 * @return all users in arraylist
	 */
	public static ArrayList<User> getUsers()
	{
		User u;
		ArrayList<User> list = new ArrayList<User>();
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM  `users`");
	        while (rs.next()) {
	        	u = new User(rs.getString("id"),rs.getString("username"),rs.getString("name"),rs.getString("surname"),rs.getString("answer"));
	        	list.add(u);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Get user through given id
	 * @param id user's id
	 * @return user which has given id
	 */
	public static User getUserById(int id)
	{
		User u = null;
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM users WHERE id='"+id+"'");
	        while (rs.next()) {
	        	u = new User(rs.getString("id"),rs.getString("username"),rs.getString("name"),rs.getString("surname"),rs.getString("answer"));
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	/**
	 * Get user who has given username
	 * @param username username
	 * @return user according to given username
	 */
	public static User cmpAnswer(String username)
	{
		User u = null;
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM users WHERE username='"+username+"'");
	        while (rs.next()) {
	        	u = new User(rs.getString("id"),rs.getString("username"),rs.getString("name"),rs.getString("surname"),rs.getString("answer"));
	        	u.setPassword(rs.getString("password"));
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	/**
	 * Get users who watched given movie
	 * @param m Movie that will be watched
	 * @return users list that contains users who watched given movie
	 */
	public static ArrayList<User> getWatchedUsers(Movie m)
	{
		User u;
		ArrayList<User> list = new ArrayList<User>();
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM watched WHERE movie_id='"+m.id+"'");
	        while (rs.next()) {
	        	u = getUserById(rs.getInt("user_id"));
	        	list.add(u);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Save movie to database 
	 * @param m Movie that will be added
	 * @return movie
	 */
	public static Movie addMovie(Movie m)
	{
		Gson gson = new Gson();
		String info = gson.toJson(m);
		connect();
		try {
			prepared = (PreparedStatement) con.prepareStatement("INSERT INTO movies (id,original_title,vote_average,popularity,release_date,revenue,info) VALUES(?,?,?,?,?,?,?)");
			prepared.setInt(1, m.id);
			prepared.setString(2, m.original_title);
			prepared.setDouble(3, m.vote_average);
			prepared.setDouble(4, m.popularity);
			prepared.setString(5, m.release_date);
			prepared.setInt(6,m.revenue);
			prepared.setString(7, info);
			prepared.executeUpdate();
			return getMovieById(m.id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
		
	}
	
	/**
	 * Get movie through given id
	 * @param id movie's id
	 * @return movie which has given id
	 */
	public static Movie getMovieById(int id)
	{
		Movie m = null;
		Gson gson = new Gson();
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM movies WHERE id='"+id+"'");
	        while (rs.next()) {
	        	m = gson.fromJson(rs.getString("info"), Movie.class);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * Get movies according to their popularity
	 * @return movies which are arranged through their popularity
	 */
	public static Movies getPopularMovies()
	{
		Movies list = new Movies();
		Movie m;
		Gson gson = new Gson();
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM  `movies` ORDER BY  `movies`.`popularity` DESC LIMIT 0 , 30");
	        while (rs.next()) {
	        	m = gson.fromJson(rs.getString("info"), Movie.class);
	        	list.addMovie(m);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Get movies through vote average
	 * @return movies which are arranged according to their vote average
	 */
	public static Movies getTopMovies()
	{
		Movies list = new Movies();
		Movie m;
		Gson gson = new Gson();
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM  `movies` ORDER BY  `movies`.`vote_average` AND popularity > 1000 DESC LIMIT 0 , 30");
	        while (rs.next()) {
	        	m = gson.fromJson(rs.getString("info"), Movie.class);
	        	list.addMovie(m);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Get movies through their date
	 * @return movies which are arranged their date
	 */
	public static Movies getLastMovies()
	{
		Movies list = new Movies();
		Movie m;
		Gson gson = new Gson();
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM  `movies` ORDER BY `movies`.`release_date` DESC LIMIT 0 , 30");
	        while (rs.next()) {
	        	m = gson.fromJson(rs.getString("info"), Movie.class);
	        	list.addMovie(m);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}	
	
	/**
	 * Get random movies from database
	 * @return movies which are determined randomly
	 */
	public static Movies getRandomMovies()
	{
		Movies list = new Movies();
		Movie m;
		Gson gson = new Gson();
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM  `movies` ORDER BY RAND() LIMIT 0 , 3");
	        while (rs.next()) {
	        	m = gson.fromJson(rs.getString("info"), Movie.class);
	        	list.addMovie(m);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * get a random movie from database
	 * @return movie
	 */
	public static Movie getRandomMovie()
	{
		Movie m = null;
		Gson gson = new Gson();
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM  `movies` ORDER BY RAND() LIMIT 0 , 1");
	        while (rs.next()) {
	        	m = gson.fromJson(rs.getString("info"), Movie.class);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * Control whether given user watched given movie or not
	 * @param u User
	 * @param m Movie
	 * @return whether operation is successful or not
	 */
	public static boolean isWatched(User u,Movie m)
	{
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM watched WHERE user_id='"+u.id+"' AND movie_id='"+m.id+"'");
	        while (rs.next()) {
	        	return true;
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Add given movie to given user's list
	 * @param u user
	 * @param m Movie
	 * @param vote users' vote
	 * @return whether operation is successful or not
	 */
	public static boolean addWatchedList(User u, Movie m, double vote)
	{
		connect();
		try {
			stmt.executeUpdate("INSERT INTO watched (user_id,movie_id,vote) VALUES('"+u.id+"','"+m.id+"','"+vote+"')");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Get movies which are watched by given user
	 * @param u User who watched film
	 * @return movies
	 */
	public static Movies getWatchedMovies(User u)
	{
		Movies list = new Movies();
		Movie m;
		connect();
		ResultSet rs;
		try {
			rs = (ResultSet) stmt.executeQuery("SELECT * FROM watched WHERE user_id='"+u.id+"'");
	        while (rs.next()) {
	        	m = getMovieById(rs.getInt("movie_id"));
	        	list.addMovie(m);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
