package GuiElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ThreadExample extends Thread {

    private JPanel panel;
    private CardLayout cardLayout;
    private JButton but;

    public ThreadExample(JPanel panel,JButton but) {
        this.panel = panel;
        this.cardLayout = (CardLayout) panel.getLayout();
        this.but = but;
    }

    @Override
    public void run() {
    	but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	cardLayout.next(panel);
            }
        });
    }

    public static void main(String[] args) {
    	
        JFrame frame = new JFrame("Thread Example");
        frame.setLayout(new GridLayout(2,0));
        frame.setMinimumSize(new Dimension(100,400));
        //frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JPanel panelbutton = new JPanel();
        JButton button = new JButton("Switch Panels");
        panelbutton.add(button);
        
        button.setVisible(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());
        frame.add(panel);
        
        LoadingPanel lp = new LoadingPanel();
        panel.add(lp, "Panel 1");

        OptionsPanel op = new OptionsPanel();
        panel.add(op, "Panel 2");

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.GREEN);
        panel.add(panel3, "Panel 3");
        frame.add(button);
        
        // Todo debe ser añadido antes del thread.
        ThreadExample threadExample = new ThreadExample(panel,button);
        threadExample.start();

        //frame.pack();
    }
}