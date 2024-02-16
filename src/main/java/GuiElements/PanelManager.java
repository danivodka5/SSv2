package GuiElements;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelManager extends JPanel {
	
	
	private List<JPanel> panelList;
	
	public PanelManager() {
		panelList = new ArrayList<JPanel>();
	}
	
	public void addInitialPanels(JPanel... jp) {

		for (JPanel c : jp) {
			panelList.add(c);
		}
		
		this.add(panelList.get(0), BorderLayout.CENTER);
		this.setVisible(true);
	};
	
	public void showPanel(int panel) {
		
	}
}
