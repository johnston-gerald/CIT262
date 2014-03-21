package jmessenger;

/*
||  Program name:     FileTypeFilter.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    04/07/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Methods used as custom FileFilter for File IO.
||  Program purpose:  Designed as a custom FileFilter class.
*/

// Class imports.
import java.io.*;                       // Required for Java streams.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
class FileTypeFilter extends javax.swing.filechooser.FileFilter
{
  // -------------------------- Class Variables ------------------------------/

  // Define String(s) and String array(s).
  private final String label;
  private final String[] types;

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  public     Default           FileTypeFilter(String label,String[] types)
  */

  // -------------------------------------------------------------------------/

  // Default constructor.
  public FileTypeFilter(String label,String[] types)
  {
    // Assign label to class label variable.
    this.label = label;

    // Define a new array.
    this.types = (String[]) types.clone();

    // Set the types.
    setTypes();

  } // End of default constructor.


  // --------------------------- Begin Methods -------------------------------/

  /*
  || The static methods define the library:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  boolean      accept()                       public     File file
  ||  String       getDescription()               public
  ||  String       getTypes()                     private
  ||  void         setTypes()                     private
  */

  // -------------------------------------------------------------------------/

  // Implement FileFilter accept() method.
  @Override
  public boolean accept(File file)
  {
    // Define and initialize return value.
    boolean retValue = false;

    // Define local object(s).
    String name = file.getName();

    // If file is a directory.
    if (file.isDirectory())
    {
      // Return value.
      retValue = true;

    } // End of if file is a directory.
    else
    {
        for (String type : types) {
            // If file has correct type.
            if (name.endsWith(type)) {
                retValue = true;
            }
        } // End of for-loop through file types.

    } // End of else file is not a directory.

    // Return false if File is not valid or not a Directory.
    return retValue;

  } // End of accept() method.

  // -------------------------------------------------------------------------/

  // Define getTypes() method.
  private String getTypes()
  {
    // Define return type and initialize with beginning character.
    String s = "(";

    // Iterate through the types and append them to a String.
    for (int i = 0;i < types.length;i++)
    {
      // Evaluate if last item in array.
      if (i == (types.length - 1))
      {
        // Assign last item with a closing pararenthsis.
        s += "*" + types[i] + ")";

      } // End if last item in array.
      else
      {
        // Assign item and delimiting comma.
        s += "*" + types[i] + ",";

      } // End of if last item or not.

    } // End of for-loop to evaluate all type values.

    // Return file types.
    return s;

  } // End of getTypes() method.

  // -------------------------------------------------------------------------/

  // Implement FileFilter getDescription() method.
  @Override
  public String getDescription()
  {
    // Return file types.
    return (label + " " + getTypes());

  } // End of getDescription() method.

  // -------------------------------------------------------------------------/

  // Define setTypes() method.
  public final void setTypes()
  {
    // Iterate through array and append a period where missing.
    for (int i = 0;i < types.length;i++)
    {
      // If type does not start with a period.
      if (!types[i].startsWith("."))
      {
        // Prepend a period and assign the type.
        types[i] = "." + types[i];

      } // End of if type does not start with a period.

    } // End of loop through submitted types.

  } // End of setTypes() method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Inner Class -----------------------------/

  // -------------------------- End Inner Class ------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of FileTypeFilter class.

// ------------------------------- End Class ---------------------------------/