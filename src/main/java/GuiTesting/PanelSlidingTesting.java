package GuiTesting;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GuiElements.PanelSlide;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

public class PanelSlidingTesting {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelSlidingTesting window = new PanelSlidingTesting();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PanelSlidingTesting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		PanelSlide ps = new PanelSlide();
		
		//ps.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ps.setBounds(34, 43, 301, 345);

		JPanel j1 = new JPanel();
		JLabel l1 = new JLabel("hola");
		j1.add(l1);
		
		JPanel j2 = new JPanel();
		JLabel l2 = new JLabel("que tal");
		j2.add(l2);
		
		ps.init(j1,j2);	
		frame.getContentPane().add(ps);
		
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(377, 247, 89, 23);
		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ps.show(0);
				System.out.println("mostrando 0");
			}
		});
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(377, 308, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ps.show(1);
				System.out.println("mostrando1");
			}
		});
		
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(btnNewButton_1);
		
	}
}
