package jmessenger;

/*
||  Program name:     JButtonPanel.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    03/23/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as a Swing JPanel with zero initial
||                    instances of JButton.  JButtons are added by calls
||                    to getButton(String s).
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.
import java.util.ArrayList;   // Required for the ArrayList class - Gerald
import javax.swing.*;         // Required for Swing widgets.
//import java.util.*;           // Required for the Vector class.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class JButtonPanel extends JPanel
{
  // -------------------------- Class Variables ------------------------------/

  // Define int variable(s).
  private int bIndex = 0;

  // Define and initialize a default Dimension for JButton(s).
  private Dimension dimension = new Dimension(80,25);

  // Define and initialize a default JButton font that is platform independent.
  private Font buttonFont = new Font(
                              new JButton().getFont().getName(),Font.BOLD,10);

  // ---------------------------------/
  // Define AWT and Swing objects.
  // ---------------------------------/

  // Define a JButton(s).
  private JButton button[] = new JButton[0];

  // Define a JPanel(s).
  private final JPanel panel = new JPanel();

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default          JButtonPanel()
  */

  // -------------------------------------------------------------------------/

  // Define default constructor.
  protected JButtonPanel()
  {
    // Build JButtonPanel.
    buildPanel();

  } // End of default constructor.

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The class must be instantiated from another class instance:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         buildPanel()                   private
  ||  JButton      getButton()                    protected  String s
  ||  Dimension    getDimension()                 protected
  ||  Font         getButtonFont()                protected
  ||  JPanel       getButtonPanel()               protected
  ||  JButton      setButton()                    private    int i
  ||                                                         String s
  ||  void         setButtonArray()               private    JButton b
  ||  void         getButtonDimension()           protected  Dimension dim
  ||  void         getButtonFont()                protected  Font font
  */

  // -------------------------------------------------------------------------/

  // Define buildPanel() method.
  private void buildPanel()
  {
    // Set JPanel Layout.
    setLayout(new BorderLayout());

    // Add a JTextAreaPanel(s).
    add(panel,BorderLayout.CENTER);

  } // End of buildPanel() method.

  // ------------------- API Component Accessor Methods ----------------------/

  // Define accessor methods for JButton(s).
  protected JButton getButton(String s)  { return setButton(bIndex++,s); }

  // -------------------------------------------------------------------------/

  // Define getButtonDimension() method.
  protected Dimension getButtonDimension()  { return dimension; }

  // -------------------------------------------------------------------------/

  // Define getButtonFont() method.
  protected Font getButtonFont()  { return buttonFont; }

  // -------------------------------------------------------------------------/

  // Define setButton() method.
  private JButton setButton(int i,String s)
  {
	// Add an element to JButton array.
	setButtonArray(new JButton(s));

    // Set preferred button size.
    button[i].setPreferredSize(dimension);

    // Set JButton(s) font size.
    button[i].setFont(buttonFont);

    // Add new JButton to JPanel.
    panel.add(button[i]);

    // Return JButton.
    return button[i];

  } // End of setButton() method.

  // -------------------------------------------------------------------------/

  // Define method to set a JButton array.
  private void setButtonArray(JButton b)
  {
    // Define and constructor a local Vector.
    ArrayList v = new ArrayList();  //changed obsolete vector to ArrayList - Gerald
      for (JButton button1 : button) {
          // Add an object to the vector until all lines are added.
          v.add((Object) button1);
      } // End of for-loop to populate the array.

    // Add the new line to the vector.
    v.add((Object) b);

    // Define, size and initialize the String array.
    button = new JButton[v.size()];

    // The copyInto method will use the instanceOf to determine the right
    // subtype and does not require explicit casting.
    v.toArray(button);  //changed obsolete copyInto to toArray - Gerald
    

  } // End of setButtonArray() method.

  // -------------------------------------------------------------------------/

  // Define setButtonDimension() method.
  protected void setButtonDimension(Dimension dim)
  {
    // Return current Dimension.
    dimension = dim;
      for (JButton button1 : button) {
          // Set preferred button size.
          button1.setPreferredSize(dimension);
      } // End of for-loop to reset buttonsizes.

  } // End of setButtonDimension() method.

  // -------------------------------------------------------------------------/

  // Define setButtonFont() method.
  protected void setButtonFont(Font font)
  {
    // Reset JButton Font.
    buttonFont = font;
      for (JButton button1 : button) {
          button1.setFont(buttonFont);
      }

  } // End of setButtonFont() method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Inner Class -----------------------------/

  // -------------------------- End Inner Class ------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of JButtonPanel class.

// ------------------------------- End Class ---------------------------------/