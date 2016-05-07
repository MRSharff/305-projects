/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package gui;

import actions.ColorPicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A menu bar that holds window actions, drawing options, tools, and help dialog.
 * @author Mat Sharff
 * @version 1
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
    
    /** The window title. */
    private static final String ABOUT_APP = "TCSS 305 PowerPaint\nAutumn 2014";
    
    /** The sliders numbered tick interval. */
    private static final int SLIDER_MAJOR_SPACING = 5;
    
    /** The sliders small tick interval. */
    private static final int SLIDER_MINOR_SPACING = 1;
    
    /** The max value of the slider. */
    private static final int SLIDER_MAX = 20;
    
    /** The minimum value of the slider. */
    private static final int SLIDER_MIN = 0;
    
    /** The sliders default value at startup. */
    private static final int SLIDER_DEFAULT = 5;
    
    /** The button group for tools. */
    private final ButtonGroup myButtonGroup;

    /** The Tools menu to add the button group of tools and color button to. */
    private final JMenu myToolsMenu;

    /** The PaintPanel to perform actions on.  */
    private final PaintPanel myPanel;
    
    /**
     * Constructs a menu bar with nothing in the tools menu.
     * @param theFrame is the frame to attach it to
     * @param thePanel is the drawing panel
     * @param theAction is the action to attach to the color button
     */
    public MenuBar(final JFrame theFrame,
                   final PaintPanel thePanel,
                   final ColorPicker theAction) {
        super();
        myPanel = thePanel;
        myButtonGroup = new ButtonGroup();
        myToolsMenu = new JMenu("Tools");
        setup(theFrame, theAction);
    }
    
    /**
     * Sets up the menu bar.
     * @param theFrame the frame to attach menu bar to
     * @param theAction the action to attach to the color button
     */
    private void setup(final JFrame theFrame, final ColorPicker theAction) {
        
        myToolsMenu.setMnemonic(KeyEvent.VK_T);
        createFileMenu(theFrame);
        createOptionMenu();
        
        final JMenuItem colorButton = new JMenuItem(theAction);
        colorButton.setMnemonic(KeyEvent.VK_C);
        colorButton.setIcon(theAction.getIcon());
        myToolsMenu.add(colorButton);
        myToolsMenu.addSeparator();
        
        add(myToolsMenu);
        
        createHelpMenu(theFrame);
    }
    
    /**
     * Creates a file menu and adds a clear button and exit button.
     * @param theFrame the frame to exit
     */
    private void createFileMenu(final JFrame theFrame) {
        final JMenu fileMenu = new JMenu("File");
        
        
        final JMenuItem clearButton = new JMenuItem("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                //Clear Button function goes here
                myPanel.clearShapes();
            }
        });
        
        final JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {

                theFrame.dispatchEvent(new WindowEvent(theFrame,
                                                       WindowEvent.WINDOW_CLOSING));
            }
        });
        
        //Set Mnemonics
        fileMenu.setMnemonic(KeyEvent.VK_F);
        clearButton.setMnemonic(KeyEvent.VK_C);
        exitButton.setMnemonic(KeyEvent.VK_X);
        
        add(fileMenu);
        fileMenu.add(clearButton);
        fileMenu.addSeparator();
        fileMenu.add(exitButton);
    }
    
    /**
     * Adds tool buttons to the Tool menu.
     * @param theAction The action to add to the tool button
     */
    public void createToolsItem(final Action theAction) {
        final JRadioButtonMenuItem newButton = new JRadioButtonMenuItem(theAction);
        
        myToolsMenu.add(newButton);
        myButtonGroup.add(newButton);
    }
    
    /** Creates an options menu. */
    public void createOptionMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        
        final JCheckBoxMenuItem gridToggle = new JCheckBoxMenuItem("Grid");
        
        gridToggle.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                //Grid toggle function goes here
                myPanel.changeGridStatus();
                myPanel.repaint();
                //firePropertyChange("grid", gridToggle, gridToggle);
            }
        });
        
        final JMenu thicknessMenu = new JMenu("Thickness");
        
        final JSlider thicknessSlider = new JSlider();
        
        //set Mnemonics
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        gridToggle.setMnemonic(KeyEvent.VK_G);
        
        //set slider properties
        //thicknessSlider.addChangeListener(new ThicknessAction());
        thicknessSlider.setMajorTickSpacing(SLIDER_MAJOR_SPACING);
        thicknessSlider.setMinorTickSpacing(SLIDER_MINOR_SPACING);
        thicknessSlider.setMinimum(SLIDER_MIN);
        thicknessSlider.setMaximum(SLIDER_MAX);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);
        thicknessSlider.setValue(SLIDER_DEFAULT);
        
        thicknessSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent theEvent) {
                myPanel.setThickness(thicknessSlider.getValue());
            }
        });
        
        optionsMenu.add(gridToggle);
        thicknessMenu.add(thicknessSlider);
        optionsMenu.add(thicknessMenu);
        add(optionsMenu);
    }
    
    /**
     * Creates a help menu.
     * @param theFrame the frame to attach the dialog frame to
     */
    public void createHelpMenu(final JFrame theFrame) {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        add(helpMenu);
        
        final JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        
        helpMenu.add(aboutItem);
        
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null,
                                              ABOUT_APP,
                                              "About",
                                              JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}