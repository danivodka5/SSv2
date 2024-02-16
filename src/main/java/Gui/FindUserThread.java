package Gui;

import java.awt.BorderLayout;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPanel;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import GuiElements.LoadingPanel;

public class FindUserThread implements Runnable {

	private Thread worker;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private ChromeDriver driver;
	private String user;
	private LoadingPanel lp; 

	
	public FindUserThread(ChromeDriver cd, String user,LoadingPanel lp) {
		driver = cd;
		this.user = user;
		this.lp = lp;
	}
	
	public void start() {
		worker = new Thread(this);
		worker.start();
	}
	public void stop() {
		running.set(false);
	}
	public void join() {
		try { worker.join(); } catch (InterruptedException e) {  e.printStackTrace(); }
	}
	
	@Override
	public void run() {
		lp.setLoadingPanel();
		
		driver.get("https://www.instagram.com/"+user);
		System.out.println("Inicio del metodo ejecutarBusqueda() usuario: "+user);
		System.out.println("Fin del metodo");
		
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

		boolean userExists = (boolean) js.executeScript("const userl = document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0]; if (typeof userl !== 'undefined') { return(false); } else { return (true); }");
		lp.setLoadingPanel();
		if (userExists) {
			System.out.println("El usuario existe <3");
			lp.setLoadingPanelNull();
			
			// Mostrar Panel de Opciones de usuario en este caso
		} else {
			String adv = (String) js.executeScript("return document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0].textContent;");
			System.out.println("adv ="+adv);
			lp.setUserNotFound(adv);
		}
		
	}
}


