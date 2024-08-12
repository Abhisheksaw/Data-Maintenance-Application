/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.gui.swing;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 *
 * @author hp
 */
public class PanelTransparent extends JComponent{
    public float getAlpha() {
        return alpha;
    }
    
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }
    
    private float alpha;
    
    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        int x[] = {0, getWidth(), getWidth() - 100, 0};
        int y[] = {0, 0, getHeight(), getHeight()};
        g2.fillPolygon(x, y, x.length);
        g2.dispose();
        super.paint(grphcs);
    }
}
