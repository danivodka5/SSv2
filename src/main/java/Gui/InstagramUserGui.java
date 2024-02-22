package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import GuiElements.InstagramTextField;
import GuiElements.InstagramUserGuiSearchPanel;
import GuiElements.LoadingPanel;
import GuiElements.PanelManager;
import GuiElements.PanelSlide;
import GuiElements.SearchGuiIcon;
import GuiElements.userDoesNotExist;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InstagramUserGui {

	private JFrame frame;
	
	private PanelSlide ps;
	private InstagramUserGuiSearchPanel iugsp;

	private ChromeDriver driver;
	private PanelManager pm;
	
	// El error esta aqui, ya que jpanel icon esta vacio
	private JPanel jpanelicon;
	
	private Thread findUserThread;

	private LoadingPanel lp;
	
	
	
	public static void main(String[] args) {
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
		
	}
	
	// Constructor 
	/**
	 * @wbp.parser.entryPoint
	 */
	
	public InstagramUserGui(ChromeDriver d) {
		this.driver = d;
		initialize();
	}
	
	
	public InstagramUserGui() {
		initialize();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(600,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		      System.out.println("Closed");
		      e.getWindow().dispose();
		    }
		});
		
		
		ps = new PanelSlide();
		frame.getContentPane().add(ps);
		
		ps.setLayout(new BorderLayout());
		
		// Panel de Busqueda
		iugsp = new InstagramUserGuiSearchPanel();
		
		
		iugsp.setPreferredSize(new Dimension(100,100));
		
		// Añadimos el panel de busqueda Arriba
		ps.add(iugsp, BorderLayout.NORTH);
		
		// JPanelManager 
		pm = new PanelManager();
		pm.setLayout(new BorderLayout());
		pm.setBorder(new EmptyBorder(100,100,100,100));
		
	
		// Adding JPanels to JPanelManager
		
		
		// JPanel Manager added
		ps.add(pm, BorderLayout.CENTER);
		
		jpanelicon = new JPanel(new BorderLayout());
		jpanelicon.setVisible(false);
		
		lp = new LoadingPanel();
		lp.setVisible(true);
		jpanelicon.add(lp, BorderLayout.CENTER);
		 
		jpanelicon.setPreferredSize(new Dimension(100,100));   	
		jpanelicon.setBackground(null);
			
		// Pm adds jpanelicon after its created
		pm.addInitialPanels(jpanelicon);
		//pm.add(jpanelicon, BorderLayout.CENTER);
		
		
		// Frame listener (EMPTY!!)
		frame.addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {

		    }
		  });	
		
		// Bar MouseListener, Cuando hacemos click al buscador
		iugsp.ugi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		    	if (iugsp.getTextSize() > 0) {
		    		// Hacemos visible el panel de carga
		    		jpanelicon.setVisible(true);
		    		System.out.println("Visibilidad = "+jpanelicon.isVisible());
		    		
		    		// Ejecutamos el Thread
		    		FindUserThread fut = new FindUserThread(driver,iugsp.getText(),lp);
		    		fut.start();

		    	}
			}
		});
		frame.setVisible(true);
	}
	
	// Metodo searchUser [Busqueda de Usuario]
	public void searchUser(final String user) {
		// Mostrar el Panel de carga mientras que UserFound = false;
		// https://docs.oracle.com/javase/6/docs/api/javax/swing/SwingWorker.html
		// https://www.youtube.com/watch?v=qbrE6idMsWU
		
		
		// El hilo no se ejecuta si no se introduce start
		findUserThread = new Thread(new Runnable() {
			@Override
			public void run() {
				driver.get("https://www.instagram.com/"+user);
				System.out.println("Inicio del metodo ejecutarBusqueda() usuario: "+user);
				System.out.println("Fin del metodo");
			}
		});
		
		findUserThread.start();
		// Espero a que finalice el hilo
		try { findUserThread.join(); } catch (InterruptedException e) { e.printStackTrace(); }
		
		jpanelicon.setVisible(false);
		
		//driver.get("https://www.instagram.com/"+user);
		//System.out.println("Inicio del metodo ejecutarBusqueda() usuario: "+user);
		
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		// Metodo para comprobar cuando la pagina ha cargado completamente
		/*
		boolean loading = true;
		while (loading) {
			try {	
				Thread.sleep(100);
				String status = (String) js.executeScript("return document.getElementById('splash-screen').style.display;");
				if (status.equals("none")) {
					loading = false;
				}
			} catch (InterruptedException e) { e.printStackTrace(); }
		}

		System.out.println("Pagina de usuario Cargada ");
		
		// El usuario existe?	
		boolean userExists = (boolean) js.executeScript("const userl = document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0]; if (typeof userl !== 'undefined') { return(false); } else { return (true); }");
		/*
		if (userExists) {
			System.out.println("El usuario existe");
			/*
			btnanuser.setEnabled(true);
			btnanuser.setBooleanEmpty(false);
			btnanuser.setCursor(true);
			
			// Comprobamos si el usuario esta en la base de datos y de paso lo creamos
			System.out.println(conn.doesUserExists(user));
			
			// CREAR USUARIO, ESCOGER USUARIO
			if ((!(conn == null)) &&(!conn.doesUserExists(user))) {
				System.out.println(conn.createUser(user));
			}
			
			
					
		} else {
			System.out.println("El usuario no existe");	
			/*
        	btnanuser.setBooleanEmpty(true);
        	btnanuser.setEnabled(false);
        	btnanuser.setCursor(false);  
        	
			String adv = (String) js.executeScript("return document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0].textContent;");
			System.out.println("adv ="+adv);
			labelNotFound.setText(adv);
			labelNotFound.setVisible(true);
			
		}
		*/
	}
}
