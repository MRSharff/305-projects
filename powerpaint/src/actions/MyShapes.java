/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package actions;

import java.awt.Color;
import java.awt.Shape;

/**
 * A class to record a shape and  it's color and thickness properties.
 * @author Mat Sharff
 * @version 1
 */
public class MyShapes {
    
    /** A shape. */
    private final Shape myShape;
    
    /** The color of the shape. */
    private final Color myColor;
    
    /** The stroke size of the shape. */
    private final int myThickness;
    
    /**
     * Constructor that creates a new myShape.
     * @param theShape is a shape
     * @param theColor is the color of the shape
     * @param theThickness is the stroke size of the shape
     */
    public MyShapes(final Shape theShape, final Color theColor, final int theThickness) {
        myShape = theShape;
        myColor = theColor;
        myThickness = theThickness;
    }
    
    /**
     * Returns a shape.
     * @return returns myShape
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Returns a color.
     * @return returns myColor
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Returns the stroke width.
     * @return returns myThickness
     */
    public int getThickness() {
        return myThickness;
    }
    
}