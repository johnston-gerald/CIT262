package jmessenger;

/*
||  Program name:     StackTraceDialog.java
||  Created by:       Michael McLaughlin
||  Creation date:    08/26/01
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Customized Swing dialog for displaying a stack
||                    trace to the application.
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.
import java.awt.event.*;      // Required for AWT events.
import javax.swing.*;         // Required for Swing widgets.

// ------------------------------ Begin Class --------------------------------/

// Define class.
public class StackTraceDialog extends JDialog implements ActionListener
{
  // Define and initialize a default Toolkit.
  private final Toolkit tk = Toolkit.getDefaultToolkit();

  // Define and initialize screen Dimension(s).
  private final Dimension dim = tk.getScreenSize();

  // Define and initialize Container(s).
  private final Container c = getContentPane();

  // Define and intialize phsyical size dimensions.
  int left              = 0;
  int top               = 0;
  int buttonWidth       = 50;
  int buttonHeight      = 25;
  int displayAreaWidth  = dim.width - 400;
  int displayAreaHeight = dim.height - 400;
  int offsetMargin      = 20;

  // The dialog width and height are derived from base objects.
  int dialogWidth  = displayAreaWidth + offsetMargin;
  int dialogHeight = displayAreaHeight + buttonHeight + (3 * offsetMargin);

  // Define String(s).
  private final String eStackTrace;

  // ---------------------------------/
  // Define AWT and Swing objects.
  // ---------------------------------/

  // Define and initialize JButton(s).
  private final JButton okButton      = new JButton("OK");

  // Define and initialize JTextArea(s).
  private final JTextArea displayArea = new JTextArea();

  // Define Box(s).
  private Box displayBox;

  // ---------------------------- Constructor --------------------------------/

  // The class requires an owning frame and cannot be called from a default
  // constructor because none is defined in the JDialog class.
  public StackTraceDialog(Frame owner,String title,boolean modal)
  {
    // Call the constructor for JDialog.
    super(owner,modal);

    // The title argument is treated as overloaded because the stack trace
    // String is passed into an instance of this class by using it.  Then,
    // the String is assigned to a class level variable.
    eStackTrace = title;

    // Call the buildDialogBox() method.
    buildDialogBox();

  } // End of StackTraceDialog constructor.

  // --------------------------- Begin Methods -------------------------------/

  // Method to build the dialog box for help.
  private void buildDialogBox()
  {
    // Set the JDialog window properties.
    setTitle("Stack Trace Detail");
    setResizable(false);
    setSize(dialogWidth,dialogHeight);

    // Append the stack trace output to the display area.
    displayArea.append(eStackTrace);

    // Create horizontal and vertical scrollbars for box.
    displayBox = Box.createHorizontalBox();
    displayBox = Box.createVerticalBox();

    // Add a JScrollPane to the Box.
    displayBox.add(new JScrollPane(displayArea));

    // Define behaviors of container.
    c.setLayout(null);
    c.add(displayBox);
    c.add(okButton);

    // Set scroll pane bounds.
    displayBox.setBounds((dialogWidth / 2) - ((displayAreaWidth / 2 ) + 2),
                         (top + (offsetMargin / 2)),
                          displayAreaWidth,displayAreaHeight);

    // Set the behaviors, bounds and action listener for the button.
    okButton.setBounds((dialogWidth / 2) - (buttonWidth / 2),
                       (displayAreaHeight + offsetMargin),
                        buttonWidth,buttonHeight);

    // Set the font to the platform default Font for the object with the
    // properties of bold and font size of 11.
    okButton.setFont(
      new Font(okButton.getFont().getName(),Font.BOLD,11));

    // The class implements the ActionListener interface and therefore
    // provides an implementation of the actionPerformed() method.  When a
    // class implements ActionListener, the instance handler returns an
    // ActionListener.  The ActionListener then performs actionPerformed()
    // method on an ActionEvent.
    okButton.addActionListener(this);

    // Set the screen and display dialog window in relation to screen size.
    setLocation((dim.width / 2) - (dialogWidth / 2),
                (dim.height / 2) - (dialogHeight / 2));

    // Display JDialog.
    //show();
    setVisible(true);   //replaced deprecated show() with setVisible(true) - Gerald

  } // End of buildDialogBox method.

  // Class listener based on implementing ActionListener.
  @Override
  public void actionPerformed(ActionEvent e)
  {

    this.setVisible(false);
    this.dispose();

  } // End of actionPerformed method.

  // ---------------------------- End Methods --------------------------------/

} // End of StackTraceDialog class.

// ------------------------------- End Class ---------------------------------/