/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package gui;

import actions.ColorPicker;
import actions.EllipseAction;
import actions.LineAction;
import actions.PencilAction;
import actions.RectangleAction;

import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * A frame with tools to draw with.
 * @author Mat Sharff
 * @version 1
 */
@SuppressWarnings("serial")
public class PowerPaintGUI extends JFrame {

    /** The url of the icon to use for the frame. */
    private static final String ICON_URL = "./icons/pencilIcon.png";
    
    /** The drawing panel. */
    private final PaintPanel myPanel;

    /**
     * Constructs a new PowerPaintGUI Frame with a PaintPanel to draw on.
     */
    public PowerPaintGUI() {
        super("TCSS 305 PowerPaint");
        myPanel = new PaintPanel();
    }
    
    /**
     * Creates all the components of the PowerPaint program.
     */
    public void start() {

        final ColorPicker colorPicker = new ColorPicker(myPanel);
        
        final ToolBar toolBar = new ToolBar(colorPicker);
        
        final MenuBar menuBar = new MenuBar(this, myPanel, colorPicker);
        
        
        final Action[] toolActions = {new PencilAction(myPanel),
                                      new LineAction(myPanel),
                                      new RectangleAction(myPanel),
                                      new EllipseAction(myPanel)};
        
        for (final Action action : toolActions) {
            menuBar.createToolsItem(action);
            toolBar.createToolsButton(action);
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set the window Icon
        final ImageIcon windowIcon = new ImageIcon(ICON_URL);
        setIconImage(windowIcon.getImage());
        new BorderLayout();
        
        
        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.SOUTH);
        
        ((AbstractButton) toolBar.getComponent(2)).setSelected(true);
        ((AbstractButton) toolBar.getComponent(2)).doClick();
        
        add(myPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
