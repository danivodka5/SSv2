package GuiElements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Gui.InstagramLoginGui;



public class SearchGuiIcon extends JPanel{

	private Image image;
	private boolean animation;
	
	int x = 20;
	int y = 20;
	
	// Constructor Driver

	
	// Constructor Testing
	public SearchGuiIcon() {
		setBackground(null);
		//setBackground(new Color(0, 64, 0));
		setPreferredSize(new Dimension(60,60));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		setBorder(new RoundedLineBorder(Color.BLACK, 2, x, y));
		
		ImageIcon icon = new ImageIcon(InstagramUserGuiSearchPanel.class.getResource("/Images/search-icon-empty.png"));
		image = icon.getImage(); 
      		
        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		animation = true;
                repaint();
        	} 
        	@Override
        	public void mouseExited(MouseEvent e) {
        		animation = false;
                repaint();
        	} 
        });
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, x, y);
      
        // Draw the image on the panel 
        if (animation) {
            g.drawImage(image, 14, 13, getWidth()-30, getHeight()-30, this);  
            setForeground(new Color(211,211,211));
        } else {
            g.drawImage(image, 17, 16, getWidth()-35, getHeight()-35, this);
            setForeground(Color.white);
        }
    }	
}
