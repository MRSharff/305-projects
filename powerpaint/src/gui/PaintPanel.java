/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package gui;

import actions.MyShapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 * A JPanel to paint on with various tools.
 * @author Mat Sharff
 * @version 1
 */
@SuppressWarnings("serial")
public class PaintPanel extends JPanel {
    
    /** The default color to draw with. */
    private static final Color DEFAULT_COLOR = Color.BLACK;
    
    /** The default window color. */
    private static final Color DEFAULT_WINDOW_COLOR = Color.WHITE;
    
    /** The default size of the PaintPanel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(550, 300);
    
    /** The default grid spacing. */
    private static final int GRID_SPACING = 10;
    
    /** The default grid line thickness. */
    private static final int GRID_THICKNESS = 1;
    
    /** The default stroke width to draw at startup. */
    private static final int DEFAULT_STROKE = 5;
    
    /** Boolean value that determines whether to show (draw) the grid or not. */
    private boolean myGridStatus;
    
    /** A collection of shapes previously drawn. */
    private final Deque<MyShapes> myShapes;

    /** The current shape to draw. */
    private Shape myCurrentShape;
    
    /** The current stroke width to draw with. */
    private int myThickness;
    
    /** The current color to draw with. */
    private Color myColor;
    
    /** The current tool. */
    private MouseInputAdapter myAdapter;
    
    /**
     * Constructs a paint panel with default properties and no shapes to draw.
     */
    public PaintPanel() {
        super();
        myGridStatus = false;
        setPreferredSize(DEFAULT_SIZE);
        myShapes = new ArrayDeque<MyShapes>();
        

        myThickness = DEFAULT_STROKE;
        myColor = DEFAULT_COLOR;
        setBackground(DEFAULT_WINDOW_COLOR);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        //draw previous shapes
        for (final MyShapes aShape : myShapes) {
            g2d.setColor(aShape.getColor());
            g2d.setStroke(new BasicStroke(aShape.getThickness()));
            g2d.draw(aShape.getShape());
        }
        
        //draw shape that is currently being created
        if (myCurrentShape != null) {
            g2d.setPaint(myColor);
            g2d.setStroke(new BasicStroke(myThickness));
            g2d.draw(myCurrentShape);
        }
        
        //draw grid if the grid box is checked
        if (myGridStatus) {
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(GRID_THICKNESS));
            
            //draw vertical lines
            for (int i = GRID_SPACING; i < getWidth(); i += GRID_SPACING)  {
                g2d.drawLine(i, 0, i, getHeight());
            }
            //draw horizontal lines
            for (int i = GRID_SPACING; i < getHeight(); i += GRID_SPACING)  {
                g2d.drawLine(0, i, getWidth(), i);
            }
        }
        
    }
    
    /**
     * Adds a shape to myShapes to be drawn.
     * @param theShape The shape to add
     */
    public void addShape(final Shape theShape) {
        myShapes.add(new MyShapes(theShape, myColor, myThickness));
    }
    
    /** Enables and disables the grid. */
    public void changeGridStatus() {
        myGridStatus ^= true;
    }
    
    
    /** Clears the paint panel of all shapes by clearing the Deque of MyShapes. */
    public void clearShapes() {
        myShapes.clear();
        repaint();
    }

    
    /** 
     * Sets the current shape to be drawn while the mouse is dragged.
     * @param theShape The shape to draw
     */
    public void setCurrentShape(final Shape theShape) {
        myCurrentShape = theShape;
    }
    
    /** 
     * Sets the mouse listeners when a tool is selected.
     * @param theAdapter The adapter (tool) to use.
     */
    public void setMouseAdapter(final MouseInputAdapter theAdapter) {
        removeMouseListener(myAdapter);
        removeMouseMotionListener(myAdapter);
        myAdapter = theAdapter;
        addMouseListener(myAdapter);
        addMouseMotionListener(myAdapter);
    }
    
    /**
     * Sets the stroke width to draw with.
     * @param theThickness the stroke width
     */
    public void setThickness(final int theThickness) {
        myThickness = theThickness;
    }
    
    /**
     * Sets the panels drawing color.
     * @param theColor the color to set
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }

    /** 
     * Gets the paint panels current drawing color.
     * @return the panels current drawing color.
     */
    public Color getColor() {
        return myColor;
    }
    
}
