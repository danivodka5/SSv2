package GuiElements;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelManager extends JPanel {
	
	private List<JPanel> panelList;
	private CardLayout cl;
	private JPanel mainPanel;
	private int i=1;
	private JPanel emptyPanel = new JPanel();
	public OptionsPanel op;
	
	private int currentPanel;
	
	public PanelManager(List<JPanel> pl) {
		this.panelList = pl;
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
		emptyPanel.setBackground(Color.blue);
		//mainPanel.add(emptyPanel, 0);
		mainPanel.add(emptyPanel, 0);
		for (int a=0;a<panelList.size(); a++) {
			String p = "p"+i;
			mainPanel.add(panelList.get(a), p);
			i++;
		}		
		currentPanel = 0;
		cl.show(mainPanel, "p"+0);
	}
	public void showPanel(int panel) {
		currentPanel = panel;
		System.out.println("Mostrando el Panel "+panel);
		
		String p = "p"+panel;
		cl.show(mainPanel, p);

	}	
	/*
	public void showNextPanel() {
		cl.next(mainPanel);
	}
	*/
	public JPanel getPanel(int panel) {
		return panelList.get(panel);
	}
	
	public void addListenerioc(OptionsPanel opp) {
		op = opp;
		op.btnaiu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		op.btnaeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("holi!!");
            }
        });
	}
	
	
	
	public int getCurrentPanel() {
		return currentPanel;
	}
	public void setCurrentPanel(int position) {
		currentPanel = position;
		
	}

}
