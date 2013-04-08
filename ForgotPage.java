


package First;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * A JFrame class for the GUI design and the method of reminding the password
 * @author Ayşegül Sümeyye Kütük
 * @version 3.18
 */
public class ForgotPage extends JFrame implements ActionListener
{
	JPanel contentPane;
	JPasswordField passField;
	JTextField userField;
	JButton resetButton,cancelButton;
	String usrname;
	JLabel lblShow;
	/**
	 * ForgotPage class for the GUI design
	 * @param usrname user name 
	 */
	public ForgotPage(String usrname)
	{
		super("Welcome To Movie Recommender");
		this.usrname = usrname;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 30, 500, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250,250,250));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ImageIcon imageIcon = new ImageIcon("images.jpg");
		JLabel icon = new JLabel();
		icon.setBounds(50, 140, 500, 500);
		icon.setIcon(imageIcon);
		contentPane.add(icon);
				
		JLabel lblCoi = new JLabel("Forgot Your Password?");
		lblCoi.setForeground(Color.BLACK);
		lblCoi.setFont(new Font("Calibri", Font.BOLD, 26));
		lblCoi.setBounds(70, 11, 400, 51);
		contentPane.add(lblCoi);
		
		userField = new JTextField();
		userField.setBounds(210, 80, 180, 26);
		contentPane.add(userField);
		
		JLabel lblUsername = new JLabel("Your secret answer:");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(70, 80, 150, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPass = new JLabel("Your password is :");
		lblPass.setForeground(Color.BLACK);
		lblPass.setBounds(70,110,180,26);
		contentPane.add(lblPass);
		
		lblShow = new JLabel ("");
		lblShow.setForeground(Color.BLACK);
		lblShow.setBounds(210,110,180,26);
		contentPane.add(lblShow);

		resetButton = new JButton("Enter!");
		resetButton.setBackground(new Color(153,217,234));
		resetButton.setForeground(Color.BLACK);
		resetButton.setBounds(230, 180, 160, 26);
		contentPane.add(resetButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(153,217,234));
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBounds(230, 210, 160, 26);
		contentPane.add(cancelButton);
		
		resetButton.addActionListener(this);
		cancelButton.addActionListener(this);	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		User user;
		if(resetButton == arg0.getSource())
		{			
			user = Database.cmpAnswer(usrname);
			String a= userField.getText();
			System.out.println(user.getAnswer());
			/**
			 * The conditional for the function of reminding the password after the user name is taken by the system
			 */
			if(user!=null)
			{
				if (user.getAnswer().equals(a))
				{
					System.out.println(user.password);
					this.lblShow.setText(""+user.password);	
				}
				else
				{
					lblShow.setText("You entered wrong answer!");
				}
			}
		}
		/**
		 *Cancel operation to get back to the login page
		 */
		else if(cancelButton == arg0.getSource())	
		{
			Login login = new Login();
			login.setVisible(true);
			this.dispose();
		}
	}
}
