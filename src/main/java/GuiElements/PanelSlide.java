	package GuiElements;

import java.awt.Component;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// https://www.youtube.com/watch?v=jkMEsPbghcE
public class PanelSlide extends JPanel {
	
	private Timer timer;
	private Component comExit;
	private Component comShow;
	private List<Component> list;
	
	private int currentShowing;
	private boolean animateRight;
	
	public PanelSlide() {
		list = new ArrayList<>();
		timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				animate();
			}
		});
	}
	
	// Los tres puntos me permiten colocar varios argumentos de la misma variable
	public void init(Component... com) {
		if (com.length > 0) {
			for (Component c : com) {
				System.out.println(" 1 ");
				list.add(c);
				System.out.println(" 2 ");
				c.setSize(getSize());
				System.out.println(" 3 ");
				c.setVisible(false);
				
				System.out.println(" 4 ");
				this.add(c);
				System.out.println(" 5 ");
				}
		}
		/*
		Component show = list.get(0);
		show.setVisible(true);
		show.setLocation(0, 0);
		*/
		
		
	}
	public void show(int index) {
		if (!timer.isRunning() ) {
			if ((list.size() > 2) && (index < list.size() )&&(index != currentShowing) ) {
				comShow = list.get(index);
				comExit = list.get(currentShowing);
				animateRight = index < currentShowing;
				currentShowing = index;
				comShow.setVisible(true);
				
				if(animateRight) {
					comShow.setLocation(-comShow.getWidth(), 0);
				} else {
					comShow.setLocation(getWidth(), 0);
				}	
				timer.start();
			}
		}
	}
	
	private void animate() {
		if (animateRight) {
			if (comShow.getLocation().x < 0) {
				comShow.setLocation(comShow.getLocation().x + 1, 0);
			} else {
				// Stop animation
				comShow.setLocation(0, 0);
				timer.stop();
				comExit.setVisible(false);
			}
		} else {
			if (comShow.getLocation().x > 0) {
				comShow.setLocation(comShow.getLocation().x - 1, 0);
				comExit.setLocation(comExit.getLocation().x - 1, 0);
			} else {
				comShow.setLocation(0, 0);
				timer.stop();
				comExit.setVisible(true);
			}
		}
	}
	
	public static void main(String[] args){
		
	}
}
