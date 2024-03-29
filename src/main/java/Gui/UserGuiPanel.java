package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.NodeList;

import MySQL.Conexion;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class UserGuiPanel {
	// Atributos
	private JFrame frame;
	private ImageIcon icon1;
	
	private UserGuiSearchBar ugsb;
	private Boton btnbuscar;
	
	private ChromeDriver driver;
	private String user;
	private boolean userExists;
	
	private UserPanel userpanel;
	private Conexion conn;
	
	private ArrayList<String> arrstorysrc = new ArrayList<>();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGuiPanel window = new UserGuiPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// Constructor
	public UserGuiPanel(ChromeDriver driver, Conexion conn) {
		this.conn = conn;
		this.driver = driver;
		initialize();
	}
	
	// Constructor
	public UserGuiPanel(ChromeDriver driver) {
		this.driver = driver;
		initialize();
		
	}
	
	// Constructor testing
	public UserGuiPanel() {
		initialize();
	}
	
	// Metodo Inicial
	
	// Solo si el perfil existe mostrar las opciones, de lo contrario no.
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setResizable(false);
		//frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 470, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ERROR CAUSADO POR ->>>>>>>> ?
		//frame.getContentPane().setLayout(null);
		
		icon1 = new ImageIcon(InstagramLoginGui.class.getResource("/Images/ZoomInsta2.png"));
		
		ImageIcon temp1 = icon1;

		
		userpanel = new UserPanel();
		userpanel.setPreferredSize(new Dimension(50,100));
		userpanel.setBounds(0, 0, 456, 79);
		frame.getContentPane().add(userpanel, BorderLayout.NORTH);
		userpanel.setLayout(null);
		
		ugsb = new UserGuiSearchBar();
		ugsb.setBounds(28, 23, 274, 45);
		userpanel.add(ugsb);
		ugsb.setHorizontalAlignment(SwingConstants.CENTER);
		ugsb.setColumns(15);
		
		// Boton buscar
		btnbuscar = new Boton("Buscar", new Color(112, 196, 240), new Color(0, 149, 246));
		btnbuscar.setBounds(322, 26, 95, 40);
		btnbuscar.setVisible(true);
		btnbuscar.setText("<html><font color = white>Buscar</font></html>");
		btnbuscar.setFont(new Font("Arial", Font.BOLD, 16));
		btnbuscar.setEnabled(false);
		userpanel.add(btnbuscar);
		
		// Metodo boton buscar
		btnbuscar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("me pulsaron");
				user = ugsb.getText();
				
				
				//ejecutarBusqueda();
				//findInstagramStories();
			}
		});
		
		ugsb.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkEnableButton();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkEnableButton();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {	
				checkEnableButton();
			}
            private void checkEnableButton() {
                if (ugsb.getText().length() > 0) {
                	btnbuscar.setBooleanEmpty(false);
                	btnbuscar.setEnabled(true);
                	btnbuscar.setCursor(true);
                } else {
                	btnbuscar.setBooleanEmpty(true);
                	btnbuscar.setEnabled(false);
                	btnbuscar.setCursor(false);                	
                }
            }
		});
	}
	private void ejecutarBusqueda() {
		driver.get("https://www.instagram.com/"+user);
		System.out.println("Inicio del metodo ejecutarBusqueda() usuario: "+user);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Metodo para comprobar cuando la pagina ha cargado completamente
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
		userExists = (boolean) js.executeScript("const userl = document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0]; if (typeof userl !== 'undefined') { return(false); } else { return (true); }");
		
		if (userExists) {
			System.out.println("El usuario existe");
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
        	btnanuser.setBooleanEmpty(true);
        	btnanuser.setEnabled(false);
        	btnanuser.setCursor(false);  
        	
			String adv = (String) js.executeScript("return document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0].textContent;");
			System.out.println("adv ="+adv);
			labelNotFound.setText(adv);
			labelNotFound.setVisible(true);
		}
	
	
		Thread userFound = new Thread(new Runnable() {
			 @Override
			 public void run() {
				 
			 }
		});	
		//userFound.start();

	}
	
	private void analizarPerfil() {
		System.out.println("Iniciamos metodo analizarFollowers");
		
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Title
			System.out.println("Titulo= "+js.executeScript("return document.getElementsByClassName('x1lliihq x1plvlek xryxfnj x1n2onr6 x193iq5w xeuugli x1fj9vlw x13faqbe x1vvkbs x1s928wv xhkezso x1gmr53x x1cpjm7i x1fgarty x1943h6x x1i0vuye xvs91rp x1s688f x5n08af x10wh9bi x1wdrske x8viiok x18hxmgj')[0].textContent;"));
			
			// Biography
			String scriptBio = "const bio = document.getElementsByClassName('_ap3a _aaco _aacu _aacx _aad6 _aade')[0];";
			String script2 = "if (typeof bio !== 'undefined') {return (bio.textContent);} else {return ''};";
			String res = (String) js.executeScript(scriptBio+script2);
			System.out.println("Bio = "+res);
			
			// URL
			//js = (JavascriptExecutor) driver;
			String scriptURL = "const url =  document.getElementsByClassName('x3nfvp2')[6].getElementsByTagName('a')[0]; if (typeof url !== 'undefined'){return (url.textContent);} else {return ''}";
			System.out.println("URL="+js.executeScript(scriptURL));
					
		}catch (Exception e) {
			System.out.println("error en "+e.getMessage());
		}		
		
	}
	
	private void analizarFollowers() {
		System.out.println("Iniciando metodo analizar Followers() ");
		driver.get("https://www.instagram.com/"+user+"/followers/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Buscamos si se ha abierto el div
		boolean loading = true;
		while (loading) {
			loading = loadingFollows(js);
			
		}
		
		// Nosotros siempre seremos el primer seguidor
		System.out.println("\n\nSegunda etapa del metodo--");
		
		int t = 0;
		loading = true;
		while (loading) {
			try {		/*
				String sc = "const scrollBar = document.getElementsByClassName('_aano')[0];  let childrenbar = scrollBar.firstChild.firstChild.childNodes; return(childrenbar.length)";		
				int seguidores = (int) js.executeScript(sc);
				System.out.println("Seguidores = "+seguidores);
				*/
				Thread.sleep(150);
				js.executeScript("const scrollBar = document.getElementsByClassName('_aano')[0];  scrollBar.scrollTop = scrollBar.scrollHeight; ");	
				driver.findElement(By.xpath("//div[@class='x78zum5 xdt5ytf xl56j7k']"));		
				System.out.println("Cargando barra");
				//int ref = (int) js.executeScript(sc);
				/*
				if (seguidores != ref) {
					System.out.println("Seguidores = "+ref);
				}
				*/
				
				t = 0;
			}catch (Exception e) {
				System.out.println("No se ha encontrado el final");
				t++;
				if (t > 6) {
					// Intentar almacenar los followers como un array.
					loading = false;
					
					int segn =  ((Long) js.executeScript("let container = document.getElementsByClassName('_aano')[0]; let childrenbar = container.firstChild.firstChild.childNodes; let f = []; for (let i=0; i<childrenbar.length; i++) { f[i] = childrenbar[i].firstChild.firstChild.firstChild.childNodes[1].firstChild.firstChild.firstChild.firstChild.textContent; } return(f.length);")).intValue();
					
					System.out.println("segn "+segn);
					String sega[] = new String[segn];

					String segscript = "let container = document.getElementsByClassName('_aano')[0]; let childrenbar = container.firstChild.firstChild.childNodes; let f = []; for (let i=0; i<childrenbar.length; i++) { f[i] = childrenbar[i].firstChild.firstChild.firstChild.childNodes[1].firstChild.firstChild.firstChild.firstChild.textContent; }";	

					for(int i=0; i<segn; i++) {
						sega[i] = (String) js.executeScript(segscript+"return(f["+i+"]"+");");
					}	
					System.out.println("fin de coleccion");
					for(int i=0; i<segn; i++) {
						System.out.println("usuario "+sega[i]);
					}
					
				}
			}
		}
	}

	private boolean loadingFollows(JavascriptExecutor js) {
		try {
			Thread.sleep(900);
			driver.findElement(By.xpath("//div[contains(@class, 'x1n2onr6 xzkaem6')]"));
			String sname = "return (document.getElementsByClassName('_aano')[0].firstChild.firstChild.firstChild.firstChild.childNodes[0].firstChild.childNodes[1].firstChild.firstChild.firstChild.textContent);";
			String snamejs = (String) js.executeScript(sname);
			
			// Una vez aparezca el div
			if (snamejs.length() > 1) {
				System.out.println("El Menu ha cargado (hemos encontrado el div)"+ snamejs);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Menu Followers cargando...");
			return true;
		}
		return true;
	}
	
	private void findInstagramStories() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		/*
		 *  var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; 
			var network = performance.getEntries() || {}; 



			for (let i=0; i<network.length;i++){
				if (network[i].initiatorType == 'fetch'){
				console.log(network[i]);
			}
			}

		 */


		//String script = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {};  var network = performance.getEntries() || {}; for (let i=0; i<network.length;i++){ if (network[i].initiatorType == 'fetch'){  return(network["++"].name);	} }";
	
		driver.get("https://www.instagram.com/stories/"+user);
		System.out.println("Inicio del metodo testHistoriaInstagram usuario: "+user);
		
		// Metodo para comprobar cuando la pagina ha cargado completamente
		// Al acceder a la storie mediante url se nos preguntara SIEMPRE si queremos visualizar la historia(mola).
		// Debemos limpiar el array 
		boolean loading = true;
		while (loading) {
			try {
				Thread.sleep(100);
				String status = (String) js.executeScript("return document.getElementById('splash-screen').style.display;"); // ?
				if (status.equals("none")) {
					// Button See History
					driver.findElement(By.xpath("//div[contains(@class, 'x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xg87l8a x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh x1nhvcw1')]")).click();
					System.out.println("See story as username clicked");
					loading = false;
				}
			} catch (InterruptedException e) { 
				System.out.println("No button See story as found"); 
				loading = false;
			}
		}

		/*
		 * 					
					// Numero de historias en el perfil
					int l = ((Long) js.executeScript("return(document.getElementsByClassName('x1ned7t2 x78zum5')[0].childNodes.length)")).intValue();
					System.out.println("Hay un total de "+l+" historias");
		 */
		
		/*
		int nimg = ((Long) js.executeScript("let images = document.getElementsByTagName('img'); return images.length")).intValue();
		System.out.println("Imagenes = "+nimg);
		*/
		
		loading = true;

		// Numero de historias en el perfil = document.getElementsByClassName('x1ned7t2 x78zum5')[0].childNodes.length;
		int l = ((Long) js.executeScript("return(document.getElementsByClassName('x1ned7t2 x78zum5')[0].childNodes.length)")).intValue();
		System.out.println("Hay un total de "+l+" historias");
		
		// Hay que comprobar que siempre empezamos por la primera story, hay situaciones en las que podemos empezar
		// en otro lado.
		while (loading) {
			String currentUrl = driver.getCurrentUrl();
			
			System.out.println("Currenturl ="+currentUrl);
			if (currentUrl.contains("stories")) {
				try {
					Thread.sleep(1000);
					// Averiguamos en que historia estamos
					int sp = ((Long) js.executeScript("let cn = document.getElementsByClassName('x1ned7t2 x78zum5')[0].childNodes; for (let i=0; i< cn.length; i++){ if (cn[i].childNodes.length == 1) return(i); }")).intValue();
					sp +=1;
					
					System.out.println("Estoy en la story "+sp);
					System.out.println("sp="+sp+" l="+l);
					
					// Primero pregunto si para pasar de storie, es un boton o un svg
					String sbutton = "if ( document.getElementsByTagName('button').length > 0){	return true; } else { return false; }";
					boolean isButton = (boolean) js.executeScript(sbutton);
					System.out.println("Hay boton? = "+isButton);
					
					int res = ((Long) js.executeScript("return(document.getElementsByTagName('video').length);")).intValue();
					
					
					if (sp == l) {
						// Cuando llegemos a la ultima historia, tendremos todas las imagenes y videos cacheados
						System.out.println("Estoy en la ultima story, hora de sacar la informacion");
						
						driver.findElements(By.xpath("//*[name()='div' and @class='x6s0dn4 x78zum5 xdt5ytf xl56j7k']")).get(0).click();
						System.out.println("Le hemos dado a pause correctamente");
						
						int networkl = ((Long) js.executeScript("let performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {};  let network = performance.getEntries() || {}; return(network.length);")).intValue();
						System.out.println("Ejecutando el script...");
						
						for(int i=0; i<networkl ;i++) {
							String nscript = "let performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; let network = performance.getEntries() || {}; if ( (network["+i+"].initiatorType == 'fetch') || (network["+i+"].initiatorType == 'img') ){ return(network["+i+"].name)} ";
							
							String storysc = (String) js.executeScript(nscript);
							if (!(storysc == null)) {
								if (storysc.contains("&bytestart=")) {
									int bytestart = storysc.indexOf("&bytestart=");
									storysc = storysc.substring(0, bytestart);
								}
								if (!(arrstorysrc.contains(storysc))) {
									arrstorysrc.add(storysc);
								}		
							}
						}	
						loading = false;
					} else {
						List<WebElement> svgElements = null;
						if (isButton) {
							svgElements = driver.findElements(By.tagName("button"));
						} 
						else if (!isButton) {
							svgElements = driver.findElements(By.tagName("svg"));
						}
						// Recorro los elementos hasta encontrar la flecha ya sea un boton 
						for (WebElement svgElement : svgElements) {
							// Esta variable es muy importante porque variara segun el idioma
							String next = "Siguiente";
							if (svgElement.getAttribute("ariaLabel").equals(next)) {
								svgElement.click();
							}
						}	
					}
				}catch (Exception e) { System.out.println("\n\nError no encuentro la flecha"); e.printStackTrace(); }
			} else {
				loading = false;	
			}			
		}
		System.out.println("Hay un total de "+l+" instagram stories js");
				
		System.out.println("Leyendo las direcciones =");
		for (int i=0; i<arrstorysrc.size(); i++) {
			System.out.println("imagen "+i+" "+arrstorysrc.get(i));
		}
		
	}
}
