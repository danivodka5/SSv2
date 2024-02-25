package GuiElements;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class PanelManager extends JPanel {
	
	private List<JPanel> panelList;
	private CardLayout cl;
	private JPanel mainPanel;
	private int i=1;
	private JPanel emptyPanel = new JPanel();
	
	public PanelManager(List<JPanel> panelList) {
		this.panelList = panelList;
		cl = new CardLayout();
		
		this.setBackground(Color.red);
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(1,1,1,1));
		
		mainPanel = new JPanel(cl);
		mainPanel.setSize(new Dimension(100,100));

		mainPanel.setBackground(Color.blue);
		mainPanel.setForeground(Color.blue);
		this.add(mainPanel, BorderLayout.CENTER);
		
		addJPanelsCard();
	}
	public void addJPanelsCard() {
		mainPanel.add(emptyPanel, 0);
		for (int a=0;a<panelList.size(); a++) {
			String p = "p"+i;
			mainPanel.add(panelList.get(a), p);
			i++;
		}		
		cl.show(mainPanel, "p"+0);
		
	}
	
	public void showPanel(int panel) {
		String p = "p"+panel;
		cl.show(mainPanel, p);

	}	
	public void showNextPanel() {
		cl.next(mainPanel);
	}
}
