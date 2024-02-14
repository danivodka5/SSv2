package GuiElements;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelManager extends JPanel {
	
	
	private ArrayList<Component> ljp;
	
	public PanelManager() {
		
	};
	
	public void addPanel(JPanel... jp) {
		if (jp.length > 0) {
			for (Component c : jp) {
				ljp.add(c);
				c.setSize(getSize());
				c.setVisible(false);
				this.add(c);
				}
		}
		Component show = ljp.get(0);
		show.setVisible(true);
		show.setLocation(0, 0);
	};

}
