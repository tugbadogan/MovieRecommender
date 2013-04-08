package First;

import java.util.ArrayList;
/**
 * Class which holds list of movie objects
 * @author Isil Irem TEKES
 * @version 3.61
 */
public class Movies {
	ArrayList<Movie> results = new ArrayList<Movie>();
	
	/**
	 * Add movies to ArrayList
	 * @param m Movie
	 */
	public void addMovie(Movie m)
	{
		results.add(m);
	}
}
