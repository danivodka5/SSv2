package GuiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InsideOptionsPanel extends JPanel {
	
	private GridLayout gl;
	private GridLayout gl2;
    private JCheckBox check1,check2,check3,check4;
    private NewBoton nb1;
    private JFileChooser jfc;
    private JButton jb;
    private JPanel dpanel;
    
    private JPanel test1;
    private JPanel test2;

	
	public InsideOptionsPanel() {
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		gl = new GridLayout(6,0);

		
		GridBagConstraints gbc = new GridBagConstraints();
		dpanel = new JPanel();
		dpanel.setLayout(new GridBagLayout());
		
        gl.setHgap(0); // horizontal gap
        gl.setVgap(0); // vertical gap
        
        setLayout(gl);        

        jb = new JButton();	
        
        check1 = new JCheckBox("Stories");
        check2 = new JCheckBox("Biografia");
        check3 = new JCheckBox("Fotografias");
        check4 = new JCheckBox("Seguidores");
        nb1 = new NewBoton("Descargar");
        
        jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        
        add(check1);
        add(check2);
        add(check3);
        add(check4);
        

        add(dpanel);
        
        
        test1 =  new JPanel();
        test1.setBackground(Color.red);
        test2 = new JPanel();
        test2.setBackground(Color.blue);

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        dpanel.add(test1, gbc1);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dpanel.add(test2, gbc1);
 
        add(nb1);
 
	}
	public static void main(String[] args) {		
        JFrame frame = new JFrame("JPanel con botones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600,800));

        InsideOptionsPanel iop = new InsideOptionsPanel();

        
        frame.add(iop);
        frame.setVisible(true);	
	}
	// return buttons

	public void metodoTesting() {
		
	}
}