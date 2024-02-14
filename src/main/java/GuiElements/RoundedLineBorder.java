package GuiElements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.LineBorder;

public class RoundedLineBorder extends LineBorder {
	
    private int arcWidth;
    private int arcHeight;

    public RoundedLineBorder(Color color, int thickness, int arcWidth, int arcHeight) {
        super(color, thickness);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int borderThickness = getThickness();
        return new Insets(borderThickness, borderThickness, borderThickness, borderThickness);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Color oldColor = g.getColor();
        int borderThickness = getThickness();

        g.setColor(getLineColor());
        for (int i = 0; i < borderThickness; i++) {
            g.drawRoundRect(x + i, y + i, width - i * 2 - 1, height - i * 2 - 1, arcWidth, arcHeight);
        }

        g.setColor(oldColor);
    }
}
