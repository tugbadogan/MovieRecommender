package First;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Panel which shows all users
 * @author Tugba DOGAN
 * @version 4.33
 */
public class UsersPanel extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	private JButton b[] = new JButton[100];
	JScrollPane jScroll;
	JPanel content;
	JFrame menu;

	ArrayList<User> users = Database.getUsers();
	public UsersPanel(JFrame menu) {
		this.menu = menu;
		setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,6));
		panel.setBackground(Color.ORANGE);
		panel.setBounds(92, 91, 266, 133);
		for(int i=0 ; i<users.size() ; i++){
			b[i]=new JButton();
			b[i].setLayout(new GridLayout(2,1));
			JLabel label1 = new JLabel(users.get(i).name + " " + users.get(i).surname);
			JLabel label2 = new JLabel(users.get(i).username);
			b[i].add(label1);
			b[i].add(label2);
			b[i].addActionListener(this);
			panel.add(b[i]);
		}

		jScroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(jScroll, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i=0; i<users.size(); i++)
		{
			if(b[i] == arg0.getSource())
			{
				
				((Menu)menu).contentPane.remove(((Menu)menu).centerPanel);
				((Menu)menu).centerPanel = new Profile(users.get(i));
				((Menu)menu).contentPane.add(((Menu)menu).centerPanel,BorderLayout.CENTER);
				menu.validate();
			}
		}
	}
}
