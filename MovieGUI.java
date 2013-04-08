package First;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
/**
 * A JPanel which has 4 buttons when the user presses one of the buttons
 * another panel is added to this pannel and shows sections like
 * Top Movies, Latest Movies etc...
 * @author Hasan Uygurer
 * @version 4.37
 */
public class MovieGUI extends JPanel {

	/**
	 * Create the panel.
	 */
	User u;
	JPanel panel_1 = new JPanel();
	/**
	 * 
	 * @param u get users information
	 */
	public MovieGUI(User u) {
		this.u=u;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JButton top_movies = new JButton("Top Movies");
		top_movies.addActionListener(new TopMoviesListener());
		panel.add(top_movies);
		
		JButton popular = new JButton("Popular Movies");
		popular.addActionListener(new PopularListener());
		panel.add(popular);
		
		JButton latest = new JButton("Latest");
		latest.addActionListener(new LatestListener());
		panel.add(latest);
		
		JButton random_movies = new JButton("Random");
		random_movies.addActionListener(new RandomListener());
		panel.add(random_movies);
		
		panel_1.setBackground(Color.ORANGE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		setLayout(groupLayout);
		setBackground(Color.ORANGE);
	}
	class TopMoviesListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			panel_1.removeAll();
			TopMovies movies = new TopMovies(u);
			panel_1.add(movies);
			validate();
		}
	}
	class PopularListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			panel_1.removeAll();
			PopularMovie pop=new PopularMovie(u);
			panel_1.add(pop);
			validate();
		}
	}
	class RandomListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			panel_1.removeAll();
			RandomMovie random=new RandomMovie(u);
			panel_1.add(random);
			validate();
			
		}
	}
	class LatestListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			panel_1.removeAll();
			LatestMovies late = new LatestMovies(u);
			panel_1.add(late);
			validate();
		}
	}
}
