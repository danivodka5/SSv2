package GuiElements;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class userDoesNotExist extends JPanel{
	
	private JLabel bigLabel;
	private JLabel smallLabel;
	
	public userDoesNotExist(String label1, String label2) {
        setLayout(new GridLayout(2, 1));
        
        // Create and customize the big label
        bigLabel = new JLabel(label1);
        bigLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bigLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bigLabel);
        
        // Create and customize the small label
        smallLabel = new JLabel(label2);
        smallLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        smallLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(smallLabel);
	}

}
