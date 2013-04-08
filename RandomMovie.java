package First;

import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * This is the RandomMovies panel which is opened when the user clicks
 * the random button in the MovieGUI panel. It opens the panel which has
 * 3 movies chosen randomly and user can chose one of the movies to
 * watch. 
 * @author Hasan Uygurer
 * @version 4.21
 */
public class RandomMovie extends JPanel {

	/**
	 * Create the panel.
	 */
	private ImageIcon icon;
	private JButton[] button = new JButton[6]; 
	Movies m=Database.getRandomMovies();
	User u; 
	//@param u gets the User object
	public RandomMovie(User u) {
		this.u=u;
		setLayout(new GridLayout(0,3));
		for(int i=0 ; i<m.results.size() ; i++){
			try {
				icon=new ImageIcon(new URL("http://cf2.imgobject.com/t/p/w92/"+m.results.get(i).poster_path));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			button[i]=new JButton(icon);
			button[i].addActionListener(new ButtonListener());
			add(button[i]);
		}
		
	}
	class ButtonListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			for(int i=0 ; i<m.results.size() ; i++){
				if(button[i]==evt.getSource()){
					MovieGUI2 movies = new MovieGUI2(m.results.get(i),u);
				}
			}
		}
	}

}
