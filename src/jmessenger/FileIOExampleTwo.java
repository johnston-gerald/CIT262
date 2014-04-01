package jmessenger;

/*
||  Program name:     FileIOExampleTwo.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    04/21/02
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
import java.io.*;                       // Required for Java streams.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class FileIOExampleTwo
{
  // -------------------------- Class Variables ------------------------------/

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  public     Default           FileIOExampleTwo()
  */

  // -------------------------------------------------------------------------/

  // Default constructor.
  public FileIOExampleTwo() {}

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The static methods define the library:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  */

  // -------------------------------------------------------------------------/

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Inner Class -----------------------------/

  // -------------------------- End Inner Class ------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // Static main program.
  public static void main(String[] args) throws IOException
  {
		// Define primitive(s).
		int c;

		// Define File(s).
	  File inFile  = new File("jmessenger/AVerseText.txt");
	  File outFile = new File("jmessenger/AVerseCopy.txt");
          FileWriter out;
      try (FileReader in = new FileReader(inFile)) {
          out = new FileWriter(outFile);
          while ((c = in.read()) != -1)
          {
              // Write character-by-character.
              out.write(c);
              
          }
      }

    // Close FileWriter.
    out.close();

  } // End of static main.

  // -------------------------- End Static Main ------------------------------/

} // End of FileIOExampleTwo class.

// ------------------------------- End Class ---------------------------------/


