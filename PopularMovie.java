package First;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.*;
/**
 * This is the PopularMovies panel which is opened when the user clicks
 * the popular movie button in the MovieGUI panel. 
 * @author Hasan Uygurer
 * @version 4.22
 */
public class PopularMovie extends JPanel {

	/**
	 * Create the panel.
	 */
	private ImageIcon icon;
	private JButton[] button = new JButton[34];
	Movies m=Database.getPopularMovies();
	JScrollPane jScroll;
	JPanel content;
	User u;
	//@param u gets the User object
	public PopularMovie(User u) {
		this.u = u;
		setLayout(new BorderLayout());
		content = new JPanel();
		content.setLayout(new GridLayout(5,6));
		for(int i=0 ; i<m.results.size() ; i++){
			try {
				icon = new ImageIcon(new URL("http://cf2.imgobject.com/t/p/w92/"+m.results.get(i).poster_path));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			button[i] = new JButton(icon);
			
			button[i].addActionListener(new ButtonListener());
			//image.setBounds(10, 10, 92, 128);
			content.add(button[i]);
			content.setBounds(10,10,500,1000);
			jScroll = new JScrollPane(content,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(jScroll, BorderLayout.CENTER);
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
