package GuiElements;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class InstagramUserGuiSearchPanel extends JPanel {

	// Soy un simple panel
	
	private JPanel exit;
	private JPanel searchbar;
	private JPanel searchimg;
	
	// Constructor
	public InstagramUserGuiSearchPanel() {
		setLayout(new BorderLayout());
		
		exit = new JPanel();
		searchbar = new JPanel();
		searchimg = new JPanel();
		
		exit.setBackground(Color.red);
		searchbar.setBackground(Color.BLUE);
		searchimg.setBackground(Color.cyan);
		
		add(exit, BorderLayout.WEST);
		add(searchbar, BorderLayout.CENTER);
		add(searchimg, BorderLayout.EAST);
		
		
	};
	
	
	
}
