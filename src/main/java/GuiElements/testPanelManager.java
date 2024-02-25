package GuiElements;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class testPanelManager {

	public static void main(String[] args) {
        JFrame frame = new JFrame("CardLayout Example");
        
    	List<JPanel> panelList = new ArrayList<JPanel>();
    	
        JPanel panelContainer = new JPanel(new CardLayout());
        
        LoadingPanel lp = new LoadingPanel();
        OptionsPanel op = new OptionsPanel();
        
        panelList.add(lp);
        panelList.add(op);
        
        
        PanelManager pm = new PanelManager(panelList);
        
/*
        panelContainer.add(panel1, "Panel 1");
        panelContainer.add(panel2, "Panel 2");
*/
        JButton button = new JButton("Switch Panels");
        button.addActionListener(new ActionListener() {
        	int i=0;
            @Override
            public void actionPerformed(ActionEvent e) {/*
                CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
                cardLayout.next(panelContainer);
                */

            		pm.showPanel(2);
            		
            }
        });

        frame.add(pm, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
	
	}

}
