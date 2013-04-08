package First;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
/**
 * A JPanel class which includes JPasswordField to change the password of the specified user.
 * @author Burak Mete Canpınar
 * @version 4.25
 */

public class SettingsPanel extends JPanel {
	private User u;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	/**
	 * SettingsPanel class for the account settings panel
	 * @param u user object 
	 */
	public SettingsPanel(User u) {
		this.u = u;
		setBounds(new Rectangle(5, 2, 0, 0));
		setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAccountSettings = new JLabel("Account Settings");
		GridBagConstraints gbc_lblAccountSettings = new GridBagConstraints();
		gbc_lblAccountSettings.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccountSettings.gridx = 1;
		gbc_lblAccountSettings.gridy = 1;
		add(lblAccountSettings, gbc_lblAccountSettings);
		
		JLabel lblOldPassword = new JLabel("Old password");
		GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
		gbc_lblOldPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblOldPassword.anchor = GridBagConstraints.WEST;
		gbc_lblOldPassword.gridx = 1;
		gbc_lblOldPassword.gridy = 3;
		add(lblOldPassword, gbc_lblOldPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.addActionListener(new ButtonListener());
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 3;
		add(passwordField, gbc_passwordField);
		
		JLabel lblNewPassword = new JLabel("New Password");
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPassword.anchor = GridBagConstraints.WEST;
		gbc_lblNewPassword.gridx = 1;
		gbc_lblNewPassword.gridy = 4;
		add(lblNewPassword, gbc_lblNewPassword);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 2;
		gbc_passwordField_1.gridy = 4;
		add(passwordField_1, gbc_passwordField_1);
		
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 6;
		add(btnSubmit, gbc_btnSubmit);
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			String old_password = passwordField.getText();
			String new_password = passwordField_1.getText();
			if(u.getPassword().equals(old_password)){
				u.setPassword(new_password);
				Database.update(u);
			}

			passwordField.setText("");
			passwordField_1.setText("");
	}
}
}