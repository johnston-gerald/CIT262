package jmessenger;

/*
||  Program name:     JMessagingFrame.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    04/10/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a controlling JFrame class from
||                    the static main() method.  Up to arguments are used
||                    from the command-line.  They are used as width and
||                    height values; however, only the width has any real
||                    impact because this currently implements a simple
||                    BorderLayout().  If modified to a GridBagLayout()
||                    the height parameter becomes meaningful and
||                    manageable.
||  Program purpose:  Designed as a Swing JFrame with a two instances
||                    of JTextAreaPanel and one of JButtonPanel.
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.
import java.awt.event.*;      // Required for AWT events.
import java.io.*;             // Required for Java streams.
import javax.swing.*;         // Required for Swing widgets.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class JMessagingFrame extends JFrame {
  // -------------------------- Class Variables ------------------------------/

  // Define and initialize boolean(s).
  private boolean debugEnabled = false;
  private boolean fileOpen     = false;

  // Define and initialize int JFrame constants.
  private final int END_X = 530;
  private final int END_Y = 460;

  // Define and initialize default column and row constants.
  private final int COL  = 30;
  private final int ROW = 10;

  // Define and initialize a default Dimension for window frame sizing.
  private Dimension dim;

  // Define String(s).
  private String messageString;
  private String eString;

  // Toolkit is used in the sizing of the window.
  private final Toolkit tk = Toolkit.getDefaultToolkit();

  // Define Java IO object(s).
  private File   fileName  = new File("");
  private final File[] fileNames = new File[0];

  // Define Java streams.
  private BufferedReader input;
  private BufferedWriter output;
  private PrintWriter ePrintWriter;
  private StringWriter eStringWriter;

  // ---------------------------------/
  // Define AWT and Swing objects.
  // ---------------------------------/

  // Define JButton(s).
  private JButton pushButton;
  private JButton pullButton;

  // Menu bar.
  private final JMenuBar menuBar = new JMenuBar();

  // Menus.
  private final JMenu file     = new JMenu("File");
  private final JMenu settings = new JMenu("Settings");
  private final JMenu help     = new JMenu("Help");


  // Menu items.
  private final JMenuItem menuItemOpen       = new JMenuItem("Open");
  private final JMenuItem menuItemSave       = new JMenuItem("Save");
  private final JMenuItem menuItemSettings   = new JMenuItem("Connection Settings");
  private final JMenuItem menuItemHelp       = new JMenuItem("About");

  // Define JScrollPane(s).
  private JScrollPane scrollPane;

  // Define a JTextAreaPanel(s).
  private JTextAreaPanel content;
  private JButtonPanel   buttonPanel;

  private final Settings mSettings = new Settings();
  
  // Used to actually connect to database 
  private String mUrl      = new String();
  private String mPassword = new String();

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default          JMessagingFrame()
  ||  protected  Override         JMessagingFrame(int width,int height)
  */

  // -------------------------------------------------------------------------/

  // Define default constructor.
  protected JMessagingFrame()
  {
    // Initiate set methods.
    buildFrame(END_X,END_Y);

  } // End of default constructor.

  // -------------------------------------------------------------------------/

  // Define default constructor.
  protected JMessagingFrame(int width,int height)
  {
    // Initiate set methods.
    buildFrame(setMinimumWidth(width),setMinimumHeight(height));

  } // End of default constructor.

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The static main instantiates a test instance of the class:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         buildButtons()                 private
  ||  void         buildButtonActionListeners()   private
  ||  void         buildFrame()                   private    int width
  ||                                                         int height
  ||  JMenuBar     buildMenu()                    private
  ||  void         buildMenuActionListeners()     private
  ||  boolean      getDebugEnabled()              private
  ||  void         getMessage()                   private    JScrollPane in
  ||  void         getStackTraceDialog()          private
  ||  void         openFile()                     private
  ||  void         resetExceptionString()         private
  ||  void         setDebugEnabled()              private    boolean state
  ||  void         setExceptionPane()             private    String msg
  ||  void         setExceptionString()           private    Throwable eThrow
  ||  void         setMessage()                   private    JScrollPane out
  ||                                                         JScrollPane in
  ||  void         setMinimumHeight()             protected  int height
  ||  void         setMinimumWidth()              protected  int width
  ||  JScrollPane  setScrollPaneProperties()      private    JScrollPane pane
  */

  // ------------------- API Component Accessor Methods ----------------------/

  // Build JButtons.
  protected void buildButtons() {
    // Define and initialize JButton(s).
    pushButton = buttonPanel.getButton("Push");
    pullButton = buttonPanel.getButton("Pull");
  } // End of setMessage() method.

  // -------------------------------------------------------------------------/

  // Method builds button action listeners.
  private void buildButtonActionListeners()
  {
    /*
    || Section adds action listeners for buttons:
    || ==========================================
    ||  - pushButton
    ||  - pullButton
    */

    // Send button.
    pushButton.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Send message.
          setMessage(content, content);

        } // End of actionPerformed() method.
      }); // End of sendButton action listener.

    // Receive button.
    pullButton.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Receive message.
          getMessage(content);

        } // End of actionPerformed() method.
      }); // End of receiveButton action listener.

  } // End of buildButtonActionListeners() method.

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  private void buildFrame(int width, int height)
  {
    // Set JFrame title.
    setTitle("Communicator");
    
    mSettings.init();

    // Initialize and set policies for JScrollPane(s).
    scrollPane = setScrollPaneProperties(new JScrollPane(content = new JTextAreaPanel(COL,ROW)));

    // Enable editability of receiver JTextAreaPanel.
    content.setEditable(true);

    // Initialize JButtonPanel.
    buttonPanel = new JButtonPanel();

    // Define and initialize JButton(s).
    buildButtons();

    // Set panel size.
    setSize(width,height);

    // Set JPanel Layout.
    getContentPane().setLayout(new BorderLayout());

    // Add a JTextAreaPanel(s).
    getContentPane().add(scrollPane,BorderLayout.NORTH);
    getContentPane().add(buttonPanel,BorderLayout.SOUTH);

    // Build JMenuBar.
    buildMenu();

    // Build JButton action listeners.
    buildButtonActionListeners();

    // Set the screen and display dialog window in relation to screen size.
    dim = tk.getScreenSize();
    setLocation((dim.width / 100),(dim.height / 100));

    // --------------------- Begin Window ActionListener ---------------------/

    // Add window listener to the frame.
    addWindowListener(new WindowAdapter()
    {
      @Override
      public void windowClosing(WindowEvent closingEvent)
      {
        // Exit on window frame close.
        System.exit(0);

      } // End of windowClosing() method.
    }); // End of addWindowListener() method for JFrame.

    // Set resizeable window off.
    setResizable(false);

    // Display the JFrame to the platform window manager.
    //show(); //removed to fix
    setVisible(true); //added to fix

  } // End of buildFrame() method.

  // -------------------------------------------------------------------------/

  // Method builds menu components.
  private JMenuBar buildMenu()
  {
    // Add menus to the menu bar.
    menuBar.add(file);
    menuBar.add(settings);
    menuBar.add(help);

    // Set mnemonics for menu selections.
    file.setMnemonic    ('F');
    settings.setMnemonic('S');
    help.setMnemonic    ('H');

    // Menu items for file menu.
    file.add(menuItemOpen);
    file.addSeparator();
    file.add(menuItemSave);

    // Menu items for settings menu.
    settings.add(menuItemSettings);
    
    // Set mnemonics for menu item selections for file menu.
    menuItemOpen.setMnemonic('O');
    menuItemSave.setMnemonic('S');

    // Set mnemonics for data item selections for file menu.
    menuItemSettings.setMnemonic('C');

    // Menu items to help menu.
    help.add(menuItemHelp);

    // Set mnemonics for menu item selections for edit menu.
    menuItemHelp.setMnemonic('A');

    // Build menu action listeners.
    buildMenuActionListeners();

    // Set the menu bar in the frame and return menu bar.
    setJMenuBar(menuBar);

    // Return JMenuBar
    return menuBar;

  } // End of buildMenu() method.

  // -------------------------------------------------------------------------/

  // Method builds menu action listeners.
  private void buildMenuActionListeners()
  {
    /*
    || Section adds action listeners to window frame menus:
    || ===================================================
    ||  - Menu file:
    ||    ---------
    ||    - menuItemSend
    ||    - menuItemReceive
    ||    - menuItemExit
    ||
    ||  - Menu edit:
    ||    ---------
    ||    - menuItemHelp
    */

    // ---------------------------------/
    // Menu item listeners to file menu.
    // ---------------------------------/

    menuItemOpen.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Open file.
          openFile();

        } // End of actionPerformed method.
      }); // End of menuItemOpen action listener.

    menuItemSave.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Save current sender JTextAreaPanel String as current file.
          saveAsFile();

        } // End of actionPerformed method.
      }); // End of menuItemSave action listener.

    // ---------------------------------/
    // Menu item listeners to settings menu.
    // ---------------------------------/
    
    menuItemSettings.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mSettings.setVisible(true);
            }
        });

    // ---------------------------------/
    // Menu item listeners to help menu.
    // ---------------------------------/
    
    // Add menu item action listener for help menu.
    menuItemHelp.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Call inner class help handler.
            HelpHandler helpHandler = new HelpHandler(JMessagingFrame.this,true);

        } // End of actionPerformed() method.
      }); // End of menuItemHelp action listener.

  } // End of buildMenuActionListeners() method.

  // -------------------------------------------------------------------------/

  // Method to closeFile().
  private void closeFile()
  {
//    // Dismiss file reference.
//    fileName = new File("");
//
//    // If JTextAreaPanel contains text.
//    if (!sender.isTextAreaEmpty())
//    {
//      // Clear display area.
//      sender.replaceRange(null,0,sender.getText().length());
//
//    } // End of if check if JTextAreaPanel contains text.

  } // End of closeFile() method.

  // -------------------------------------------------------------------------/

  // Method to get the debug state.
  private boolean getDebugEnabled()
  {
    // Return current debug state.
    return debugEnabled;

  } // End of setDebugEnabled() method.

  // -------------------------------------------------------------------------/

  // Define a getExceptionString() method.
  private String getExceptionString()
  {
    // Return the current exception String.
    return eString;

  } // End of getExceptionString() method.

  // -------------------------------------------------------------------------/

  // Define a getMessage() method.
  private void getMessage(JTextAreaPanel in)
  {
    // Try to get incoming message.
    try
    {
      // Receive message text.
      in.getMessage();

    } // End of try to get message.
    catch (BufferEmptyException e)  // Catch BufferEmptyException.
    {
      // Capture the stack trace into a String.
      setExceptionString(e.fillInStackTrace());

      // Pass message to the JOptionPane manager.
      setExceptionPane("There is nothing in the buffer to receive.\n\n" +
                       "Do you want to see the stack trace?");

    } // End of catch on BufferEmptyException.

  } // End of getMessage() method.

  // -------------------------------------------------------------------------/

  // Get the stack trace dialog event.
  private void getStackTraceDialog()
  {
      // Create an instance of StackTraceDialog.
      StackTraceDialog stackTraceDialog = new StackTraceDialog(JMessagingFrame.this,getExceptionString(),true);

    // Disposing of the JDialog will leave a value in the class instance
    // exception String.  This resets the value to a zero length String.
    resetExceptionString();

  } // End of getStackTraceDialog() method.

  // -------------------------------------------------------------------------/

  // Method to newFile().
  private void newFile()
  {
    // Close any open file(s).
    closeFile();

  } // End of newFile() method.

  // -------------------------------------------------------------------------/

  // Method to open a file for sequential read-only.
  private void openFile()
  {
    // Define local primitive(s).
    String contents = new String();

    // If a file is open, prompt to save the file before dismissing content
    // and reference.
    if (!file.toString().equals(""))
    {
      // Close the existing file.
      closeFile();

    } // End of if file not null or not equal to a zero String.

    // Open a file.
    fileName = FileIO.findFile(this);

    // If a file name is returned.
    if (fileName != null)
    {
      // Read file.
      contents = FileIO.openFile(this,fileName);

      // Verify that the JTextAreaPanel is empty.
      if (content.isTextAreaEmpty())
      {
        // Set JTextAreaPanel.
        content.setText(contents);

      } // End of if JTextAreaPanel is empty.
      else
      {
        // Reset JTextAreaPanel by replacing the range.
        content.replaceRange(contents,0,content.getText().length());

      } // End of else JTextAreaPanel is not empty.

      // Set file open flag to true.
      fileOpen = true;

    } // End of if a fileName is selected.

  } // End of openFile method.

  // -------------------------------------------------------------------------/

  // Get exception string with current exception content.
  private void resetExceptionString()
  {
    // Reset the exception string to a string of zero length.
    eString = new String();

  } // End of resetExceptionString() method.

  // -------------------------------------------------------------------------/

  // Method to saveAsFile().
  private void saveAsFile()
  {
    // Set a file.
    fileName = FileIO.nameFile(this);

    // If a file name is selected.
    if (fileName != null)
    {
      // Try block to throw a custom exception.
      try
      {
        // Save file.
        FileIO.saveFile(this,fileName,content.getText());

      } // End of try to get message.
      catch (BufferEmptyException e)  // Catch InvalidFileReference.
      {
        // Capture the stack trace into a String.
        setExceptionString(e.fillInStackTrace());

        // Pass message to the JOptionPane manager.
        setExceptionPane("There is nothing in the sender panel to write.\n\n" +
                         "Do you want to see the stack trace?");

      } // End of catch on BufferEmptyException.

    } // End of if a fileName is selected.

  } // End of saveAsFile() method.

  // -------------------------------------------------------------------------/

  // Method to saveFile().
  private void saveFile()
  {
    // If a file name is returned.
    if (fileName != null)
    {
      // Try block to throw a custom exception.
      try
      {
        // Save file.
        FileIO.saveFile(this,fileName,content.getText());

      } // End of try to get message.
      catch (BufferEmptyException e)  // Catch InvalidFileReference.
      {
        // Capture the stack trace into a String.
        setExceptionString(e.fillInStackTrace());

        // Pass message to the JOptionPane manager.
        setExceptionPane("There is nothing in the sender panel to write.\n\n" +
                         "Do you want to see the stack trace?");

      } // End of catch on BufferEmptyException.

    } // End of if a fileName is selected.

  } // End of saveFile() method.

  // -------------------------------------------------------------------------/

  // Method to set the debug state.
  private void setDebugEnabled(boolean enabledState)
  {
    // Set debugEnabled to other state.
    if (debugEnabled != enabledState)
    {
      // Assign the opposite state.
      debugEnabled = enabledState;

    } // End of if debug state is equal to argument.

  } // End of setDebugEnabled() method.

  // -------------------------------------------------------------------------/

  private void setExceptionPane(Object msg)
  {
    // If debug is enabled display dialog.
    if (debugEnabled)
    {
      // Display message and retrieve selected value.
      int result = JOptionPane.showConfirmDialog(
                     this,msg,"Information",JOptionPane.YES_NO_OPTION);

      // Display message and retrieve selected value.
      switch (result)
      {
        case JOptionPane.CLOSED_OPTION:
             resetExceptionString();
             break;

        case JOptionPane.YES_OPTION:
             getStackTraceDialog();
             break;

        case JOptionPane.NO_OPTION:
             resetExceptionString();
             break;

      } // End of result evaluation switch.

    } // End of if debugEnabled evaluation.
    else
    {
      // Disgard the error and reset the class instance exception String.
      // This resets the value to a zero length String.
      resetExceptionString();

    } // End of else debugEnabled evaluation.

  } // End of setExceptionPane() method.

  // -------------------------------------------------------------------------/

  // Set exception string with current exception content.
  protected void setExceptionString(Throwable eThrowable)
  {
    // Initialize a StringWriter.
    eStringWriter = new StringWriter();

    // Initialize a PrintWriter.
    ePrintWriter  = new PrintWriter(eStringWriter);

    // Pass contents from Throwable object to a StringWriter object.
    eThrowable.printStackTrace(ePrintWriter);

    // Assign String from StringWriter.
    eString = eStringWriter.toString();

  } // End of setExceptionString() method.

  // -------------------------------------------------------------------------/

  // Define a setMessage() method.
  private void setMessage(JTextAreaPanel out,JTextAreaPanel in)
  {
    // Try to get incoming message.
    try
    {
      // Send message text.
      in.setMessage(out.getText());

      // Consume sent message text.
      out.replaceRange(null,0,out.getText().length());

    } // End of try to get message.

    // Catch BufferEmptyException.
    catch (BufferEmptyException e)
    {
      // Capture the stack trace into a String.
      setExceptionString(e.fillInStackTrace());

      // Pass message to the JOptionPane manager.
      setExceptionPane("There is nothing in the buffer to send.\n\n" +
                       "Do you want to see the stack trace?");

    } // End of catch on BufferEmptyException.

  } // End of setMessage() method.

  // -------------------------------------------------------------------------/

  // Define a setMinimumHeight() method.
  protected final int setMinimumHeight(int height)
  {
    // Set return value.
    int retVal = END_Y;

    // If height is greater than minimum allow override.
    if (height >= END_Y)
    {
      // Functionality for screen resolution of spacing not implemented.
      retVal = END_Y;

    } // End of if height is greater than or equal to minimum.

    // Return value.
    return retVal;

  } // End of setMinimumHeight() method.

  // -------------------------------------------------------------------------/

  // Define a setMinimumWidth() method.
  protected final int setMinimumWidth(int width)
  {
    // Set return value.
    int retVal = END_X;

    // If width is greater than minimum allow override.
    if (width >= END_X)
    {
      // Override minimum.
      retVal = width;

    } // End of if width is greater than or equal to minimum.

    // Return value.
    return retVal;

  } // End of setMinimumWidth() method.

  // -------------------------------------------------------------------------/

  // Define a setScrollPaneProperties() method.
  private JScrollPane setScrollPaneProperties(JScrollPane pane)
  {
    // Set JScrollPane properties.
    pane.setVerticalScrollBarPolicy(
           JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    pane.setHorizontalScrollBarPolicy(
           JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    // Return value.
    return pane;

  } // End of setScrollPaneProperties() method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Inner Class -----------------------------/

  // Inner class to manage help modal diaglog object.
  class HelpHandler extends JDialog implements ActionListener
  {
    // Define and initialize AWT container.
    Container c = getContentPane();

    // Define and initialize Swing widgets.
    JButton okButton    = new JButton("Check the Java API");
    ImageIcon imageIcon = new ImageIcon("surfing.gif");
    JLabel image        = new JLabel(imageIcon);

    // Define and intialize phsyical size dimensions.
    int left         = 0;
    int top          = 0;
    int buttonWidth  = 150;
    int buttonHeight = 25;
    int imageWidth   = imageIcon.getIconWidth();
    int imageHeight  = imageIcon.getIconHeight();
    int offsetMargin = 20;

    // The dialog width and height are derived from base objects.
    int dialogWidth  = imageWidth + offsetMargin;
    int dialogHeight = imageHeight + buttonHeight + (3 * offsetMargin);

    // --------------------------- Constructor -------------------------------/

    // The inner class requires an owning frame and cannot be called from
    // a default constructor.  So, the default constructor is excluded.
    public HelpHandler(Frame owner,boolean modal)
    {
      super(owner,modal);
      buildDialogBox();
    }

    // -------------------------- Begin Methods ------------------------------/

    // Method to build the dialog box for help.
    private void buildDialogBox()
    {
      // Set the JDialog window properties.
      setTitle("Learning about Java");
      setResizable(false);
      setSize(dialogWidth,dialogHeight);

      // Define behaviors of container.
      c.setLayout(null);
      c.setBackground(Color.cyan);
      c.add(image);
      c.add(okButton);

      // Set the bounds for the image.
      image.setBounds((dialogWidth / 2) - (imageWidth / 2 ),
                      (top + (offsetMargin / 2)),imageWidth,imageHeight);

      // Set the behaviors, bounds and action listener for the button.
      okButton.setBounds((dialogWidth / 2) - (buttonWidth / 2),
                         (imageHeight + (int) 1.5 * offsetMargin),
                          buttonWidth,buttonHeight);


      // Set the font to the platform default Font for the object with the
      // properties of bold and font size of 11.
      okButton.setFont(
        new Font(okButton.getFont().getName(),Font.BOLD,11));

      // Set foreground and background of JButton(s).
      okButton.setForeground(Color.white);
      okButton.setBackground(Color.blue);

      // The class implements the ActionListener interface and therefore
      // provides an implementation of the actionPerformed() method.  When a
      // class implements ActionListener, the instance handler returns an
      // ActionListener.  The ActionListener then performs actionPerformed()
      // method on an ActionEvent.
      okButton.addActionListener(this);

      // Set the screen and display dialog window in relation to screen size.
      dim = tk.getScreenSize();
      setLocation((dim.width / 2) - (dialogWidth / 2),
                  (dim.height / 2) - (dialogHeight / 2));

      // Display the dialog.
      //show(); //removed to fix
      setVisible(true); //added to fix

    } // End of buildDialogBox method.

    // --------------------- Window ActionListener ---------------------------/

    // Class listener based on implementing ActionListener.
    @Override
    public void actionPerformed(ActionEvent e)
    {
      // Dispose of the help dialog.
      setVisible(false);
      dispose();

    } // End of actionPerformed method.

    // -------------------------- End Methods --------------------------------/

  } // End of HelpHandler inner class.

  // -------------------------- End Inner Class ------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // Static main program for executing a test of the class.
  public static void main(String args[])
  {
    // Define int variables.
    int width  = 0;
    int height = 0;

    // If arguments are greater than zero.
    if (args.length > 0)
    {

      // If arguments are two.
      if (args.length >= 2)
      {
        // Use try block to parse for an integer.
        try
        {
          // Verify first argument is an integer.
          width  = Integer.parseInt(args[0]);
          height = Integer.parseInt(args[1]);

          // Define a default instance of JMessagingFrame.
          JMessagingFrame f = new JMessagingFrame(width,height);

        } // Catch parsing failure exception.
        catch (NumberFormatException e)
        {
          // Print default runtime message.
          System.out.println("If you are testing the override constructor,");
          System.out.println("then you need to provide two integer values.");

        } // End try-catch block on integer parse.

      } // End of if two arguments provided.
      else // When there are less than or more than two arguments.
      {
        // Print default runtime message.
        System.out.println("If you are testing the override constructor,");
        System.out.println("then you need to provide two integer values.");

      } // End of else when there are less than or more than two arguments.

    } // End of else when there are two arguments.
    else // No arguments provided.
    {
      // Define a default instance of JMessagingFrame.
      JMessagingFrame f = new JMessagingFrame();

      // Clean-up by signaling the garbage collector.
      System.gc();

    } // End of else when no arguments are provided.

  }  // End of static main.

  // -------------------------- End Static Main ------------------------------/

    /**
    * Inner class settings brings up a menu to type in a url and password
    * for a database to connect to
    */
  class Settings extends JFrame
  {
      /**
       * Components to add to Settings menu
       */
      JLabel         mUrlLabel    = new JLabel("Database URL:");
      JLabel         mPassLabel   = new JLabel("Password:");
      JTextField     mUrlField    = new JTextField();
      JPasswordField mPassField   = new JPasswordField();
      JButton        mApplyButton = new JButton("Apply");
      JPanel         mUrlArea     = new JPanel();
      JPanel         mPassArea    = new JPanel();      

      /**
       * Initializes Frame
       */
      public void init()
      {
	  // Initialize components
	  initApplyButton();
	  initLayouts();
	  // Set up Frame
	  setTitle("Connection Settings");
	  setResizable(false);
	  setVisible(false);
	  setSize(400, 300);
	  // Set up main layout
	  getContentPane().setLayout(new FlowLayout());
	  getContentPane().add(mUrlArea);
	  getContentPane().add(mPassArea);
	  getContentPane().add(mApplyButton);

      }

      /**
       * Set up layout and add components to JPanels
       */
      private void initLayouts()
      {
	  // Set up URL area
	  mUrlArea.setLayout(new FlowLayout());
	  mUrlArea.add(mUrlLabel);
	  mUrlField.setPreferredSize(new Dimension(300, 26));
	  mUrlArea.add(mUrlField);
	  // Set up Password area
	  mPassArea.setLayout(new FlowLayout());
	  mPassArea.add(mPassLabel);
	  mPassField.setPreferredSize(new Dimension(300, 26));
	  mPassArea.add(mPassField);
      }

      /**
       * Set up action listener for apply button
       */
      private void initApplyButton()
      {
	  mApplyButton.addActionListener(new ActionListener()
	      {
                  @Override
		  public void actionPerformed(ActionEvent e)
		  {
		      apply();
		      setVisible(false);
		  }
	      });
      }

      /**
       * Gets the text from the textfields and stores them in
       * memory as strings
       */
      private void apply()
      {
	  mUrl      = mUrlField .getText();
	  mPassword = mPassField.getText();
	  System.out.println(mUrl);
	  System.out.println(mPassword);
      }

  }
  
} // End of JMessagingFrame class.

// ------------------------------- End Class ---------------------------------/