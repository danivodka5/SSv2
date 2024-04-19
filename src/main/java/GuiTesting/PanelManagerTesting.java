package GuiTesting;

import javax.swing.*;

import GuiElements.InsideOptionsPanel;
import GuiElements.LoadingPanel;
import GuiElements.OptionsPanel;
import GuiElements.PanelManager;
import GuiElements.userDoesNotExist;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelManagerTesting extends JFrame {
    public PanelManagerTesting() {
        setTitle("Main Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setBackground(Color.red);

        JButton izquierda = new JButton("<--");
        JButton derecha = new JButton("-->");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(izquierda);
        buttonPanel.add(derecha);

        Container contentPane = getContentPane();
        
		// LoadingPanel
		LoadingPanel lp = new LoadingPanel();
		lp.setVisible(true);
		
		// OptionsPANEL
		OptionsPanel op = new OptionsPanel();
		
		// InsideOptionsPanel
		InsideOptionsPanel iop = new InsideOptionsPanel();
		
		// UserDoesntExistPanel
		userDoesNotExist udnt = new userDoesNotExist("Nose", "algo");

		// PanelList
		List<JPanel> panelList = new ArrayList<>();
		panelList.add(panel);
		panelList.add(lp); 		// 1
		panelList.add(op); 		// 2
		panelList.add(udnt); 	// 3
		panelList.add(iop);  	// 4
        PanelManager pm = new PanelManager(panelList);
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(pm, BorderLayout.CENTER);
        
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        izquierda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				pm.showPanel(pm.getCurrentPanel()-1);
			}
		});
        
        
        derecha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("der");
				pm.showPanel(pm.getCurrentPanel()+1);
			}
		});
        
        
        
        
        setVisible(true);
    }

    public static void main(String[] args) {
    	PanelManagerTesting frame = new PanelManagerTesting();
    	
    }
}
