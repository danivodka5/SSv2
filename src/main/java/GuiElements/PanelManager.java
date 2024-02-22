package GuiElements;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelManager extends JPanel {
	
	
	private List<JPanel> panelList;
	private JPanel currentPanel;
	
	public PanelManager() {
		panelList = new ArrayList<JPanel>();
	}
	
	public void addInitialPanels(JPanel... jp) {
		System.out.println("-- Probando el metodo --");
		for (JPanel c : jp) {
			panelList.add(c);
		}
		System.out.println("-- Probando this.add --");
		
		currentPanel = panelList.get(0);
		add(currentPanel, BorderLayout.CENTER);
		this.setVisible(true);

	};
	
	public void showPanel(int panel) {
		remove(currentPanel);
		currentPanel = panelList.get(panel);
		add(currentPanel, BorderLayout.CENTER);
	}
}
