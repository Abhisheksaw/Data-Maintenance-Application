/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.gui.AboutAppSwing;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import javax.swing.JTextArea;

/**
 *
 * @author hp
 */
public class TextArea extends JTextArea {
    public TextArea() {
        setEditable(false);
        setFocusable(false);
        setWrapStyleWord(true);
        setLineWrap(true);
    }

    @Override
    public synchronized void addMouseListener(MouseListener ml) {
    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener ml) {
    }

    @Override
    public synchronized void addMouseWheelListener(MouseWheelListener ml) {
    }

    @Override
    public void addNotify() {
    }
}
