package jmessenger;

/*
||  Program name:     InvalidURLException.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    04/21/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as a user defined Exception.
*/

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class InvalidURLException extends RuntimeException
{
  // -------------------------- Class Variables ------------------------------/

  // ------------------------- Begin Constructor -----------------------------/

  // A RuntimeException is the superclass of those exceptions that can be
  // thrown during the normal operation of the JVM.  A method is not required
  // to declare in its throws clause any subclasses of RuntimeException that
  // might be thrown during the execution of the method but not caught.

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  public     Default           InvalidURLException()
  ||  public     Default           InvalidURLException(String s)
  */

  // -------------------------------------------------------------------------/

  // Default constructor extends RuntimeException.
  public InvalidURLException() {}

  // Override constructor extends RuntimeException.
  public InvalidURLException(String s)
  {
    // Pass error message to super class Throwable.
    super(s);

  } // End of inherited override constructor.

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The class must be instantiated from another class instance:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  */

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Inner Class -----------------------------/

  // -------------------------- End Inner Class ------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of InvalidURLException class.

// ------------------------------- End Class ---------------------------------/