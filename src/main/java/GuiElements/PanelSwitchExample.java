package GuiElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelSwitchExample extends JFrame implements ActionListener {
   private JPanel cards;
   private JButton switchButton;

   public PanelSwitchExample() {
       setTitle("Panel Switch Example");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       cards = new JPanel(new CardLayout());

       JPanel panel1 = new JPanel();
       panel1.add(switchButton = new JButton("Switch Panels"));
       switchButton.addActionListener(this);

       JPanel panel2 = new JPanel();
       JLabel jlabel = new JLabel("aasdasd");
       panel2.add(jlabel);
       panel2.setPreferredSize(new Dimension(200, 100));

       cards.add(panel1, "Panel 1");
       cards.add(panel2, "Panel 2");

       add(cards);

       pack();
       setLocationRelativeTo(null);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
       CardLayout cardLayout = (CardLayout) cards.getLayout();
       cardLayout.next(cards);
   }

   public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
           PanelSwitchExample example = new PanelSwitchExample();
           example.setVisible(true);
       });
   }
}