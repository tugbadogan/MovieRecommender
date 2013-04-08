package First;
import java.awt.BorderLayout;

import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Button;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

/**
 * This is the JFrame Menu which has buttons to change the center panel with
 * the selected options and uses the database methods to add random movies
 * to the south panel.
 * @author Hasan Uygurer
 * @version 4.22
 */

public class Menu extends JFrame {

	public JPanel contentPane;

	/**
	 * Launch the application.
	 */

	//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Menu frame = new Menu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	ImageIcon icon,icon2;

	private JLabel image,image2;
	Movies m =Database.getRandomMovies();
	JPanel centerPanel;
	private User u;
	//@param u; 
	public Menu(User u) throws MalformedURLException {
		this.u=u;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 0, 800, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.NORTH);
		Button button = new Button("Home");
		button.addActionListener(new HomeListener());
		panel.add(button);
		
	
		
		Button button_1 = new Button("Movies");
		button_1.addActionListener(new MoviesListener());
		panel.add(button_1);
		
		Button button_2 = new Button("Users");
		button_2.addActionListener(new UsersListener());
		panel.add(button_2);
		
		Button button_3 = new Button("Settings");
		button_3.addActionListener( new MyEventHandler());
		
		centerPanel = new JPanel();
		contentPane.add(centerPanel);
		icon = new ImageIcon(new URL("http://marciebrockbookmarketingmaven.files.wordpress.com/2012/03/be-original.jpg"));
		image=new JLabel(icon,SwingConstants.CENTER);
		centerPanel.add(image);
		centerPanel.setBackground(Color.ORANGE);
		
		Button button_5 = new Button("Profile");
		button_5.addActionListener(new ProfileListener());
		panel.add(button_5);
		
		panel.add(button_3);
		
		Button button_4 = new Button("Log out");
		button_4.addActionListener(new LogoutEvent());
		panel.add(button_4);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		for(int i=0 ; i<m.results.size() ; i++){
			icon2=new ImageIcon(new URL("http://cf2.imgobject.com/t/p/w185/"+m.results.get(i).poster_path));
			icon2 = new ImageIcon(icon2.getImage().getScaledInstance(250,275, java.awt.Image.SCALE_SMOOTH));
			image2=new JLabel(icon2,SwingConstants.LEFT);
			panel_2.add(image2);
		}
		
		panel_2.setBackground(Color.ORANGE);
	}
	class MyEventHandler implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			contentPane.remove(centerPanel);
			centerPanel = new SettingsPanel(u);
		    contentPane.add(centerPanel, BorderLayout.CENTER);
		    validate();
		}
    }
	class MoviesListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			contentPane.remove(centerPanel);
			centerPanel = new MovieGUI(u);
			contentPane.add(centerPanel,BorderLayout.CENTER);
			validate();
		}
	}
	class LogoutEvent implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			Menu.this.dispose();
			Login login = new Login();
		}
	}
	class HomeListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			contentPane.remove(centerPanel);
			centerPanel=new HomePanel();
			contentPane.add(centerPanel,BorderLayout.CENTER);
			validate();
			
		}
	}
	class UsersListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			contentPane.remove(centerPanel);
			centerPanel=new UsersPanel(Menu.this);
			contentPane.add(centerPanel,BorderLayout.CENTER);
			validate();
		}
	}
	class ProfileListener implements java.awt.event.ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt){
			contentPane.remove(centerPanel);
			centerPanel=new Profile(u);
			contentPane.add(centerPanel,BorderLayout.CENTER);
			validate();
		}
	}

}

