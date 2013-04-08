package First;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * This is the LatestMovies panel which is opened when the user clicks
 * the Latest button in the MovieGUI panel. 
 * @author Hasan Uygurer
 * @version 4.22
 */
public class LatestMovies extends JPanel {

	/**
	 * Create the panel.
	 */
	private ImageIcon icon;
	private JButton[] button = new JButton[34];
	JScrollPane jScroll;
	JPanel content;

	Movies m=Database.getLastMovies();
	User u;
	//@param u gets the User object
	public LatestMovies(User u) {
		this.u=u;
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
			button[i]=new JButton(icon);
			button[i].addActionListener(new ButtonListener());
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
