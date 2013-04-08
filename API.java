package First;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
/**
 * API class enables us to use TMDB API’s [] functionalities such as getting movie info, search movies.
 * A JFrame class for the LogIn part of the program
 * @author Isil Irem TEKES
 * @version 4.36
 */
public class API {
	  public static void main(String[] args) throws IOException {
		  Movie film = getMovie(121);
		  System.out.println(film.original_title);
	    }
	  /**
	   * 
	   * @param id get the id of the films to call all of them
	   * @return
	   * @throws IOException
	   */
	  public static Movie getMovie(int id) throws IOException{
		  Gson gson = new Gson();
		    String line = "";
	        URL oracle = new URL("http://api.themoviedb.org/3/movie/" + id +"?api_key=9db8f0074c1ed1366f321eceb1d42bf0");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(oracle.openStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	        	line+=inputLine;
	        
		    Movie m = gson.fromJson(line, Movie.class);
		    return m;
	  }
	  /**
	   * 
	   * @param m words for search
	   * @return
	   * @throws IOException
	   */
	  public static Movies searchMovie(String m)  throws IOException{
		  Gson gson = new Gson();
		    String line = "";
	        URL oracle = new URL("http://api.themoviedb.org/3/search/movie?api_key=9db8f0074c1ed1366f321eceb1d42bf0&query=" + m );
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(oracle.openStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	        	line+=inputLine;
	        
		  Movies mov = gson.fromJson(line,Movies.class);
		  return mov;
	  }
}
