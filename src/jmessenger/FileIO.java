package jmessenger;

/*
||  Program name:     FileIO.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    04/22/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Methods used as static libraries for File IO.
||  Program purpose:  Designed as a File IO library class.
*/

// Class imports.
import java.awt.*;                      // Required for AWT widgets.
import java.io.*;                       // Required for Java streams.
import java.net.*;                      // Required for networking.
import javax.swing.*;                   // Required for Swing widgets.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public abstract class FileIO
{
  // -------------------------- Class Variables ------------------------------/

  // Define labeling constant(s).
  private final static String NAME    = "FileIO.java";
  private final static String VERSION = "1.0";
  private final static String DATE    = "04/22/02";

  // Define constant primitive(s).
  private final static int ERROR       = JOptionPane.ERROR_MESSAGE;
  private final static int INFORMATION = JOptionPane.INFORMATION_MESSAGE;
  private final static int PLAIN       = JOptionPane.PLAIN_MESSAGE;
  private final static int QUESTION    = JOptionPane.QUESTION_MESSAGE;

  // Define primitive(s).
  private static boolean componentEnabled = false;

  // Define language wrapper class(es).
  private static String contents = new String();

  // Define Swing variable(s).
  private static Component caller;
  private static JFileChooser fileChooser;
  private static javax.swing.filechooser.FileFilter[] filter;

  // Define IO variable(s).
  private static File file;
  private static File[] files;

  // Define IO stream(s).
  private static BufferedReader input;
  private static BufferedWriter output;
  private static FileReader inFile;
  private static FileWriter outFile;

  // Define network object(s).
  private static URL url;
  private static URLConnection uConn;

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  public     Default           FileIO()
  */

  // -------------------------------------------------------------------------/

  // Default constructor.
  public FileIO() {}

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The static methods define the library:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         copyFile()                     public     File fromFile
  ||                                                         File toFile
  ||  void         copyFile()                     public     Component frame
  ||                                                         File fromFile
  ||                                                         File toFile
  ||  File         findFile()                     public     Component frame
  ||  File[]       findFiles()                    public     Component frame
  ||  String       getVersion()                   public
  ||  File         nameFile()                     public     Component frame
  ||  String       openFile()                     public     File file
  ||  String       openFile()                     public     Component frame
  ||                                                         File file
  ||  String       openURL()                      public     URL url
  ||  String       openURL()                      public     URL url
  ||                                                         String proxyHost
  ||  String       openURL()                      public     URL url
  ||                                                         String proxyHost
  ||                                                         String proxyPort
  ||  String       openURL()                      public     Component frame
  ||                                                         URL url
  ||  String       openURL()                      public     Component frame
  ||                                                         URL url
  ||                                                         String proxyHost
  ||  String       openURL()                      public     Component frame
  ||                                                         URL url
  ||                                                         String proxyHost
  ||                                                         String proxyPort
  ||  String       isPortValid()                  private    String port
  ||  void         readFile()                     private
  ||  void         readURL()                      private
  ||  void         saveFile()                     public     File file
  ||  void         saveFile()                     public     Component frame
  ||                                                         File file
  ||                                                         String contents
  ||  void         setComponent()                 private    Component frame
  ||  void         setCurrentDirectory()          private
  ||  void         setFileChooser()               private
  ||  void         showMessageDialog()            private    String msg
  ||                                                         String title
  ||                                                         int msgType
  */

  // -------------------------------------------------------------------------/

  // Define copyFile() method.
  public static void copyFile(File fromFile,File toFile)
  {
    // Call copyFile() method with null Component.
    copyFile(null,fromFile,toFile);

  } // End of copyFile() method.

  // -------------------------------------------------------------------------/

  // Define copyFile() method.
  public static void copyFile(Component frame,File fromFile,File toFile)
  {
    // Set environment.
    setComponent(frame);

    // Define primitive(s).
    int c;

    // Try to copy the files.
    try
    {
      // Define and initialize FileReader(s).
      inFile  = new FileReader(fromFile);

      // Define and initialize FileWriter(s).
      outFile = new FileWriter(toFile);

      // Loop while reading character-by-character until all characters read.
      while ((c = inFile.read()) != -1)
      {
        // Write character-by-character.
        outFile.write(c);

      } // End of character-by-character read.

      // Close FileReader.
      inFile.close();

      // Close FileWriter.
      outFile.close();

    }
    catch (IOException e)
    {
      // If modal Component enabled.
      if (componentEnabled)
      {
        // Display message dialog.
        showMessageDialog("Potential bad file name(s) or\n" +
                          "permission problem.","File IO Error.",ERROR);

      }
      else
      {
        // Throw runtime exception for a bad file name or path.
        throw new InvalidFileException("Potential bad file(s) name or " +
                                       "permissions problem.");

      } // End of if Component enabled.

    } // End of catch block to open file.

  } // End of copyFile() method.

  // -------------------------------------------------------------------------/

  // Define findFile() method.
  public static File findFile(Component frame)
  {
    // Set environment.
    setComponent(frame);
    setFileChooser();
    setCurrentDirectory();

    // Invoke file chooser and return exit code.
    int result = fileChooser.showOpenDialog(frame);

    // Evaluate whether cancel button choosen and exit method.
    if (result == JFileChooser.CANCEL_OPTION)  { return null; }

    // Initialize variable with returned file.
    file = fileChooser.getSelectedFile();

    // Return file.
    return file;

  } // End of findFile() method.

  // -------------------------------------------------------------------------/

  // Define findFiles() method.
  public static File[] findFiles(Component frame)
  {
    // Set environment.
    setComponent(frame);
    setFileChooser();
    setCurrentDirectory();

    // Enable multiple file selection.
    fileChooser.setMultiSelectionEnabled(true);

    // Invoke file chooser and return exit code.
    int result = fileChooser.showOpenDialog(frame);

    // Evaluate whether cancel button choosen and exit method.
    if (result == JFileChooser.CANCEL_OPTION)  { return null; }

    // Initialize variable with returned file.
    files = fileChooser.getSelectedFiles();

    // Return file.
    return files;

  } // End of findFiles() method.

  // -------------------------------------------------------------------------/

  // Define getVersion() method.
  public static String getVersion()
  {
    // Read all lines in array.
    return "< " + NAME + " : " + VERSION + " : " + DATE + " >";

  } // End of getVersion() method.

  // -------------------------------------------------------------------------/

  // Define nameFile() method.
  public static File nameFile(Component frame)
  {
    // Set environment.
    setComponent(frame);
    setFileChooser();
    setCurrentDirectory();

    // Invoke file chooser and return exit code.
    int result = fileChooser.showSaveDialog(frame);

    // Evaluate whether cancel button choosen and exit method.
    if (result == JFileChooser.CANCEL_OPTION)  { return null; }

    // Initialize variable with returned file.
    file = fileChooser.getSelectedFile();

    // Return file.
    return file;

  } // End of nameFile() method.

  // -------------------------------------------------------------------------/

  // Define openFile() method.
  public static String openFile(File file)
  {
    // Return value.
    return openFile(null,file);

  } // End of openFile() method.

  // -------------------------------------------------------------------------/

  // Define openFile() method.
  public static String openFile(Component frame,File file)
  {
    // Initialize Component when not null for modality.
    setComponent(frame);

    // Define and initialize local Swing variable(s).
    String retValue = "";

    // Raise an error if file name is null or open file.
    if (file == null || file.getName().equals(""))
    {
      // If modal Component enabled.
      if (componentEnabled)
      {
        // Display message dialog.
        showMessageDialog("File reference is a null value.",
                          "Invalid file name",ERROR);

      }
      else
      {
        // Throw runtime exception for a null file reference.
        throw new InvalidFileException("File reference is a null value.");

      } // End of if Component enabled.

    }
    else // File name is valid.
    {
      // Open file for sequential read.
      try
      {
        // Create a BufferedReader for line-by-line read.
        input = new BufferedReader(new FileReader(file));

        // Read file line-by-line to end-of-file marker.
        readFile();

        // Assign read contents.
        retValue = contents;

        // Close file because static context disallows maintaining it.
        input.close();

      } // End of try to open file for reading.
      catch (IOException e)
      {
        // If modal Component enabled.
        if (componentEnabled)
        {
          // Display message dialog.
          showMessageDialog("File name or path not found.",
                            "File IO error",ERROR);

        }
        else
        {
          // Throw runtime exception for a bad file name or path.
          throw new InvalidFileException("File name or path not found.");

        } // End of if Component enabled.

      } // End of catch block to open file.

    } // End of if to determine if file name is valid.

    // Return value.
    return retValue;

  } // End of openFile() method.

  // -------------------------------------------------------------------------/

  // Define openURL() method.
  public static String openURL(URL url)
  {
    // Return value.
    return openURL(null,url,null,null);

  } // End of openURL() method.

  // -------------------------------------------------------------------------/

  // Define openURL() method.
  public static String openURL(URL url,String proxyHost)
  {
    // Return value.
    return openURL(null,url,proxyHost,null);

  } // End of openURL() method.

  // -------------------------------------------------------------------------/

  // Define openURL() method.
  public static String openURL(URL url,String proxyHost,String proxyPort)
  {
    // Return value.
    return openURL(null,url,proxyHost,proxyPort);

  } // End of openURL() method.

  // -------------------------------------------------------------------------/

  // Define openURL() method.
  public static String openURL(Component frame,URL url)
  {
    // Return value.
    return openURL(null,url,null,null);

  } // End of openURL() method.

  // -------------------------------------------------------------------------/

  // Define openURL() method.
  public static String openURL(Component frame,URL url,String proxyHost)
  {
    // Return value.
    return openURL(null,url,proxyHost,null);

  } // End of openURL() method.

  // -------------------------------------------------------------------------/

  // Define openURL() method.
  private static String openURL(Component frame,URL urlIn,
                                String proxyHost,String proxyPort)
  {
    // Initialize Component when not null for modality.
    setComponent(frame);

    // Define and initialize local Swing variable(s).
    String retValue = "";

    // Raise an error if file name is null or open file.
    if (urlIn == null || urlIn.toString().equals(""))
    {
      // If modal Component enabled.
      if (componentEnabled)
      {
        // Display message dialog.
        showMessageDialog("URL reference is a null.",
                          "Network file IO error",ERROR);

      }
      else
      {
        // Throw runtime exception for a null file reference.
        throw new InvalidURLException("URL reference is a null.");

      } // End of if Component enabled.

    }
    else // File name is valid.
    {
      // Open file for sequential read.
      try
      {
        // Set URL.
        url = urlIn;

        // Create a URL connection.
        uConn = url.openConnection();

        // Create a BufferedReader for line-by-line read.
        input = new BufferedReader(
                  new InputStreamReader(
                    new BufferedInputStream(uConn.getInputStream())));

        // Read file line-by-line to end-of-file marker.
        readURL();

        // Assign read contents.
        retValue = contents;

        // Close file because static context disallows maintaining it.
        input.close();

      } // End of try to open file for reading.
      catch (IOException e)
      {
        // If modal Component enabled.
        if (componentEnabled)
        {
          showMessageDialog("URL not found or resolved.",
                            "Network URL connection error",ERROR);

        }
        else
        {
          // Throw runtime exception for:
          // ====================================
          // 1. "Host unreachable: connect"
          // 2. "Operation timed out: connect"
          throw new InvalidURLException(e.getMessage());

        } // End of if Component enabled.

      } // End of catch block to open file.

    } // End of if to determine if file name is valid.

    // Return value.
    return retValue;

  } // End of openURL() method.

  // -------------------------------------------------------------------------/

  // Define isPortValid() method.
  private static String isPortValid(String port)
  {
    // Define local return value.
    String retValue = port;

    // Try to parse String to int.
    try                             { Integer.parseInt(port); }
    catch (NumberFormatException e) { retValue = "80"; }

    // Return value.
    return retValue;

  } // End of isPortValid() method.

  // -------------------------------------------------------------------------/

  // Method to read an open file sequentially.
  private static void readFile()
  {
    // Define String(s).
    String line = new String();

    // Try to read the file.
    try
    {
      // Read initial line into String variable.
      line = input.readLine();

      // Assign line to file String.
      contents = line + "\n";

      // Read file until no new lines.
      while (line != null)
      {
        // Read next line into a String variable.
        line = input.readLine();

        // Concatenate the line to the file.
        if (line != null)  { contents += line + "\n"; }

      } // End of while lines to read is valid.

    } // End of try to read sequentially an open file.
    catch ( EOFException eofe)
    {
      // Display message dialog.
      showMessageDialog("No more lines to read.",
                        "End of file error",ERROR);

    } // End of EOFException catch block.
    catch ( IOException ioe)
    {
      // Display message dialog.
      showMessageDialog("Error reading file.",
                        "File IO error",ERROR);

    } // End of IOException catch block.

  } // End of readFile() method.

  // -------------------------------------------------------------------------/

  // Method to read an open file sequentially.
  private static void readURL()
  {
  // Define primitive(s).
  int charRead;

    // Try to read the file.
    try
    {
      // Read file until no new lines.
      while ((charRead = input.read()) != -1)
      {
        // Read character into a String variable.
        contents += new Character((char) charRead).toString();

      } // End of while lines to read is valid.

    } // End of try to read sequentially an open file.
    catch ( EOFException eofe)
    {
      // Display message dialog.
      showMessageDialog("No more lines to read.",
                        "End of file error",ERROR);

    } // End of EOFException catch block.
    catch ( IOException ioe)
    {
      // Display message dialog.
      showMessageDialog("Error reading network file.",
                        "Network file IO error",ERROR);

    } // End of IOException catch block.

  } // End of readFile() method.

  // -------------------------------------------------------------------------/

  // Define saveFile() method.
  public static void saveFile(File file,String contents)
  {
    // Call method with null Component.
    saveFile(null,file,contents);

  } // End of saveFile() method.

  // -------------------------------------------------------------------------/

  // Define saveFile() method.
  public static void saveFile(Component frame,File file,String contents)
  {
    // Initialize Component when not null for modality.
    setComponent(frame);

    // Raise an error if file name is null or open file.
    if (file == null || file.getName().equals(""))
    {
      // If modal Component enabled.
      if (componentEnabled)
      {
        // Display message dialog.
        showMessageDialog("File reference is a null value.",
                          "Invalid file name",ERROR);

      }
      else
      {
        // Throw runtime exception for a null file reference.
        throw new InvalidFileException("File reference is a null value.");

      } // End of if Component enabled.

    }
    else // File name is valid.
    {
      // Open file for sequential read.
      try
      {
        // Create a BufferedReader for line-by-line read.
        output = new BufferedWriter(new FileWriter(file));

        // Read file line-by-line to end-of-file marker.
        output.write(contents,0,contents.length());

        // Close stream.
        output.close();

      } // End of try to open file for reading.
      catch (IOException e)
      {
        // If modal Component enabled.
        if (componentEnabled)
        {
          // Display message dialog.
          showMessageDialog("File name or path not found.",
                            "File IO error",ERROR);

        }
        else
        {
          // Throw runtime exception for a bad file name or path.
          throw new InvalidFileException("File name or path not found.");

        } // End of if Component enabled.

      } // End of catch block to open file.

    } // End of if to determine if file name is valid.

  } // End of saveFile() method.

  // -------------------------------------------------------------------------/

  // Define setComponent() method.
  private static void setComponent(Component frame)
  {
    // If Component is not null then initialize it for modality.
    if (frame != null)
    {
      // Initialize calling Component.
      caller = frame;

      // Enable component flag.
      componentEnabled = true;

    } // End of if Component is null.

  } // End of setComponent() method.

  // -------------------------------------------------------------------------/

  // Define setCurrentDirectory() method.
  private static void setCurrentDirectory()
  {
    // Evaluate OS type, MS Windows or UNIX.
    if (new Character(File.separatorChar).charValue() == '\\')
    {
      // Set current directory based on MS Windows.
      file = new File("C:\\");

    } // End of if OS is MS Windows.
    else // Else OS is UNIX.
    {
      // Set current directory based on UNIX.
      file = new File("/");

    } // End of else OS is UNIX.

    // Initialize JFileChooser states.
    fileChooser.setCurrentDirectory(file);

  } // End of setCurrentDirectory() method.

  // -------------------------------------------------------------------------/

  // Define setFileChooser() method.
  private static void setFileChooser()
  {
    // Initialize FileChooser
    fileChooser = new JFileChooser();

    // Initialize FileFilter.
    javax.swing.filechooser.FileFilter[] filter =
      new javax.swing.filechooser.FileFilter[]
      { new FileTypeFilter("C/C++",
          new String[] { "c","cpp","h","hpp","cxx","hxx","inl" }),
        new FileTypeFilter("HTML",
          new String[] { "htm","html","stm" }),
        new FileTypeFilter("Java",
          new String[] { "java","sqlj" }),
        new FileTypeFilter("Log",
          new String[] { "log","lst" }),
        new FileTypeFilter("Oracle",
          new String[] { "pls","pll","ora","sql" }),
        new FileTypeFilter("Text",
          new String[] { "cfg","ini","txt" })
      };
      for (javax.swing.filechooser.FileFilter filter1 : filter) {
          // Assign a FileFilter to the JFileChooser.
          fileChooser.addChoosableFileFilter(filter1);
      } // End loop to populate the JFileChooser.

    // Set JFileChooser FileFilter with all FileFilter values.
    fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());

  } // End of setFileChooser() method.

  // -------------------------------------------------------------------------/

  // Define a showMessageDialog() wrapper method.
  private static void showMessageDialog(String msg,String title,int msgType)
  {
    // Display message dialog.
    JOptionPane.showMessageDialog(caller,msg,title,msgType);

    // Signal an explict exception error to release resources on Windows.
    System.exit(0);

  } // End of showMessageDialog() wrapper method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Inner Class -----------------------------/

  // -------------------------- End Inner Class ------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // Static main test program.
  public static void main(String[] args) throws MalformedURLException
  {
    // Return class name and version.
    System.out.println("\n" + getVersion() + "\n");

  } // End of static main.

  // -------------------------- End Static Main ------------------------------/

} // End of FileIO class.

// ------------------------------- End Class ---------------------------------/