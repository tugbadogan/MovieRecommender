package First;

import java.util.ArrayList;
/**
 * Movie class that holds the movie objects
 * @author Işıl İrem Tekeş
 * @version 4.35
 */

public class Movie {
	// variables
	String original_title, backdrop_path,homepage, imdb_id, release_date,poster_path;
	String overview, tagline, title; 
	boolean adult;
	int budget,id, revenue, runtime, vote_count;
	double popularity, vote_average; 
	// lists
	ArrayList<genres> genres;
	ArrayList<production_companies> production_companies;
	ArrayList<production_countries> production_countries;
	ArrayList<spoken_languages> spoken_languages; 
	//class
	public class genres {
		int id;
		String name;
	}
    public class belongs_to_collection{
    	int id;
    	String name, poster_path, backdrop_path;
    }
    public class production_companies{
    	int id;
    	String name;
    }
    public class production_countries{
    	String iso_3166_1, name;
    }
    public class spoken_languages{
    	String iso_3166_1, name;
    }
    
}
