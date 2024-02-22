package GuiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class OptionsPanel extends JPanel{
	private GridLayout gl;
	private Color color1 = new Color(112,196,240);
	private Color color2 = new Color(0,149,246);

	public Boton b1;
	public Boton b2;
	public Boton b3;
	
	
	public OptionsPanel() {
		
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		gl = new GridLayout(5,0);
        gl.setHgap(0); // horizontal gap
        gl.setVgap(20); // vertical gap
        setLayout(gl);
        
        b1 = new Boton("Analizar Usuario",color1,color2);
        b1.setEnabled(true);
        System.out.println(b1.isEnabled());
        add(b1);
        
        
	}
	
	public static void main(String[] args) {		
        JFrame frame = new JFrame("JPanel con botones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600,800));

        OptionsPanel op = new OptionsPanel();

        frame.add(op);
        frame.setVisible(true);	
	}
}
