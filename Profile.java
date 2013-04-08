package First;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import First.TopMovies.ButtonListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel which shows user's profile
 * @author Tugba DOGAN
 * @version 4.47
 */
public class Profile extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	private JButton b[] = new JButton[100];
	JScrollPane jScroll;
	JPanel content;
	User u;
	private ImageIcon icon;
	Movies list;

	public Profile(User u) {
		this.u = u;
		setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600,500));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBackground(Color.ORANGE);
		leftPanel.setPreferredSize(new Dimension(100,500));
		System.out.println(u.getName()+" "+ u.getSurname());
		JLabel name = new JLabel(u.getName()+" "+ u.getSurname());
		name.setBounds(10,10,100,50);
		leftPanel.add(name);
		add(leftPanel,BorderLayout.WEST);
		
		JPanel watchedPanel = new JPanel();
		watchedPanel.setLayout(new GridLayout(5,6));
		
		list = Database.getWatchedMovies(u);
		for(int i=0 ; i<list.results.size() ; i++){
			try {
				icon = new ImageIcon(new URL("http://cf2.imgobject.com/t/p/w92/"+list.results.get(i).poster_path));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			b[i] = new JButton(icon);
			
			b[i].addActionListener(this);
			//image.setBounds(10, 10, 92, 128);
			watchedPanel.add(b[i]);
			}
		
		jScroll = new JScrollPane(watchedPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(jScroll, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i=0 ; i<list.results.size() ; i++){
			if(b[i]==arg0.getSource()){
				MovieGUI2 movies = new MovieGUI2(list.results.get(i),u);
			}
		}
	}
}
