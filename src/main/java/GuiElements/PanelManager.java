package GuiElements;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;

public class PanelManager extends JPanel {
	
	private List<JPanel> panelList;
	private CardLayout cl;
	private JPanel mainPanel;
	private int i=1;
	private JPanel emptyPanel = new JPanel();
	public OptionsPanel op;
	
	private int currentPanel;
	private ChromeDriver driver;
	
	private ArrayList<String> arrstorysrc = new ArrayList<>();
	private String user;
	
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
	
	public void addListenerioc(OptionsPanel opp, ChromeDriver driver) {
		this.driver = driver;
		op = opp;
		op.btnaiu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Analizar Interior Usuario
				System.out.println("Iniciamos metodo analizarFollowers");
			
				boolean route = true;
				while (route) {
			        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			        fileChooser.setDialogTitle("Seleccione un directorio");
			        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			            File selectedDirectory = fileChooser.getSelectedFile();
			            System.out.println("Directorio seleccionado: " + selectedDirectory.getAbsolutePath());
			            route = false;
			            
			            
			        }	       
				}
				// Ejecutamos el metodo de descarga driver
				getDatosPerfil();
				findInstagramStories();
				buscarFotos();
		}
		});
		
		op.btnaeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Analizar Exterior Usuario
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
	
	
	public void getDatosPerfil() {
		try {
			// Titulo
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String scriptInfo = "let infon = document.getElementsByClassName('xrvj5dj xpagfr2 xl463y0 x3mjgb7 xdj266r xh8yej3')[0].childNodes[3].childNodes[0].childElementCount; let textinfo = ''; for (let i = 0; i < infon+1; i++) { textinfo = textinfo + '\\n' +document.getElementsByClassName('xrvj5dj xpagfr2 xl463y0 x3mjgb7 xdj266r xh8yej3')[0].childNodes[3].childNodes[0].childNodes[i].textContent; }  return(textinfo);";	
			String res = (String) js.executeScript(scriptInfo);
			System.out.println(res);
			
		}catch (Exception e2) {
			System.out.println("error en "+e2.getMessage());
		}		
	}
	
	private void findInstagramStories() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Comprobamos si existe historia
		int segn = ((Long) js.executeScript("return(document.getElementsByClassName('x6s0dn4 xamitd3 x1lliihq xl56j7k x1n2onr6')[0].childNodes.length)")).intValue();
		
		user = driver.getCurrentUrl().replace("https://www.instagram.com/","");
		driver.get("https://www.instagram.com/stories/"+user);
		System.out.println("Inicio del metodo testHistoriaInstagram usuario: "+user);
		boolean loading = true;
		
		
		
		if (segn == 2) {
			
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
	
	private void buscarFotos() {
		driver.get("https://www.instagram.com/"+user);
		System.out.println("Iniciando metodo buscarFotos()");
		ArrayList<String> srcPhotos = new ArrayList<>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> listaFotos = new ArrayList<>();
		
		boolean condicion = true;
		while (condicion ) {
			try {
				// Lo almaceno todo en un ArrayList

				List<WebElement> plants = driver.findElements(By.xpath("//div[contains(@class, 'x1lliihq x1n2onr6 xh8yej3 x4gyw5p x2pgyrj xbkimgs xfllauq xh8taat xo2y696')]"));
				  
				for(int i=0; i<plants.size(); i++){
				    WebElement a = plants.get(i).findElement(By.xpath("//img[@crossorigin='anonymous']"));
				    
				    if (!arrstorysrc.contains(a.getAttribute("src"))) {
				    	arrstorysrc.add(a.getAttribute("src"));
				    }			  
				    
				}
				
				System.out.println("size = "+plants.size());
				System.out.println(" ");
				// x1lliihq x1n2onr6 xh8yej3 x4gyw5p x2pgyrj xbkimgs xfllauq xh8taat xo2y696
				
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				driver.findElement(By.xpath("//div[@role = 'progressbar']"));
				Thread.sleep(1500);
				System.out.println("Estamos cargando el perfil...");
			} catch (Exception e) {
				e.printStackTrace();
				condicion = false;
			}
		}
		System.out.println("Fin de buscarPerfil\n");
	}
}
