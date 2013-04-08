package First;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.*;

/**
 * A JFrame class for the GUI design and the method of reminding the password
 * @author Burak Mete Canpınar
 * @version 4.23
 */
public class MovieGUI2 extends JFrame {

	private JPanel contentPane;
	Movie m;
	User u;
	String genres_string="";
	String companies_string="";
	String languages_string="";
	String countries_string="";
	Integer[] nums = {1,2,3,4,5,6,7,8,9,10};
	JComboBox votebox;
	int selected = 0;
	JButton btnNewButton;

	/**
	 * MovieGUI2 class for the design of movie page
	 * @param u user object
	 * @param m movie object 
	 */
	public MovieGUI2(Movie m, User u) {
		this.m = m;
		this.u=u;
		//for loops to generate strings for specified ArrayLists
		for(int i=0;i<m.genres.size();i++)
			genres_string+=m.genres.get(i).name + ",";
		for(int i=0;i<m.production_companies.size();i++)
			companies_string+=m.production_companies.get(i).name + ",";
		for(int i=0;i<m.spoken_languages.size();i++)
			languages_string+=m.spoken_languages.get(i).name + ",";
		for(int i=0;i<m.production_countries.size();i++)
			countries_string+=m.production_countries.get(i).name + ",";
		setTitle(m.title);
		setVisible(true);
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new URL("http://cf2.imgobject.com/t/p/w185/"+m.poster_path));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 163, 222);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel image=new JLabel(icon);
		panel.add(image);
		
		btnNewButton = new JButton("Vote,add to your watchlist");
		btnNewButton.setBounds(0, 230, 163, 37);
		contentPane.add(btnNewButton);
		if(!Database.isWatched(u, m))
			btnNewButton.setEnabled(true);
		else
			btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ButtonListener());
		
		JLabel lblMoveTtle = new JLabel(m.original_title);
		lblMoveTtle.setBounds(173, 11, 317, 14);
		contentPane.add(lblMoveTtle);
		
		JLabel lblRating = new JLabel(""+m.vote_average);
		lblRating.setBounds(173, 68, 46, 14);
		contentPane.add(lblRating);
		
		votebox = new JComboBox(nums);
		votebox.setBounds(229, 59, 200, 23);
		contentPane.add(votebox);
		
		JLabel lblGenres = new JLabel("Genres: "+genres_string);
		lblGenres.setBounds(173, 219, 317, 14);
		contentPane.add(lblGenres);
		
		JLabel lblRuntime = new JLabel("Runtime: "+m.runtime);
		lblRuntime.setBounds(183, 31, 100, 14);
		contentPane.add(lblRuntime);
		
		JLabel lblReleaseDate = new JLabel("Release date: "+m.release_date);
		lblReleaseDate.setBounds(309, 31, 174, 14);
		contentPane.add(lblReleaseDate);
		
		JLabel lblVotes = new JLabel(m.vote_count+" votes");
		lblVotes.setBounds(439, 68, 71, 14);
		contentPane.add(lblVotes);
		
		JLabel lblBudget = new JLabel("Budget: "+m.budget);
		lblBudget.setBounds(173, 109, 142, 14);
		contentPane.add(lblBudget);
		
		JLabel lblRevenue = new JLabel("Revenue: "+m.revenue);
		lblRevenue.setBounds(325, 109, 163, 14);
		contentPane.add(lblRevenue);
		
		JLabel lblSpokenLanguages = new JLabel("Spoken Languages: "+languages_string);
		lblSpokenLanguages.setBounds(173, 144, 337, 14);
		contentPane.add(lblSpokenLanguages);
		
		JLabel lblCompanies = new JLabel("Companies: "+companies_string);
		lblCompanies.setBounds(173, 169, 337, 14);
		contentPane.add(lblCompanies);
		
		JLabel lblCompanies_1 = new JLabel("Countries: "+countries_string);
		lblCompanies_1.setBounds(173, 194, 337, 14);
		contentPane.add(lblCompanies_1);
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			int vote = ((Integer) votebox.getSelectedItem()).intValue(); 
			 Database.addWatchedList(u, m, vote);
			 btnNewButton.setEnabled(false);
		}
	}
}
