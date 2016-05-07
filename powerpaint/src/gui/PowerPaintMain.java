/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package gui;

import java.awt.EventQueue;

/**
 * The driver for the PowerPaint program.
 * @author Mat Sharff
 * @version 1
 */
public final class PowerPaintMain {
    
    /**
     * The driver should not be instantiated.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }

    /**
     * Greats a new GUI and starts the program.
     * @param theArgs an array of command line arguments
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final PowerPaintGUI gui = new PowerPaintGUI();
                gui.start();
            }
        });
    }
}
