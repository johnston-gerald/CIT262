package jmessenger;

/*
||  Program name:     JTextAreaPanel.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    03/31/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as a Swing JPanel with an embedded
||                    JTextArea and wrapper methods to use the JTextArea
||                    and JTextComponent classes..
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.
import java.util.ArrayList;   // Required for using a ArrayList()
import javax.swing.*;         // Required for Swing widgets.
import javax.swing.text.*;    // Required for wrappers to JTextArea().
//import java.util.*;           // Required for using a Vector().

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class JTextAreaPanel extends JPanel
{
  // -------------------------- Class Variables ------------------------------/

  // Define and initialize constants.
  private final int COL = 10;
  private final int ROW =  1;

  // Define and initialize primitive variable(s).
  private final int vIndex = 0;

  // Define and initialize private Object variable.
  private final ArrayList inMessage = new ArrayList(1); //changed obsolete vector to ArrayList - Gerald

  // ---------------------------------/
  // Define AWT and Swing objects.
  // ---------------------------------/

  // Define a JTextArea.
  private final JTextArea ta = new JTextArea();

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default          JTextAreaPanel()
  ||  protected  Override         JTextAreaPanel(int width,int height)
  */

  // -------------------------------------------------------------------------/

  // Define default constructor.
  protected JTextAreaPanel()
  {
    // Initiate set methods.
    buildPanel(COL,ROW);

  } // End of default constructor.

  // -------------------------------------------------------------------------/

  // Define override constructor.
  protected JTextAreaPanel(int columns,int rows)
  {
    // Initiate set methods.
    buildPanel(columns,rows);

  } // End of override constructor.

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The class must be instantiated from another class instance:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         append()                       protected  String s
  ||  void         buildPanel()                   private    int column
  ||                                                         int row
  ||  int          getColumns()                   protected
  ||  int          getLineCount()                 protected
  ||  int          getLineEndOffset()             protected  int line
  ||  int          getLineOfOffset()              protected  int offset
  ||  int          getLineStartOffset()           protected  int line
  ||  boolean      getLineWrap()                  protected
  ||  void         getMessage()                   protected
  ||  Dimension    getPreferredSize()             protected
  ||  int          getRows()                      protected
  ||  int          getTabSize()                   protected
  ||  boolean      getWrapSytleWord()             protected
  ||  String       getText()                      protected
  ||  boolean      isEditable()                   protected
  ||  void         insert()                       protected  String s
  ||                                                         int position
  ||  boolean      isManagingFocus()              public
  ||  boolean      isTextAreaEmpty()              private
  ||  void         replaceRange()                 protected  String s
  ||                                                         int start
  ||                                                         int end
  ||  void         requestFocus()                 public
  ||  void         setColumns()                   protected  int columns
  ||  void         setColors()                    protected
  ||  void         setColors()                    protected  Color foreground
  ||                                                         Color background
  ||  void         setEditable()                  protected  boolean b
  ||  void         setFont()                      public     Font font
  ||  void         setLineWrap()                  protected  boolean wrap
  ||  void         setMessage()                   protected  Object msg
  ||  void         getPreferredSize()             public     Dimension dim
  ||  void         getPreferredSize()             protected  int width
  ||                                                         int height
  ||  void         setRows()                      protected  int rows
  ||  void         setTabSize()                   protected  int size
  ||  void         setText()                      protected  String s
  ||  void         setWrapStyleWord()             protected  boolean word
  */

  // ------------------- API Component Accessor Methods ----------------------/

  // -------------------------------------------------------------------------/

  // Define an append() wrapper method.
  protected void append(String s)
  {
    // Append a String to the JTextArea.
    ta.append(s);

  } // End of append() wrapper method.

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  private void buildPanel(int columns,int rows)
  {
    // Set panel colors.
    setColors();

    // Sets columns and rows for JPanel.
    setColumns(columns);
    setRows(rows);

    // Set JPanel Layout.
    setLayout(new BorderLayout());

    // Add a JTextArea.
    add(ta,BorderLayout.CENTER);

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define a getColumns() wrapper method.
  protected int getColumns()
  {
    // Get number of JTextArea columns.
    return ta.getColumns();

  } // End of getColumns() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getLineCount() wrapper method.
  protected int getLineCount()
  {
    // Get count of JTextArea lines.
    return ta.getLineCount();

  } // End of getLineCount() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getLineEndOffset() wrapper method.
  protected int getLineEndOffset(int line)
  {
    // Define and intialize return value.
    int retVal = -1;

    // Encapsulate in try-catch because native method may throw an exception.
    try
    {
      // Get line end off set for JTextArea.
      retVal = ta.getLineEndOffset(line);

    } // End of try block.
    catch(BadLocationException e)
    {
        // Catch and throw wrapped exception.
        Throwable throwable = new Throwable(e.getMessage());

    } // End of catch block.

    // Return value.
    return retVal;

  } // End of getLineEndOffset() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getLineOfOffset() wrapper method.
  protected int getLineOfOffset(int offset)
  {
    // Define and intialize return value.
    int retVal = -1;

    // Encapsulate in try-catch because native method may throw an exception.
    try
    {
      // Get offset line number for JTextArea.
      retVal = ta.getLineOfOffset(offset);

    } // End of try block.
    catch(BadLocationException e)
    {
        // Catch and throw wrapped exception.
        Throwable throwable = new Throwable(e.getMessage());

    } // End of catch block.

    // Return value.
    return retVal;

  } // End of getLineOfOffset() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getLineStartOffset() wrapper method.
  protected int getLineStartOffset(int line)
  {
    // Define and intialize return value.
    int retVal = -1;

    // Encapsulate in try-catch because native method may throw an exception.
    try
    {
      // Get line end off set for JTextArea.
      retVal = ta.getLineStartOffset(line);

    } // End of try block.
    catch(BadLocationException e)
    {
        // Catch and throw wrapped exception.
        Throwable throwable = new Throwable(e.getMessage());

    } // End of catch block.

    // Return value.
    return retVal;

  } // End of getLineStartOffset() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getLineWrap() wrapper method.
  protected boolean getLineWrap()
  {
    // Get JTextArea line wrap status.
    return ta.getLineWrap();

  } // End of getLineWrap() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getMessage() method.
  protected void getMessage()
  {
    // If first message, then write message, otherwise append it to message.
    if (isTextAreaEmpty() && !inMessage.isEmpty())
    {
      // Ensure cast-safe operations.
      if (inMessage.get(vIndex) instanceof String)
      {
        // Display received message.
        setText(inMessage.get(vIndex).toString());

      } // End of cast evaluation for String.

    } // End of first message write.
    else
    {
      // If message buffer is not consumed.
      if (!inMessage.isEmpty())
      {
        // Ensure cast-safe operations.
        if (inMessage.get(vIndex) instanceof String)
        {
          // Append message to display and display messages.
          append(inMessage.get(vIndex).toString());

        } // End of cast evaluation for String.

      } // End of if message buffer is consumed.
      else
      {
        // Throw a runtime exception on an attempt to read an empty buffer.
        throw new BufferEmptyException("Empty buffer cannot be read.");

      } // End of else message buffer is not consumed.

    } // End of else to get Message.

    // Consume read message text.
    inMessage.clear();

  } // End of setMessage() method.

  // -------------------------------------------------------------------------/

  // Define a getPreferredSize() wrapper method.
  // Pulic specifier required to override JComponent getPreferredSize().
  @Override
  public Dimension getPreferredSize()
  {
    // Get JTextArea preferred size.
    return ta.getPreferredSize();

  } // End of getPreferredSize() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getRows() wrapper method.
  protected int getRows()
  {
    // Get JTextArea number of rows.
    return ta.getRows();

  } // End of getRows() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getTabSize() wrapper method.
  protected int getTabSize()
  {
    // Get JTextArea table size.
    return ta.getTabSize();

  } // End of getTabSize() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getWrapStyleWord() wrapper method.
  protected boolean getWrapStyleWord()
  {
    // Get JTextArea word wrap style status.
    return ta.getWrapStyleWord();

  } // End of getWrapStyleWord() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a getText() wrapper method.
  protected String getText()
  {
    // If getText() returns a String other than a zero length String.
    if (ta.getText().equals(""))
    {
      // Throw a runtime exception on an attempt to read an empty buffer.
      throw new BufferEmptyException("Empty buffer cannot be read.");

    } // End of if JTextArea contains other than a zero length String.

    // Get JTextArea text.
    return ta.getText();

  } // End of getText() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a insert() wrapper method.
  protected void insert(String s,int position)
  {
    // Insert JTextArea text.
    ta.insert(s,position);

  } // End of insert() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a isEditable() wrapper method.
  public boolean isEditable()
  {
    // Get JTextArea managing focus status.
    return ta.isEditable();

  } // End of isEditable() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a isTextAreaEmpty() wrapper method.
  protected boolean isTextAreaEmpty()
  {
    // Define retun variable.
    boolean retValue = false;

    // Check if JTextArea has an empty String.
    if (ta.getText().length() == 0)  { retValue = true; }

    // Return the boolean value.
    return retValue;

  } // End of isTextAreaEmpty() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a isManagingFocus() wrapper method.
  /*@Override   //commented out deprecated isManagingFocus (it wasn't being used anyway) - Gerald
  public boolean isManagingFocus()
  {
    return KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == ta;

  }*/

  // -------------------------------------------------------------------------/

  // Define a replaceRange() wrapper method.
  protected void replaceRange(String s,int start,int end)
  {
    // Replace JTextArea String text.
    ta.replaceRange(s,start,end);

  } // End of replaceRange() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a requestFocus() wrapper method.
  @Override
  public void requestFocus()
  {
    // Request JTextArea has focus.
    ta.requestFocus();

  } // End of requestFocus() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a setColumns() wrapper method.
  protected void setColumns(int columns)
  {
    // Set columns in JTextArea.
    ta.setColumns(columns);

  } // End of setColumns() wrapper method.

  // -------------------------------------------------------------------------/

  // Define setColors null argument default method.
  protected void setColors()
  {
    // Set colors for JPanel.
    setForeground(Color.white);
    setBackground(Color.lightGray);

  } // End of setColors() null argument default method.

  // -------------------------------------------------------------------------/

  // Define setColors overloaded method.
  protected void setColors(Color foreground,Color background)
  {
    // Set colors for JPanel.
    setForeground(foreground);
    setBackground(background);

  } // End of setColors() overloaded method.

  // -------------------------------------------------------------------------/

  // Define setEditable method.
  protected void setEditable(boolean b)
  {
    // Enable JTextArea text editable.
    ta.setEditable(b);

  } // End of setEditable() method.

  // -------------------------------------------------------------------------/

  // Define a setFont() wrapper method.
  @Override
  public void setFont(Font font)
  {
    // Use try-catch to trap for initialization state where JTextArea has
    // a null String content.
    try
    {
      // Set font for JTextArea.
      ta.setFont(font);

    } // End of try block.
    catch(NullPointerException e) {}

  } // End of setFont() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a setLineWrap() wrapper method.
  protected void setLineWrap(boolean wrap)
  {
    // Set line wrap for JTextArea.
    ta.setLineWrap(wrap);

  } // End of setLineWrap() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a setMessage() method.
  protected void setMessage(Object msg)
  {
    // If a prior message has not been consumed, append to the message.
    if (!inMessage.isEmpty())
    {
      // Ensure cast-safe operations.
      if (inMessage.get(vIndex) instanceof String)
      {
        // Set message.
        inMessage.add(vIndex, inMessage.get(vIndex) + (String) msg); //replace obsolete insertElementAt with .add - Gerald

      } // End of cast evaluation for String.

    } // End of if prior message has not been consumed.
    else
    {
      // Ensure cast-safe operations.
      if (msg instanceof String)
      {
        // Set Object value as message.
        inMessage.add((String) msg);

      } // End of cast evaluation for String.

    } // End of else prior message has been consumed.

  } // End of setMessage() method.

  // -------------------------------------------------------------------------/

  // Define a setPreferredSize() wrapper method.
  // Pulic specifier required to override JComponent getPreferredSize().
  @Override
  public void setPreferredSize(Dimension dim)
  {
    // Set preferred size for JTextArea.
    ta.setPreferredSize(dim);

  } // End of setPreferredSize() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a setPreferredSize() wrapper method.
  public void setPreferredSize(int width,int height)
  {
    // Set preferred size for JTextArea.
    ta.setPreferredSize(new Dimension(width,height));

  } // End of setPreferredSize() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a setRows() wrapper method.
  protected void setRows(int rows)
  {
    // Set number of rows for JTextArea.
    ta.setRows(rows);

  } // End of setRows() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a setTabSize() wrapper method.
  protected void setTabSize(int size)
  {
    // Set size for tabs in JTextArea.
    ta.setTabSize(size);

  } // End of setTabSize() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a setText() wrapper method.
  protected void setText(String s)
  {
    // Set JTextArea text.
    ta.setText(s);

  } // End of setText() wrapper method.

  // -------------------------------------------------------------------------/

  // Define a setWrapStyleWord() wrapper method.
  protected void setWrapStyleWord(boolean word)
  {
    // Set word wrap size for JTextArea.
    ta.setWrapStyleWord(word);

  } // End of setWrapStyleWord() wrapper method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Inner Class -----------------------------/

  // -------------------------- End Inner Class ------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of JTextAreaPanel class.

// ------------------------------- End Class ---------------------------------/