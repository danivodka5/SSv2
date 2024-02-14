package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentAdapter;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import GuiElements.InstagramTextField;
import GuiElements.InstagramUserGuiSearchPanel;
import GuiElements.LoadingPanel;
import GuiElements.PanelManager;
import GuiElements.PanelSlide;
import GuiElements.SearchGuiIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.chrome.ChromeDriver;

public class InstagramUserGui {

	private JFrame frame;
	
	private PanelSlide ps;
	private InstagramUserGuiSearchPanel iugsp;
	private JLabel lblNewLabel; 
	
	private JPanel loadingpanel;
	private JTextField txtHolaa;
	private JTextField txtQueTal;
	
	private ChromeDriver driver;
	private PanelManager pm;
	private JPanel test1;
	
	

	public static void main(String[] args) {
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					InstagramUserGui window = new InstagramUserGui();
					window.frame.setVisible(true);
					
					window.frame.addComponentListener(new ComponentAdapter() {
			            public void componentResized(ComponentEvent e) {
			                System.out.println("Frame Resized");
			            }
			        });
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		*/
	}

	// Constructor 
	/**
	 * @wbp.parser.entryPoint
	 */
	public InstagramUserGui(ChromeDriver driver) {
		initialize(driver);
	}
	
	
	private void initialize(ChromeDriver driver) {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(600,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ps = new PanelSlide();
		frame.getContentPane().add(ps);
		
		ps.setLayout(new BorderLayout());
		
		// Panel de Busqueda
		iugsp = new InstagramUserGuiSearchPanel();
		iugsp.setVisible(true);
		iugsp.setPreferredSize(new Dimension(100,100));
		
		// Añadimos el panel de busqueda Arriba
		ps.add(iugsp, BorderLayout.NORTH);
		
		// JPanelManager 
		pm = new PanelManager();
		pm.setLayout(new BorderLayout());
		
		pm.setBorder(new EmptyBorder(100,100,100,100));
		
		
		test1 = new JPanel(new BorderLayout());
		
		LoadingPanel lp = new LoadingPanel();
		test1.add(lp, BorderLayout.CENTER);

	    
    	test1.setPreferredSize(new Dimension(100,100));   	
		test1.setBackground(null);
		
		pm.add(test1, BorderLayout.CENTER);
		
		ps.add(pm, BorderLayout.CENTER);
		
	
		// Frame listener
		frame.addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {

		    }
		  });	
		
		
		frame.setVisible(true);
	}
}
