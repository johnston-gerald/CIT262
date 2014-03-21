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
  private JButton sendButton;
  private JButton receiveButton;
  private JButton JDBCButton;
  private JButton fileButton;
  private JButton rSendButton;
  private JButton rReceiveButton;

  // Menu bar.
  private final JMenuBar menuBar = new JMenuBar();

  // Menus.
  private final JMenu file = new JMenu("File");
  private final JMenu comm = new JMenu("Communicate");
  private final JMenu data = new JMenu("Database");
  private final JMenu help = new JMenu("Help");

  // Menu items.
  private final JMenuItem menuItemNew     = new JMenuItem("New");
  private final JMenuItem menuItemOpen    = new JMenuItem("Open");
  private final JMenuItem menuItemClose   = new JMenuItem("Close");
  private final JMenuItem menuItemSave    = new JMenuItem("Save");
  private final JMenuItem menuItemSaveAs  = new JMenuItem("Save As");
  private final JMenuItem menuItemSend    = new JMenuItem("Send");
  private final JMenuItem menuItemReceive = new JMenuItem("Receive");
  private final JMenuItem menuItemConnect = new JMenuItem("Connect");
  private final JMenuItem menuItemSubmit  = new JMenuItem("Run SQL");
  private final JMenuItem menuItemExit    = new JMenuItem("Exit");
  private final JMenuItem menuItemHelp    = new JMenuItem("About");

  // Checkbox menu items.
  private final JCheckBoxMenuItem menuCheckBoxItemDebug =
                              new JCheckBoxMenuItem("Debug");

  // Define JScrollPane(s).
  private JScrollPane senderScrollPane;
  private JScrollPane receiverScrollPane;

  // Define a JTextAreaPanel(s).
  private JTextAreaPanel receiver;
  private JButtonPanel button;
  private JTextAreaPanel sender;

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
  protected void buildButtons()
  {
    // Define and initialize JButton(s).
    sendButton     = button.getButton("Send");
    receiveButton  = button.getButton("Receive");
    JDBCButton     = button.getButton("Connect");
    fileButton     = button.getButton("Query");
    rSendButton    = button.getButton("rSend");
    rReceiveButton = button.getButton("rReceive");

  } // End of setMessage() method.

  // -------------------------------------------------------------------------/

  // Method builds button action listeners.
  private void buildButtonActionListeners()
  {
    /*
    || Section adds action listeners for buttons:
    || ==========================================
    ||  - sendButton
    ||  - receiveButton
    ||  - JDBCButton
    ||  - fileButton
    ||  - rSendButton
    ||  - rReceiveButton
    */

    // Send button.
    sendButton.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Send message.
          setMessage(sender,receiver);

        } // End of actionPerformed() method.
      }); // End of sendButton action listener.

    // Receive button.
    receiveButton.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Receive message.
          getMessage(receiver);

        } // End of actionPerformed() method.
      }); // End of receiveButton action listener.

    // JDBC button.
    JDBCButton.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Print message until implemented.
          System.out.println("Database Connection .... ");

        } // End of actionPerformed() method.
      }); // End of JDBCButton action listener.

    // File button.
    fileButton.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Print message until implemented.
          System.out.println("SQL Submission .... ");

        } // End of actionPerformed() method.
      }); // End of fileButton action listener.

    // Send button.
    rSendButton.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Print message until implemented.
          System.out.println("Remote Sending .... ");

        } // End of actionPerformed() method.
      }); // End of rSendButton action listener.

    // Receive button.
    rReceiveButton.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Print message until implemented.
          System.out.println("Remote Receiving .... ");

        } // End of actionPerformed() method.
      }); // End of rReceiveButton action listener.

  } // End of buildButtonActionListeners() method.

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  private void buildFrame(int width, int height)
  {
    // Set JFrame title.
    setTitle("Communicator");

    // Initialize and set policies for JScrollPane(s).
    receiverScrollPane = setScrollPaneProperties(new JScrollPane(
			                     receiver = new JTextAreaPanel(COL,ROW)));
    senderScrollPane   = setScrollPaneProperties(new JScrollPane(
                           sender = new JTextAreaPanel(COL,ROW)));

    // Disable editability of receiver JTextAreaPanel.
    receiver.setEditable(false);

    // Initialize JButtonPanel.
    button = new JButtonPanel();

    // Define and initialize JButton(s).
    buildButtons();

    // Set panel size.
    setSize(width,height);

    // Set JPanel Layout.
    getContentPane().setLayout(new BorderLayout());

    // Add a JTextAreaPanel(s).
    getContentPane().add(receiverScrollPane,BorderLayout.NORTH);
    getContentPane().add(button,BorderLayout.CENTER);
    getContentPane().add(senderScrollPane,BorderLayout.SOUTH);

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
    menuBar.add(comm);
    menuBar.add(data);
    menuBar.add(help);

    // Set mnemonics for menu selections.
    file.setMnemonic('F');
    comm.setMnemonic('C');
    data.setMnemonic('D');
    help.setMnemonic('H');

    // Menu items for file menu.
    file.add(menuItemNew);
    file.addSeparator();
    file.add(menuItemOpen);
    file.add(menuItemClose);
    file.addSeparator();
    file.add(menuItemSave);
    file.add(menuItemSaveAs);
    file.addSeparator();
    file.add(menuItemExit);

    // Menu items for comm menu.
    comm.add(menuItemSend);
    comm.add(menuItemReceive);

    // Menu items for comm menu.
    data.add(menuItemConnect);
    data.add(menuItemSubmit);

    // Set mnemonics for menu item selections for file menu.
    menuItemNew.setMnemonic('N');
    menuItemOpen.setMnemonic('O');
    menuItemClose.setMnemonic('C');
    menuItemSave.setMnemonic('S');
    menuItemSaveAs.setMnemonic('A');
    menuItemExit.setMnemonic('X');

    // Set mnemonics for comm item selections for file menu.
    menuItemSend.setMnemonic('S');
    menuItemReceive.setMnemonic('R');

    // Set mnemonics for data item selections for file menu.
    menuItemConnect.setMnemonic('C');
    menuItemSubmit.setMnemonic('R');

    // Menu items to help menu.
    help.add(menuCheckBoxItemDebug);
    help.addSeparator();
    help.add(menuItemHelp);

    // Set mnemonics for menu item selections for edit menu.
    menuCheckBoxItemDebug.setMnemonic('D');
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

    menuItemNew.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // New file.
          newFile();

        } // End of actionPerformed method.
      }); // End of menuItemNew action listener.

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

    menuItemClose.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Close file.
          closeFile();

        } // End of actionPerformed method.
      }); // End of menuItemClose action listener.

    menuItemSave.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Save current sender JTextAreaPanel String as current file.
          saveFile();

        } // End of actionPerformed method.
      }); // End of menuItemSave action listener.

    menuItemSaveAs.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Save current sender JTextAreaPanel String as new file.
          saveAsFile();

        } // End of actionPerformed method.
      }); // End of menuItemSaveAs action listener.

    menuItemExit.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Exit the system on menu exit.
          System.exit(0);

        } // End of actionPerformed() method.

      }); // End of menuItemExit action listener.

    menuItemSend.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Send message.
          setMessage(sender,receiver);

        } // End of actionPerformed() method.

      }); // End of menuItemSend action listener.

    menuItemReceive.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Receive message.
          getMessage(receiver);

        } // End of actionPerformed() method.

      }); // End of menuItemReceive action listener.

    menuItemConnect.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Functionality to be implemented.
          System.out.println("This is future functionality to Connect to DB.");

        } // End of actionPerformed() method.

      }); // End of menuItemConnect action listener.

    menuItemSubmit.addActionListener(
      new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          // Functionality to be implemented.
          System.out.println("This is future functionality to Submit SQL.");

        } // End of actionPerformed() method.

      }); // End of menuItemSubmit action listener.

    // ---------------------------------/
    // Menu item listeners to help menu.
    // ---------------------------------/

    // Add menu item listeners for debug check box menu item.
    menuCheckBoxItemDebug.addItemListener(
      new ItemListener()
      {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
          // Set stack trace enablement to opposite state.
          setDebugEnabled(!getDebugEnabled());

        } // End of actionPerformed method.

      }); // End of menuCheckBoxItemDebug item listener.

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
    // Dismiss file reference.
    fileName = new File("");

    // If JTextAreaPanel contains text.
    if (!sender.isTextAreaEmpty())
    {
      // Clear display area.
      sender.replaceRange(null,0,sender.getText().length());

    } // End of if check if JTextAreaPanel contains text.

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
      if (sender.isTextAreaEmpty())
      {
        // Set JTextAreaPanel.
        sender.setText(contents);

      } // End of if JTextAreaPanel is empty.
      else
      {
        // Reset JTextAreaPanel by replacing the range.
        sender.replaceRange(contents,0,sender.getText().length());

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
        FileIO.saveFile(this,fileName,sender.getText());

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
        FileIO.saveFile(this,fileName,sender.getText());

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

} // End of JMessagingFrame class.

// ------------------------------- End Class ---------------------------------/