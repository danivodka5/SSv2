package GuiElements;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel{
	
	private GridLayout gl;
	private Color color1 = new Color(112,196,240);
	private Color color2 = new Color(0,149,246);

	public NewBoton btnaiu;
	public NewBoton btnaeu;
	private CardLayout cl;
	
	public OptionsPanel() {
		this.cl = cl;
		
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		gl = new GridLayout(7,0);
        gl.setHgap(0); // horizontal gap
        gl.setVgap(20); // vertical gap
        setLayout(gl);        

        btnaiu = new NewBoton("Analizar Interior Usuario");
        btnaeu = new NewBoton("Analizar Exterior Usuario");
        
        add(btnaiu);
        add(btnaeu);
       
	}
	public static void main(String[] args) {		
        JFrame frame = new JFrame("JPanel con botones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600,800));

        OptionsPanel op = new OptionsPanel();

        frame.add(op);
        frame.setVisible(true);	
	}
	public NewBoton getFirstButton() {
		return btnaiu;
	}
	
}
