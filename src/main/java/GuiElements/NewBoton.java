package GuiElements;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;

public class NewBoton extends JButton{
	
	
	private PropertyChangeSupport propertySupport;
	private Shape shape;	
	private Color blockedColor = new Color(75,180,248);
	private Color unblockedColor = new Color(0,149,246);
	private Color HoverColor = new Color(0,116,206);
	
	private boolean blocked = false;
	private boolean unblocked = true;
	private boolean hover = false;
		
	public NewBoton(String text) {
		loadProperties(text);
		
	}
	
	public void loadProperties(String text) {
		setContentAreaFilled(false);	// Elimina Color relleno
		setRolloverEnabled(false);		// Elimina el glow del boton	
		setText(text);					// Establece el titulo del boton
		setFocusPainted(false);
		setBorderPainted(false);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 20));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	System.out.println("holi");
		    	hover = true;
		    	repaint();
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	hover = false;
		    	repaint();
		    }
		});
		
		repaint();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		if (blocked) {
	    	g.setColor(blockedColor);
		} else if (unblocked) {
			if (hover) {
				g.setColor(HoverColor);
			} else {
				g.setColor(unblockedColor);
			}
		} 

        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 18, 18);
        super.paintComponent(g);
   }
	
	public void setBlocked(boolean b) {
		boolean before = blocked;
		blocked = b;	
		
		propertySupport.firePropertyChange(null);
	}
}
