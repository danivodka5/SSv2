package GuiTesting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GuiElements.InsideOptionsPanel;
import GuiElements.LoadingPanel;
import GuiElements.OptionsPanel;
import GuiElements.PanelManager;
import GuiElements.userDoesNotExist;

public class testInsideOptionsPanel {
	
	private InsideOptionsPanel iop;
	private JFrame frame;
	private LoadingPanel lp;
	private OptionsPanel op;
	private userDoesNotExist udnt;
	private ArrayList<JPanel> panelList;
	private PanelManager pm;
	
	public testInsideOptionsPanel() {
		
		JPanel mainPanel = new JPanel();
		
		// Creacion del Frame
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(600,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// LoadingPanel
		lp = new LoadingPanel();
		lp.setVisible(true);
		
		// OptionsPANEL
		op = new OptionsPanel();
		
		// InsideOptionsPanel
		iop = new InsideOptionsPanel();
		
		// UserDoesntExistPanel
		udnt = new userDoesNotExist("Nose", "algo");

		// PanelList
		panelList = new ArrayList<>();
		panelList.add(lp); 		// 1
		panelList.add(op); 		// 2
		panelList.add(udnt); 	// 3
		panelList.add(iop);  	// 4
		

		// JPanelManager 
		pm = new PanelManager(panelList);
		pm.addListenerioc(op);
		
		// JPanel Manager added
		System.out.println("-- voy a añadir--");	
		mainPanel.add(pm, BorderLayout.CENTER);
		
		frame.add(mainPanel);
		
		pm.showPanel(2);
		
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		testInsideOptionsPanel test1 = new testInsideOptionsPanel();
		
	}
}
