
package First;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.net.MalformedURLException;

/**
 * A JFrame class for the register page of the program
 * @author Ayşegül Sümeyye KÜTÜK
 * @version 3.28
 */
public class SignUpPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_6;
	public JButton btnSignUp, btnCancel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPage frame = new SignUpPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUpPage() {
		
		super("Have your own account !!");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 30, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color (250, 250, 250));
		setContentPane(contentPane);
		contentPane.setLayout(null); //new GridLayout(0, 1, 0, 0));
	

		JLabel title = new JLabel("Create new account!");
		title.setForeground(Color.BLACK);
		title.setFont(new Font("Calibri", Font.BOLD, 20));
		title.setBounds(60, 30, 200, 35);
		contentPane.add(title);
	
		// username 
		textField = new JTextField();
		textField.setBounds(300, 90, 239, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// firstname
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(300, 120, 239, 20);
		contentPane.add(textField_6);
		
		// lastname
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(300, 150, 239, 20);
		contentPane.add(textField_2);
		
		// password
		passwordField = new JPasswordField();
		passwordField.setBounds(300, 180, 239, 20);
		contentPane.add(passwordField);
		
		//question
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(300, 210, 239,20);
		contentPane.add(textField_1);
	
		
		JLabel lblUserName = new JLabel("Username: ");
		lblUserName.setBounds(21, 90, 120, 16);
		contentPane.add(lblUserName);
		
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(21, 120, 120, 16);
		contentPane.add(lblFirstName);

		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(21, 150, 120, 16);
		contentPane.add(lblLastName);
		
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(21, 180, 120, 16);
		contentPane.add(lblPassword);
		
		JLabel lblQuestion = new JLabel("Who is the best teacher you have ever had?");
		lblQuestion .setBounds(21, 210, 300, 16);
		contentPane.add(lblQuestion );
		
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBackground(new Color(52,112,143));
		btnSignUp.setForeground(new Color(250,250,250));
		btnSignUp.setBounds(300, 370, 117, 27);
		contentPane.add(btnSignUp);
		btnSignUp.addActionListener(new ButtonListener());
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(52,112,143));
		btnCancel.setForeground(new Color(250,250,250));
		btnCancel.setBounds(450, 370, 117, 27);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ButtonListener());
		
		ImageIcon imageIcon = new ImageIcon("thumbs-up.jpg");
		
		JLabel icon = new JLabel();
		icon.setBounds(0, 180, 400, 400);
		icon.setIcon(imageIcon);
		contentPane.add(icon);
		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSignUp)
			{
				User user;
				String username, firstname, lastname;
				String password;
				String pass;
				username = textField.getText();
				firstname = textField_6.getText();
				lastname = textField_2.getText();
				
				password = passwordField.getText();
				pass = new String(password);
				
				String answer = textField_1.getText();

				user = new User("0",username,firstname,lastname,answer);
				user.setPassword(pass);
				user = Database.register(user);
				Menu menuPage;
				try {
					menuPage = new Menu(user);
					menuPage.setVisible(true);
					} catch (MalformedURLException e1) 
				{
					e1.printStackTrace();
				}
				
				System.out.println(user.name);
				SignUpPage.this.dispose();
			}
			else if(e.getSource()==btnCancel)
			{
				Login login = new Login();
				login.setVisible(true);
				SignUpPage.this.dispose();
			}
		}
	}
}
