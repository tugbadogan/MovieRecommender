package First;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * This is the panel which holds the main picture and when homw button in the
 * Menu GUI is pressed this panel is opened by in the Center Layout.
 * @author Hasan Uygurer
 * @version 4.52
 */
public class HomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private ImageIcon icon;
	private JLabel image;
	public HomePanel() {
		setBackground(Color.ORANGE);
		setLayout(new BorderLayout(0, 0));
		try {
			icon = new ImageIcon(new URL("http://marciebrockbookmarketingmaven.files.wordpress.com/2012/03/be-original.jpg"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image=new JLabel(icon,SwingConstants.CENTER);
		add(image);

	}

}
