package First;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.io.IOException;
import java.net.URL;
import javax.swing.JScrollBar;
/**
 * This class is made for administration operations such as searching and adding movies to the database.
 * GUI Part of the search part
 * @author Isil Irem TEKES
 * @version 4.35
 */
public class Admin extends JFrame implements ActionListener {
	/**
	 * variables 
	 */
	JTextField textField;
	ImageIcon icon;
	JButton btnNewButton;
	JPanel panel;
	JButton[] add = new JButton[66];
	Movies mov;
	JScrollPane jScroll;
	/**
	 * include panels buttons and scroll . GUI
	 */
	public Admin() {
		getContentPane().setBackground(Color.PINK);
		btnNewButton = new JButton("Search");
		btnNewButton.setBounds(315, 0, 109, 23);
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * search button and its action 
			 */
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnNewButton)
				{
					/**
					 * get text from the text field and search it in movie database
					 */
					String movie = textField.getText();
					try {
						
						mov = API.searchMovie(movie);
						int x=0, i=0;
						panel.removeAll();
						/**
						 * first check the id and print the results with add button next each of them
						 */
						for(Movie m : mov.results)
						{
							m = API.getMovie(m.id);
							add[i] = new JButton("ADD");
							add[i].setBounds(500,x,120,25);
							add[i].addActionListener(Admin.this);
							/**
							 * when u choose ones it change already choosen button "added"
							 */
							if(Database.getMovieById(m.id)!=null)
							{
								add[i].setEnabled(false);// able to not choose again
								add[i].setText("ADDED");
							}
							panel.add(add[i]);
							/**
							 * print all results images next to name
							 */
							icon = new ImageIcon(new URL("http://cf2.imgobject.com/t/p/w92/"+m.poster_path));
							icon = new ImageIcon(icon.getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH));
							
							JLabel image = new JLabel(icon);
							image.setBounds(0,x,100,100);
							panel.add(image);
							
							System.out.println(m.original_title);
							JLabel name = new JLabel(m.original_title);
							
							name.setBounds(110, x, 500, 20);
							panel.add(name);
							panel.setPreferredSize(new Dimension(500,x+100));
							panel.repaint();
							jScroll.revalidate();
							repaint();
							x+=110;
							i++;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnNewButton);
		/**
		 * text button for users to enter the name of the films
		 */
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(182, 1, 133, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		/**
		 * scroll panel for panel to see all films result
		 */
		JPanel forScroll = new JPanel();
		forScroll.setBounds(20,51,647,500);
		forScroll.setLayout(new BorderLayout());
		getContentPane().add(forScroll);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setPreferredSize(new Dimension(500,400));
		
		jScroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//jScroll.setViewportView(panel);
		//jScroll.setBounds(20, 51, 647, 500);
		//jScroll.setViewportView(panel);


		forScroll.add(jScroll,BorderLayout.CENTER);
		//forScroll.add(panel, BorderLayout.CENTER);

		//panel.setLayout(new BorderLayout(0, 0));
		setVisible(true);
		setBounds(0, 0, 700, 700);
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		int i=0;
		for(i=0;i<mov.results.size();i++)
		{
			if(e.getSource()==add[i]) {
				try {
					Database.addMovie(API.getMovie(mov.results.get(i).id));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				add[i].setText("ADDED");
				add[i].setEnabled(false);
			}
		}
		
	}
}
