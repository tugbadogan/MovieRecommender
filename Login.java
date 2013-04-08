package First;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * A JFrame class for the LogIn part of the program
 * @author Aysegül Sümeyye KÜTÜK
 * @version 3.25
 */

public class Login extends JFrame implements ActionListener
{
	JPanel contentPane;
	JPasswordField passField;
	JFormattedTextField userField;
	JButton loginButton,haveAccountButton,forgetButton;
	
	public Login()
	{
		super("New Generation Movie Recommender from NOOB");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 30, 700, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(209,224,243));  //Color.ORANGE);
		//contentPane.setForeground(new Color(0,0,0)); //0, 0, 0));
		//contentPane.setBorder(new EmptyBorder(50, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCoi = new JLabel("New Generation Movie Recommender");
		lblCoi.setForeground(Color.BLACK);
		lblCoi.setFont(new Font("Calibri", Font.BOLD, 26));
		lblCoi.setBounds(100, 30, 500, 30);
		contentPane.add(lblCoi);
		
		JLabel lblCoi2 = new JLabel("                    From NOOB                      ");
		lblCoi2.setForeground(Color.BLACK);
		lblCoi2.setFont(new Font("Calibri", Font.BOLD, 26));
		lblCoi2.setBounds(100, 60, 500, 50);
		contentPane.add(lblCoi2);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(100, 120, 100, 20);
		lblUsername.setForeground(Color.BLACK);
		contentPane.add(lblUsername);
		
		userField = new JFormattedTextField();
		userField.setBounds(170, 120, 200, 24);
		contentPane.add(userField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(100, 160, 100, 14);
		lblPassword.setForeground(Color.BLACK);
		contentPane.add(lblPassword);
		
		passField = new JPasswordField();
		passField.setBounds(170, 160, 200, 24);
		contentPane.add(passField);
		
		
		
		loginButton = new JButton("Login!");
		loginButton.setBackground(new Color(22, 56, 56));
		loginButton.setForeground(new Color(250,250,250));
		loginButton.setBounds(170, 205, 200, 26);
		contentPane.add(loginButton);
		
		forgetButton = new JButton("Forgot Your Password?");
		forgetButton.setBackground(new Color(52,112,143));//50, 128, 128));
		forgetButton.setForeground(new Color(250,250,250));
		forgetButton.setBounds(170, 250, 200, 23);
		contentPane.add(forgetButton);
		
		haveAccountButton = new JButton("Create an Account");
		haveAccountButton.setBackground(new Color(52,112,143));//(50, 128, 128));
		haveAccountButton.setForeground(new Color(250,250,250));
		haveAccountButton.setBounds(400, 250, 166, 23);
		contentPane.add(haveAccountButton);
		
		
		ImageIcon imageIcon = new ImageIcon("home_bg.png");
	    //Image image = imageIcon.getImage();
		
		JLabel icon = new JLabel();
		icon.setBounds(120, 200, 700, 335);
		icon.setIcon(imageIcon);
		contentPane.add(icon);
		
		loginButton.addActionListener(this);
		haveAccountButton.addActionListener(this);
		forgetButton.addActionListener(this);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		JButton clicked = (JButton)arg0.getSource();
		User user;
		/**
		 *The conditionals of relevant actions for different buttons
		 */
		if(loginButton == clicked)
		{
			user = Database.login(userField.getText(), passField.getText());
			/**
			 *LogIn operation for the users other than null
			 */
			if(user!=null)
			{	
				System.out.println("User Logged in : " + user.name);
				try {
					Menu menuPage = new Menu(user);
					menuPage.setVisible(true);
					this.dispose();
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
			{
				System.out.println("FALSE");
				
			}
		}
		else if(haveAccountButton == clicked)	
		{
			SignUpPage signup = new SignUpPage();
			signup.setVisible(true);
			this.dispose();
		}
		else if(forgetButton == clicked)
		{
			//System.out.println("aaaaa="+userField.getText());
			if (userField.getText().length()>1) {
				ForgotPage forgot = new ForgotPage(userField.getText());
				forgot.setVisible(true);
				this.dispose();
			}
		}
		
	}
	
	

}
